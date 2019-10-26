package com.stonetree.freemoving

import android.os.Bundle
import com.stonetree.view.feature.activity.CoreActivity

class NavigatorActivity : CoreActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_main)
    }
}
