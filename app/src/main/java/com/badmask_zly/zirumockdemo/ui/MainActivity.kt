package com.badmask_zly.zirumockdemo.ui

import android.databinding.Observable
import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.ZiRuActivity
import com.badmask_zly.zirumockdemo.databinding.ActivityMainBinding
import com.badmask_zly.zirumockdemo.ui.life.fragment.LifeFramgent
import com.badmask_zly.zirumockdemo.ui.minsu.fragment.MinSuFragment
import com.badmask_zly.zirumockdemo.ui.rent.fragmnet.RentFragment
import com.badmask_zly.zirumockdemo.ui.ziruyu.fragment.ZiRuYuFragment
import com.badmask_zly.zirumockdemo.utils.LogUtil
import com.badmask_zly.zirumockdemo.viewmodel.HomeTabVm

class MainActivity : ZiRuActivity<HomeTabVm, ActivityMainBinding>() {

    companion object {
        val RENT = "rent"
        val ZIRUYU = "ziruyu"
        val MINSU = "minsu"
        val LIFE = "life"
    }

    lateinit var mRentFragment: RentFragment
    lateinit var mZiRuYuFramgent: ZiRuYuFragment
    lateinit var mMinSUFramgent: MinSuFragment
    lateinit var mLifeFramgent: LifeFramgent

    override fun initialize() {
        viewModel.selectedTab.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: Observable?, p1: Int) {
                switchTab()
            }
        })
        viewModel.selectedTab.set(RENT)
    }

    private fun switchTab() {

        viewDataBinding.mainMenuLayout.mainMenuRlRent.isSelected = false
        viewDataBinding.mainMenuLayout.mainMenuRlZiruyu.isSelected = false
        viewDataBinding.mainMenuLayout.mainMenuRlMinsu.isSelected = false
        viewDataBinding.mainMenuLayout.mainMenuRlLife.isSelected = false

        when (viewModel.selectedTab.get()) {
            RENT -> {
                viewDataBinding.mainMenuLayout.mainMenuRlRent.isSelected = true
                mRentFragment = RentFragment()
                replaceFragmentByTag(getFragmentLayoutId(), mRentFragment, RENT)
            }
            ZIRUYU -> {
                viewDataBinding.mainMenuLayout.mainMenuRlZiruyu.isSelected = true
                mZiRuYuFramgent = ZiRuYuFragment()
                replaceFragmentByTag(getFragmentLayoutId(), mZiRuYuFramgent, ZIRUYU)
            }
            MINSU -> {
                viewDataBinding.mainMenuLayout.mainMenuRlMinsu.isSelected = true
                mMinSUFramgent = MinSuFragment()
                replaceFragmentByTag(getFragmentLayoutId(), mMinSUFramgent, MINSU)
            }
            LIFE -> {
                viewDataBinding.mainMenuLayout.mainMenuRlLife.isSelected = true
                mLifeFramgent = LifeFramgent()
                replaceFragmentByTag(getFragmentLayoutId(), mLifeFramgent, LIFE)
            }
            else -> viewModel.selectedTab.set(MainActivity.RENT)

        }
    }


    override fun loadViewModel(): HomeTabVm {
        LogUtil.e("zly", "loadViewModel")
        return HomeTabVm()
    }

    override fun loadLayoutId(): Int {
        return R.layout.activity_main

    }

    fun getFragmentLayoutId(): Int {
        return R.id.main_activity_fl_fragment
    }


}
