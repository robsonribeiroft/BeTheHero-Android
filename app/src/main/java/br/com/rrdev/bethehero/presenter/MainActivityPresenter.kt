package br.com.rrdev.bethehero.presenter

import android.content.Context
import br.com.rrdev.bethehero.contract.MainActivityContract
import br.com.rrdev.bethehero.repository.Api
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivityPresenter: MainActivityContract.Presenter<MainActivityContract.View>, CoroutineScope {

    private lateinit var view: MainActivityContract.View
    private lateinit var context: Context

    private val supervisorJob = SupervisorJob()
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        view.updateUIonError(throwable)
    }

    override fun attach(view: MainActivityContract.View) {
        this.view = view
        context = view as Context
    }

    fun getIncidents() = launch{
        val api = Api.getApiService()
        println("dismiss")
        val response = api.getIncidents().execute()
        if (response.isSuccessful && response.body() != null){
            withContext(Dispatchers.Main){ view.updateUI(response.body()!!) }
        }
    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + supervisorJob + coroutineExceptionHandler
}