package com.hyyu.githubrepos.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyyu.githubrepos.R
import com.hyyu.githubrepos.databinding.CellRepoBinding
import com.hyyu.githubrepos.ui.model.RepoModel

class ReposAdapter(val context: Context, val onItemClickListener: ItemClickListener? = null)
    : RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    var items: List<RepoModel> = mutableListOf()
        set(value) {
            field = mutableListOf<RepoModel>().apply { addAll(value) }
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder =
        RepoViewHolder(
            CellRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            tvFullName.text = item.fullName
            tvForks.text = when (item.forks) {
                0 -> context.resources.getString(R.string.cell_quantity_zero, context.resources.getString(R.string.label_fork_sing))
                1 -> context.resources.getString(R.string.cell_quantity_one, context.resources.getString(R.string.label_fork_sing))
                else -> context.resources.getString(R.string.cell_quantity_more, item.forks, context.resources.getString(R.string.label_fork_plural))
            }
            tvOpenIssues.text = when (item.forks) {
                0 -> context.resources.getString(R.string.cell_quantity_zero, context.resources.getString(R.string.label_open_issues_sing))
                1 -> context.resources.getString(R.string.cell_quantity_one, context.resources.getString(R.string.label_open_issues_sing))
                else -> context.resources.getString(R.string.cell_quantity_more, item.openIssues, context.resources.getString(R.string.label_open_issues_plural))
            }
            tvWatchers.text = when (item.forks) {
                0 -> context.resources.getString(R.string.cell_quantity_zero, context.resources.getString(R.string.label_watchers_sing))
                1 -> context.resources.getString(R.string.cell_quantity_one, context.resources.getString(R.string.label_watchers_sing))
                else -> context.resources.getString(R.string.cell_quantity_more, item.watchers, context.resources.getString(R.string.label_watchers_plural))
            }
        }
    }

    private fun getItem(position: Int) = items[position]

    inner class RepoViewHolder(val binding: CellRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            onItemClickListener?.let { listener ->
                itemView.setOnClickListener { listener.onItemClicked(bindingAdapterPosition) }
            }
        }
    }

}
