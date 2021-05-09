package com.example.itunes.ui.video

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunes.databinding.VideoFragmentBinding
import com.example.itunes.model.ResultsItem
import com.example.itunes.model.SongsListResponse
import com.example.itunes.network.NetworkResponseWrapper
import com.example.itunes.room.BrowsedSongsEntity
import com.example.itunes.ui.MainActivity
import com.example.itunes.ui.videoinfopage.VideoInfoActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class VideoFragment : Fragment(),VideoInterface {

    private var _binding: VideoFragmentBinding? = null
    private val videoViewModel: VideoViewModel by viewModels()
    private var videoInterface: VideoInterface? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = VideoFragmentBinding.inflate(inflater, container, false)

        videoViewModel.songsListResponse.observe(viewLifecycleOwner, mDataObserver)
        binding.recyclerViewVideos.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoInterface = this@VideoFragment
        videoViewModel.getSongsList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val mDataObserver = Observer<NetworkResponseWrapper<SongsListResponse>> { result ->

        when (result?.status) {
            NetworkResponseWrapper.Status.LOADING -> {
                // Loading data
                Log.d(MainActivity::class.java.simpleName, "loading")
            }
            NetworkResponseWrapper.Status.ERROR -> {
                // Error for https request
                Log.d(MainActivity::class.java.simpleName, result.error?.message.toString())
            }
            NetworkResponseWrapper.Status.SUCCESS -> {
                // Data from API
                binding.recyclerViewVideos.adapter =
                    result.response?.results?.let { VideoAdapter(requireContext(), it,videoInterface) }
            }
        }
    }

    override fun videoInfo(video: ResultsItem) {
        val browsedSongsEntity = BrowsedSongsEntity(null, video.trackCensoredName, video.artworkUrl100)
        videoViewModel.insertBrowsedSongsEntity(browsedSongsEntity)
        activity?.let{
            val intent = Intent (it, VideoInfoActivity::class.java)
            val gson = Gson()
            intent.putExtra("videoInfo", gson.toJson(video))
            it.startActivity(intent)
        }
    }
}

