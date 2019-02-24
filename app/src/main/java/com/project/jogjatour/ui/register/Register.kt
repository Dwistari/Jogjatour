package com.project.jogjatour.ui.register


import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.project.jogjatour.R
import com.project.jogjatour.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mDatabaseReference: DatabaseReference? = null
    private var mProgressBar: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mProgressBar = ProgressDialog(this)


        btn_login.setOnClickListener {
            val intent = Intent(this@Register, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        btn_register.setOnClickListener {

            val firstName = et_first_name.text.toString()
            val lastName = et_last_name.text.toString()
            val email = et_email.text.toString()
            val password = et_password.text.toString()


            if (firstName.equals("") || lastName.equals("") ||
                email.equals("") || password.equals("")
            ) {
                Toast.makeText(this@Register, "Lengkapi data terlebih dahulu.",
                    Toast.LENGTH_SHORT).show()

            } else if (password.length < 6){
                Toast.makeText(this@Register, "Password minimum 6 karakter",
                    Toast.LENGTH_SHORT).show()
            } else {

                mProgressBar!!.setMessage("Registering User...")
                mProgressBar!!.show()

                mAuth?.createUserWithEmailAndPassword(email, password)
                    ?.addOnCompleteListener {
                        mProgressBar!!.hide()
                        if (it.isSuccessful) {

                            verifyEmail()

                            Log.d("Main", "Create user berhasil ${it.result?.user?.uid}")
                            val userId = mAuth!!.currentUser!!.uid

                            val currentUserDb = mDatabaseReference!!.child(userId)
                            currentUserDb.child("firstName").setValue(firstName)
                            currentUserDb.child("lastName").setValue(lastName)

                            val intent = Intent(this@Register, LoginActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)

                        } else {
                            Toast.makeText(
                                this@Register, "Authentication failed.", Toast.LENGTH_SHORT
                            ).show()


                        }
                    }
            }
        }

    }


    private fun verifyEmail() {
        val mUser = mAuth!!.currentUser;
        mUser!!.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@Register,
                        "Verification email sent to " + mUser.getEmail(), Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@Register,
                        "Failed to send verification email.", Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}

