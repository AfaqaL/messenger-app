package ge.edu.freeuni.messenger.app.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.messenger.app.R
import ge.edu.freeuni.messenger.app.main.MainActivity

class SearchActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
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
//        searchBar.addTextChangedListener {
//
//        }
    }

    private fun setUpBackButton() {
        var button = findViewById<ImageView>(R.id.back)
        button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    private fun setUpRecyclerView() {
        recyclerView = findViewById(R.id.search_recycler_view)
        recyclerView.adapter = SearchRecyclerViewAdapter()
    }
}