package com.meowingtons.forms.entity

import java.util.*

data class RateItem (var rateState: RateItemState, var goalName : String,
                     val date : Date, val rateNumber : Int?)

enum class RateItemState{
    RATED, NOT_RATED, FREE_DAY
}