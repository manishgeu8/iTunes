package com.example.itunes.ui.history

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
import com.example.itunes.databinding.VideoHistoryFragmentBinding
import com.example.itunes.network.NetworkResponseWrapper
import com.example.itunes.room.BrowsedSongsEntity
import com.example.itunes.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class VideoHistoryFragment : Fragment() {

    private var _binding: VideoHistoryFragmentBinding? = null
    private val videoHistoryViewModel: VideoHistoryViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VideoHistoryFragmentBinding.inflate(inflater, container, false)

        binding.recyclerViewVideosHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoHistoryViewModel.browsedHistoryResponse.observe(viewLifecycleOwner, mDataObserver)
        videoHistoryViewModel.getSongsList()
    }

    private val mDataObserver = Observer<NetworkResponseWrapper<List<BrowsedSongsEntity>>> { result ->

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
                binding.recyclerViewVideosHistory.adapter = VideoHistoryAdapter(requireContext(), result.response!!)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        videoHistoryViewModel.getSongsList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

