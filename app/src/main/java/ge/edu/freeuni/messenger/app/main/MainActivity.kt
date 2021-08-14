package ge.edu.freeuni.messenger.app.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnFocusChangeListene

        Toast.makeText(this, bottomNavigationView.selectedItemId.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun setUpRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.messages_recycler_view)
        recyclerView.adapter = RecyclerViewAdapter()
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).background = null;
    }
}