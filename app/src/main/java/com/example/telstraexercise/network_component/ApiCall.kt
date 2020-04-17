package com.example.telstraexercise.network_component

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.telstraexercise.home.HomeContract
import java.lang.Exception

class ApiCall : HomeContract.HomeScreenServerCallInterface {

    /** @Method to fetch list data. api call */

    override fun getListItem(context: Context, url: String, mListItemListener: ListItemInterface) {
        try {
            val queue = Volley.newRequestQueue(context)
            val stringReq = StringRequest(Request.Method.GET, url,
                Response.Listener { response ->
                    mListItemListener.onSuccess(response)
                },
                Response.ErrorListener { error ->
                    mListItemListener.onFailure(error)
                })
            queue.add(stringReq)
        } catch (ex: Exception) {

        }
    }




}