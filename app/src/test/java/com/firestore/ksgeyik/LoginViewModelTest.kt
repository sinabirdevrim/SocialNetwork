package com.firestore.ksgeyik

import androidx.lifecycle.Observer
import androidx.test.rule.ActivityTestRule
import com.firestore.android.repository.DataManager
import com.firestore.ksgeyik.presentation.login.LoginViewModel
import com.firestore.ksgeyik.presentation.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class LoginViewModelTest {

    @Mock
    lateinit var observer: Observer<Boolean>
    @Mock
    var dataManager: DataManager? = null
    var loginViewModel: LoginViewModel? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        loginViewModel = LoginViewModel(dataManager)
        loginViewModel?.liveData?.observeForever(observer)
    }

    @Test
    fun testApiFetchDataSuccess() {
        loginViewModel?.login("gurkan", "1234")
        Mockito.verify(observer)?.onChanged(true)
    }

}