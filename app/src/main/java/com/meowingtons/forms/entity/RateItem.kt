package com.meowingtons.forms.entity

import java.util.*

data class RateItem (var isRated : Boolean, var isFreeDay : Boolean, var goalName : String,
                     val date : Date, val rateNumber : Int?)