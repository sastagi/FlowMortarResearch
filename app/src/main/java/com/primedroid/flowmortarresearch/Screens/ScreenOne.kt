package com.primedroid.flowmortarresearch.Screens

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.primedroid.flowmortarresearch.R
import com.primedroid.flowmortarresearch.core.Screen
import com.primedroid.flowmortarresearch.core.inflate
import mortar.ViewPresenter

class ScreenOne: Screen {
    override fun createPresenter() = ScreenOnePresenter()
    override fun createView(context: Context, layout_id: Int) = ScreenOneView(context, layout_id)
}

class ScreenOnePresenter : ViewPresenter<ScreenOneView>() {
    private var serial = -2

    override fun onLoad(savedInstanceState: Bundle?) {
        if (savedInstanceState != null && serial == -2)
            serial = savedInstanceState.getInt("serial")
        view.show("ScreenOne")
    }

    override fun onSave(outState: Bundle?) {
        outState?.getInt("serial", serial)
    }
}

class ScreenOneView(
        context: Context,
        layout_id: Int
) : LinearLayout(context) {
    private var presenter: ScreenOnePresenter? = context.getSystemService(ScreenOnePresenter::class.java.name) as? ScreenOnePresenter
    private var textView = findViewById<TextView>(R.id.text)

    init {
        @Suppress("LeakingThis") // How is this a leak?
        context.inflate(layout_id, this)
        onFinishInflate()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        textView = findViewById(R.id.text)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter?.takeView(this)
    }

    override fun onDetachedFromWindow() {
        presenter?.dropView(this)
        super.onDetachedFromWindow()
    }

    fun show(stuff: CharSequence) {
        textView.text = stuff
    }
}