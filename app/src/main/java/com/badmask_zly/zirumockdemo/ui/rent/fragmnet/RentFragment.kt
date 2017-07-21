package com.badmask_zly.zirumockdemo.ui.rent.fragmnet

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.ZiRuFragment
import com.badmask_zly.zirumockdemo.databinding.FragmentRentBinding
import com.badmask_zly.zirumockdemo.viewmodel.RentFragmentVM


/**
 * Created by badmask_zly on 2017/7/13.
 */
class RentFragment : ZiRuFragment<RentFragmentVM, FragmentRentBinding>() {
    override fun loadViewModel(): RentFragmentVM {
        return RentFragmentVM()
    }

    override fun loadLayoutId(): Int {
        return R.layout.fragment_rent
    }


    override fun initialize() {
    }
}