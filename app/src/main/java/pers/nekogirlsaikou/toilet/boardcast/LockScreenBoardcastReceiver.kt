package pers.nekogirlsaikou.toilet.boardcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import pers.nekogirlsaikou.toilet.utils.disable
import pers.nekogirlsaikou.toilet.utils.isInToilet

class LockScreenBoardcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Realm.init(context)
        val realm_config = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(realm_config)
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