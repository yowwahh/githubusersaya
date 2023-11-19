package com.example.githubapps
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.githubapps.data.model.ResponseUserGithub
import com.example.githubapps.databinding.ItemUserBinding


class UserAdapter(private val data: MutableList<ResponseUserGithub.ItemsItem> = mutableListOf()) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    fun setData(data: MutableList<ResponseUserGithub.ItemsItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
    class UserViewHolder(private val v: ItemUserBinding) :RecyclerView.ViewHolder(v.root) {


        fun bind(item: ResponseUserGithub.ItemsItem) {
            v.image.load(item.avatarUrl) {
                transformations(CircleCropTransformation())
            }
            v.username.text = item.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

}
