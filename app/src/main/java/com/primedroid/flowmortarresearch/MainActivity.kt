package com.primedroid.flowmortarresearch

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import mortar.MortarScope
import mortar.MortarScope.buildChild
import mortar.MortarScope.findChild
import mortar.bundler.BundleServiceRunner

class MainActivity : Activity() {

    override fun getSystemService(name: String?): Any? {
        var activityScope: MortarScope? = findChild(applicationContext, getScopeName())

        if (activityScope == null) {

            activityScope = buildChild(applicationContext) //
                    .withService(BundleServiceRunner.SERVICE_NAME, BundleServiceRunner())
                    .withService(MainPresenter::class.java!!.name, MainPresenter())
                    .build(getScopeName())
        }

        return if (activityScope!!.hasService(name))
            activityScope.getService(name)
        else
            super.getSystemService(name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        if (isFinishing) run {
            val activityScope: MortarScope = findChild(applicationContext, getScopeName())
            activityScope.destroy()
        }
        super.onDestroy()
    }



    private fun getScopeName(): String {
        return javaClass.name
    }
}
