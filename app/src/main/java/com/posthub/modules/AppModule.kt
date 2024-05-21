package com.posthub.modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : AppModule() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            // Android context
            androidContext(this@MyApplication)
            // Modules
            modules(appModule)
        }
    }
}
