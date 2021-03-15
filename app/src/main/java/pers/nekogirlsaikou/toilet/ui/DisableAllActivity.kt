package pers.nekogirlsaikou.toilet.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pers.nekogirlsaikou.toilet.utils.disable
import pers.nekogirlsaikou.toilet.utils.isInToilet

class DisableAllActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        packageManager.getInstalledPackages(0)
                .filter { it.isInToilet() && it.applicationInfo.enabled }
                .forEach { it.disable() }
        finish()
    }
}