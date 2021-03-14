package pers.nekogirlsaikou.toilet.ui.app_selector

import android.content.pm.PackageInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import pers.nekogirlsaikou.toilet.R
import pers.nekogirlsaikou.toilet.adapters.AppListAdapter
import pers.nekogirlsaikou.toilet.databinding.FragmentInstalledAppsBinding
import pers.nekogirlsaikou.toilet.utils.*

/**
 * A simple [Fragment] subclass.
 * Use the [InstalledAppsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InstalledAppsFragment : Fragment() {
    lateinit var binding:FragmentInstalledAppsBinding
    var appFilter:(PackageInfo)->Boolean = {true}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInstalledAppsBinding.inflate(layoutInflater)


        val mLayoutManager = GridLayoutManager(requireContext(),3,GridLayoutManager.VERTICAL,false)
        val mAdapter = AppListAdapter(requireContext().packageManager,appFilter)
        mAdapter.onItemClickListener = {
            val position = binding.rvApps.getChildAdapterPosition(it)
            val popupMenu = PopupMenu(requireContext(),it)
            popupMenu.setAppMenu(mAdapter,position)
            popupMenu.show()
        }
        binding.rvApps.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
            itemAnimator = DefaultItemAnimator()
        }



        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment UserAppsFragment.
         */
        @JvmStatic
        fun newInstance(appFilter:((PackageInfo)->Boolean)= { true }) =
            InstalledAppsFragment().apply {
                this.appFilter = appFilter
            }
    }
}