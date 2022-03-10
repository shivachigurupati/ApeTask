package com.stc.apetask.presentation.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stc.apetask.data.models.factsResponse.FactsData
import com.stc.apetask.data.models.factsResponse.FactsDataResponse
import com.stc.apetask.domain.usecase.FactsDataUseCase
import com.stc.apetask.presentation.util.ApiState
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val factsDataUseCase: FactsDataUseCase
) :
    ViewModel() {

    private lateinit var disposable: Disposable
    //private val factsDataList = ArrayList<FactsData>()

    val apiStateLiveData = MutableLiveData<ApiState>()

    var factsDataLive = MutableLiveData<List<FactsData>>()

    //This method is used to call the Api service and get the data from server
    fun loadFactsData() {
        apiStateLiveData.postValue(ApiState.LOADING)
        val singleObservable = factsDataUseCase.executeFactsData()

        singleObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<FactsDataResponse> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onSuccess(factsDataResponse: FactsDataResponse) {
                    Log.i("FactsResponse==>", "${factsDataResponse.data}")
                    factsDataResponse.data.let {
                        it.let {
                            //factsDataList.clear()
                            //factsDataList.add(item)
                            /*for (item in it) {
                                if (!item.imageHref.isNullOrEmpty() &&
                                    !item.title.isNullOrEmpty() &&
                                    !item.description.isNullOrEmpty()
                                ) {
                                    factsDataList.add(item)
                                }
                            }*/
                            //factsDataLive.value = factsDataList
                            factsDataLive.value = it
                            apiStateLiveData.postValue(ApiState.SUCCESS)
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    apiStateLiveData.postValue(ApiState.FAILED)
                }
            })
    }


    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}