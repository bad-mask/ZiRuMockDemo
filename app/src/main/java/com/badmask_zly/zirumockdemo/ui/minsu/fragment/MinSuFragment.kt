package com.badmask_zly.zirumockdemo.ui.minsu.fragment

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.ZiRuFragment
import com.badmask_zly.zirumockdemo.databinding.FragmentMinsuBinding
import com.badmask_zly.zirumockdemo.viewmodel.MinsuFragmentVM

/**
 * Created by badmask_zly on 2017/7/13.
 */
class MinSuFragment : ZiRuFragment<MinsuFragmentVM, FragmentMinsuBinding>() {
    override fun loadViewModel(): MinsuFragmentVM {
        return MinsuFragmentVM()
    }

    override fun loadLayoutId(): Int {
        return R.layout.fragment_minsu
    }

    override fun initialize() {
        // TODO
    }
}