package ge.edu.freeuni.messenger.app.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.messenger.app.ProfileActivity
import ge.edu.freeuni.messenger.app.R
import ge.edu.freeuni.messenger.app.database.FirebaseUtil
import ge.edu.freeuni.messenger.app.database.model.Message
import ge.edu.freeuni.messenger.app.main.MainActivity

class ChatActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var chatter: String
    val data = arrayListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        chatter = intent.getStringExtra("conv-info")!!
        setupRecyclerView(chatter)
        addListeners()
    }

    private fun addListeners() {
        findViewById<ImageView>(R.id.chat_back).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        findViewById<TextView>(R.id.chat_username).text = chatter
        val professions = FirebaseUtil.access("users", chatter).get().addOnCompleteListener {
            if (it.isSuccessful) {
                findViewById<TextView>(R.id.chat_profession).text = it.result!!.value.toString()
            }
        }
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