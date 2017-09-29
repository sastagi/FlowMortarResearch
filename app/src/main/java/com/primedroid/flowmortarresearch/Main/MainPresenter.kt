package com.primedroid.flowmortarresearch.Main

import android.os.Bundle
import mortar.ViewPresenter

class MainPresenter : ViewPresenter<MainView>() {
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
