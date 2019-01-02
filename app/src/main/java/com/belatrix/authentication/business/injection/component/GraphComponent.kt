package com.belatrix.authentication.business.injection.component

import com.belatrix.authentication.business.injection.module.ApiClientModule
import com.belatrix.authentication.business.injection.module.ApisModule
import com.belatrix.authentication.business.injection.scope.ApplicationScope
import com.belatrix.authentication.ui.LoginActivity
import dagger.Component

@ApplicationScope
@Component(
        modules = arrayOf(ApiClientModule::class, ApisModule::class),
        dependencies = arrayOf(ApplicationComponent::class)
)
interface GraphComponent {
    fun inject(activity: LoginActivity)
}