package com.jaysinghtalreja.githubdemo.ui.repositories.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jaysinghtalreja.githubdemo.data.sourceofdata.entity.RepoData
import com.jaysinghtalreja.githubdemo.databinding.RepositoryItemBinding
import com.jaysinghtalreja.githubdemo.ui.repositories.ItemSelectionListener
import com.jaysinghtalreja.githubdemo.ui.repositories.OnRepositorySearchListener
import java.util.*

class GithubRepositoryAdapter(private val itemSelectionListener: ItemSelectionListener, private val repositorySearchListener: OnRepositorySearchListener) : ListAdapter<RepoData, GithubRepositoryAdapter.GithubRepositoryViewHolder>(GithubRepositoryDataDiffCallback()), Filterable {

    var reposDataList: List<RepoData> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepositoryViewHolder {
        return GithubRepositoryViewHolder(
            RepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: GithubRepositoryViewHolder, position: Int) {
        val repositoryItem = getItem(position)
        holder.bind(repositoryItem, itemSelectionListener)
    }

    class GithubRepositoryViewHolder(private val binding : RepositoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(repositoryItem : RepoData, itemSelectionListener: ItemSelectionListener) {
            binding.apply {
                this.item = repositoryItem
            }
            binding.card.setOnClickListener {
                itemSelectionListener.onSelectItem(repositoryItem)
            }
        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredRepos: MutableList<RepoData> = ArrayList()
                val filterResults = FilterResults()

                if (constraint == null || constraint.isEmpty()) {
                    filteredRepos.addAll(reposDataList)
                } else {
                    val reposSearchItem = constraint.toString().toLowerCase(Locale.getDefault())
                        .trim { it <= ' ' }
                    for (repo in reposDataList) {
                        if(repo.fullName?.toLowerCase(Locale.getDefault())!!.contains(reposSearchItem)) {
                            filteredRepos.add(repo)
                        }
                    }
                }
                filterResults.values = filteredRepos

                /**
                 * Toggle Search Results UiState
                 */
                if(filteredRepos.size == 0) {
                    repositorySearchListener.hasData(false)
                }
                else {
                    repositorySearchListener.hasData(true)
                }
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                submitList(results?.values as List<RepoData>)
                notifyDataSetChanged()
            }
        }
    }

}

class GithubRepositoryDataDiffCallback: DiffUtil.ItemCallback<RepoData>(){
    override fun areItemsTheSame(oldItem: RepoData, newItem: RepoData): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: RepoData, newItem: RepoData): Boolean {
        return oldItem == newItem
    }
}