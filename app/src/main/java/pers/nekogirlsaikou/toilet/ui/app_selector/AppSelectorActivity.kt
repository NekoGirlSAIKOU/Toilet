package pers.nekogirlsaikou.toilet.ui.app_selector

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import pers.nekogirlsaikou.toilet.databinding.ActivityAppSelectorBinding

class AppSelectorActivity : AppCompatActivity() {
    lateinit var binding:ActivityAppSelectorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppSelectorBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}