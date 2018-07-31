package com.meowingtons.forms.entity

class Poll (val title : String){
    val questions = ArrayList<Question>()
}

class Question(val title : String, @Transient val hint : String,
               val isMandatory : Boolean, val type : Type){
    enum class Type{SINGLE_CHOICE, MULTIPLE_CHOICE, TEXT, INT_TEXT, DOUBLE_TEXT}

    @Transient
    var options = ArrayList<String>()

    var answer : String = "NO ANSWER"
}

class PollContext {
    fun bind() {

    }

    fun build(title:String): Poll {
        return Poll(title)
    }
}

//dsl