package com.jaysinghtalreja.githubdemo.ui.repositories

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaysinghtalreja.githubdemo.data.sourceofdata.entity.RepoData
import com.jaysinghtalreja.githubdemo.databinding.FragmentGithubTrendingBinding
import com.jaysinghtalreja.githubdemo.di.modules.viewmodel.ViewModelFactory
import com.jaysinghtalreja.githubdemo.ui.common.UiState
import com.jaysinghtalreja.githubdemo.ui.repositories.adapters.GithubRepositoryAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FragmentGithubTrending : DaggerFragment(), ItemSelectionListener, OnRepositorySearchListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding : FragmentGithubTrendingBinding

    private lateinit var adapter : GithubRepositoryAdapter


    private val fragmentGithubTrendingViewModel : FragmentGithubTrendingViewModel by viewModels { viewModelFactory }
    private var layoutManager : LinearLayoutManager? = null
    private lateinit var repoDataList : List<RepoData>
    private var selectedRepo : RepoData? = null
    private var recyclerViewPosition: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreAdapterPosition(savedInstanceState)

    }

    /**
     * Restore Adapter's Last Visible Item Position
     */
    private fun restoreAdapterPosition(savedInstanceState: Bundle?) {
        savedInstanceState?.getInt(ADAPTER_POSITION).let {
            recyclerViewPosition = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGithubTrendingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initData()
        initView()
        initObservers()
    }


    override fun onResume() {
        super.onResume()
        recyclerViewPosition?.let {position ->
            updateRecyclerViewPosition(position)
        }
    }

    private fun initData() {
        fragmentGithubTrendingViewModel.loadTrendingRepository()
    }

    private fun initAdapter() {
        layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        layoutManager?.orientation = if(fragmentGithubTrendingViewModel.orientationVertical.get()) {
            LinearLayoutManager.VERTICAL
        }
        else {
            LinearLayoutManager.HORIZONTAL
        }
        binding.repoRv.layoutManager = layoutManager
        adapter = GithubRepositoryAdapter(this, this)
        binding.repoRv.adapter = adapter
    }

    private fun initView() {
        fragmentGithubTrendingViewModel.loadTrendingRepository()
        with(binding) {
            binding.viewModel = fragmentGithubTrendingViewModel
            binding.horizontalOrientation.setOnClickListener {
                layoutManager?.orientation = LinearLayoutManager.HORIZONTAL
                fragmentGithubTrendingViewModel.orientationVertical.set(false)
            }

            binding.verticalOrientation.setOnClickListener {
                layoutManager?.orientation = LinearLayoutManager.VERTICAL
                fragmentGithubTrendingViewModel.orientationVertical.set(true)
            }

            binding.searchRefuelerTv.doOnTextChanged { text, _, _, _t ->
                adapter.filter.filter(text)
            }

            binding.repoRv.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    recyclerViewPosition = layoutManager?.findFirstVisibleItemPosition()
                }
            })

        }
    }



    private fun initObservers() {
        fragmentGithubTrendingViewModel.repoData.observe(viewLifecycleOwner, Observer {
            repoDataList = it
            checkForSelectedRepository(repoDataList)
            adapter.submitList(repoDataList)
            adapter.reposDataList = it
        })

        fragmentGithubTrendingViewModel.selectedRepository.observe(viewLifecycleOwner, Observer {
            selectedRepo = it
        })

    }

    private fun checkForSelectedRepository(repoDataList: List<RepoData>?) {
        selectedRepo?.let {
            repoDataList?.forEach { item ->
                if (item.id == selectedRepo!!.id) {
                    item.apply {
                        isSelected = true
                    }
                }
            }
        }
    }

    override fun onSelectItem(item: RepoData) {
        repoDataList.forEach {
            it.isSelected = false
        }
        item.isSelected = true
        fragmentGithubTrendingViewModel.selectedRepository.value = item
        adapter.notifyDataSetChanged()
    }


    override fun hasData(found: Boolean) {
        if(found) {
            fragmentGithubTrendingViewModel.uiState = UiState.LOADED
        }
        else {
            fragmentGithubTrendingViewModel.uiState = UiState.EMPTY
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recyclerViewPosition?.let {
            outState.putInt(ADAPTER_POSITION , it)
        }
    }


    private fun updateRecyclerViewPosition(position: Int) {
        binding.repoRv.scrollToPosition(position)
    }

    /**
     * Static
     */
    companion object {
        const val FRAGMENT_ID = "GitHubTrending"
        const val ADAPTER_POSITION = "adapter_position"
    }

}

interface ItemSelectionListener {
    fun onSelectItem(item : RepoData)
}

interface OnRepositorySearchListener {
    fun hasData(found : Boolean)
}
