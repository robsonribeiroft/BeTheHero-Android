package br.com.rrdev.bethehero.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.rrdev.bethehero.R
import br.com.rrdev.bethehero.contract.MainActivityContract
import br.com.rrdev.bethehero.models.Incident
import br.com.rrdev.bethehero.presenter.MainActivityPresenter
import br.com.rrdev.bethehero.recyclers.IncidentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    private val adapter: IncidentAdapter = IncidentAdapter()
    private val presenter = MainActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter.onItemSelect = ::onItem

        recycler_view.adapter = adapter
        recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        presenter.attach(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.getIncidents()
    }

    private fun onItem(position: Int){
        DetailActivity.newActivityInstance(applicationContext, adapter.getItem(position))
    }

    override fun updateUI(list: List<Incident>) {
        adapter.list = list
    }

    override fun updateUIonError(throwable: Throwable) = runOnUiThread{
        Toast.makeText(applicationContext, throwable.message, Toast.LENGTH_LONG).show()
        Log.d("UPDATE_ERROR", "message: ${throwable.message}")
    }
}
