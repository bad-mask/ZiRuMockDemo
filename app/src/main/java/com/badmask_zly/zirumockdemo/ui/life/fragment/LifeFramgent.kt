package com.badmask_zly.zirumockdemo.ui.life.fragment

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.ZiRuFragment
import com.badmask_zly.zirumockdemo.databinding.FragmentLifeBinding
import com.badmask_zly.zirumockdemo.viewmodel.LifeFragmentVM

/**
 * Created by badmask_zly on 2017/7/13.
 */
class LifeFramgent : ZiRuFragment<LifeFragmentVM, FragmentLifeBinding>() {
    override fun loadViewModel(): LifeFragmentVM {
        return LifeFragmentVM()
    }

    override fun loadLayoutId(): Int {
        return R.layout.fragment_life
    }

    override fun initialize() {
        // TODO
    }
}