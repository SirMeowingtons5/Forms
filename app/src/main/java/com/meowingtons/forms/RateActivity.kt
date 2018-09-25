package com.meowingtons.forms


import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.meowingtons.forms.adapter.RateAdapter
import com.meowingtons.forms.entity.RateItem
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*
import java.util.*
import com.meowingtons.forms.entity.RateItemState
import kotlinx.android.synthetic.main.fragment_rate_new.*
import kotlinx.android.synthetic.main.layout_rate_dialog.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class RateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate)
        initTabs()
        initAdapter()
        //showDialog()
        /*launch(UI){
            delay(2000)
            startService(Intent(this@RateActivity, RateNotificationService::class.java))
        }*/
    }

    private fun initTabs(){
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
                        tab.icon?.alpha = 100
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

    private fun showDialog(){
        val dialog = Dialog(this@RateActivity)
        dialog.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.layout_rate_dialog)
        dialog.setCancelable(true)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window.attributes)
        dialog.window.attributes = lp
        dialog.show()
        btnDayOff.onClick {
            dialog.dismiss()
        }
    }

    private fun initAdapter(){

        rvList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@RateActivity)
            adapter = RateAdapter(generateFakeData())
        }
    }

    private fun generateFakeData() : List<RateItem>{
        val res = ArrayList<RateItem>()
        for (i in 0..10) {
            val timestamp : Long = 1532957218000
            res.add(RateItem(RateItemState.NOT_RATED, "#goal_name", Date(timestamp), null))
            res.add(RateItem(RateItemState.RATED, "#goal_name", Date(timestamp), 7))
            res.add(RateItem(RateItemState.FREE_DAY, "#goal_name", Date(timestamp), null))
        }
        return res
    }
}