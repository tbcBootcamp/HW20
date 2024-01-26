package com.example.hw20.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hw20.databinding.ListItemBinding
import com.example.hw20.presentation.model.UserUiModel

class UserAdapter() : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var list: List<UserUiModel>? = emptyList()

    var onEditClick: (UserUiModel) -> Unit = {}
    var onRemoveClick: (UserUiModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = list?.get(position)
        user?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = list?.size ?: 0

    inner class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserUiModel) {
            binding.apply {
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
                tvMail.text = user.email

                btnEdit.setOnClickListener { onEditClick(user) }
                btnRemove.setOnClickListener { onRemoveClick(user) }
            }
        }
    }

    fun setData(newUserList: List<UserUiModel>) {
        val diffUtil = DiffUtil(oldList = list, newList = newUserList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list = newUserList
        diffResult.dispatchUpdatesTo(this)
    }

}

private class DiffUtil(private val oldList: List<UserUiModel>?, private val newList: List<UserUiModel>?) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return newList?.size ?: 0
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList?.get(oldItemPosition)?.id ?: 0) == (newList?.get(newItemPosition)?.id ?: 0)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            (oldList?.get(oldItemPosition)?.id ?: 0) != (newList?.get(newItemPosition)?.id
                ?: 0) -> false
            (oldList?.get(oldItemPosition)?.firstName ?: 0) != (newList?.get(newItemPosition)?.firstName
                ?: 0) -> false
            (oldList?.get(oldItemPosition)?.lastName ?: 0) != (newList?.get(newItemPosition)?.lastName
                ?: 0) -> false
            (oldList?.get(oldItemPosition)?.email ?: 0) != (newList?.get(newItemPosition)?.email
                ?: 0) -> false
            else -> true

        }
    }

}