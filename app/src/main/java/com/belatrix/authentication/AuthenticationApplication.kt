package com.belatrix.authentication

import android.app.Application
import android.util.Log
import com.belatrix.authentication.business.injection.component.DaggerApplicationComponent
import com.belatrix.authentication.business.injection.component.DaggerGraphComponent
import com.belatrix.authentication.business.injection.component.GraphComponent
import com.belatrix.authentication.business.injection.module.ApiClientModule
import com.belatrix.authentication.business.injection.module.ApplicationModule
import com.orhanobut.hawk.Hawk
import timber.log.Timber

class AuthenticationApplication : Application() {
    /** Server ip */
    private val baseUrl = "http://192.168.2.1:8000/"

    /** Injection component */
    companion object {
        @JvmStatic lateinit var component: GraphComponent
    }

    override fun onCreate() {
        super.onCreate()

        // Init injection - dagger2
        val appComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        component = DaggerGraphComponent.builder()
                .applicationComponent(appComponent)
                .apiClientModule(ApiClientModule(baseUrl))
                .build()

        // Init secure preferences
        Hawk.init(this).build();

        // Verify release/debug
        if (BuildConfig.DEBUG) {
            // Init timber
            Timber.plant(object : Timber.DebugTree() {
                // Adds the line number
                override fun createStackElementTag(element: StackTraceElement): String {
                    return super.createStackElementTag(element) + ':' + element.lineNumber
                }
            })
        } else {
            // Remove logs
            Timber.plant(CrashReportingTree())
        }
    }

    /** A tree which logs important information for crash reporting.  */
    private class CrashReportingTree : Timber.Tree() {
        /** The max size of a line  */
        private val MAX_LOG_LENGTH = 4000

        override fun log(priority: Int, tag: String, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
                return
            }

            /*if (priority == Log.ERROR && t != null) {
                Crashlytics.logException(t)
            }*/

            if (message.length < MAX_LOG_LENGTH) {
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, message)
                } else {
                    Log.println(priority, tag, message)
                }
                return
            }

            var i = 0
            val length = message.length
            while (i < length) {
                var newLine = message.indexOf('\n', i)
                newLine = if (newLine != -1) newLine else length
                do {
                    val end = Math.min(newLine, i + MAX_LOG_LENGTH)
                    val part = message.substring(i, end)
                    if (priority == Log.ASSERT) {
                        Log.wtf(tag, part)
                    } else {
                        Log.println(priority, tag, part)
                    }
                    i = end
                } while (i < newLine)
                i++
            }
        }
    }
}