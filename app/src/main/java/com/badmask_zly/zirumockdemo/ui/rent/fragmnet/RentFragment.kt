package com.badmask_zly.zirumockdemo.ui.rent.fragmnet

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.ZiRuFragment
import com.badmask_zly.zirumockdemo.bean.RentContentItem
import com.badmask_zly.zirumockdemo.databinding.FragmentRentBinding
import com.badmask_zly.zirumockdemo.recyclerview.ZiRuRecyclerFragment
import com.badmask_zly.zirumockdemo.viewmodel.RentFragmentVM2


/**
 * Created by badmask_zly on 2017/7/13.
 */
class RentFragment : ZiRuFragment<RentFragmentVM2, FragmentRentBinding>() {


    override fun loadViewModel(): RentFragmentVM2 {
        return RentFragmentVM2()
    }

    override fun loadLayoutId(): Int {
        return R.layout.fragment_rent
    }


    override fun initialize() = replaceFragmentByTag(R.id.fragment_rent_recycler, ZiRuRecyclerFragment<RentContentItem>().setViewModel(viewModel), "minsu_fragment")


}


































