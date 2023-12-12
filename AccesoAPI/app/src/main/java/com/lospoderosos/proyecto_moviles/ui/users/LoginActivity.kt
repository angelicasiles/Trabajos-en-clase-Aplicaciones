package com.lospoderosos.proyecto_moviles.ui.users
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.lospoderosos.proyecto_moviles.MainActivity
import com.lospoderosos.proyecto_moviles.R
import com.lospoderosos.proyecto_moviles.ui.users.SignupActivity
import java.util.Date


const val valorIntentSignup = 1
class LoginActivity : AppCompatActivity() {

    var auth = FirebaseAuth.getInstance()

    private lateinit var buttonLogin: Button
    private lateinit var textEmail: EditText
    private lateinit var textPassword: EditText
    private lateinit var btnRegistrar: TextView
    var db = FirebaseFirestore.getInstance()

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        buttonLogin = findViewById(R.id.buttonLogin)
        textEmail = findViewById(R.id.textEmail)
        textPassword = findViewById(R.id.textPassword)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            goToSignup()
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        findViewById<Button>(R.id.btnGoogle).setOnClickListener{
            googleSignIn();
        }
        buttonLogin.setOnClickListener {
            if(textEmail.text.isNotEmpty() && textPassword.text.isNotEmpty()){
                auth.signInWithEmailAndPassword(textEmail.text.toString(), textPassword.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        val dt: Date = Date()

                        val user = hashMapOf(
                            "ultAcceso" to dt.toString()
                        )

                        db.collection("datosUsuarios").whereEqualTo("idemp", it.result?.user?.uid.toString()).get()
                            .addOnSuccessListener { documentReference ->
                                documentReference.forEach { document ->
                                    db.collection("datosUsuarios").document(document.id).update(user as Map<String, Any>)
                                }
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this,"Error al actualizar los datos del usuario", Toast.LENGTH_SHORT).show()
                            }

                        //Register the data into the local storage
                        val prefe = this.getSharedPreferences("appData", Context.MODE_PRIVATE)

                        //Create editor object for write app data
                        val editor = prefe.edit()

                        //Set editor fields with the new values
                        editor.putString("email", textEmail.text.toString())
                        editor.putString("contra", textPassword.text.toString())

                        //Write app data
                        editor.commit()

                        // call back to main activity
                        Intent().let {
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                    }else{
                        showAlert("Error","Al autenticar el usuario")
                    }
                }
            }else{
                showAlert("Error","El correo electrónico y contraseña no pueden estar vacíos")
            }
        }
    }

    private fun googleSignIn() {
        val signInClient = googleSignInClient.signInIntent
        launcher.launch(signInClient)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->

        if (result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

            manageResults(task);
        }
    }

    private fun manageResults(task: Task<GoogleSignInAccount>) {
        val account : GoogleSignInAccount? = task .result

        if (account != null) {
            val credential = GoogleAuthProvider.getCredential(account.idToken, "")
            auth.signInWithCredential(credential).addOnCompleteListener{
                if( task.isSuccessful){
                    //val intent = Intent(this,MainActivity::class.java )
                    //startActivity(intent)
                    Intent().let {
                        setResult(Activity.RESULT_OK)
                        finish()
                    }

                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
        else{
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    private fun goToSignup() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivityForResult(intent, valorIntentSignup)
    }

    private fun showAlert(titu:String, mssg: String){
        val diagMessage = AlertDialog.Builder(this)
        diagMessage.setTitle(titu)
        diagMessage.setMessage(mssg)
        diagMessage.setPositiveButton("Aceptar", null)

        val diagVentana: AlertDialog = diagMessage.create()
        diagVentana.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // validate control variables
        if(resultCode == Activity.RESULT_OK){
            // call back to main activity
            Intent().let {
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }
}