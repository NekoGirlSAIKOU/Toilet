package pers.nekogirlsaikou.toilet.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class AppInToilet(packageName:String = ""):RealmObject() {
    @PrimaryKey
    var packageName:String = packageName
}