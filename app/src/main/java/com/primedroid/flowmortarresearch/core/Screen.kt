package com.primedroid.flowmortarresearch.core

import android.content.Context
import android.view.View

/**
 * Created by santosh.astagi on 9/28/17.
 */
interface Screen {
    companion object {
        fun createView(context: Context): View {
            return createView(context)
        }
    }

    fun createView(context: Context): View

}