package com.jaysinghtalreja.githubdemo.ui.repositories

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jaysinghtalreja.githubdemo.data.sourceofdata.GithubRepository
import com.jaysinghtalreja.githubdemo.data.sourceofdata.entity.RepoData
import com.jaysinghtalreja.githubdemo.ui.common.UiState
import com.jaysinghtalreja.githubdemo.ui.common.UiStateViewModel
import com.jaysinghtalreja.githubdemo.utils.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject

class FragmentGithubTrendingViewModel @Inject constructor(private val githubRepository: GithubRepository) : UiStateViewModel() {

    val repoData = githubRepository.getRepoDataLiveData()
    val selectedRepository = MutableLiveData<RepoData>(null)
    val orientationVertical = ObservableBoolean(true)
    val recyclerViewPosition = MutableLiveData<Int>(null)

    fun loadTrendingRepository() {
        uiState = UiState.INIT
        viewModelScope.launch {
            val (response, statusCode) = try {
                githubRepository.getGithubTrendingRepository()
            }
            catch (e: Exception) {
                uiState = UiState.ERROR
                null to 0
            }

            if (statusCode == Constants.RESPONSE_200_SUCCESS) {
                response?.items?.let {listRepoData ->
                    if(listRepoData.size == 0) {
                        uiState = UiState.EMPTY
                    }
                    else {
                        uiState = UiState.LOADED
                    }
                    githubRepository.saveGithubRepositoryLocal(listRepoData)
                }

            }
            else {
                uiState = UiState.ERROR
            }
        }
    }
}