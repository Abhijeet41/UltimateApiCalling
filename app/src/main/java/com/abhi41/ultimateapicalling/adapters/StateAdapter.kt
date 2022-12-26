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
import com.abhi41.ultimateapicalling.model.StateModel
import java.nio.channels.Selector

class StateAdapter(val context: Context, var stateList:List<StateModel>) : BaseAdapter(),
    Filterable {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return stateList.size
    }

    override fun getItem(position: Int): Any {
        return stateList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.single_state_name, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        }else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.label.text = stateList.get(position).statename
        return view
    }

    private class ItemHolder(row: View?) {
        val label: TextView
        init {
            label = row?.findViewById(R.id.txtState) as TextView
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()
                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    stateList
                else
                    stateList.filter {
                        it.statename?.toLowerCase()?.contains(queryString) ?: false
                    }
                return filterResults

            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {

            }

        }
    }

}