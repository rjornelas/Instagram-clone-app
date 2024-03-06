package rjornelas.course.instagram.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rjornelas.course.instagram.R
import rjornelas.course.instagram.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var binding: FragmentProfileBinding? = null

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        binding?.let {
            it.profileRv.layoutManager = GridLayoutManager(requireContext(), 3)
            it.profileRv.adapter = PostAdapter()
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
        ): PostViewHolder {
            return PostViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_profile_grid, parent, false)
            )
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.bind(R.drawable.ic_insta_add)
        }

        override fun getItemCount(): Int {
            return 30
        }

        private class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(image: Int) {
                itemView.findViewById<ImageView>(R.id.item_profile_img_grid)
                    .setImageResource(image) //TODO
            }
        }
    }
}