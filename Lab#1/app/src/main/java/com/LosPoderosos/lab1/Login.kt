package com.LosPoderosos.lab1


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import android.content.Intent

class Login : AppCompatActivity() {

    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnLogin: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtEmail   = findViewById(R.id.textEmail)
        txtPassword   = findViewById(R.id.textPassword)
        btnLogin   = findViewById(R.id.buttonLogin)


        btnLogin.setOnClickListener {

            val email = txtEmail.text.toString()
            val password = txtPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                // Muestra un mensaje de error si alguno de los campos está vacío
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                    val intent = Intent(this, PaginaPrincipal::class.java)
                    startActivity(intent)
                }
            }
        }

    }
