package pers.nekogirlsaikou.toilet.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import pers.nekogirlsaikou.toilet.utils.RealmUtil
import pers.nekogirlsaikou.toilet.utils.disable
import pers.nekogirlsaikou.toilet.utils.isInToilet

class LockScreenBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        RealmUtil.initRealm(context)
        when (intent.action){
            Intent.ACTION_SCREEN_OFF -> {
                context.packageManager.getInstalledPackages(0).filter {
                    it.isInToilet()
                }.forEach {
                    it.disable()
                }
            }
        }
    }
}