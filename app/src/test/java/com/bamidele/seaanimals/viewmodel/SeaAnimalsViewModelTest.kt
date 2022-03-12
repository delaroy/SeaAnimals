package com.bamidele.seaanimals.viewmodel

import android.os.Looper
import com.bamidele.seaanimals.api.FakeApiService
import com.bamidele.seaanimals.data.network.ApiRepository
import com.bamidele.seaanimals.presentation.model.UIState
import com.bamidele.seaanimals.presentation.viewmodel.SeaAnimalsViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [25], application = HiltTestApplication::class)
@ExperimentalCoroutinesApi
@LooperMode(LooperMode.Mode.PAUSED)
class SeaAnimalsViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiRepository: ApiRepository

    @BindValue
    @JvmField
    val fakeApiService: FakeApiService = FakeApiService()

    @Mock
    private lateinit var seaAnimalsViewModel: SeaAnimalsViewModel

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    @Before
    fun setup() {
        hiltRule.inject()
        seaAnimalsViewModel = SeaAnimalsViewModel(apiRepository)
    }

    @Test
    fun `test SeaAnimals data success`() = runBlockingTest {
        scope.launch {
            seaAnimalsViewModel.loadSeaAnimals()
            Shadows.shadowOf(Looper.getMainLooper()).idle()
            val value = seaAnimalsViewModel.seaAnimalsFlow.value
            Assert.assertTrue(value is UIState.Success)
            Assert.assertNotNull(value.data)
            Assert.assertEquals("White Hake", value.data!![0].speciesName)
        }
    }

   @Test
    fun `test SeaAnimals data api failure`() = runBlockingTest {
        fakeApiService.failUserApi = true
        seaAnimalsViewModel.loadSeaAnimals()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = seaAnimalsViewModel.seaAnimalsFlow.value
        Assert.assertTrue(value is UIState.Error)
        Assert.assertNull(value.data)
    }

    @Test
    fun `test SeaAnimals wrong data`() = runBlockingTest {
        scope.launch {
            fakeApiService.wrongResponse = true
            seaAnimalsViewModel.loadSeaAnimals()
            Shadows.shadowOf(Looper.getMainLooper()).idle()
            val value = seaAnimalsViewModel.seaAnimalsFlow.value
            Assert.assertTrue(value is UIState.Success)
            Assert.assertNotNull(value.data)
            Assert.assertEquals("", value.data!![0].speciesName)
        }
    }

}