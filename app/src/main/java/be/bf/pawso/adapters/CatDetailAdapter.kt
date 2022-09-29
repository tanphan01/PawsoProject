package be.bf.pawso.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.bf.pawso.R
import be.bf.pawso.models.CatWithShelter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.card.MaterialCardView

class CatDetailAdapter (private val mContext: Context,
                        private var data : MutableList<CatWithShelter>,
                        private val onClickCat : (cat : CatWithShelter) -> Unit) : RecyclerView.Adapter<CatDetailAdapter.CatViewHolder>() {

        inner class CatViewHolder(view : View) : RecyclerView.ViewHolder(view) {

            val image: ImageView = view.findViewById(R.id.profile_image)
            var fName: TextView = view.findViewById(R.id.tv_firstname)
            var gender: TextView = view.findViewById(R.id.tv_gender)
            var age: TextView = view.findViewById(R.id.tv_age)
            var shelter: TextView = view.findViewById(R.id.tv_shelter)
            var mood: TextView = view.findViewById(R.id.tv_mood)
            var profileCard : MaterialCardView = view.findViewById(R.id.profile_card)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cat_detail, parent, false)

            return CatViewHolder(view)
        }

        override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
            val currentCat = data[position]
            holder.fName.text = currentCat.cat.name
            holder.gender.text = currentCat.cat.gender
            holder.age.text = currentCat.cat.age
            holder.shelter.text = currentCat.shelter.nameShelter
            holder.mood.text = currentCat.cat.adjective

            holder.profileCard.setOnClickListener {
                onClickCat(currentCat)
            }

            Glide.with(mContext)
                .load(currentCat.cat.image + "?temp=" + currentCat.cat.id)
                .error(R.drawable.ic_error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .centerCrop()
                .into(holder.image)

        }

        override fun getItemCount(): Int {
            return data.size
        }

        fun update(newCats : MutableList<CatWithShelter>) {
            this.data = newCats
            notifyDataSetChanged()
        }

}
