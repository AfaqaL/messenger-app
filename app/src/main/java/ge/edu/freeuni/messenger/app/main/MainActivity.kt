package ge.edu.freeuni.messenger.app.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import ge.edu.freeuni.messenger.app.ProfileActivity
import ge.edu.freeuni.messenger.app.R

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()
        setUpBottomNavigationBar()

    }

    private fun setUpBottomNavigationBar() {
        bottomNavigationView =  findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.background = null;
        var shouldChange = false;

        bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId != R.id.home) {
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            }
            true
        }
    }

    private fun setUpRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.messages_recycler_view)
        recyclerView.adapter = RecyclerViewAdapter()
    }
}