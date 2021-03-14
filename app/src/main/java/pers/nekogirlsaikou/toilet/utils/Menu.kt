package pers.nekogirlsaikou.toilet.utils

import android.content.Context
import android.widget.PopupMenu
import pers.nekogirlsaikou.toilet.R
import pers.nekogirlsaikou.toilet.adapters.AppListAdapter

fun PopupMenu.setAppMenu(mAdapter:AppListAdapter, position:Int, context:Context?=null){
    inflate(R.menu.menu_app)
    if (context == null){
        menu.findItem(R.id.create_shortcut).setVisible(false)
    }
    setOnMenuItemClickListener { item ->
        when (item.itemId){
            R.id.release -> {
                mAdapter.apps[position].releaseFromToilet()
                mAdapter.reloadApps()
                true
            }
            R.id.drop -> {
                mAdapter.apps[position].dropToToilet()
                mAdapter.reloadApps()
                true
            }
            R.id.create_shortcut -> {
                mAdapter.apps[position].createToiletAppShortcut(context!!)
                true
            }
            R.id.enable -> {
                mAdapter.apps[position].enable()
                mAdapter.reloadAppEnabled(position)
                true
            }
            R.id.disable -> {
                mAdapter.apps[position].disable()
                mAdapter.reloadAppEnabled(position)
                true
            }
            R.id.uninstall -> {
                mAdapter.apps[position].uninstall()
                mAdapter.reloadApps()
                true
            }
            else -> false
        }
    }

    if (mAdapter.apps[position].isInToilet()){
        this.menu.findItem(R.id.drop).setVisible(false)
    } else {
        this.menu.findItem(R.id.release).setVisible(false)
    }
    if (mAdapter.apps[position].applicationInfo.enabled){
        this.menu.findItem(R.id.enable).setVisible(false)
    } else {
        this.menu.findItem(R.id.disable).setVisible(false)
    }
}