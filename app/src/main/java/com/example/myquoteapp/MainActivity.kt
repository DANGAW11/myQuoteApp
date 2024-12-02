package com.example.myquoteapp

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myquoteapp.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var citations = mutableListOf<String>()
    private var currentPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("CitationsApp", MODE_PRIVATE)
        loadCitations()

        // Show the current quote
        updateQuote()
        handleButtons()

        // Next button click listener
        binding.nextButton.setOnClickListener {
            if (currentPosition < citations.size - 1) {
                currentPosition++
                updateQuote()
                handleButtons()
            }
        }

        // Previous button click listener
        binding.previousButton.setOnClickListener {
            if (currentPosition > 0) {
                currentPosition--
                updateQuote()
                handleButtons()
            }
        }

        // Quit button click listener
        binding.quitButton.setOnClickListener {
            saveCitations()  // Save updated qoutes list before the app closes
            finish()
        }

        // Delete button click listener
        binding.deleteButton.setOnClickListener {
            if (citations.isNotEmpty()) {
                citations.removeAt(currentPosition)  // Remove the current quote
                // Ensure the position stays within bounds
                if (currentPosition >= citations.size) {
                    currentPosition = citations.size - 1
                }
                updateQuote()  // Update to show the next quote
                handleButtons()
            }
        }
    }

    // Load the quotes from SharedPreferences or cites.txt
    private fun loadCitations() {
        val savedCitations = sharedPreferences.getString("citations_list", null)
        if (savedCitations != null) {

            // If there is a saved quotes list in SharedPreferences, load it
            citations = savedCitations.split("\n").toMutableList()
        } else {
            // If not, we load it from cites.txt
            try {
                val reader = BufferedReader(InputStreamReader(assets.open("cites.txt")))
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    line?.let { citations.add(it) }
                }
                reader.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Update the quote displayed in the TextView
    private fun updateQuote() {
        if (citations.isNotEmpty()) {
            binding.quoteTextView.text = citations[currentPosition]
        }
    }

    // Handle enabling/disabling previous and next buttons to give the user a better UI of whats possible
    private fun handleButtons() {
        // Enable or disable the previous and next buttons based on the position
        val disabledColor = getColor(R.color.disabled_button_color)
        val enabledColor = getColor(R.color.button_color)

        binding.previousButton.isEnabled = currentPosition > 0
        binding.nextButton.isEnabled = currentPosition < citations.size - 1

        // Setting the background color for the buttons
        binding.previousButton.backgroundTintList =
            if (binding.previousButton.isEnabled) getColorStateList(R.color.button_color) else getColorStateList(R.color.disabled_button_color)

        binding.nextButton.backgroundTintList =
            if (binding.nextButton.isEnabled) getColorStateList(R.color.button_color) else getColorStateList(R.color.disabled_button_color)
    }

    // Save the quotes list to SharedPreferences
    private fun saveCitations() {
        val citationsString = citations.joinToString("\n")
        sharedPreferences.edit().putString("citations_list", citationsString).apply()
    }

    // Handle saving the quotes list when the app stops
    override fun onStop() {
        super.onStop() // Save the quotes list when the app is closed
        saveCitations()
    }
}
