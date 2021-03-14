package pers.nekogirlsaikou.toilet.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import pers.nekogirlsaikou.toilet.utils.enable
import pers.nekogirlsaikou.toilet.utils.launch

class LaunchAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val packageName = intent.extras?.getString("packageName")
        if (packageName != null){
            val packageInfo = packageManager.getPackageInfo(packageName,0)
            if (!packageInfo.applicationInfo.enabled){
                packageInfo.enable()
            }
            packageInfo.launch(this)
        }
        finish()
    }
}