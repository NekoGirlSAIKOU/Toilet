package pers.nekogirlsaikou.toilet.utils

import android.widget.PopupMenu
import pers.nekogirlsaikou.toilet.R
import pers.nekogirlsaikou.toilet.adapters.AppListAdapter

fun PopupMenu.setAppMenu(mAdapter:AppListAdapter, position:Int){
    inflate(R.menu.menu_app)
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
        this.menu.findItem(R.id.release).setVisible(true)
    }
    if (mAdapter.apps[position].applicationInfo.enabled){
        this.menu.findItem(R.id.enable).setVisible(false)
    } else {
        this.menu.findItem(R.id.disable).setVisible(false)
    }
}