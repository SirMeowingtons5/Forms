package com.meowingtons.forms.adapter

import android.app.Dialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import com.meowingtons.forms.entity.RateItem
import com.meowingtons.forms.R
import com.meowingtons.forms.entity.RateItemState
import kotlinx.android.synthetic.main.item_rate_button.view.*
import kotlinx.android.synthetic.main.item_rate_rating_mark.view.*
import kotlinx.android.synthetic.main.layout_item_day_rate.view.*
import kotlinx.android.synthetic.main.layout_rate_dialog.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.DateFormat.getDateInstance

class RateAdapter (private val data: List<RateItem>)
    : RecyclerView.Adapter<RateAdapter.ViewHolder>(){
    private lateinit var mRecyclerView: RecyclerView

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

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
                listOf(view.tvDate, view.tvGoalName).forEach { it.alpha = 0.5f }
            }
            RateItemState.FREE_DAY.ordinal ->{
                view.stubBtnRate.inflate()
                view.btnRate.isClickable = false
                listOf(view.tvDate, view.tvGoalName, view.btnRate).forEach { it.alpha = 0.25f }
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
                holder.view.tvRateNumber.text = data[position].rateNumber.toString()
            }
            RateItemState.NOT_RATED ->{
                Log.d(LOG_TAG, "NOT_RATED @ $position")
                holder.view.btnRate.onClick {
                    val dialog = Dialog(holder.view.context)
                    dialog.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    dialog.setContentView(R.layout.layout_rate_dialog)
                    dialog.setCancelable(true)
                    dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
                    val lp = WindowManager.LayoutParams()
                    lp.copyFrom(dialog.window.attributes)
                    dialog.window.attributes = lp
                    //dialog.window.attributes.windowAnimations = R.style.DialogAnimationBottomUp
                    dialog.window.attributes.windowAnimations = R.style.DialogAnimationFade
                    dialog.show()
                    dialog.btnDayOff.onClick {
                        dialog.dismiss()
                    }
                }
            }
            RateItemState.FREE_DAY ->{
                Log.d(LOG_TAG, "FREE DAY @ $position")
            }
        }
    }
}