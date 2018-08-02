package com.meowingtons.forms.service
import android.app.AlertDialog
import android.app.Dialog
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.meowingtons.forms.R
import android.view.Gravity
import android.view.WindowManager
import android.os.Build
import android.support.v7.view.ContextThemeWrapper
import android.view.Window
import kotlinx.android.synthetic.main.layout_rate_notification.*
import org.jetbrains.anko.sdk25.coroutines.onClick


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
        if(dialog.isShowing) dialog.dismiss()
    }

    private fun showDialog(){
        dialog = Dialog(this)
        dialog.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.layout_rate_notification)
        dialog.setCancelable(true)
        dialog.window.setGravity(Gravity.TOP or Gravity.CENTER)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        dialog.window.attributes = lp
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            dialog.window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
        }else{
            dialog.window.setType(WindowManager.LayoutParams.TYPE_PHONE)
        }
        dialog.show()
        dialog.tvNotNow.onClick { dialog.dismiss() }

        MediaPlayer.create(this, R.raw.rate_notification).start()
    }
}