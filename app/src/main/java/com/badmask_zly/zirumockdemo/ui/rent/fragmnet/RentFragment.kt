package com.badmask_zly.zirumockdemo.ui.rent.fragmnet

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.ZiRuFragment
import com.badmask_zly.zirumockdemo.databinding.FragmentRentBinding
import com.badmask_zly.zirumockdemo.viewmodel.RentHomeVM


/**
 * Created by badmask_zly on 2017/7/13.
 */
class RentFragment : ZiRuFragment<RentHomeVM, FragmentRentBinding>() {


    override fun loadViewModel(): RentHomeVM {
        return RentHomeVM()
    }

    override fun loadLayoutId(): Int {
        return R.layout.fragment_rent
    }


    override fun initialize() {

    }

}


































