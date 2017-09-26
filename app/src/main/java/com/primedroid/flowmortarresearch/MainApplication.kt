package com.primedroid.flowmortarresearch

import android.app.Application
import mortar.MortarScope

class MainApplication : Application() {
    private var rootScope: MortarScope? = null

    override fun getSystemService(name: String?): Any {
        if (rootScope == null) rootScope = MortarScope.buildRootScope().build("Root")
        if (rootScope!!.hasService(name)) {
            return rootScope!!.getService(name)
        }
        return super.getSystemService(name)
    }
}