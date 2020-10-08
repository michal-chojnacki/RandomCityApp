package com.github.michalchojnacki.randomcityapp.ui.utils

import androidx.recyclerview.widget.RecyclerView

inline fun <reified T : RecyclerView.Adapter<*>> RecyclerView.getLazyAdapter(initializer: () -> T): T =
    this.adapter as? T ?: initializer().apply { adapter = this }
