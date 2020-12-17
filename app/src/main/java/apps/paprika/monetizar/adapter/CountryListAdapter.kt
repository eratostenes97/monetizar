package apps.paprika.monetizar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import apps.paprika.monetizar.R
import apps.paprika.monetizar.model.Country
import apps.paprika.monetizar.model.ListItem
import apps.paprika.monetizar.util.getProgressDrawable
import apps.paprika.monetizar.util.loadImage


class CountryListAdapter(var countries: ArrayList<ListItem>, val clickListener: CountryClickListener):
        RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>(){

    fun updateCountries(newCountries:ArrayList<ListItem>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()

    }

    override fun getItemViewType(position: Int) = countries[position].type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_layout, parent, false)
        return CountryViewHolder(view, clickListener)
    }

    override fun getItemCount()=countries.size


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
      holder.bind(countries[position])
    }

    abstract class CountryListViewHolder(view: View): RecyclerView.ViewHolder(view){
        abstract fun bind(item: ListItem)
    }


    class CountryViewHolder(view:View, var clickListener: CountryClickListener): CountryListViewHolder(view){

        private val layout:LinearLayout = itemView.findViewById(R.id.layout)
        private val imagen:ImageView = itemView.findViewById(R.id.imageViewjaja)
        private val nombre:TextView = itemView.findViewById(R.id.name)
        private val capital:TextView = itemView.findViewById(R.id.capital)

        override fun bind(item: ListItem){

            val country = item as Country

            nombre.text = country.countryName
            capital.text = country.capital
            imagen.loadImage(country.flag, getProgressDrawable(imagen.context))
            layout.setOnClickListener { clickListener.onCountryClick(country) }
        }

    }

    class adViewHolder(view: View):CountryListViewHolder(view){
        override fun bind(item: ListItem) {
            TODO("Not yet implemented")
        }
    }

}
