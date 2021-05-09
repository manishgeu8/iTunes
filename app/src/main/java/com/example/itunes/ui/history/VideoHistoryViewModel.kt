package com.example.itunes.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunes.network.NetworkResponseWrapper
import com.example.itunes.room.BrowsedSongsEntity
import com.example.itunes.room.HistoryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoHistoryViewModel @Inject constructor(private val historyDao: HistoryDao): ViewModel() {

    private val browsedHistoryResponseInternal = MutableLiveData<NetworkResponseWrapper<List<BrowsedSongsEntity>>>()
    val browsedHistoryResponse: LiveData<NetworkResponseWrapper<List<BrowsedSongsEntity>>>
        get() = browsedHistoryResponseInternal

    fun getSongsList() {
        viewModelScope.launch(Dispatchers.IO) {
            browsedHistoryResponseInternal.postValue(NetworkResponseWrapper.loading())
            runCatching {
                val responseData = NetworkResponseWrapper.success(
                    historyDao.getHistoryList()
                )
                browsedHistoryResponseInternal.postValue(responseData)
            }.onFailure {
                browsedHistoryResponseInternal.postValue(NetworkResponseWrapper.error(it))
            }
        }
    }
}