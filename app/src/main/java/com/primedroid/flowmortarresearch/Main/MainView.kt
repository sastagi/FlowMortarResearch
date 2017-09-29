package com.primedroid.flowmortarresearch.Main

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.primedroid.flowmortarresearch.R

class MainView : LinearLayout {
    private var presenter: MainPresenter? = context.getSystemService(MainPresenter::class.java.name) as? MainPresenter
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

