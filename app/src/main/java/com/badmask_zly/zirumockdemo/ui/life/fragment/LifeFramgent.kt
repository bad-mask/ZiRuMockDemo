package com.badmask_zly.zirumockdemo.ui.life.fragment

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.ZiRuFragment
import com.badmask_zly.zirumockdemo.bean.MinSuAndLifeContentItem
import com.badmask_zly.zirumockdemo.databinding.FragmentLifeBinding
import com.badmask_zly.zirumockdemo.recyclerview.ZiRuRecyclerFragment
import com.badmask_zly.zirumockdemo.viewmodel.LifeFragmentVM

/**
 * Created by badmask_zly on 2017/7/13.
 */
class LifeFramgent : ZiRuFragment<LifeFragmentVM, FragmentLifeBinding>() {

    lateinit var mLifeFragmentVM: LifeFragmentVM

    lateinit var mZiRuRecyclerFragment: ZiRuRecyclerFragment<List<MinSuAndLifeContentItem>?>

    override fun loadViewModel(): LifeFragmentVM = mLifeFragmentVM

    override fun loadLayoutId(): Int {
        mLifeFragmentVM = LifeFragmentVM()
        return R.layout.fragment_life
    }

    override fun initialize() {

        mZiRuRecyclerFragment = ZiRuRecyclerFragment()
        replaceFragmentByTag(R.id.fragment_life_recycler, mZiRuRecyclerFragment.setViewModel(viewModel), "life_fragment")

    }

}