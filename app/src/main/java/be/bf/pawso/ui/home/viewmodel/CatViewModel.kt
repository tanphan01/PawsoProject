package be.bf.pawso.ui.home.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.bf.pawso.api.CatApi
import be.bf.pawso.api.RetrofitClient
import be.bf.pawso.dal.daos.CatDAO
import be.bf.pawso.dal.daos.ShelterDAO
import be.bf.pawso.models.Cat
import be.bf.pawso.models.CatWithShelter
import be.bf.pawso.models.Shelter
import be.bf.pawso.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatViewModel(private val catDao : CatDAO, private val shelterDao : ShelterDAO) : ViewModel() {

    private val _cats = mutableListOf<Cat>()
    private val cats = MutableLiveData<List<Cat>>()
    val Cats : LiveData<List<Cat>>
        get () = cats

    private val _userCats = mutableListOf<CatWithShelter>()
    private val userCats = MutableLiveData<List<CatWithShelter>>()
    val UserCats : LiveData<List<CatWithShelter>>
        get () = userCats

    private val user : MutableLiveData<User> = MutableLiveData()
    val User : LiveData<User>
        get() = user

    private lateinit var api : CatApi

    init {
        getCat()
    }

    fun getCat() {
        viewModelScope.launch {

            if (!this@CatViewModel::api.isInitialized) {
                api = RetrofitClient.getClient().create(CatApi::class.java)
            }

            api.cats().enqueue(object : Callback<List<Cat>> {
                override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                    if (response.isSuccessful) {
                        response.body()?.let { it ->
                            _cats.addAll(it)
                            cats.value = _cats
                        }
                    }
                }

                override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                    Log.d("VM", t.message.toString())
                }

            })
        }
    }

    fun deleteFirstCat() {
        _cats.removeAt(0)
        cats.value = _cats
    }

    fun addToFavs(cat: Cat,context: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.d("VM", user.value?.id.toString())
                cat.userId = user.value?.id
                Log.d("VM", cat.toString())
                catDao.create(cat)
                addShelter(cat.shelterId)
            }

        }

    }

    private fun addShelter(shelterId: String?) {
        if (!this@CatViewModel::api.isInitialized) {
            api = RetrofitClient.getClient().create(CatApi::class.java)
        }

        if (shelterId != null) {
            api.shelterById(shelterId).enqueue(object : Callback<Shelter> {
                override fun onResponse(call: Call<Shelter>, response: Response<Shelter>) {
                    if (response.isSuccessful) {
                        response.body()?.let { it ->
                            viewModelScope.launch(Dispatchers.IO) {
                                shelterDao.create(it)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<Shelter>, t: Throwable) {
                    Log.d("VMshelter", t.message.toString())
                }
            })
        }
    }

    fun setUser(user : User) {
        this.user.value = user
        Log.d("VM", this.user.value.toString())
    }

    fun getUserCat() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                user.value?.id?.let {
                    catDao.getCatsByUser(it).collect() { catsWithShelter ->
                        Log.d("VM", catsWithShelter.toString())
                        val listOfCatWithShelter = mutableListOf<CatWithShelter>()
                        for (elem in catsWithShelter) {
                            val catWithShelter = CatWithShelter(
                                cat = elem.key,
                                shelter = elem.value)
                            listOfCatWithShelter.add(catWithShelter)
                        }
                        withContext(Dispatchers.Main) {
                            _userCats.clear()
                            _userCats.addAll(listOfCatWithShelter)
                            userCats.value = _userCats
                        }
                    }
                }
            }
        }
    }
}