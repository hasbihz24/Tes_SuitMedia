package com.example.suitmedia_tes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl

class UserAdapter(private val users: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var selectedUserName: String? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
       holder.itemView.setOnClickListener {
            selectedUserName = user.firstName
            notifyDataSetChanged()
        }

        if (selectedUserName == user.firstName) {
            holder.itemView.setBackgroundColor(holder.itemView.context.resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
        } else {
            holder.itemView.setBackgroundColor(holder.itemView.context.resources.getColor(R.color.white))
        }


    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun getSelectedUserName(): String? {
        return selectedUserName
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_Firstname)
        private val emailTextView: TextView = itemView.findViewById(R.id.tv_email)
        private val imageView: ImageView = itemView.findViewById(R.id.IV_image)

        fun bind(user: User) {
            nameTextView.text = "${user.firstName} ${user.lastName}"
            emailTextView.text = user.email
            val resizeImage = user.avatar.toUri()
            Glide.with(imageView).load(resizeImage).circleCrop().into(imageView);
        }
    }
}