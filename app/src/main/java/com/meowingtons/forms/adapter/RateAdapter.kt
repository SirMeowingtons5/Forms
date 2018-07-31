package com.meowingtons.forms.adapter
import android.util.Log
import android.widget.Button
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.meowingtons.forms.R
import com.meowingtons.forms.entity.RateItem
import com.meowingtons.forms.entity.RateItemState
import java.text.SimpleDateFormat


class RateAdapter(data: List<RateItem>?)
    : BaseQuickAdapter<RateItem, BaseViewHolder>(R.layout.item_day_rate, data) {
    private val LOG_TAG = this.javaClass.canonicalName

    override fun convert(helper: BaseViewHolder?, item: RateItem?){
        helper?.setText(R.id.tvDate, SimpleDateFormat("dd.MM.yy").format(item?.date))
        helper?.setText(R.id.tvGoalName, item?.goalName)
        if(item!=null){
            when(item.rateState){
                RateItemState.RATED     ->{
                    Log.d(LOG_TAG, "RATED")
                    helper?.setText(R.id.tvRateNumber, item.rateNumber!!.toString())
                    listOf(R.id.tvDate, R.id.tvGoalName).forEach { helper?.setAlpha(it, 1f) }
                    helper?.setVisible(R.id.btnRate, false)
                    helper?.setVisible(R.id.containerRate, true)
                }
                RateItemState.NOT_RATED ->{
                    Log.d(LOG_TAG, "NOT_RATED")
                    listOf(R.id.tvDate, R.id.tvGoalName).forEach { helper?.setAlpha(it, 0.5f) }
                    helper?.setVisible(R.id.containerRate, false)
                    helper?.setVisible(R.id.btnRate, true)
                }
                RateItemState.FREE_DAY  ->{
                    Log.d(LOG_TAG, "FREE_DAY")
                    listOf(R.id.tvDate, R.id.tvGoalName, R.id.btnRate).forEach { helper?.setAlpha(it, 0.25f) }
                    helper?.setVisible(R.id.containerRate, false)
                    helper?.setVisible(R.id.btnRate, true)
                    helper?.getView<Button>(R.id.btnRate)?.isClickable = false
                }
            }
        }
    }
}