package com.example.muiltapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class JournalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal)

        val journalInput = findViewById<EditText>(R.id.journalEditText)
        val saveButton = findViewById<Button>(R.id.saveJournalBtn)

        saveButton.setOnClickListener {
            val text = journalInput.text.toString()
            if (text.isNotEmpty()) {
                val timeStamp = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.getDefault()).format(Date())
                val fileName = "Journal_$timeStamp.txt"
                val file = File(filesDir, fileName)
                FileOutputStream(file).use {
                    it.write(text.toByteArray())
                }
                Toast.makeText(this, "Journal Saved", Toast.LENGTH_SHORT).show()
                journalInput.text.clear()
            } else {
                Toast.makeText(this, "Please write something first.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
