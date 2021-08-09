package ge.edu.freeuni.messenger.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import ge.edu.freeuni.messenger.app.database.FirebaseUtil
import ge.edu.freeuni.messenger.app.database.model.Message

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val ref = FirebaseUtil.ref

        val et = findViewById<EditText>(R.id.edit_text_email)
        val btn = findViewById<Button>(R.id.button_sign_in)

        btn.setOnClickListener{
            ref.child("mesg").setValue(Message(et.text.toString()))
        }

    }


}