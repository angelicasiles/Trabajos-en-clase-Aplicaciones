package com.marvin.a14_firebaseaccess.ui.customers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.marvin.a14_firebaseaccess.R
import com.marvin.a14_firebaseaccess.ui.categories.CategoryAdapter

class moduloOrden : AppCompatActivity() {

    var auth = FirebaseAuth.getInstance()

    private lateinit var cancel_button: Button
    private lateinit var aplicar_button: Button
    private lateinit var textCode: TextView
    private lateinit var textproduc: TextView
    private lateinit var textpriceUni: TextView
    private lateinit var textCant: TextView
    private lateinit var textDescuento:TextView

    var db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modulo_orden)

        val btnStar: Button = findViewById(R.id.agregar)

        btnStar.setOnClickListener {
            val intent = Intent(this, CategoryAdapter::class.java)
            startActivity(intent)
        }
    }
}