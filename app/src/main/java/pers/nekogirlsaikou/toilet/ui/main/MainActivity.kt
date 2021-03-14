package pers.nekogirlsaikou.toilet.ui.main

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        val mLayoutManager = GridLayoutManager(this,3, GridLayoutManager.VERTICAL,false)
        val mAdapter = AppListAdapter(this.packageManager) {it.isInToilet()}
        mAdapter.onItemLongClickListener = {
            val position = binding.rvApps.getChildAdapterPosition(it)
            val popupMenu = PopupMenu(this,it)
            popupMenu.setAppMenu(mAdapter,position)
            popupMenu.show()
            true
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
            else -> super.onOptionsItemSelected(item)
        }
    }
}