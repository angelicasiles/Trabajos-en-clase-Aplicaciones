package com.lospoderosos.proyecto_moviles

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.lospoderosos.proyecto_moviles.databinding.ActivityMainBinding
import com.lospoderosos.proyecto_moviles.ui.users.LoginActivity

const val valorIntentLogin = 1
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

private lateinit var fragmentManager: FragmentManager
private lateinit var binding: ActivityMainBinding
    var auth = FirebaseAuth.getInstance()
    var email: String? = null
    var contra: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

// intenta obtener el token del usuario del local storage, sino llama a la ventana de registro
        val prefe = getSharedPreferences("appData", MODE_PRIVATE)
        email = prefe.getString("email","")
        contra = prefe.getString("contra","")

        if(email.toString().trim { it <= ' ' }.length == 0){
            val intent = Intent(this, LoginActivity::class.java)
            startActivityForResult(intent, valorIntentLogin)
        }else {
            val uid: String = auth.uid.toString()
            if (uid == "null"){
                auth.signInWithEmailAndPassword(email.toString(), contra.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this,"Autenticación correcta", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            obtenerDatos()
        }

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLoyout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLoyout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_home -> openFragment(homeFragment())
                R.id.bottom_files -> openFragment(fileFragment())
                R.id.bottom_galery -> openFragment(galeryFragment())
                R.id.bottom_profile -> openFragment(profileFragment())
            }
            true
        }
        fragmentManager = supportFragmentManager
        openFragment((homeFragment()))

        binding.fab.setOnClickListener{
            Toast.makeText(this,"Categories", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> openFragment(homeFragment())
            R.id.nav_files -> openFragment(fileFragment())
            R.id.nav_galery -> openFragment(galeryFragment())
            R.id.nav_perfil -> openFragment(profileFragment())
            R.id.nav_aboutUs -> openFragment(aboutUsFragment())
            R.id.nav_addProyect -> openFragment(addProyectFragment())
        }
        binding.drawerLoyout.closeDrawer((GravityCompat.START))
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLoyout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLoyout.closeDrawer(GravityCompat.START)
        }else{
            super.getOnBackPressedDispatcher().onBackPressed()
        }
    }

    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

private fun obtenerDatos() {
    /*   //Toast.makeText(this,"Esperando hacer algo importante", Toast.LENGTH_LONG).show()
       var coleccion: ArrayList<cls_Category?> = ArrayList()
       var listaView: ListView = findViewById(R.id.lstCategories)
       db.collection("Categories").orderBy("CategoryID")
           .get()
           .addOnCompleteListener { docc ->
               if (docc.isSuccessful) {
                   for (document in docc.result!!) {
                       Log.d(TAG, document.id + " => " + document.data)
                       var datos: cls_Category = cls_Category(document.data["CategoryID"].toString().toInt(),
                           document.data["CategoryName"].toString(),
                           document.data["Description"].toString(),
                           document.data["urlImage"].toString())
                       coleccion.add(datos)
                   }
                   var adapter: CategoryAdapter = CategoryAdapter(this, coleccion)
                   listaView.adapter =adapter
               } else {
                   Log.w(TAG, "Error getting documents.", docc.exception)
               }
           }
   */}

}
