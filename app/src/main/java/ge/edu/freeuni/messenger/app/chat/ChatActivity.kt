package ge.edu.freeuni.messenger.app.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.messenger.app.R
import ge.edu.freeuni.messenger.app.database.FirebaseUtil
import ge.edu.freeuni.messenger.app.database.model.Message

class ChatActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var chatter: String
    val data = arrayListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        chatter = intent.getStringExtra("conv-info")!!
        setupRecyclerView(chatter)

    }

    private fun setupRecyclerView(user: String) {
        FirebaseUtil.initChatMessages(data, user, this::updateRV)
        recyclerView = findViewById(R.id.chat_recycler_view)
        recyclerView.adapter = ChatRecyclerViewAdapter(data)
    }

    private fun updateRV(){
        recyclerView.adapter?.notifyDataSetChanged()
    }

    fun sendMessage(view: View){
        val messageText = findViewById<EditText>(R.id.message_input).text.toString()
        data.add(Message(messageText, true))
        updateRV()
        FirebaseUtil.sendSms(chatter, messageText)
    }
}