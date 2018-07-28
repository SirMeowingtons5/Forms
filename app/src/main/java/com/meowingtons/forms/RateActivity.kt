package com.meowingtons.forms


import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*

class RateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate)
        initTabs()
        showDialog()
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
                        tab?.icon?.alpha = 100
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
                tab?.icon?.alpha = 255
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
                tab?.icon?.alpha = 100
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    fun showDialog(){
        val dialog = Dialog(this@RateActivity)
        dialog.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.rate_dialog_event)
        dialog.setCancelable(true)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window.attributes)
        dialog.show()
    }
}