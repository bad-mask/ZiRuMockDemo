package com.badmask_zly.zirumockdemo.ui.minsu.fragment

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.ZiRuFragment
import com.badmask_zly.zirumockdemo.bean.MinSuContentItem
import com.badmask_zly.zirumockdemo.databinding.FragmentMinsuBinding
import com.badmask_zly.zirumockdemo.recyclerview.ZiRuRecyclerFragment
import com.badmask_zly.zirumockdemo.viewmodel.MinsuFragmentVM2

/**
 * Created by badmask_zly on 2017/7/13.
 */
class MinSuFragment : ZiRuFragment<MinsuFragmentVM2, FragmentMinsuBinding>() {
    override fun loadViewModel(): MinsuFragmentVM2 {
        return MinsuFragmentVM2()
    }

    override fun loadLayoutId(): Int {
        return R.layout.fragment_minsu
    }

    override fun initialize() = replaceFragmentByTag(R.id.fragment_minsu_recycler, ZiRuRecyclerFragment<List<MinSuContentItem>?>().setViewModel(viewModel), "minsu_fragment")
}