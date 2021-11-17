package com.vaasugambhir.furniturekart.data

import android.widget.Toast
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

object ARHandler {
    fun placeObject(arFragment: ArFragment, anchor: Anchor, uri: Int) {
        ModelRenderable.builder()
            .setSource(arFragment.requireContext(), uri)
            .build()
            .thenAccept {
                addNodeToScene(arFragment, anchor, it)
            }
            .exceptionally {
                Toast.makeText(arFragment.requireContext(), "Error: " + it.message, Toast.LENGTH_SHORT).show()
                return@exceptionally null
            }
    }

    private fun addNodeToScene(arFragment: ArFragment, anchor: Anchor, renderable: Renderable) {
        val anchorNode = AnchorNode(anchor)
        val node = TransformableNode(arFragment.transformationSystem)
        node.renderable = renderable
        node.setParent(anchorNode)
        arFragment.arSceneView.scene.addChild(anchorNode)
        node.select()
    }
}