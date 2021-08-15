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

//        access("chats", "afqena", "nino").setValue(Convo("baro"))
//        access("chats", "afqena", "saba").setValue(Convo("zd"))
//        access("chats", "afqena", "temo").setValue(Convo("lashuka"))
//        access("chats", "afqena", "givi").setValue(Convo("achu"))
//
//        access("chats", "nino", "givi").setValue(Convo("troli"))
//        access("chats", "nino", "sabuba").setValue(Convo("baba"))
//
//
//        FirebaseUtil.sendSms("nino", "a")
//        FirebaseUtil.sendSms("nino", "b")
//        FirebaseUtil.sendSms("nino", "c")
//        FirebaseUtil.sendSms("nino", "d")
//        FirebaseUtil.sendSms("nino", "e")

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