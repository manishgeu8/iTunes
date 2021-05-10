# iTunes
1. Activity
  a. Main Activity
     binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
    i) ViewPager
    ii) ViewPagerAdapter
  b. Video Info Activity
    i) fun getImageGlide(imgItem: ImageView, strImgPath: String) 
      To set the image
    ii) resultsItem = gson.fromJson(intent.getStringExtra("videoInfo"), ResultsItem::class.java)
      To display video info and get the data from the video fragment
      
2. Fragments 
  a. Video Fragment
    i) videoViewModel.getSongsList()
      fun to get video song list from the api
    ii) videoViewModel.songsListResponse.observe(viewLifecycleOwner, mDataObserver)
      Observe data from the videoViewModel 
    iii) binding.recyclerViewVideos.adapter = result.response?.results?.let {VideoAdapter(requireContext(), it,videoInterface) }
      Set list of songs in UI with VideoAdapter in recyclerView
     iv) override fun videoInfo(video: ResultsItem) 
       Function videoinfo will save the clicked video in room database and send the clicked video info to videoInfoActivity.
 
  b. Video History Fragment
    i) videoHistoryViewModel.browsedHistoryResponse.observe(viewLifecycleOwner,mDataObserver)
      Observe data from the videoHistoryViewModel
    ii) videoHistoryViewModel.getSongsList()
      get Video songs history from the database.
      
3. Database 
  a. Room database used
  b. Database name: String = "browsed_songs_db"
  c. data class BrowsedSongsEntity : tableName = "browsed_songs"
  
4. Dependencies
  a. Kotlin dependencies
  b. Hilt dependencies
  c. Androidx.lifecycle
  d. Kotlin Coroutines
  e. Retrofit
  f. Kotlinx-serialization
  g. Glide
  h. Room database
