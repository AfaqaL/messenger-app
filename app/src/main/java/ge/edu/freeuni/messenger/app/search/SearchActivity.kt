package ge.edu.freeuni.messenger.app.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.messenger.app.R

class SearchActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        recyclerView = findViewById(R.id.search_recycler_view)
        recyclerView.adapter = SearchRecyclerViewAdapter()
    }
}