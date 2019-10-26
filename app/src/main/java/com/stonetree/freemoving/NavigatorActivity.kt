package com.stonetree.freemoving

import android.os.Bundle
import androidx.navigation.findNavController
import com.stonetree.view.feature.activity.CoreActivity

class NavigatorActivity : MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_main)
        findNavController(R.id.navigator)
    }
}
