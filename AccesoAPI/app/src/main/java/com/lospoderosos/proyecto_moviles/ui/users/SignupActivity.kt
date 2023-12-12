package com.lospoderosos.proyecto_moviles.ui.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lospoderosos.proyecto_moviles.R

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.Button
import android.widget.Toast


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class SignupActivity : AppCompatActivity() {

    var auth = FirebaseAuth.getInstance()
    var db = FirebaseFirestore.getInstance()

    private lateinit var textName: EditText
    private lateinit var textEmail: EditText
    private lateinit var textPassword: EditText
    private lateinit var textrePassword: EditText
    private lateinit var textGradoE: EditText
    private lateinit var buttonSignup: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        textName = findViewById(R.id.textName)
        textEmail = findViewById(R.id.textEmail)
        textPassword = findViewById(R.id.textPassword)
        textrePassword = findViewById(R.id.textrePassword)
        textGradoE = findViewById(R.id.textGradoE)
        buttonSignup = findViewById(R.id.buttonSignup)

        buttonSignup.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        val nombre = textName.text.toString()
        val email = textEmail.text.toString()
        val contra = textPassword.text.toString()
        val reContra = textrePassword.text.toString()
        val textGradoE = textGradoE.text.toString()

        if (nombre.isEmpty() || email.isEmpty() || contra.isEmpty() || reContra.isEmpty() || textGradoE.isEmpty()) {
            Toast.makeText(this, "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show()
        } else {
            if (contra == reContra) {
                auth.createUserWithEmailAndPassword(email, contra)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val dt: Date = Date()
                            val user = hashMapOf(
                                "idemp" to task.result?.user?.uid,
                                "usuario" to nombre,
                                "email" to email,
                                "ultAcceso" to dt.toString(),
                            )
                            db.collection("datosUsuarios")
                                .add(user)
                                .addOnSuccessListener { documentReference ->

                                    //Register the data into the local storage
                                    val prefe = this.getSharedPreferences("appData", Context.MODE_PRIVATE)

                                    //Create editor object for write app data
                                    val editor = prefe.edit()

                                    //Set editor fields with the new values
                                    editor.putString("email", email.toString())
                                    editor.putString("contra", contra.toString())

                                    //Write app data
                                    editor.commit()

                                    Toast.makeText(this,"Usuario registrado correctamente",Toast.LENGTH_SHORT).show()

                                    Intent().let {
                                        setResult(Activity.RESULT_OK)
                                        finish()
                                    }
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(this,"Error al registrar usuario",Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(this,"Error al registrar usuario",Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

