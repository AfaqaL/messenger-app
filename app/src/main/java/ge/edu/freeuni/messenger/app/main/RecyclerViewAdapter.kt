package ge.edu.freeuni.messenger.app.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.messenger.app.R

class RecyclerViewAdapter(): RecyclerView.Adapter<RecyclerViewViewHolder>() {

    var list = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return RecyclerViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 20;
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {

    }
}

class RecyclerViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

}
