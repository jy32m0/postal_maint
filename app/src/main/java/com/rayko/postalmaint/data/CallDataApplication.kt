package com.rayko.postalmaint.data

import android.app.Application

class CallDataApplication : Application() {
    val database: CallDatabase by lazy { CallDatabase.getInstance(this) }
}