package be.bf.pawso.ui.home.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.bf.pawso.dal.DbHelper

class CatViewModelFactory(val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CatViewModel(DbHelper.instance(context).cats(), DbHelper.instance(context).shelters()) as T
    }
}