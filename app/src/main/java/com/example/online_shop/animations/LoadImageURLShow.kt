package com.example.online_shop.animations

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.online_shop.R
import javax.inject.Inject

class LoadImageURLShow @Inject constructor(){
    var animationCard = AnimationDrawable()

    @Inject lateinit var cont: Context
    @SuppressLint("ResourceAsColor")
    fun setAnimation(view: ImageView, imageURL: String?, roundedCorners:Int, centerCrop:Boolean = true){

        val radius = view.resources.getDimensionPixelOffset(roundedCorners)
        if (view.background != null) {
            animationCard = view.background as AnimationDrawable
            animationCard.apply {
                setEnterFadeDuration(1000)
                setExitFadeDuration(1000)
                start()
            }
        }
        if (imageURL == ""){
            view.setImageResource(R.drawable.ic_baseline_image_not_supported_24)
            animationCard.stop()
        } else if (imageURL != null){
            val option = if (!centerCrop) RequestOptions().transform(RoundedCorners(radius))
            else RequestOptions().transform(CenterCrop(), RoundedCorners(radius))
            Glide
                .with(view)
                .load(imageURL)
                .fallback(R.drawable.ic_baseline_image_not_supported_24)
                .apply(option)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed( resource: GlideException?, model: Any?,
                        target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        Toast.makeText(cont,"Error load image",Toast.LENGTH_SHORT).show()
                        return false
                    }
                    override fun onResourceReady(resource: Drawable?, model: Any?,
                        target: Target<Drawable>?, dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean): Boolean {
                        animationCard.stop()
                        view.background?.alpha = (0)
                        return false
                    }
                })
                .into(view)
        }
    }
}