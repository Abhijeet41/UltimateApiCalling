package com.abhi41.ultimateapicalling.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.abhi41.ultimateapicalling.R
import com.abhi41.ultimateapicalling.model.CountryModel
import java.nio.channels.Selector

class CountryAdapter(val context: Context, var countryList:List<CountryModel>) : BaseAdapter(),
    Filterable {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return countryList.size
    }

    override fun getItem(position: Int): Any {
        return countryList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.single_country_name, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        }else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.label.text = countryList.get(position).countryname
        return view
    }

    private class ItemHolder(row: View?) {
        val label: TextView
        init {
            label = row?.findViewById(R.id.txtCountry) as TextView
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()
                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    countryList
                else
                    countryList.filter {
                        it.countryname?.toLowerCase()?.contains(queryString) ?: false
                    }
                return filterResults

            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {

            }

        }
    }

}