package com.meowingtons.forms.service
import android.app.Dialog
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.ImageView
import com.meowingtons.forms.R
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import android.view.Gravity
import android.graphics.PixelFormat
import android.view.WindowManager
import android.os.Build
import android.view.Window


/**
 * Из-за изменения правила для android.permission.SYSTEM_ALERT_WINDOW, любое приложение с API 23+
 * при установке НЕ из Play Store
 * _НЕ_ получает этот пермишен по умолчанию
 * Для дебага необходимо зайти в Настройки -> Приложения -> %приложениенейм% -> пролистать в самый низ
 * В разделе "Дополнительные настройки" есть пункт "Поверх других приложений"
 * Открыть его, активировать флаг
 */

class RateNotificationService : Service() {
    private val LOG_TAG = this.javaClass.canonicalName
    private lateinit var dialog: Dialog

    override fun onBind(intent: Intent?): IBinder {
        return Binder()
    }

    override fun onCreate() {
        super.onCreate()
        showDialog()
    }
    override fun onDestroy() {
        super.onDestroy()
        dialog.dismiss()
    }

    private fun showDialog(){
        dialog = Dialog(this@RateNotificationService)
        dialog.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.rate_dialog_event)
        dialog.setCancelable(true)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window.attributes)

        dialog.window.attributes = lp
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            dialog.window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
        }else{
            dialog.window.setType(WindowManager.LayoutParams.TYPE_PHONE)
        }
        dialog.show()
    }
}