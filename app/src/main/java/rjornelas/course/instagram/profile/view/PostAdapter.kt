package rjornelas.course.instagram.profile.view

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rjornelas.course.instagram.R
import rjornelas.course.instagram.common.model.Post

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var items: List<Post> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_profile_grid, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position].photoUrl)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photoUrl: String?) {
            Glide.with(itemView.context).load(photoUrl).into(itemView.findViewById(R.id.item_profile_img_grid))
        }
    }
}