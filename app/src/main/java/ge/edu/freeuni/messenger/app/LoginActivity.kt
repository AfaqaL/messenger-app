package ge.edu.freeuni.messenger.app

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ge.edu.freeuni.messenger.app.database.FirebaseUtil
import ge.edu.freeuni.messenger.app.database.FirebaseUtil.access
import ge.edu.freeuni.messenger.app.database.model.Convo
import ge.edu.freeuni.messenger.app.main.MainActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        FirebaseUtil.fUser?.let {
            loginRedirect(true)
        }
    }


    /* bound functions */

    fun login(view: View){
        val uet = findViewById<EditText>(R.id.edit_text_email)
        val pet = findViewById<EditText>(R.id.edit_text_password)
        val loader = findViewById<ProgressBar>(R.id.login_progress)

        loader.visibility = View.VISIBLE
        FirebaseUtil.login(uet.text.toString(), pet.text.toString(),this, this::loginRedirect)
    }

    fun register(view: View){
        startActivity(Intent(this, SignUpActivity::class.java))
        // TODO: finish this activity or set flags in signup
    }

    private fun loginRedirect(success: Boolean){
        if (success) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        findViewById<ProgressBar>(R.id.login_progress).visibility = View.INVISIBLE
    }
}