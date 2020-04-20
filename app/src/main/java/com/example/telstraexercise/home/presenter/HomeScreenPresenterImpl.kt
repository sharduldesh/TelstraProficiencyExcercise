package com.example.telstraexercise.home.presenter

import android.content.Context
import android.util.Log
import com.android.volley.VolleyError
import com.example.telstraexercise.home.HomeContract
import com.example.telstraexercise.network_component.ListItemInterface
import com.example.telstraexercise.network_component.ApiCall
import com.example.telstraexercise.home.model.ListData
import com.example.telstraexercise.utility.Constant
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException

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
        val gson = GsonBuilder().create()
        try{
            val listData = gson.fromJson(response, ListData::class.java)
            mListItemView?.showListDetails(listData)
        }catch(parseExc: JsonParseException){
            Log.e("Json Parse Exception", "Json Parse Exception: $parseExc")
        }
        catch(syntaxExc: JsonSyntaxException){
            Log.e("Json Syntax Exception", "Json Syntax Exception: $syntaxExc")
        }
        catch (ex:Exception){
            Log.e("Exception", "Exception: $ex")
        }
    }

    /** callback method for failure reponse **/
    override fun onFailure(volleyError: VolleyError) {
        mListItemView?.showError(volleyError)
    }
}
