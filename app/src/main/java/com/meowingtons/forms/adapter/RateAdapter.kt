package com.meowingtons.forms.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import com.meowingtons.forms.entity.RateItem
import android.view.View
import android.view.ViewGroup
import com.meowingtons.forms.R
import com.meowingtons.forms.entity.RateItemState
import kotlinx.android.synthetic.main.item_rate_button.view.*
import kotlinx.android.synthetic.main.item_rate_rating_mark.view.*
import kotlinx.android.synthetic.main.layout_item_day_rate.view.*
import java.text.DateFormat.getDateInstance

class RateAdapter (private val data: List<RateItem>)
    : RecyclerView.Adapter<RateAdapter.ViewHolder>(){
    private val LOG_TAG = this.javaClass.canonicalName

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = data[position].rateState.ordinal


    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_day_rate, parent, false)
        when (viewType){
            RateItemState.RATED.ordinal ->{
                view.stubRatingMark.inflate()
            }
            RateItemState.NOT_RATED.ordinal ->{
                view.stubBtnRate.inflate()
            }
            RateItemState.FREE_DAY.ordinal ->{
                view.stubBtnRate.inflate()
                view.btnRate.isClickable = false
                view.btnRate.alpha = 0.25f
            }
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.tvDate.text = getDateInstance().format(data[position].date)
        holder.view.tvGoalName.text = data[position].goalName
        when(data[position].rateState){
            RateItemState.RATED ->{
                Log.d(LOG_TAG, "RATED @ $position")
                //holder.view?.stubRatingMark?.inflate()
                holder.view.tvRateNumber.text = data[position].rateNumber.toString()
            }
            RateItemState.NOT_RATED ->{
                Log.d(LOG_TAG, "NOT_RATED @ $position")
                //holder.view?.stubBtnRate?.inflate()
            }
            RateItemState.FREE_DAY ->{
                Log.d(LOG_TAG, "NOT_RATED @ $position")
                //holder.view?.stubBtnRate?.inflate()
                //holder.view.btnRate.isClickable = false
                //holder.view.btnRate.alpha = 0.25f
            }
        }
    }
}