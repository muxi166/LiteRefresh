package com.androidpi.literefresh.sample.ui.fragment

import android.os.Bundle
import android.view.View
import com.androidpi.literefresh.sample.R
import com.androidpi.literefresh.sample.base.ui.BaseFragment
import com.androidpi.literefresh.sample.base.ui.BaseViewHolder
import com.androidpi.literefresh.sample.base.ui.RecyclerAdapter
import com.androidpi.literefresh.sample.base.ui.ViewBinder
import com.androidpi.literefresh.sample.databinding.FragmentVideoInfoBinding
import com.androidpi.literefresh.sample.databinding.VhVideoInfoHeaderBinding
import com.androidpi.literefresh.sample.databinding.VhVideoInfoRecommendBinding
import layoutbinder.annotations.BindLayout

class VideoInfoFragment : BaseFragment() {

    @BindLayout(R.layout.fragment_video_info)
    lateinit var binding: FragmentVideoInfoBinding

    lateinit var adapter: RecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecyclerAdapter()
        adapter.attachToRecyclerView(binding.recyclerView)
        adapter.register(VideoInfoHeaderViewHolder::class.java,
                VideoInfoRecommendViewHolder::class.java)
        adapter.addPayload(VideoInfoHeader())
        adapter.addPayload(VideoRecommend())
        adapter.addPayload(VideoRecommend())
    }
}

@ViewBinder(R.layout.vh_video_info_header, dataTypes = [VideoInfoHeader::class])
class VideoInfoHeaderViewHolder(itemView: View) : BaseViewHolder<VhVideoInfoHeaderBinding>(itemView) {

    override fun <T> onBind(data: T, position: Int) {
    }
}

@ViewBinder(R.layout.vh_video_info_recommend, dataTypes = [VideoRecommend::class])
class VideoInfoRecommendViewHolder(itemView: View) : BaseViewHolder<VhVideoInfoRecommendBinding>(itemView) {

    override fun <T> onBind(data: T, position: Int) {

    }
}

class VideoInfoHeader {

}

class VideoRecommend {

}

