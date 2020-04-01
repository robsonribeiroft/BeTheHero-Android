package br.com.rrdev.bethehero.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rrdev.bethehero.R
import br.com.rrdev.bethehero.contract.MainActivityContract
import br.com.rrdev.bethehero.models.Incident
import br.com.rrdev.bethehero.presenter.MainActivityPresenter
import br.com.rrdev.bethehero.recyclers.IncidentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    private lateinit var adapter: IncidentAdapter
    private val presenter = MainActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = IncidentAdapter()
        adapter.onItemSelect = ::onItem

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        presenter.attach(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.getIncidents()
    }

    private fun onItem(position: Int){
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra("incident", adapter.getItem(position))
        startActivity(intent)
    }


    override fun updateUI(list: List<Incident>) {
        val text = "Total de ${list.size} casos"
        val spannable = SpannableString(text)
        spannable.setSpan(ForegroundColorSpan(Color.BLACK), 9, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text_total_incidents.text = spannable
        adapter.list = list
    }

    override fun updateUIonError(throwable: Throwable) = runOnUiThread{
        Toast.makeText(applicationContext, throwable.message, Toast.LENGTH_LONG).show()
        Log.d("UPDATE_ERROR", throwable.message)
    }
}
