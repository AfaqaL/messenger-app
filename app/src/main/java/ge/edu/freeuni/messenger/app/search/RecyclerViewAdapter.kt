package ge.edu.freeuni.messenger.app.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.messenger.app.R

class SearchRecyclerViewAdapter(): RecyclerView.Adapter<SearchRecyclerViewViewHolder>() {

    var list = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return SearchRecyclerViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 20;
    }

    override fun onBindViewHolder(holder: SearchRecyclerViewViewHolder, position: Int) {

    }
}

class SearchRecyclerViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

}
