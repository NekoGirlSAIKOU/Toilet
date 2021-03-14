package pers.nekogirlsaikou.toilet.utils

import android.content.pm.PackageInfo

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
    TODO()
}

fun PackageInfo.releaseFromToilet(){
    TODO()
}

fun PackageInfo.isInToilet():Boolean{
    return true
}