package com.mtsenov.breakingbad

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LiveDataViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MyViewModel

    @Before
    fun initViewModel() {
        viewModel = MyViewModel()
    }

    @Test
    fun getCharactersNotEmpty() {
        val cachedValue = viewModel.getCharacters().getOrAwaitValue()
        assertTrue(cachedValue.isNotEmpty())
    }

    @Test
    fun getCharactersSize() {
        val cachedValue = viewModel.getCharacters().getOrAwaitValue()
        assertTrue(cachedValue.size == 62)
    }

}