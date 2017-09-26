package com.primedroid.flowmortarresearch

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView

class MainView : LinearLayout {
    private var presenter: MainPresenter?
    private lateinit var textView: TextView

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    init {
        presenter = context.getSystemService(MainPresenter::class.java.name) as? MainPresenter
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

