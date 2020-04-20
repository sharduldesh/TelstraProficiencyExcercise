package com.example.telstraexercise.network_component

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.telstraexercise.home.HomeContract
import java.io.UnsupportedEncodingException
import java.lang.Exception

class ApiCall : HomeContract.HomeScreenServerCallInterface {

    /** api call for fetch list data.*/

    override fun getListItem(context: Context, url: String, mListItemListener: ListItemInterface) {
        try {
            val queue = Volley.newRequestQueue(context)
            val stringReq = StringRequest(Request.Method.GET, url,
                Response.Listener { response ->
                    mListItemListener.onSuccess(response)
                },
                Response.ErrorListener {
                        error -> mListItemListener.onFailure(error)
                })
            queue.add(stringReq)
        }   catch (e: UnsupportedEncodingException) {
            Log.e("Encoding Exception", "Unsupported Encoding Exception: $e")
            }
        catch (ex:Exception){
            Log.e("Exception", "Exception: $ex")
        }
        }
    }
