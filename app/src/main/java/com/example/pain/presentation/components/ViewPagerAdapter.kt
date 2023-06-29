package com.example.pain.presentation.components

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pain.presentation.CompletedTasks
import com.example.pain.presentation.FavouriteTasks
import com.example.pain.presentation.UncompletedTasks

enum class PageType(val position: Int) {
    UNCOMPLETED_TASKS(0),
    COMPLETED_TASKS(1),
    FAVOURITE_TASKS(2);

    companion object {
        fun fromPosition(position: Int): PageType {
            return values().find { it.position == position }
                ?: throw IllegalArgumentException("Invalid position: $position")
        }
    }
}

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = PageType.values().size

    override fun createFragment(position: Int): Fragment {
        return when (PageType.fromPosition(position)) {
            PageType.UNCOMPLETED_TASKS -> UncompletedTasks()
            PageType.COMPLETED_TASKS -> CompletedTasks()
            PageType.FAVOURITE_TASKS -> FavouriteTasks()
        }
    }
}
