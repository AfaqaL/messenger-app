package ge.edu.freeuni.messenger.app.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.messenger.app.R

class ChatActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        Log.d("mylog", "onCreate: ${intent.getStringExtra("conv-info")}")
        recyclerView = findViewById(R.id.chat_recycler_view)
        recyclerView.adapter = ChatRecyclerViewAdapter()
    }
}