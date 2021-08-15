package ge.edu.freeuni.messenger.app.search

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.messenger.app.HolderClickListener
import ge.edu.freeuni.messenger.app.R
import ge.edu.freeuni.messenger.app.database.model.User

class SearchRecyclerViewAdapter(private val clickListener: HolderClickListener): RecyclerView.Adapter<SearchRecyclerViewViewHolder>() {

    var list = listOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return SearchRecyclerViewViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    override fun onBindViewHolder(holder: SearchRecyclerViewViewHolder, position: Int) {
        val user = list[position]
        holder.username.text = user.username
        holder.profession.text = user.occupation
    }
}

class SearchRecyclerViewViewHolder(itemView: View, private val clickListener: HolderClickListener): RecyclerView.ViewHolder(itemView), View.OnClickListener {
    val username = itemView.findViewById<TextView>(R.id.search_username)
    val profession = itemView.findViewById<TextView>(R.id.search_profession)
    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        clickListener.onClick(adapterPosition)
    }
}
