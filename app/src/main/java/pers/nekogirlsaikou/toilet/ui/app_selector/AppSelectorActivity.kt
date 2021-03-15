package pers.nekogirlsaikou.toilet.ui.app_selector

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import pers.nekogirlsaikou.toilet.databinding.ActivityAppSelectorBinding
import pers.nekogirlsaikou.toilet.utils.RealmUtil

class AppSelectorActivity : AppCompatActivity() {
    lateinit var binding:ActivityAppSelectorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RealmUtil.initRealm(this)
        binding = ActivityAppSelectorBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}