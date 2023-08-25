package com.rayko.postalmaint.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rayko.postalmaint.data.CallDao
import com.rayko.postalmaint.data.CallEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogViewModel(
    private val callDao: CallDao
) : ViewModel() {

    private var viewModelJob = Job()

    // cancel all coroutines when this viewModel is destroyed.
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _currentCall = MutableLiveData<CallEntity?>()
    val currentCall = _currentCall

    private var _allCalls = callDao.getAllFlow()
    val allCalls = _allCalls

    lateinit var logResult: List<CallEntity>

    init {
        initializeCurrentCall()
    }

    private fun initializeCurrentCall() {
        uiScope.launch {
            _currentCall.value = currentCallFromDatabase()
            Log.i("LogViewModel", "debugging: 42 - after currentCall null-check - " + currentCall.value)
        }
    }

    // If no currentCall, return null
    private suspend fun currentCallFromDatabase(): CallEntity? {
        return withContext(Dispatchers.IO) {
            var lastCall = callDao.getLastCall()
            if (lastCall?.clearTime != lastCall?.callTime) {
                lastCall = null
            }
            lastCall
        }
    }

    // New log upon entering the logFragment from Db or MiscEquipFragment
    fun onStartLogging(type: String, num: String) {
        uiScope.launch {
            val newCall = CallEntity()
            newCall.equipType = type
            newCall.equipNum = num.toInt()
            insert(newCall)
            _currentCall.value = currentCallFromDatabase()
        }
    }

    private suspend fun insert(newCall: CallEntity) {
        withContext(Dispatchers.IO) {
            callDao.insert(newCall)
            Log.i("LogViewModel", "debugging: 70 - newCall {$newCall}")
        }
    }

    fun getFullList(): Flow<List<CallEntity>> {
        uiScope.launch {
            _allCalls = callDao.getAllFlow()
        }
        Log.i("LogViewModel", "debugging: 78 - _allCalls {$_allCalls}")
        return allCalls
    }

}

// Instantiate this LogViewModelFactory object with LogViewModelFactory.create()
// to make LogViewModel lifecycle aware without the fragment having to handle it directly.
class LogViewModelFactory(
    private val callDao: CallDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LogViewModel(callDao) as T
        }
        throw IllegalArgumentException("LogViewModelFactory: Unknown ViewModel class")
    }
}