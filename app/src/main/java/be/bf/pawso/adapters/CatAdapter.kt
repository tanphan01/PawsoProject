package be.bf.pawso.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import be.bf.pawso.R
import be.bf.pawso.models.Cat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class CatAdapter(private val mContext: Context, private val cats : ArrayList<Cat>) : ArrayAdapter<Cat>(mContext, 0, cats) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var catItem = convertView

        if (catItem == null)
            catItem = LayoutInflater.from(mContext).inflate(R.layout.item_cat, parent, false)

        val currentCat = cats[position]

        if (catItem != null) {
            val imgCat : ImageView = catItem.findViewById(R.id.img_cat_item)
            val tvNameCat : TextView = catItem.findViewById(R.id.tv_cat_name_item)

            tvNameCat.text = currentCat.name
            Glide.with(mContext)
                .load(currentCat.image)
                .centerCrop()
                .error(R.drawable.ic_error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imgCat)
        }

        return catItem!!
    }
}