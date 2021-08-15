package ge.edu.freeuni.messenger.app.chat

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.messenger.app.R
import ge.edu.freeuni.messenger.app.database.model.Message
import ge.edu.freeuni.messenger.app.main.MainActivity.Companion.TAG

class ChatRecyclerViewAdapter(val data: ArrayList<Message>): RecyclerView.Adapter<ChatRecyclerViewViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        return if(data[position].sender){
            1
        }else{
            0
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRecyclerViewViewHolder {

        val view = if(viewType == 0) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.received_message_item, parent, false)
        }
        else {
            LayoutInflater.from(parent.context).inflate(R.layout.sent_message_item, parent, false)
        }
        return ChatRecyclerViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ChatRecyclerViewViewHolder, position: Int) {
        val curr = data[position]
        holder.messageText.text = curr.text
        holder.sentAt.text = curr.sentAt.toString() // TODO: convert to time
    }
}

class ChatRecyclerViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val messageText: TextView = itemView.findViewById(R.id.message_txt)
    val sentAt: TextView = itemView.findViewById(R.id.sent_at)
}
