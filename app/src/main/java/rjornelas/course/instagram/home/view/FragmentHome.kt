package rjornelas.course.instagram.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rjornelas.course.instagram.R
import rjornelas.course.instagram.databinding.FragmentHomeBinding

class FragmentHome : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        binding?.let {
            it.homeRv.layoutManager = LinearLayoutManager(requireContext())
            it.homeRv.adapter = PostAdapter()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): PostAdapter.PostViewHolder {
            return PostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_post_list, parent, false)
            )
        }

        override fun onBindViewHolder(holder: PostAdapter.PostViewHolder, position: Int) {
            holder.bind(R.drawable.ic_insta_add)
        }

        override fun getItemCount(): Int {
            return 30
        }

        private class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(image: Int) {
                itemView.findViewById<ImageView>(R.id.home_img_post).setImageResource(image)
            }
        }
    }
}