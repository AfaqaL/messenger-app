package ge.edu.freeuni.messenger.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import ge.edu.freeuni.messenger.app.database.FirebaseUtil
import ge.edu.freeuni.messenger.app.main.MainActivity

class ProfileActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setUpBottomNavigationBar()
        addListeners()
        showCurrentUserInfo()
    }

    private fun showCurrentUserInfo() {
        var user = FirebaseUtil.user!!
        var nickname = user.username
        var profession = user.occupation

        Toast.makeText(this, profession, Toast.LENGTH_SHORT)
        findViewById<TextView>(R.id.profile_nickname).text = nickname
        findViewById<EditText>(R.id.profile_profession).hint = profession
    }

    private fun addListeners() {
        addSignOutListener()
    }

    private fun addSignOutListener() {
        findViewById<Button>(R.id.sign_out).setOnClickListener {
            FirebaseUtil.logOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun setUpBottomNavigationBar() {
        bottomNavigationView =  findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.background = null;

        bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId != R.id.settings) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            true
        }
    }
}