package com.project.jogjatour.ui.login

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.project.jogjatour.R
import com.project.jogjatour.ui.register.Register
import com.project.jogjatour.ui.forgot_password.Forgotpasword
import com.project.jogjatour.ui.home.HomeActivity

import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var mProgressBar: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        mAuth = FirebaseAuth.getInstance()
        mProgressBar = ProgressDialog(this)


        btn_reset_password.setOnClickListener {
            val intent = Intent(this@LoginActivity, Forgotpasword::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }



        btn_signup.setOnClickListener {
            val intent = Intent(this@LoginActivity, Register::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        btn_login.setOnClickListener {


            val email = email.text.toString()
            val password = password.text.toString()


            if (email.equals("") || password.equals("")
            ) {
                Toast.makeText(this@LoginActivity, "Masukan email & password terlebih dahulu.",
                    Toast.LENGTH_SHORT).show()

            } else {

            mProgressBar!!.setMessage("Mohon tunggu...")
            mProgressBar!!.show()

            Log.d("Email is", email).toString()
            Log.d("Password is", password).toString()


            mAuth!!.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this) { task ->
                 mProgressBar!!.hide()
                    if (task.isSuccessful) {

                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(
                            this@LoginActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


        }

    }
}}
