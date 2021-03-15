package pers.nekogirlsaikou.toilet.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import pers.nekogirlsaikou.toilet.R
import pers.nekogirlsaikou.toilet.adapters.AppListAdapter
import pers.nekogirlsaikou.toilet.databinding.ActivityMainBinding
import pers.nekogirlsaikou.toilet.ui.app_selector.AppSelectorActivity
import pers.nekogirlsaikou.toilet.utils.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var mAdapter:AppListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RealmUtil.initRealm(this)

        binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        val mLayoutManager = GridLayoutManager(this,3, GridLayoutManager.VERTICAL,false)
        mAdapter = AppListAdapter(this.packageManager) {it.isInToilet()}
        mAdapter.onItemLongClickListener = {
            val position = binding.rvApps.getChildAdapterPosition(it)
            val popupMenu = PopupMenu(this,it)
            popupMenu.setAppMenu(mAdapter,position,this)
            popupMenu.menu.findItem(R.id.create_shortcut).setVisible(true)
            popupMenu.show()
            true
        }
        mAdapter.onItemClickListener = {
            val position = binding.rvApps.getChildAdapterPosition(it)
            if (!mAdapter.apps[position].applicationInfo.enabled){
                mAdapter.apps[position].enable()
            }
            mAdapter.apps[position].launch(this)
            mAdapter.reloadAppEnabled(position)
        }

        binding.rvApps.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
            itemAnimator = DefaultItemAnimator()
        }

        binding.fab.setOnClickListener { view ->
            val intent = Intent(this, AppSelectorActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onStart() {
        super.onStart()
        mAdapter.reloadApps()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.disable_all -> {
                mAdapter.apps.forEach {
                    if (it.applicationInfo.enabled){
                        it.disable()
                    }
                }
                mAdapter.reloadApps()
                true
            }
            R.id.create_disable_all_shortcut->{
                val launcherIntent = Intent(this, DisableAllActivity::class.java)
                    .setAction(Intent.ACTION_VIEW)
                    .putExtra("packageName", this.packageName)
                ShortcutCreator(this,"disable_all").apply {
                    setShortLabel(getString(R.string.menu_disable_all))
                    //setIcon(R.mipmap.ic_launcher_round)
                    setLauncherIntent(launcherIntent)
                }.createShortcut()
                true
            }
            R.id.about -> {
                val intent = Intent(this,AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}