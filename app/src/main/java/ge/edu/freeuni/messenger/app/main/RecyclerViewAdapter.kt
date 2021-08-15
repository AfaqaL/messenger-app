package ge.edu.freeuni.messenger.app.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.messenger.app.HolderClickListener
import ge.edu.freeuni.messenger.app.R
import ge.edu.freeuni.messenger.app.database.model.Convo
import java.text.SimpleDateFormat

class RecyclerViewAdapter(val data: List<Convo>, private val clickListener: HolderClickListener)
            : RecyclerView.Adapter<RecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return RecyclerViewViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int {
        return data.size;
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        val curr = data[position]
        holder.username.text = curr.user
        holder.lastTxt.text = curr.lastTxt
        val timeDiff = (System.currentTimeMillis() - curr.lastSent) / 60000
        val timeTxt: String = when{
            timeDiff < 60 -> {
                "${timeDiff}m ago"
            }
            timeDiff < 1440 -> {
                "${timeDiff / 60}h ago"
            }
            else -> {
                SimpleDateFormat( "dd LLL").format(curr.lastSent)
            }
        }
        holder.minsAgo.text = timeTxt



    }
}

class RecyclerViewViewHolder(private val itemView: View, private val clickListener: HolderClickListener)
            : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    val username: TextView = itemView.findViewById(R.id.username)
    val lastTxt: TextView = itemView.findViewById(R.id.last_sms)
    val minsAgo: TextView = itemView.findViewById(R.id.time_ago)
    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        clickListener.onClick(adapterPosition)
    }
}
