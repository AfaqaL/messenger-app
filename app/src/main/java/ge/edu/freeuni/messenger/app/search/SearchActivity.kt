package ge.edu.freeuni.messenger.app.search

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.messenger.app.R
import ge.edu.freeuni.messenger.app.database.FirebaseUtil
import ge.edu.freeuni.messenger.app.database.model.User
import ge.edu.freeuni.messenger.app.main.MainActivity

class SearchActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAdapter: SearchRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setUpRecyclerView()
        setUpToolBar()
    }

    private fun setUpToolBar() {
        setUpBackButton()
        setUpSearchBar()
    }

    private fun setUpSearchBar() {
        val searchBar = findViewById<EditText>(R.id.search)
        searchBar.addTextChangedListener {
            val text = searchBar.text.toString()

            if (text.length > 2) {
                FirebaseUtil.searchUsers(text, this::completion)
            } else {
                recyclerViewAdapter.list = listOf()
                recyclerViewAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun completion(ls: List<User>) {
        recyclerViewAdapter.list = ls
        recyclerViewAdapter.notifyDataSetChanged()
    }

    private fun setUpBackButton() {
        val button = findViewById<ImageView>(R.id.back)
        button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    private fun setUpRecyclerView() {
        recyclerView = findViewById(R.id.search_recycler_view)
        recyclerViewAdapter = SearchRecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
    }
}