package ge.edu.freeuni.messenger.app.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ge.edu.freeuni.messenger.app.ProfileActivity
import ge.edu.freeuni.messenger.app.R
import ge.edu.freeuni.messenger.app.search.SearchActivity

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNavigationBar()
        setUpRecyclerView()
        setUpToolBar()

    }

    private fun setUpToolBar() {
        val searchBar = findViewById<EditText>(R.id.searchBar)
//        searchBar.addTextChangedListener {
//
//        }
    }

    private fun setUpBottomNavigationBar() {
        bottomNavigationView =  findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.background = null

        bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId != R.id.home) {
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            }
            true
        }
        setUpFAB()
    }

    private fun setUpFAB() {
        val button = findViewById<FloatingActionButton>(R.id.profile_fab)
        button.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
            finish()
        }
    }

    private fun setUpRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.messages_recycler_view)
        recyclerView.adapter = RecyclerViewAdapter()
    }
}