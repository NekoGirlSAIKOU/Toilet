package pers.nekogirlsaikou.toilet.utils

import android.content.pm.PackageInfo
import io.realm.Realm
import io.realm.kotlin.where
import pers.nekogirlsaikou.toilet.model.AppInToilet

fun PackageInfo.disable(){
    Root.runRootCommand("pm disable "+ this.packageName)
}

fun PackageInfo.enable(){
    Root.runRootCommand("pm enable "+ this.packageName)
}

fun PackageInfo.uninstall(){
    Root.runRootCommand("pm uninstall " + this.packageName)
}

fun PackageInfo.dropToToilet(){
    //disable()
    Realm.getDefaultInstance().executeTransaction{
        it.insert(AppInToilet(this.packageName))
    }
}

fun PackageInfo.releaseFromToilet(){
    //enable()
    Realm.getDefaultInstance().executeTransaction {
        it.where<AppInToilet>()
            .equalTo("packageName",this.packageName)
            .findFirst()
            ?.deleteFromRealm()
    }
}

fun PackageInfo.isInToilet():Boolean{
    return Realm.getDefaultInstance()
        .where<AppInToilet>()
        .equalTo("packageName",this.packageName)
        .findFirst() != null
}