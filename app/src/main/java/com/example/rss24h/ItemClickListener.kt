package com.example.rss24h

import android.view.View

interface ItemClickListener {
    fun onClick(
        view: View?,
        position: Int,
        isLongClick: Boolean
    )
}