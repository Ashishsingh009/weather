package com.ashish.weather.viewmodel

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit

class MainViewModelTest {

    @Mock
    lateinit var retrofit:Retrofit



// @Rule
//    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
//
//    @Mock
//    ApiEndPoint apiEndPoint;
//    @Mock
//    NewsApiClient apiClient;
//    private NewsViewModel viewModel;
//    @Mock
//    Observer<NewsListViewState> observer;
//    @Mock
//    LifecycleOwner lifecycleOwner;
//    Lifecycle lifecycle;
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun getGetWeatherData() {
    }
}