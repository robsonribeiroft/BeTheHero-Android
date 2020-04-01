package br.com.rrdev.bethehero.contract

import br.com.rrdev.bethehero.models.Incident

interface MainActivityContract {

    interface Presenter<in T>{
        fun attach(view: T)
    }

    interface View{
        fun updateUI(list: List<Incident>)
        fun updateUIonError(throwable: Throwable)
    }
}