package com.example.rss24h

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rss24h.databinding.ActivityMainBinding
import org.w3c.dom.Element
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory


class MainActivity : AppCompatActivity() {
    //khai bao list data
    private var rssData = mutableListOf<RSSData>()
//    private lateinit var rcRSS: RecyclerView
    private lateinit var binding: ActivityMainBinding // truy cập rcRss bằng binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        createAsy()
        //truy cập rcRSS bằng binding
        binding.rcRSS.layoutManager= LinearLayoutManager(this)
        binding.rcRSS.adapter = RSSAdapter(rssData, this)
    }

    private fun createAsy(){
        val asy = object : AsyncTask<Void, Void, Void>(){
            override fun doInBackground(vararg params: Void?): Void? {
                readData()
                return null
            }
            override fun onPostExecute(result: Void?) {
                binding.rcRSS.adapter?.notifyDataSetChanged()
            }
        }
        asy.execute()
    }
    private fun readData(){
        val link = "https://cdn.24h.com.vn/upload/rss/trangchu24h.rss"
        //chuyển link thành inputstrem
        val inP = URL(link).openConnection().getInputStream()
        val builderFactory = DocumentBuilderFactory.newInstance()
        val docBuilder = builderFactory.newDocumentBuilder()
        val doc = docBuilder.parse(inP)
        val listNot = doc.getElementsByTagName("item")
        for (i in 0..listNot.length - 1){
            val node = listNot.item(i) as Element
            val title = node.getElementsByTagName("title").item(0).textContent
            val pubDate = node.getElementsByTagName("pubDate").item(0).textContent
            val description = node.getElementsByTagName("description").item(0).textContent
            val linkOP = node.getElementsByTagName("link").item(0).textContent
            val img = ((node.getElementsByTagName("description").item(0) as Element)
                    .getElementsByTagName("img").item(0)
                    as Element).getAttribute("src")
            rssData.add(
                    RSSData(title,pubDate,description,linkOP,img)
            )
        }
    }
}
