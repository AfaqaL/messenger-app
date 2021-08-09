package ge.edu.freeuni.messenger.app

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        val ref = FirebaseUtil.ref
//
//        val et = findViewById<EditText>(R.id.edit_text_email)
//        val btn = findViewById<Button>(R.id.button_sign_in)
//
//        btn.setOnClickListener{
//            ref.child("mesg").setValue(Message(et.text.toString()))
//        }

        val auth = Firebase.auth

        var user = auth.currentUser
        val usernameEditText = findViewById<EditText>(R.id.edit_text_email)
        val passEditText = findViewById<EditText>(R.id.edit_text_password)

        if (usernameEditText.text.toString().isEmpty()) {
            Toast.makeText(this, "achu cxenoou", LENGTH_SHORT).show()
        } else {

            auth.createUserWithEmailAndPassword(usernameEditText.text.toString(),
                                                passEditText.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "shevedit", LENGTH_SHORT).show()
                        user = auth.currentUser
                    } else {
                        Toast.makeText(this, "vera veraa", LENGTH_SHORT).show()

                    }
                }
        }
        user?.let {
            val nickname = it.email
            val job = usernameEditText.text.toString() + " job"
        }
    }


}