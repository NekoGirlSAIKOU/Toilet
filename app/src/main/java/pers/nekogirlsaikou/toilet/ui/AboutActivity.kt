package pers.nekogirlsaikou.toilet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pers.nekogirlsaikou.toilet.BuildConfig
import pers.nekogirlsaikou.toilet.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    lateinit var binding:ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvVersion.text = BuildConfig.VERSION_NAME+ "  " + BuildConfig.BUILD_TYPE

        binding.wvLicense.loadData(String(resources.assets.open("LICENSE").readBytes()),"text/plain",null)
    }
}