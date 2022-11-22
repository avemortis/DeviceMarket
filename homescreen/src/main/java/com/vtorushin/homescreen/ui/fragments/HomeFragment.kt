package com.vtorushin.homescreen.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.lifecycleScope
import com.vtorushin.categories.ui.recyclerview.CategoriesAdapter
import com.vtorushin.categories.usecases.SwitchActiveCategoryUseCase
import com.vtorushin.core.models.Status
import com.vtorushin.homescreen.databinding.FragmentHomeBinding
import com.vtorushin.homescreen.di.component
import com.vtorushin.hotsales.models.HotSalesItem
import com.vtorushin.hotsales.ui.HotSalesPagerAdapter
import com.vtorushin.market.entities.MarketItem
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
        binding.scrollContent.hotSalesViewpager.adapter = hotSalesAdapter
        binding.scrollContent.bestSellersList.adapter = marketAdapter
        subscribe()
        prepareCategoriesAdapter()
        return binding.root
    }

    private fun subscribe() {
        lifecycleScope.launch {
            viewModel.dataSource.collect {
                when (it.status) {
                    Status.LOADING -> {}
                    Status.SUCCESSES -> {
                        prepareHotSalesAdapter(it.data?.homeStore!!)
                        prepareMarketAdapter(it.data?.bestSeller!!)
                    }
                    Status.ERROR -> {}
                }
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
                SwitchActiveCategoryUseCase().invoke(binding.categories, viewModel.active, position)
                viewModel.active = position
            }
            if (position == viewModel.active)
                holder.setCheck()
        }
    }
}