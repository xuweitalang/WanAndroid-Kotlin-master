package com.example.kotlindemo.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.kotlindemo.R
import com.example.kotlindemo.base.mvp.BaseMVPFragment
import com.example.kotlindemo.home.adapter.HomeRecyclerAdapter
import com.example.kotlindemo.home.bean.Article
import com.example.kotlindemo.home.bean.Banner
import com.example.kotlindemo.home.contract.HomeContract
import com.example.kotlindemo.home.presenter.HomePresenter
import com.example.kotlindemo.widget.LinearItemDecoration
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_home_header.*

class HomeFragment : BaseMVPFragment<HomeContract.View, HomePresenter>(), HomeContract.View {

    private lateinit var adapter: HomeRecyclerAdapter
    private var headView: View? = null
    private var mCurPage: Int = 0

    override fun createPresenter() = HomePresenter()

    override fun initView(rootView: View, savedInstanceState: Bundle?) {
        headView = layoutInflater.inflate(R.layout.layout_home_header, null, false)
    }

    override fun initData() {
        super.initData()
        val itemDecoration = LinearItemDecoration(mContext).color(
            ContextCompat.getColor(
                mContext,
                R.color.white_eaeaea
            )
        )
            .height(1f)
            .margin(15f, 15f)
            .jumpPositions(arrayOf(0))
        rv_home.addItemDecoration(itemDecoration)
        rv_home.layoutManager = LinearLayoutManager(mContext)
        adapter = HomeRecyclerAdapter(R.layout.item_home_recycler)
        rv_home.adapter = adapter
        adapter.addHeaderView(headView)

        presenter.getBanner()
        presenter.getArticles(mCurPage)
        setListener()
    }

    private fun setListener() {
        srl_home.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                presenter.getArticles(mCurPage)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                presenter.getArticles(0)
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_home
    override fun onBanner(list: List<Banner>?) {
        val urlList = mutableListOf<String>()
        if (list != null) {
            for (banner in list) {
                urlList.add(banner.imagePath)
            }
        }
        banner.setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                val bitmapTransform = RequestOptions.bitmapTransform(RoundedCorners(20))
                Glide.with(context!!).load(path).apply(bitmapTransform).into(imageView!!)
            }
        })
        banner.setImages(urlList).isAutoPlay(true).start()
    }

    override fun onArticles(page: Int, list: List<Article>?) {
        srl_home.finishLoadMore()
        srl_home.finishRefresh()
        mCurPage = page + 1
        adapter.setNewData(list)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

}
