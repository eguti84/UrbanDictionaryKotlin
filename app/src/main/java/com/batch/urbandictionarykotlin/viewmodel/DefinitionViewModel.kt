package com.batch.urbandictionarykotlin.viewmodel

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.batch.urbandictionarykotlin.dto.Definition
import com.batch.urbandictionarykotlin.dto.UrbanResponse
import com.batch.urbandictionarykotlin.repository.DefinitionRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DefinitionViewModel(application: Application) :
    AndroidViewModel(application) {
    private val definitions = MutableLiveData<List<Definition>>()
    private val showProgressBar = MutableLiveData<Boolean>()
    private val repo: DefinitionRepository = DefinitionRepository.instance
    private val disposable = CompositeDisposable()

    fun getDefinitionsObservable(term: String) {
        repo.getDefinitions(term)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<UrbanResponse> {
                override fun onSubscribe(d: Disposable) {
                    showProgressBar.value = true
                }

                override fun onNext(urbanResponse: UrbanResponse) {
                    definitions.value = urbanResponse.list
                }

                override fun onError(e: Throwable) {
                    Log.d(ContentValues.TAG, "onError: ")
                    Log.d(ContentValues.TAG, e.message!!)
                }

                override fun onComplete() {
                    showProgressBar.value = false
                }
            })
    }

    fun getDefinitions(): LiveData<List<Definition>> {
        return definitions
    }

    fun getShowProgressBar(): LiveData<Boolean> {
        return showProgressBar
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}