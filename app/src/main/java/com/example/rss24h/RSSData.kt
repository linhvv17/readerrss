package com.example.rss24h

import android.icu.text.CaseMap
import java.io.FileDescriptor

data class RSSData(var title: String,
                   var pubDate:String,
                   var description: String,
                   var linkOP: String,
                   var img: String) {
}