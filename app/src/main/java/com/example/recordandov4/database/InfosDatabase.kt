package com.example.recordandov4.database

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class InfosDatabase : Application() {
    override fun onCreate() {
        super.onCreate()


        Realm.init(this)

        val configuration = RealmConfiguration.Builder()
            .name("Infos.realm")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()

        Realm.setDefaultConfiguration(configuration)


    }
}