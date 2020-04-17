package com.example.telstraexercise.network_component

import com.android.volley.VolleyError
/** implement by MainScreenPresenter to return success or failure **/

    interface ListItemInterface {
        fun onSuccess(response: String)
        fun onFailure(volleyError: VolleyError)
    }
