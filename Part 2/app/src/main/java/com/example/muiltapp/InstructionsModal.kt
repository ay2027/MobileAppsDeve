package com.example.muiltapp

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class InstructionsModal : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("How to Use MindHue")
            .setMessage(
                """
                1. Tap any image to play ambient sound.
                
                2. You cannot play another sound unless you pause or stop the current one.
                
                3. Use 'Pause' to pause the sound and allow switching.
                
                4. Use 'Stop' to stop all sounds.
                
                5. Visit Journal and Settings as needed.
                """.trimIndent()
            )
            .setPositiveButton("Got it") { dialog, _ -> dialog.dismiss() }
            .create()
    }
}

