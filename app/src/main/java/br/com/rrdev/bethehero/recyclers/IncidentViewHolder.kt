package br.com.rrdev.bethehero.recyclers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.rrdev.bethehero.models.Incident
import kotlinx.android.synthetic.main.incident_adapter_item.view.*

class IncidentViewHolder(private val view: View, onClick: ((position:Int) -> Unit) = {}): RecyclerView.ViewHolder(view)  {

    init {
        view.layout_see_more.setOnClickListener{
            onClick(adapterPosition)
        }
    }

    fun setContent(incident: Incident){
        view.run{
            text_ong.text = incident.name
            text_description.text = incident.title
            text_value.text = incident.getValueFormat()
        }
    }

}