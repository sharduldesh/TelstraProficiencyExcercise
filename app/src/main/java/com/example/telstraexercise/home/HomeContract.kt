package com.example.telstraexercise.home

import android.content.Context
import com.android.volley.VolleyError
import com.example.telstraexercise.network_component.ListItemInterface
import com.example.telstraexercise.home.model.ListData

class HomeContract {

    interface HomeScreenViewInterface {
        fun getActivityContext(): Context?
        fun showListDetails(listData: ListData)
        fun initPresenter()
        fun showError(volleyError: VolleyError)
    }

    interface HomeScreenPresenterImpl {
        fun attachView(view: HomeScreenViewInterface)
        fun detachView()
        fun getListItems()
    }

    interface HomeScreenServerCallInterface {
        fun getListItem(
            context: Context,
            url: String,
            mListItemListener: ListItemInterface
        )
    }
}