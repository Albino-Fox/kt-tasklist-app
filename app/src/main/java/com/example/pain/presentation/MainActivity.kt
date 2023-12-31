package com.example.pain.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.pain.TaskApp
import com.example.pain.databinding.ActivityMainBinding
import com.example.pain.presentation.components.*
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), TaskClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ViewPagerAdapter
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as TaskApp).repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Незавершённые задачи"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Завершённые задачи"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Избранные"))

        binding.viewPager.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        binding.newTaskButton.setOnClickListener{
            NewTaskSheet(null).show(supportFragmentManager, "newTaskTag")
        }
    }

    override fun editTask(task: TaskViewData) {
        NewTaskSheet(task).show(supportFragmentManager, "newTaskTag")
    }

    override fun completeTask(task: TaskViewData) {
        taskViewModel.setCompleted(task)
    }

    override fun favouriteTask(task: TaskViewData) {
        taskViewModel.setFavourited(task)
    }
}