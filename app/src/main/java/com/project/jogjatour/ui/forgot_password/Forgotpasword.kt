package com.project.jogjatour.ui.forgot_password

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.project.jogjatour.R
import com.project.jogjatour.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_forgotpasword.*

class Forgotpasword : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var mProgressBar: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpasword)

        mAuth = FirebaseAuth.getInstance()
        mProgressBar = ProgressDialog(this)

        back.setOnClickListener {
            val intent = Intent(this@Forgotpasword, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        reset_password.setOnClickListener {

            val email = reset_email.text.toString()

            if (email.equals("")) {
                Toast.makeText(
                    this@Forgotpasword, "Masukan email terlebih dahulu.",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                mProgressBar!!.setMessage("Mohon tunggu...")
                mProgressBar!!.show()

                mAuth!!
                    .sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->


                        if (task.isSuccessful) {
                            val message = "Email sent."
                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@Forgotpasword, LoginActivity::class.java)
                            startActivity(intent)

                        } else {
                            Toast.makeText(
                                this@Forgotpasword, "Email atau password salah.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
