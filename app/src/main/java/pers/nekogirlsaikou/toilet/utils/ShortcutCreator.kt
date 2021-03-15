package pers.nekogirlsaikou.toilet.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi

class ShortcutCreator(context: Context,id:String) {
    private val context = context
    private val id = id
    private var shortcutManager:ShortcutManager?=null
    private var shortcutInfoBuilder:ShortcutInfo.Builder? = null
    private var createShortcutIntent:Intent? = null
    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            shortcutManager = context.getSystemService(ShortcutManager::class.java)
            if (shortcutManager!!.isRequestPinShortcutSupported) {
                shortcutInfoBuilder = ShortcutInfo.Builder(context, id)
            } else {
                shortcutManager = null
                shortcutInfoBuilder = null
            }
        }
        // Cant use shortcutManager. So use broadcast instead.
        if (shortcutInfoBuilder == null){
            createShortcutIntent = Intent("com.android.launcher.action.INSTALL_SHORTCUT")
        }
    }


    fun setIcon(icon: Icon){
        if (shortcutInfoBuilder == null){
            createShortcutIntent!!.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,icon)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                shortcutInfoBuilder!!.setIcon(icon)
            }
        }
    }

    fun setShortLabel(label:CharSequence){
        if (shortcutInfoBuilder == null){
            createShortcutIntent!!.putExtra(Intent.EXTRA_SHORTCUT_NAME, label)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                shortcutInfoBuilder!!.setShortLabel(label)
            }
        }
    }

    fun setLauncherIntent(launcherIntent:Intent){
        if (shortcutInfoBuilder == null){
            launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER)
            createShortcutIntent!!.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                shortcutInfoBuilder!!.setIntent(launcherIntent)
            }
        }
    }

    fun createShortcut(){
        if (shortcutInfoBuilder == null){
            context.sendBroadcast(createShortcutIntent!!)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val shortcutInfo = shortcutInfoBuilder!!.build()
                val pinnedShortcutCallbackIntent = shortcutManager!!.createShortcutResultIntent(shortcutInfo)

                val successCallback = PendingIntent.getBroadcast(context, /* request code */ 0,
                    pinnedShortcutCallbackIntent, /* flags */ 0)
                shortcutManager!!.requestPinShortcut(shortcutInfo, successCallback.intentSender)
            }
        }
    }
}