package com.example.omoshiro_app.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.omoshiro_app.model.DivinationRepository
import com.example.omoshiro_app.model.DivinationResult
import com.example.omoshiro_app.model.SoundRepository

class HomeViewModel : ViewModel() {

    private val _divinationResult = MutableLiveData<DivinationResult?>()
    val divinationResult: LiveData<DivinationResult?> = _divinationResult
    private val _divinationMainText = MutableLiveData<String?>()
    val divinationMainText: LiveData<String?> = _divinationMainText
    private val _cvName = MutableLiveData<String?>()
    val cvName: LiveData<String?> = _cvName
    private val _birthDay = MutableLiveData<String?>()
    val birthDay: LiveData<String?> = _birthDay
    private val _height = MutableLiveData<String?>()
    val height: LiveData<String?> = _height
    private val _hobby = MutableLiveData<String?>()
    val hobby: LiveData<String?> = _hobby
    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?> = _name
    private val _year = MutableLiveData<String?>()
    val year: LiveData<String?> = _year

    fun requestDivination(onSuccessAction: () -> Unit, onFailureAction: () -> Unit){
        DivinationRepository().requestData(onSuccess = { result ->
            _divinationResult.postValue(result)
            _divinationMainText.postValue(result?.text)
            _cvName.postValue(result?.cv)
            _birthDay.postValue(result?.birth_day)
            _height.postValue(result?.height)
            _hobby.postValue(result?.hobby)
            _name.postValue(result?.name)
            _year.postValue(result?.year)
            onSuccessAction.invoke()
        },
            onFailure = onFailureAction
        )
    }

    fun playSE(context: Context, id: Int){
        SoundRepository().playSE(context, id)
    }
}