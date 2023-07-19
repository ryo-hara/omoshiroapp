package com.example.omoshiro_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.omoshiro_app.model.DivinationRepository
import com.example.omoshiro_app.model.DivinationResult

class HomeViewModel : ViewModel() {

    private val _divinationResult = MutableLiveData<DivinationResult?>()
    val divinationResult: LiveData<DivinationResult?> = _divinationResult
    private val _divinationMainText = MutableLiveData<String?>()
    val divinationMainText: LiveData<String?> = _divinationMainText

    fun requestDivination(onSuccessAction: () -> Unit, onFailureAction: () -> Unit){
        DivinationRepository().requestData(onSuccess = { result ->
            _divinationResult.postValue(result)
            _divinationMainText.postValue(result?.text)
            onSuccessAction.invoke()
        },
            onFailure = onFailureAction
        )
    }
}