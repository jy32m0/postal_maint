package com.rayko.postalmaint.viewmodels

import androidx.lifecycle.ViewModel
import com.rayko.postalmaint.data.CallDao

class DetailViewModel(
    private val callDao: CallDao
) : ViewModel() {
}