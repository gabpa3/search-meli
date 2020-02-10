package com.gabcode.search_meli.ui.util

interface ItemListener<in T>  {
    fun onItemClick(data: T)
}