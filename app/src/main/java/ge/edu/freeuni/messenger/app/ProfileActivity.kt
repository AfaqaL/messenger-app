package ge.edu.freeuni.messenger.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import ge.edu.freeuni.messenger.app.main.MainActivity

class ProfileActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setUpBottomNavigationBar()
        addListeners()
    }

    private fun addListeners() {
        addSignOutListener()
    }

    private fun addSignOutListener() {
        findViewById<Button>(R.id.sign_out).setOnClickListener {

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