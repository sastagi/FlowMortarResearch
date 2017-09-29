package com.primedroid.flowmortarresearch.Secondary

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.primedroid.flowmortarresearch.R

class SecondaryView : LinearLayout {
    private var presenter: SecondaryPresenter? = context.getSystemService(SecondaryPresenter::class.java.name) as? SecondaryPresenter
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