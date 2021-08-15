package ge.edu.freeuni.messenger.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ge.edu.freeuni.messenger.app.database.FirebaseUtil
import ge.edu.freeuni.messenger.app.database.model.User
import ge.edu.freeuni.messenger.app.main.MainActivity
import ge.edu.freeuni.messenger.app.search.SearchActivity

class ProfileActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        user = FirebaseUtil.user!!
        setUpBottomNavigationBar()
        addListeners()
        showCurrentUserInfo()
    }

    private fun showCurrentUserInfo() {
        val nickname = user.username
        val profession = user.occupation

        findViewById<TextView>(R.id.profile_nickname).text = nickname
        findViewById<EditText>(R.id.profile_profession).hint = profession
    }

    private fun addListeners() {
        addSignOutListener()
        addUpdateListener()
        setUpFAB()
    }

    private fun addUpdateListener() {
        val profession = findViewById<EditText>(R.id.profile_profession)
        findViewById<Button>(R.id.update).setOnClickListener {
            FirebaseUtil.update(user.username, profession.text.toString())
            FirebaseUtil.initUser()
        }
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
        bottomNavigationView.selectedItemId = R.id.settings
    }

    private fun setUpFAB() {
        val button = findViewById<FloatingActionButton>(R.id.profile_fab)
        button.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
            finish()
        }
    }
}