package com.example.online_shop.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.online_shop.R
import com.example.online_shop.animations.LoadImageURLShow
import com.example.online_shop.data.api.dto.ProductDTO
import com.example.online_shop.data.api.dto.ProductDiscountDTO
import com.example.online_shop.databinding.ItemFlashStoreHomeBinding
import com.example.online_shop.databinding.ItemLatestHomeBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import javax.inject.Inject

class ListAdapter @Inject constructor( private val onClick: (item: Any) -> Unit):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var cont: Context

    private var items: List<Any> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(listItem: List<Any>) {
        items = listItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        cont = parent.context
        val viewHolder = when (viewType) {
            R.layout.item_latest_home -> LatestVH(
                ItemLatestHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            R.layout.item_flash_store_home -> FlashVH(
                ItemFlashStoreHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalArgumentException("Unsupported layout")
        }
        return viewHolder
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val formatSymbol = DecimalFormatSymbols()
        formatSymbol.decimalSeparator = ",".single()
        formatSymbol.groupingSeparator = " ".single()
        val animationCard = LoadImageURLShow()
        when (holder) {
            is LatestVH -> {
                if (item is ProductDTO){
                    val dec = DecimalFormat("$ #,###.000", formatSymbol)
                    holder.binding.tvNameGroup.text = item.category
                    holder.binding.tvNameProduct.text = item.name
                    holder.binding.tvPriceProduct.text = dec.format(item.price)
                    animationCard.setAnimation( holder.binding.poster, item.image_url, R.dimen.latest_card_radius)
                }
            }
            is FlashVH -> {
                if (item is ProductDiscountDTO){
                    val dec = DecimalFormat("$ #,###.00", formatSymbol)
                    holder.binding.tvNameGroup.text = item.category
                    holder.binding.tvNameProduct.text = item.name
                    holder.binding.tvPriceProduct.text = dec.format(item.price)
                    holder.binding.tvDiscount.text = item.discount.toString() +
                            cont.getString(R.string.off_string)
                    animationCard.setAnimation( holder.binding.poster, item.image_url, R.dimen.latest_card_radius)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[0]) {
            is ProductDTO -> R.layout.item_latest_home
            is ProductDiscountDTO -> R.layout.item_flash_store_home
            else -> throw IllegalArgumentException("Unsupported type") // in case populated with a model we don't know how to display.
        }
    }
}

class LatestVH(val binding: ItemLatestHomeBinding): RecyclerView.ViewHolder(binding.root)
class FlashVH(val binding: ItemFlashStoreHomeBinding): RecyclerView.ViewHolder(binding.root)