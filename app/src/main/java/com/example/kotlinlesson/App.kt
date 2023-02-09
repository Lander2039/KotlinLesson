package com.example.kotlinlesson

import android.app.Application
import com.example.kotlinlesson.di.AppModule
import com.example.kotlinlesson.di.component.AppComponent
import com.example.kotlinlesson.di.component.DaggerAppComponent


class App : Application() {

    lateinit var appComponent: AppComponent

    fun provideAppComponent(): AppComponent {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
        return appComponent

    }
}