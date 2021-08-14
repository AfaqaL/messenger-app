package ge.edu.freeuni.messenger.app.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.messenger.app.R

class ChatRecyclerViewAdapter(): RecyclerView.Adapter<ChatRecyclerViewViewHolder>() {

    var list = listOf<String>()
    var x = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRecyclerViewViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.received_message_item, parent, false)
        if (x %2 == 0) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.sent_message_item, parent, false)
        }
        return ChatRecyclerViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        x++
        return 20;
    }

    override fun onBindViewHolder(holder: ChatRecyclerViewViewHolder, position: Int) {

    }
}

class ChatRecyclerViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

}
