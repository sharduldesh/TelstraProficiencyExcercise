package com.example.telstraexercise.home.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.VolleyError
import com.example.telstraexercise.R
import com.example.telstraexercise.home.HomeContract
import com.example.telstraexercise.home.adapter.ListDataAdapter
import com.example.telstraexercise.network_component.ApiCall
import com.example.telstraexercise.home.model.ListData
import com.example.telstraexercise.home.presenter.HomeScreenPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_view.*

class HomeActivity : AppCompatActivity(), HomeContract.HomeScreenViewInterface {
    private lateinit var mMainScreenPresenter: HomeScreenPresenterImpl
    private var mListDataAdapter: ListDataAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPresenter()
        getListItem()
        //swiperefresh
        refreshContainer.setOnRefreshListener {
            errorView?.visibility = View.GONE
            listRecyclerView?.visibility = View.VISIBLE
            getListItem()
        }
    }

    override fun getActivityContext(): Context? {
        return applicationContext
    }

    override fun showListDetails(listData: ListData) {
        mListDataAdapter = ListDataAdapter(this, listData)
        supportActionBar?.title = listData.title
        listRecyclerView?.layoutManager =
            LinearLayoutManager(getActivityContext(), LinearLayoutManager.VERTICAL, false)
        listRecyclerView.adapter = mListDataAdapter
        refreshContainer.isRefreshing = false
        loading.visibility = View.GONE

    }

    /**attach presenter and initialise object*/
    override fun initPresenter() {
        val mApiCall = ApiCall()
        mMainScreenPresenter = HomeScreenPresenterImpl(mApiCall)
        mMainScreenPresenter.attachView(this)
    }

    /** GetList from server */
    fun getListItem() {
        mMainScreenPresenter.getListItems()
    }

    /** error message from server  */
    override fun showError(volleyError: VolleyError) {
        listRecyclerView.visibility = View.GONE
        loading.visibility = View.GONE
        errorView.visibility = View.VISIBLE
        if(volleyError.cause.toString().contains("UnknownHostException")){
            errorText.text = "Please, Check Internet Connection."
        }

        refreshContainer.isRefreshing = false
    }
}
