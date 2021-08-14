package ge.edu.freeuni.messenger.app.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.messenger.app.R

class ChatActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        recyclerView = findViewById(R.id.chat_recycler_view)
        recyclerView.adapter = ChatRecyclerViewAdapter()
    }
}