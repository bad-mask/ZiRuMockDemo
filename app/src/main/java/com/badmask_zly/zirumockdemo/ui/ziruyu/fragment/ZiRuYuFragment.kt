package com.badmask_zly.zirumockdemo.ui.ziruyu.fragment

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.ZiRuFragment
import com.badmask_zly.zirumockdemo.databinding.FragmentZiruyuBinding
import com.badmask_zly.zirumockdemo.viewmodel.ZiRuYuFragmentVM

/**
 * Created by badmask_zly on 2017/7/13.
 */
class ZiRuYuFragment : ZiRuFragment<ZiRuYuFragmentVM, FragmentZiruyuBinding>() {
    override fun loadViewModel(): ZiRuYuFragmentVM {
        return ZiRuYuFragmentVM()
    }

    override fun loadLayoutId(): Int {
        return R.layout.fragment_ziruyu
    }

    override fun initialize() {
        // TODO
    }
}