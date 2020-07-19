/*
 * Copyright 2018 yinpinjiu@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.androidpi.literefresh.sample.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.androidpi.literefresh.sample.LiteRefreshSamples.samples
import com.androidpi.literefresh.sample.R
import com.androidpi.literefresh.sample.base.ui.BaseFragment
import com.androidpi.literefresh.sample.databinding.FragmentLiteRefreshPagerBinding
import layoutbinder.annotations.BindLayout

class LiteRefreshPagerFragment : BaseFragment() {
    @BindLayout(R.layout.fragment_lite_refresh_pager)
    lateinit var binding : FragmentLiteRefreshPagerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.viewPager.adapter = LiteRefreshSamplePagerAdapter(childFragmentManager)
    }

    private inner class LiteRefreshSamplePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            return instantiate(context!!, samples[position].fragmentClass.name)
        }

        override fun getCount(): Int {
            return samples.size
        }
    }

    companion object {
        fun newInstance(): LiteRefreshPagerFragment {
            val args = Bundle()
            val fragment = LiteRefreshPagerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}