package ge.edu.freeuni.messenger.app.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ge.edu.freeuni.messenger.app.HolderClickListener
import ge.edu.freeuni.messenger.app.ProfileActivity
import ge.edu.freeuni.messenger.app.R
import ge.edu.freeuni.messenger.app.chat.ChatActivity
import ge.edu.freeuni.messenger.app.search.SearchActivity
import ge.edu.freeuni.messenger.app.database.FirebaseUtil
import ge.edu.freeuni.messenger.app.database.model.Convo

class MainActivity : AppCompatActivity(), HolderClickListener {
    lateinit var recyclerView: RecyclerView
    lateinit var bottomNavigationView: BottomNavigationView
    private val data: ArrayList<Convo> = arrayListOf()
    companion object{
        const val TAG = "MY-LOG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNavigationBar()
        setUpRecyclerView()
        setUpToolBar()
        findViewById<ProgressBar>(R.id.main_progressBar).visibility = View.VISIBLE
        FirebaseUtil.initConversationData(data, this::updateRV)
    }

    private fun setUpToolBar() {
        val searchBar = findViewById<EditText>(R.id.searchBar)
//        searchBar.addTextChangedListener {
//
//        }
        setUpBottomNavigationBar()
        setUpRecyclerView()
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

    fun setUpRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.messages_recycler_view)
        recyclerView.adapter = RecyclerViewAdapter(data, this)
    }

    fun updateRV(){
        recyclerView.adapter?.notifyDataSetChanged()
        findViewById<ProgressBar>(R.id.main_progressBar).visibility = View.INVISIBLE
    }

    override fun onClick(position: Int) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("conv-info", data[position].user)
        startActivity(intent)
    }
}