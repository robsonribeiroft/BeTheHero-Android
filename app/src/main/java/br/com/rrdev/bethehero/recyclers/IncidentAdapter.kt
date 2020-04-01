package br.com.rrdev.bethehero.recyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.rrdev.bethehero.R
import br.com.rrdev.bethehero.models.Incident

class IncidentAdapter: RecyclerView.Adapter<IncidentViewHolder>() {

    var onItemSelect: (position:Int)->Unit = {}
    var list = emptyList<Incident>()
        set(value) {
            val diffUtil = DiffUtil.calculateDiff(IncidentDiffUtil(list, value))
            diffUtil.dispatchUpdatesTo(this)
            field = value
        }

    fun getItem(position: Int) = list[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IncidentViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.incident_adapter_item, parent, false),
        onItemSelect
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) =
        holder.setContent(list[position])


}