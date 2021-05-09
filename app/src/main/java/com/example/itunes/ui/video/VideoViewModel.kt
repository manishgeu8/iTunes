package com.example.itunes.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunes.model.SongsListResponse
import com.example.itunes.network.BaseAPIService
import com.example.itunes.network.NetworkResponseWrapper
import com.example.itunes.room.BrowsedSongsEntity
import com.example.itunes.room.HistoryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val baseAPIService: BaseAPIService,
    private val historyDao: HistoryDao,
) :
    ViewModel() {

    private val songsListResponseInternal = MutableLiveData<NetworkResponseWrapper<SongsListResponse>>()
    val songsListResponse: LiveData<NetworkResponseWrapper<SongsListResponse>>
    get() = songsListResponseInternal

    fun getSongsList() {
        viewModelScope.launch(Dispatchers.IO) {
            songsListResponseInternal.postValue(NetworkResponseWrapper.loading())
            runCatching {
                val responseData = NetworkResponseWrapper.success(
                    baseAPIService.getSongsList()
                )
                songsListResponseInternal.postValue(responseData)
            }.onFailure {
                songsListResponseInternal.postValue(NetworkResponseWrapper.error(it))
            }
        }
    }

    fun insertBrowsedSongsEntity(browsedSongsEntity: BrowsedSongsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            historyDao.insert(browsedSongsEntity)
        }
    }
}