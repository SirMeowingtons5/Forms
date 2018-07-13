package com.meowingtons.forms

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOError

class MainActivity : AppCompatActivity() {
    lateinit var poll: Poll
    lateinit var views : ArrayList<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, RateActivity::class.java)
        //val intent = Intent(this, BottomNavigationActivity::class.java)
        startActivity(intent)
        finish()

        //btnSave.setOnClick
        findViewById<Button>(R.id.btnSave).setOnClickListener {
            saveResults()
            val gson = Gson()
            Log.d("gson", gson.toJson(poll))
        }

        poll = testPoll()
        title = poll.title
        views = ArrayList<View>()
        val container = findViewById<LinearLayout>(R.id.container)
        poll.questions.forEach{
            val v = createQuestionView(it)
            container.addView(v)
            views.add(v)
        }
    }

    fun createQuestionView(question : Question) : View{
         val view = layoutInflater.inflate(R.layout.item_question, null, false)
         val subview : View
         val container = view.findViewById<LinearLayout>(R.id.viewContainer)
         when(question.type){
             Question.Type.TEXT ->{
                 subview = layoutInflater.inflate(R.layout.subitem_question_textedit, null, false)
                 container.addView(subview)
             }
             Question.Type.SINGLE_CHOICE ->{
                 subview = layoutInflater.inflate(R.layout.subitem_question_singlechoice, null, false)
                 question.options.forEach{
                     val rb = RadioButton(this)
                     rb.text = it
                     subview.findViewById<RadioGroup>(R.id.radioGroup).addView(rb)
                 }
                 container.addView(subview)
             }
             Question.Type.MULTIPLE_CHOICE ->{
                 question.options.forEach{
                     val checkBox = CheckBox(this)
                     checkBox.text = it
                     container.addView(checkBox)
                 }
             }
             Question.Type.DOUBLE_TEXT ->{
                 subview = layoutInflater
                         .inflate(R.layout.subitem_question_te_number, null, false)
                 subview.findViewById<EditText>(R.id.editText)
                         .inputType = InputType.TYPE_CLASS_NUMBER
                         .or(InputType.TYPE_NUMBER_FLAG_SIGNED)
                         .or(InputType.TYPE_NUMBER_FLAG_DECIMAL)
                 container.addView(subview)

             }
             Question.Type.INT_TEXT ->{
             subview = layoutInflater
                     .inflate(R.layout.subitem_question_te_number, null, false)
             }
         }
         view.findViewById<TextView>(R.id.title).text = question.title
         view.findViewById<TextView>(R.id.hint).text = question.hint
         return view
     }

    fun saveResults(){
        for (i in poll.questions.indices){
            when(poll.questions[i].type){
                Question.Type.TEXT, Question.Type.DOUBLE_TEXT, Question.Type.INT_TEXT ->{
                   poll.questions[i].answer = views[i].findViewById<EditText>(R.id.editText)
                           .text.toString()
               }
                Question.Type.MULTIPLE_CHOICE ->{
                    val answer = StringBuilder()
                    val v = views[i].findViewById<LinearLayout>(R.id.viewContainer)
                    for (j in 0 until v.childCount){
                        if ((v.getChildAt(j) as CheckBox).isChecked) answer.append("$j ")
                    }
                    poll.questions[i].answer = answer.trim().toString()
                }
                Question.Type.SINGLE_CHOICE ->{
                    poll.questions[i].answer =
                            views[i].findViewById<RadioGroup>(R.id.radioGroup)
                                    .checkedRadioButtonId.toString()
                }
            }
        }
        poll.questions.forEach{
            Log.d("SAVERESULTS", it.answer)



            val file = File(getExternalFilesDir("FormsApp"),
                    "${System.currentTimeMillis()}.json")
            Log.d("filepath", file.path)
            val fos = FileOutputStream(file)
            val gson = Gson()
            Log.d("gson", gson.toJson(poll))
            try {
                fos.write(gson.toJson(poll).toByteArray())

            }catch (exception : IOError){
            }finally {
                fos.close()
            }
        }
    }

    fun testPoll() : Poll{
        val poll = Poll("Test poll")

        var question = Question("Write anything about yourself",
                "Anything. Literally." ,
                true, Question.Type.TEXT)
        poll.questions.add(question)

        question = Question("Pick your favourite color",
                "Seriously. Just you favourite color. Nothing more.",
                true, Question.Type.SINGLE_CHOICE)
        arrayOf("RED", "GREEN", "BLUE", "PURPLE", "KAWABUNGA").forEach{ question.options.add(it) }
        poll.questions.add(question)

        question = Question("Pick your favourite bands",
                "Everybody likes music!",
                true, Question.Type.MULTIPLE_CHOICE)
        arrayOf("The Cranberries", "Powerwolf", "Papa Roach", "California Breed")
                .forEach{ question.options.add(it) }
        poll.questions.add(question)

        question = Question("What is your average income in dollars?",
                "You may lie. I'm just an app anyway",
                false, Question.Type.DOUBLE_TEXT)
        poll.questions.add(question)


        return poll
    }
}
