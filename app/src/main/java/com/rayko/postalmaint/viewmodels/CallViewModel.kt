package com.rayko.postalmaint.viewmodels

import androidx.lifecycle.ViewModel
import com.rayko.postalmaint.data.CallDao

class CallViewModel(
    private val callDao: CallDao
) : ViewModel() {
}