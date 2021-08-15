package ge.edu.freeuni.messenger.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import ge.edu.freeuni.messenger.app.database.FirebaseUtil
import ge.edu.freeuni.messenger.app.main.MainActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun signup(view: View){
        val uet = findViewById<EditText>(R.id.edit_text_email)
        val pet = findViewById<EditText>(R.id.edit_text_password)
        val oet = findViewById<EditText>(R.id.edit_text_occupation)

        findViewById<ProgressBar>(R.id.sign_up_progress).visibility = View.VISIBLE
        FirebaseUtil.signup(uet.text.toString(), pet.text.toString(), oet.text.toString(),
            this, this::registerCallback)
    }

    private fun registerCallback(success: Boolean){
        if (success) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        findViewById<ProgressBar>(R.id.sign_up_progress).visibility = View.INVISIBLE
    }
}