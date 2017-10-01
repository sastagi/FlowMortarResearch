package com.primedroid.flowmortarresearch.core

import android.content.Context
import android.view.View
import mortar.MortarScope
import mortar.ViewPresenter

interface Screen : Destination {
    companion object {
        fun createView(context: Context, screen: Screen, id: Int, identifier: String? = null): View {

            val childScopeName = screen.getScopeName()
            val presenter = screen.createPresenter()

            val parentScope = MortarScope.getScope(context)
            var childScope = parentScope.findChild(childScopeName)

            if (childScope == null) {
                childScope = MortarScope.buildChild(context)
                        .withService("presenter", presenter)
                        .build(childScopeName)
            }

            val childContext = childScope.createContext(context)
            return screen.createView(childContext, id)
        }
    }

    fun createView(context: Context, layout_id: Int): View
    fun createPresenter(): ViewPresenter<*>?

    override val uniqueId: String
        get() = getScopeName()

    fun getScopeName() = this::class.java.name


}


interface Destination {
    val uniqueId: String
}