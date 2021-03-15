package pers.nekogirlsaikou.toilet.boardcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
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