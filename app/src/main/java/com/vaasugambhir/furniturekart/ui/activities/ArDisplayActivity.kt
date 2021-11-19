package com.vaasugambhir.furniturekart.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.ar.sceneform.ux.ArFragment
import com.vaasugambhir.furniturekart.R
import com.vaasugambhir.furniturekart.data.ARHandler
import com.vaasugambhir.furniturekart.databinding.ActivityArDisplayBinding
import com.vaasugambhir.furniturekart.utils.Utils

class ArDisplayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArDisplayBinding
    private lateinit var arFragment: ArFragment
    private lateinit var arHandler: ARHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val value = intent.extras!!.getInt(Utils.AR_INTENT)

        arHandler = ARHandler()
        set(value)
    }

    private fun set(raw: Int) {
        arFragment = supportFragmentManager.findFragmentById(R.id.fragment_ar) as ArFragment
        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
            val anchor = hitResult.createAnchor()
            arHandler.placeObject(arFragment, anchor, raw)
        }
    }
}