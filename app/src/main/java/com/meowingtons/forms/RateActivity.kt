package com.meowingtons.forms


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_rate.*
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*

class RateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate)
        initTabs()
    }

    fun initTabs(){
        tab_layout.addTab(tab_layout.newTab().setIcon(R.mipmap.ic_scoreboard_active), 0)
        tab_layout.addTab(tab_layout.newTab().setIcon(R.mipmap.ic_performance), 1)
        tab_layout.addTab(tab_layout.newTab().setIcon(R.mipmap.ic_tutorials), 2)
        tab_layout.addTab(tab_layout.newTab().setIcon(R.mipmap.ic_settings), 3)
        tab_layout.addTab(tab_layout.newTab().setIcon(R.mipmap.ic_profile), 4)


        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d("SELECTED ", "${tab?.position}")

                when(tab?.position){
                    0 ->{
                        tab.setIcon(R.mipmap.ic_scoreboard_active)
                    }
                    1 ->{
                        tab.setIcon(R.mipmap.ic_performance_active)
                    }
                    2 ->{
                        tab.setIcon(R.mipmap.ic_tutorials_active)
                    }
                    3 ->{
                        tab.setIcon(R.mipmap.ic_settings_active)
                    }
                    4 ->{
                        tab.setIcon(R.mipmap.ic_profile_active)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("UNSELECTED ", "${tab?.position}")
                when(tab?.position){
                    0 ->{
                        tab.setIcon(R.mipmap.ic_scoreboard)
                    }
                    1 ->{
                        tab.setIcon(R.mipmap.ic_performance)
                    }
                    2 ->{
                        tab.setIcon(R.mipmap.ic_tutorials)
                    }
                    3 ->{
                        tab.setIcon(R.mipmap.ic_settings)
                    }
                    4 ->{
                        tab.setIcon(R.mipmap.ic_profile)
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
}
