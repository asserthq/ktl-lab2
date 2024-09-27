package com.example.lab2.ui.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab2.R
import com.example.lab2.util.fact
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputForm: TextInputEditText = findViewById(R.id.main_form)
        val outputLabel: TextView = findViewById(R.id.main_label)

        inputForm.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    outputLabel.text = getString(R.string.morda)
                }
                else if (s.length <= 2) {
                    try {
                        val n = s.toString().toLong()
                        if (n > 20) {
                            throw Exception("Number is too big")
                        }
                        val f = fact(n)
                        var ans: Int? = null
                        if (f >= 3) {
                            for (x in (1L..(f - 2))) {
                                val mult = x * (x + 1) * (x + 2)
                                if (mult > f) {
                                    break
                                }
                                else if (mult == f) {
                                    ans = x.toInt()
                                }
                            }
                        }
                        outputLabel.text = if (ans != null) "$ans*${ans+1}*${ans+2} = $f = $n!" else "Cannot"

                    } catch (ex: NumberFormatException) {
                        outputLabel.text = getString(R.string.not_number_ex_text)
                    } catch (ex: Exception) {
                        outputLabel.text = ex.message
                    }
                }
                else {
                    s.replace(0, s.length, s.last().toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
}