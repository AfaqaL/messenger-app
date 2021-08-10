package ge.edu.freeuni.messenger.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import ge.edu.freeuni.messenger.app.database.FirebaseUtil

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun signup(view: View){
        val uet = findViewById<EditText>(R.id.edit_text_email)
        val pet = findViewById<EditText>(R.id.edit_text_password)
        val oet = findViewById<EditText>(R.id.edit_text_occupation)

        FirebaseUtil.signup(uet.text.toString(), pet.text.toString(), oet.text.toString(), this)
    }
}