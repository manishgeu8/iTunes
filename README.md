# iTunes
1. Activity
	1. Main Activity : binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
		1. ViewPager
		2. ViewPagerAdapter
	2. Video Info Activity
		1. fun getImageGlide(imgItem: ImageView, strImgPath: String) : To set the image
		2. resultsItem = gson.fromJson(intent.getStringExtra("videoInfo"), ResultsItem::class.java) : To display video info and get the data from the video fragment
      
2. Fragments 
	1. Video Fragment
		1. videoViewModel.getSongsList() : fun to get video song list from the api
		2. videoViewModel.songsListResponse.observe(viewLifecycleOwner, mDataObserver) : Observe data from the videoViewModel 
		3. binding.recyclerViewVideos.adapter = result.response?.results?.let {VideoAdapter(requireContext(), it,videoInterface) } : Set list of songs in UI with VideoAdapter in recyclerView
		4. override fun videoInfo(video: ResultsItem) : Function videoinfo will save the clicked video in room database and send the clicked video info to videoInfoActivity.
	2. Video History Fragment
		1. videoHistoryViewModel.browsedHistoryResponse.observe(viewLifecycleOwner,mDataObserver) : Observe data from the videoHistoryViewModel
		2. videoHistoryViewModel.getSongsList() : get Video songs history from the database.

3. Database 
	1. Room database used
	2. Database name: String = "browsed_songs_db"
	3. data class BrowsedSongsEntity : tableName = "browsed_songs"
  
4. Dependencies
	1. Kotlin dependencies
	2. Hilt dependencies
	3. Androidx.lifecycle
	4. Kotlin Coroutines
	5. Retrofit
	6. Kotlinx-serialization
	7. Glide
	8. Room database
