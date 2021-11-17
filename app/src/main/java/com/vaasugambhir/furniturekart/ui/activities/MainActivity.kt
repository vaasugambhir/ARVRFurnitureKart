package com.vaasugambhir.furniturekart.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.ar.core.Plane
import com.google.ar.sceneform.ux.ArFragment
import com.vaasugambhir.furniturekart.R
import com.vaasugambhir.furniturekart.data.ARHandler

class MainActivity : AppCompatActivity() {

    private lateinit var arFragment: ArFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        set()
    }

    private fun set() {
        arFragment = supportFragmentManager.findFragmentById(R.id.fragment_ar) as ArFragment
        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
            val anchor = hitResult.createAnchor()
            ARHandler.placeObject(arFragment, anchor, R.raw.ball)
        }
    }
}