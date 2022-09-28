package be.bf.pawso.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.bf.pawso.dal.daos.UserDAO
import be.bf.pawso.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val userDao : UserDAO) : ViewModel() {

    private val _selectedUser: MutableLiveData<User?> = MutableLiveData()
    val selectedUser: LiveData<User?>
        get() = _selectedUser

    var email : String? = null
    var userName : String? = null
    var description : String? = null
    var password : String? = null
    var birthdate : Long? = null

    fun addUser() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (userName != null && email != null && password != null && description != null && birthdate != null) {
                    val user = User(id = null, userName!!, email!!, password!!, birthdate!!, description!!)
                    val id = userDao.create(user)
                    user.id = id.toInt()
                    withContext(Dispatchers.Main) {
                        _selectedUser.value = user
                    }
                }
                else {
                    withContext(Dispatchers.Main) {
                        _selectedUser.value = null
                    }
                }
            }
        }
    }

    fun logUser(lEmail : String, lPassword : String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userDao.getUserByEmail(lEmail).collect {
                    if (it == null) {
                        withContext(Dispatchers.Main) {
                            _selectedUser.value = null
                        }
                    }
                    else {
                        withContext(Dispatchers.Main) {
                            if (lPassword != it.password) {
                                _selectedUser.value = null
                            }
                            else {
                                _selectedUser.value = it
                            }
                        }
                    }
                }
            }
        }
    }

    fun getUserByEmail(lEmail : String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userDao.getUserByEmail(lEmail).collect {
                    if (it == null) {
                        withContext(Dispatchers.Main) {
                            _selectedUser.value = null
                        }
                    }
                    else {
                        withContext(Dispatchers.Main) {
                            _selectedUser.value = it
                        }
                    }
                }
            }
        }
    }
}