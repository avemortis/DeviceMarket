package com.vtorushin.homescreen.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.lifecycleScope
import com.vtorushin.categories.ui.recyclerview.CategoriesAdapter
import com.vtorushin.categories.ui.recyclerview.CategoriesViewHolder
import com.vtorushin.homescreen.databinding.FragmentHomeBinding
import com.vtorushin.homescreen.di.component
import com.vtorushin.hotsales.models.HotSalesItem
import com.vtorushin.hotsales.ui.HotSalesPagerAdapter
import com.vtorushin.market.data.MarketItem
import com.vtorushin.market.ui.MarketAdapter
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    private val hotSalesAdapter = HotSalesPagerAdapter(listOf())
    private val marketAdapter = MarketAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = component().viewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.hotSalesViewpager.adapter = hotSalesAdapter
        binding.bestSellersList.adapter = marketAdapter
        subscribe()
        prepareCategoriesAdapter()
        return binding.root
    }

    private fun subscribe() {
        lifecycleScope.launch {
            viewModel.homeStore.collect {
                prepareHotSalesAdapter(it)
            }
        }
        lifecycleScope.launch {
            viewModel.market.collect {
                prepareMarketAdapter(it)
            }
        }
    }

    private fun prepareHotSalesAdapter(list: List<HotSalesItem>) {
        hotSalesAdapter.items = list
        hotSalesAdapter.notifyItemRangeInserted(0, list.size)
    }

    private fun prepareMarketAdapter(list: List<MarketItem>) {
        marketAdapter.items = list
        marketAdapter.notifyItemRangeInserted(0, list.size)
    }

    private fun prepareCategoriesAdapter() {
        val menu = PopupMenu(requireContext(), null)
        requireActivity().menuInflater.inflate(
            com.vtorushin.categories.R.menu.categories_menu,
            menu.menu
        )
        binding.categories.adapter = CategoriesAdapter(menu.menu) { holder, position ->
            holder.itemView.setOnClickListener {
                if (position != viewModel.active) {
                    holder.setCheck()
                    (binding.categories.findViewHolderForAdapterPosition(viewModel.active) as CategoriesViewHolder).setUncheck()
                    viewModel.active = position
                }
            }
            if (position == viewModel.active)
                holder.setCheck()
        }
    }
}