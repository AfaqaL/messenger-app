package ge.edu.freeuni.messenger.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import ge.edu.freeuni.messenger.app.database.FirebaseUtil
import ge.edu.freeuni.messenger.app.main.MainActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        FirebaseUtil.fUser?.let {
            loginRedirect()
        }
    }


    /* bound functions */

    fun login(view: View){
        val uet = findViewById<EditText>(R.id.edit_text_email)
        val pet = findViewById<EditText>(R.id.edit_text_password)

        FirebaseUtil.login(uet.text.toString(), pet.text.toString(),this, this::loginRedirect)
    }

    fun register(view: View){
        startActivity(Intent(this, SignUpActivity::class.java))
        // TODO: finish this activity or set flags in signup
    }

    private fun loginRedirect(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}