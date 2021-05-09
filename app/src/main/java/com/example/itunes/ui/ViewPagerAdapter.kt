package com.example.itunes.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.itunes.ui.history.VideoHistoryFragment
import com.example.itunes.ui.video.VideoFragment


class ViewPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                VideoFragment()
            }
            1 -> {
                VideoHistoryFragment()
            }
            else -> {
                VideoFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Video"
            }
            1 -> {
                return "History"
            }
        }
        return super.getPageTitle(position)
    }

}
