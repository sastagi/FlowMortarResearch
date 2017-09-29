package com.primedroid.flowmortarresearch.Secondary

import android.os.Bundle
import mortar.ViewPresenter

class SecondaryPresenter : ViewPresenter<SecondaryView>() {
    private var serial = -1

    override fun onLoad(savedInstanceState: Bundle?) {
        if (savedInstanceState != null && serial == -1)
            serial = savedInstanceState.getInt("serial")
        view.show("Secondary")
    }

    override fun onSave(outState: Bundle?) {
        outState?.getInt("serial", serial)
    }
}