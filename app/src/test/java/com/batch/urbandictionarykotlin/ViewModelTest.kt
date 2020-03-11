package com.batch.urbandictionarykotlin

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.batch.urbandictionarykotlin.dto.Definition
import com.batch.urbandictionarykotlin.dto.UrbanResponse
import com.batch.urbandictionarykotlin.repository.remote.UrbanService
import com.batch.urbandictionarykotlin.viewmodel.DefinitionViewModel
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.*


class ViewModelTest{

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    var apiClient: UrbanService? = null

    val myApp = Mockito.mock(Application::class.java)
    private var definitionItems: List<Definition>? = null
    private var viewModel: DefinitionViewModel? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        definitionItems = ArrayList()
        viewModel = DefinitionViewModel(myApp)
        viewModel?.getDefinitions()?.value
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }
    @Test
    fun testNull(){//testing response list for non-null
       `when`(apiClient?.getDefinitions("test")).thenReturn(null)
        assertNotNull(viewModel?.getDefinitions())
    }

    @Test
    fun testApiSuccess(){//test api call success
        `when`(apiClient?.getDefinitions("test")).thenReturn(Observable.just(UrbanResponse()))
        viewModel?.getDefinitionsObservable("test")
    }

    @Test
    fun testFetchDataError() {//test api error
        `when`(apiClient?.getDefinitions("test"))
            .thenReturn(Observable.error<UrbanResponse>(Throwable("/Api Error")))
        viewModel?.getDefinitionsObservable("test")
    }

    @After
    fun tearDown() {
        apiClient = null
        viewModel = null
    }
}