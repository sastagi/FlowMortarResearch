package com.primedroid.flowmortarresearch.Screens

import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.primedroid.flowmortarresearch.R
import com.primedroid.flowmortarresearch.core.Screen
import com.primedroid.flowmortarresearch.core.inflate
import mortar.ViewPresenter

class ScreenTwo: Screen {
    override fun createPresenter() = ScreenTwoPresenter()
    override fun createView(context: Context, layout_id: Int) = ScreenTwoView(context, layout_id)
}

class ScreenTwoPresenter : ViewPresenter<ScreenTwoView>() {
    private var serial = -3

    override fun onLoad(savedInstanceState: Bundle?) {
        if (savedInstanceState != null && serial == -3)
            serial = savedInstanceState.getInt("serial")
        view.show("ScreenTwo")
        //val flow = Flow.get(view)
        //flow.set(ScreenTwo())
    }

    override fun onSave(outState: Bundle?) {
        outState?.getInt("serial", serial)
    }
}

class ScreenTwoView(
        context: Context,
        layout_id: Int
) : LinearLayout(context) {
    private var presenter: ScreenTwoPresenter? = context.getSystemService(ScreenTwoPresenter::class.java.name) as? ScreenTwoPresenter
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
