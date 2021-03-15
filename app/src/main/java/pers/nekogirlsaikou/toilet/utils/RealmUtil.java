package pers.nekogirlsaikou.toilet.utils;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmUtil {
    static boolean inited = false;
    public static void initRealm (Context context){
        if (inited == false){
            Realm.init(context);
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .allowWritesOnUiThread(true)
                    .allowQueriesOnUiThread(true)
                    .build();
            Realm.setDefaultConfiguration(config);
            inited = true;
        }
    }
}
