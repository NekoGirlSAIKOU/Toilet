package pers.nekogirlsaikou.toilet.adapters

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pers.nekogirlsaikou.toilet.databinding.RvAppItemBinding

class AppListAdapter constructor(val packageManager:PackageManager,var appFilter: (PackageInfo) -> Boolean = {true}):
    RecyclerView.Adapter<AppListAdapter.Companion.ViewHolder>() {
    var apps:List<PackageInfo> = packageManager.getInstalledPackages(0).filter(appFilter)
    var onItemClickListener:(View) -> Unit = {}
    var onItemLongClickListener:(View) -> Boolean = {true}


    companion object{
        public class ViewHolder constructor(val binding:RvAppItemBinding):RecyclerView.ViewHolder(binding.root){
        }
    }

    fun reloadApps(){
        apps = packageManager.getInstalledPackages(0).filter(appFilter)
        notifyDataSetChanged()
    }

    fun reloadAppEnabled(postition:Int){
        apps[postition].applicationInfo.enabled = packageManager.getPackageInfo(apps[postition].packageName,0).applicationInfo.enabled
        notifyItemChanged(postition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvAppItemBinding.inflate(LayoutInflater.from(parent.context))
        val viewHolder = ViewHolder(binding)

        viewHolder.itemView.setOnClickListener {onItemClickListener(it)}
        viewHolder.itemView.setOnLongClickListener{onItemLongClickListener(it)}

        return viewHolder
    }

    override fun getItemCount(): Int {
        return apps.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvName.text = packageManager.getApplicationLabel(apps[position].applicationInfo)
        holder.binding.ivIcon.setImageDrawable(packageManager.getApplicationIcon(apps[position].applicationInfo))
        holder.binding.ivDisabled.visibility = when (apps[position].applicationInfo.enabled){
            true -> INVISIBLE
            else -> VISIBLE
        }
    }
}