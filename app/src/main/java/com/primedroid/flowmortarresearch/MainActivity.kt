package com.primedroid.flowmortarresearch

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.RelativeLayout
import com.primedroid.flowmortarresearch.Screens.ScreenOne
import com.primedroid.flowmortarresearch.Screens.ScreenOnePresenter
import com.primedroid.flowmortarresearch.Screens.ScreenTwoPresenter
import com.primedroid.flowmortarresearch.core.Screen
import flow.*
import mortar.MortarScope
import mortar.MortarScope.buildChild
import mortar.MortarScope.findChild
import mortar.bundler.BundleServiceRunner

class MainActivity : Activity() {

    private val screenChanger = ScreenChanger(this)

    override fun getSystemService(name: String?): Any? {
        var activityScope: MortarScope? = findChild(applicationContext, getScopeName())

        if (activityScope == null) {

            activityScope = buildChild(applicationContext) //
                    .withService(BundleServiceRunner.SERVICE_NAME, BundleServiceRunner())
                    .withService(ScreenTwoPresenter::class.java!!.name, ScreenTwoPresenter())
                    .withService(ScreenOnePresenter::class.java!!.name, ScreenOnePresenter(this))
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

    override fun attachBaseContext(baseContext: Context) {
        super.attachBaseContext(Flow.configure(baseContext, this)
                .dispatcher(KeyDispatcher.configure(this, screenChanger).build())
                .defaultKey(ScreenOne(this))
                .install())
    }

    class ScreenChanger(
            private val activity: MainActivity
    ) : KeyChanger {
        override fun changeKey(outgoingState: State?,
                               incomingState: State,
                               direction: Direction,
                               incomingContexts: MutableMap<Any, Context>,
                               callback: TraversalCallback) {

            val inScreen: Screen = incomingState.getKey()
            val outScreen: Screen? = outgoingState?.getKey()

            //TODO Login stuff here

            val container = activity.findViewById<RelativeLayout>(R.id.activity_container)

            val flowContext = incomingContexts[inScreen]!!

            val inView: View = Screen.createView(flowContext, inScreen, R.layout.screen_one).apply {
                layoutParams = RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT
                ).apply {
                    addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                    alignWithParent = true
                }
            }

            container.removeAllViews()
            container.addView(inView)
            callback.onTraversalCompleted()

        }
    }

    override fun onBackPressed() {
        if (!getFlow().goBack()) {
            super.onBackPressed()
        }
    }

    private fun getFlow(): Flow {
        return Flow.get(this@MainActivity)
    }
}
