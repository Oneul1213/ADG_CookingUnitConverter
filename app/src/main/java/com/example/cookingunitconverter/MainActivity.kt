package com.example.cookingunitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cookingunitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convertButton.setOnClickListener { convert() }
        binding.millisToOzSwitch.setOnCheckedChangeListener { compoundButton, isChecked ->
            convert()
        }
        binding.gramsToCupSwitch.setOnCheckedChangeListener { compoundButton, b ->
            convert()
        }
    }

    private fun convert() {
        val millisOrGram = binding.millisOrGramEditText.text.toString().toDoubleOrNull()

        if (millisOrGram == null || millisOrGram == 0.0) {
            displayConvertResult(0.0)
        } else {
            displayConvertResult(millisOrGram)
        }
    }

    private fun displayConvertResult(text: Double) {
        val millisOrOz: String =
            if (binding.millisToOzSwitch.isChecked) getString(R.string.oz, (text * 0.03).toString())
            else getString(R.string.millis, text.toString())

        binding.millisOrGramTextView.text = millisOrOz

        val gramOrCup =
            if (binding.gramsToCupSwitch.isChecked) getString(R.string.cup, (text * 128).toString())
            else getString(R.string.gram, text.toString())

        binding.gramOrCupTextView.text = gramOrCup
    }
}