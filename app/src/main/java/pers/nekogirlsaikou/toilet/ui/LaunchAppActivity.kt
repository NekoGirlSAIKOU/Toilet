package pers.nekogirlsaikou.toilet.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import pers.nekogirlsaikou.toilet.utils.enable
import pers.nekogirlsaikou.toilet.utils.launch
import kotlin.system.exitProcess

class LaunchAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val packageName = intent.extras?.getString("packageName")
        if (packageName != null){
            try {
                val packageInfo = packageManager.getPackageInfo(packageName,0)
                if (!packageInfo.applicationInfo.enabled){
                    packageInfo.enable()
                }
                packageInfo.launch(this)
            } catch (e: PackageManager.NameNotFoundException){
            }
        }
        finish()
    }
}