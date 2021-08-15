package ge.edu.freeuni.messenger.app.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.messenger.app.R
import ge.edu.freeuni.messenger.app.database.model.Convo

class RecyclerViewAdapter(val data: List<Convo>): RecyclerView.Adapter<RecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return RecyclerViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size;
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        val curr = data[position]
        holder.username.text = curr.user
        holder.lastTxt.text = curr.lastTxt
        holder.minsAgo.text = curr.lastSent.toString()
    }
}

class RecyclerViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val username: TextView = itemView.findViewById(R.id.username)
    val lastTxt: TextView = itemView.findViewById(R.id.last_sms)
    val minsAgo: TextView = itemView.findViewById(R.id.time_ago)
}
