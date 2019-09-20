package com.firestore.ksgeyik

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.firestore.android.repository.DataManager
import com.firestore.ksgeyik.presentation.login.LoginViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    lateinit var loginViewModel: LoginViewModel
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    lateinit var observer: Observer<Boolean>
    @Mock
    lateinit var dataManager: DataManager

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        loginViewModel = LoginViewModel(dataManager)

    }

    @Test
    fun testApiFetchDataSuccess() {
        loginViewModel?.liveData?.observeForever(observer)
        loginViewModel?.login("gurkan", "1234")
        verify(observer)?.onChanged(true)
    }

}