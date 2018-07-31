package com.meowingtons.forms.adapter

import android.widget.Button
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.meowingtons.forms.R
import com.meowingtons.forms.entity.RateItem
import com.meowingtons.forms.entity.RateItemState
import java.text.SimpleDateFormat

class RateAdapter(data: List<RateItem>?)
    : BaseQuickAdapter<RateItem, BaseViewHolder>(R.layout.item_day_rate, data) {


    override fun convert(helper: BaseViewHolder?, item: RateItem?){
        helper?.setText(R.id.tvDate, SimpleDateFormat("dd.MM.yy").format(item?.date))
        helper?.setText(R.id.tvGoalName, item?.goalName)
        if(item!=null){
            when(item.rateState){
                RateItemState.RATED     ->{
                    helper?.setText(R.id.tvRateNumber, item.rateNumber!!.toString())
                    listOf(R.id.tvDate, R.id.tvGoalName).forEach { helper?.setAlpha(it, 1f) }
                    helper?.setVisible(R.id.btnRate, false)
                }
                RateItemState.NOT_RATED ->{
                    listOf(R.id.tvDate, R.id.tvGoalName).forEach { helper?.setAlpha(it, 0.5f) }
                    helper?.setVisible(R.id.containerRate, false)
                }
                RateItemState.FREE_DAY  ->{
                    listOf(R.id.tvDate, R.id.tvGoalName, R.id.btnRate).forEach { helper?.setAlpha(it, 0.25f) }
                    helper?.setVisible(R.id.containerRate, false)

                }
            }
        }
    }
}