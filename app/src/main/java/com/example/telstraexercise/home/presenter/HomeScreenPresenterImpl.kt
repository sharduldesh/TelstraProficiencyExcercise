package com.example.telstraexercise.home.presenter

import android.content.Context
import android.widget.Toast
import com.android.volley.VolleyError
import com.example.telstraexercise.R
import com.example.telstraexercise.home.HomeContract
import com.example.telstraexercise.network_component.ListItemInterface
import com.example.telstraexercise.network_component.ApiCall
import com.example.telstraexercise.home.model.ListData
import com.example.telstraexercise.utility.Constant
import com.google.gson.GsonBuilder

class HomeScreenPresenterImpl(private val mApiCall: ApiCall) :
    HomeContract.HomeScreenPresenterImpl, ListItemInterface {
    private var mListItemView: HomeContract.HomeScreenViewInterface? = null
    private var mContext: Context? = null

    /** attaching view  **/
    override fun attachView(view: HomeContract.HomeScreenViewInterface) {
        mListItemView = view
        mContext = view.getActivityContext()
    }

    /** releasing view for reallocation case**/
    override fun detachView() {
        mListItemView = null
    }

    /** API call **/
    override fun getListItems() {
        mContext?.let {
            mApiCall.getListItem(
                it,
                Constant.URL,
                this
            )
        }
    }

    /** callback method for successful reponse  **/
    override fun onSuccess(response: String) {

        try{
            val gson = GsonBuilder().create()
            val listData = gson.fromJson(response, ListData::class.java)
            mListItemView?.showListDetails(listData)
        }catch (ex:Exception){
            Toast.makeText(mContext,mContext?.getString(R.string.response_error),Toast.LENGTH_SHORT)?.show()
        }
    }

    /** callback method for failure reponse **/
    override fun onFailure(volleyError: VolleyError) {
        mListItemView?.showError(volleyError)
    }
}