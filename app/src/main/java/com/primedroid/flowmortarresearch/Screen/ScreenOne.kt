package com.primedroid.flowmortarresearch.Screen

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.primedroid.flowmortarresearch.R
import com.primedroid.flowmortarresearch.core.Screen
import mortar.ViewPresenter

class ScreenOne: Screen {

    override fun createView(context: Context) = ScreenOneView(context)

}

class ScreenOnePresenter : ViewPresenter<ScreenOneView>() {
    private var serial = -1

    override fun onLoad(savedInstanceState: Bundle?) {
        if (savedInstanceState != null && serial == -1)
            serial = savedInstanceState.getInt("serial")
        view.show("Main")
    }

    override fun onSave(outState: Bundle?) {
        outState?.getInt("serial", serial)
    }
}

class ScreenOneView : LinearLayout {
    private var presenter: ScreenOnePresenter? = context.getSystemService(ScreenOnePresenter::class.java.name) as? ScreenOnePresenter
    private lateinit var textView: TextView

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

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