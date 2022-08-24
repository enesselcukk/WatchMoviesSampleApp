package com.enesselcuk.watchmovies.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.enesselcuk.watchmovies.BuildConfig.IMAGE_BASE
import com.enesselcuk.watchmovies.BuildConfig.IMAGE_BASE_W500
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

fun <T> LifecycleOwner.collectLatest(flow: Flow<T>, actionsUiState: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                flow.collectLatest(actionsUiState)
            }
        }
    }
}

fun <T> LifecycleOwner.collect(flow: Flow<T>, actionsUiState: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                flow.collect {
                    actionsUiState.invoke(it)
                }
            }
        }
    }
}

@BindingAdapter("app:storeImage")
fun ImageView.setStoreUrl(imageIcon: String?) {
    Glide.with(context)
        .load(IMAGE_BASE + imageIcon)
        .fitCenter()
        .into(this)
}

@BindingAdapter("app:storeImagePage")
fun ImageView.setStoreUrlPage(imageIcon: String?) {
    Glide.with(context)
        .load(IMAGE_BASE_W500 + imageIcon)
        .fitCenter()
        .into(this)
}

@BindingAdapter("app:progressbar")
fun ProgressBar.setProgress(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:formatDate")
fun TextView.setDate(order_date: String? = null) {
    val dateParse = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
    val dateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())

    if (order_date != null) {
        val text = dateFormat.format(dateParse.parse(order_date))
        this.text = text
    } else {
        this.text = null
    }
}

