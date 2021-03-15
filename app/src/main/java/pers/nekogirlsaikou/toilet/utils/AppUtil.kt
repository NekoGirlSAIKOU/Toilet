package pers.nekogirlsaikou.toilet.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.os.Build
import io.realm.Realm
import io.realm.kotlin.where
import pers.nekogirlsaikou.toilet.model.AppInToilet
import pers.nekogirlsaikou.toilet.ui.LaunchAppActivity


fun PackageInfo.disable() {
    Root.runRootCommand("pm disable " + this.packageName)
}

fun PackageInfo.enable() {
    Root.runRootCommand("pm enable " + this.packageName)
}

fun PackageInfo.uninstall() {
    if (!applicationInfo.enabled) {
        enable()
    }
    Root.runRootCommand("pm uninstall " + this.packageName)
}

fun PackageInfo.dropToToilet() {
    disable()
    Realm.getDefaultInstance().executeTransaction {
        it.insert(AppInToilet(this.packageName))
    }
}

fun PackageInfo.releaseFromToilet() {
    enable()
    Realm.getDefaultInstance().executeTransaction {
        it.where<AppInToilet>()
                .equalTo("packageName", this.packageName)
                .findFirst()
                ?.deleteFromRealm()
    }
}

fun PackageInfo.isInToilet(): Boolean {
    return Realm.getDefaultInstance()
            .where<AppInToilet>()
            .equalTo("packageName", this.packageName)
            .findFirst() != null
}

fun PackageInfo.createToiletAppShortcut(context: Context) {
    val shortcutIntent = Intent(context, LaunchAppActivity::class.java)
        .setAction(Intent.ACTION_MAIN)
        .putExtra("packageName", this.packageName)

    ShortcutCreator(context,"launch-${this.packageName}").apply {
        setIcon(context.packageManager.getApplicationIcon(applicationInfo).toIcon())
        setShortLabel(context.packageManager.getApplicationLabel(applicationInfo))
        setLauncherIntent(shortcutIntent)
    }.createShortcut()
}

fun PackageInfo.launch(context: Context) {
    val launchIntent =
            context.packageManager.getLaunchIntentForPackage(packageName)
    if (launchIntent != null) {
        context.startActivity(launchIntent)
    }
}
