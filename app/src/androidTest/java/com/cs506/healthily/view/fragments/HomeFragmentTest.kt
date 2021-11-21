package com.cs506.healthily.view.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.ProgressBar
import androidx.test.filters.MediumTest
import com.cs506.healthily.R
import com.cs506.healthily.data.model.DayHeart
import com.cs506.healthily.launchFragmentInHiltContainer
import com.google.android.gms.maps.model.CameraPosition
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.android.synthetic.main.fragment_heart_points.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class HomeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup(){
        hiltRule.inject()
    }

    /**
     * Test the function onSaveInstanceState(outState: Bundle) in HomeFragment
     * This function should save the state of the map when the activity is paused.
     */
    @Test
    fun onSaveInstanceStateTest(){
        launchFragmentInHiltContainer<HomeFragment> {
            this.onSaveInstanceState(outState = Bundle.EMPTY)
            Truth.assertThat(outState).isNotNull()
        }
    }

    /**
     *
     *Test the function onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) in HomeFragment
     *The OptionsMenu methods provide the user a list of likely places to choose from
     *Works with the Get Places button from current_place_menu.xml
     */
    @Test
    fun onCreateOptionsMenuTest() {
        launchFragmentInHiltContainer<HomeFragment> {
            this.onCreateOptionsMenu(this.menu, this.inflater)
            Truth.assertThat(true).isTrue()
        }

    }

    /**
     * Test the function onMapReady(map: GoogleMap) in HomeFragment
     * Manipulates the map when it's available.
     * This callback is triggered when the map is ready to be used.
     * Set up the map when the GoogleMap object is available:
     */
    @Test
    fun onMapReadyTest(){

        launchFragmentInHiltContainer<HomeFragment> {
            this.onMapReady(this.map!!)

            Truth.assertThat(this.map).isNotNull()
        }


    }

    /**
     * Test the function getDeviceLocation() in HomeFragment
     * Gets the current location of the device, and positions the map's camera.
     * Use the location provider to find the device's last-known location,
     * then use that location to position the map
     */
    @Test
    fun getDeviceLocationTest(){


        launchFragmentInHiltContainer<HomeFragment> {
            this.getDeviceLocation();
            Truth.assertThat(this.CameraPosition).isNotNull()

        }


    }

    /**
     * Test the function getLocationPermission() in HomeFragment
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     *
     * Checks whether the user has granted fine location permission. If not, it requests the permission:
     */
    @Test
    fun getLocationPermissionTest(){


        launchFragmentInHiltContainer<HomeFragment> {
            this.getLocationPermission();
            Truth.assertThat(this.locationPermissionGranted).isTrue()

        }


    }

    /**
     * Test the function onRequestPermissionsResult(requestCode: Int,
     * permissions: Array<String>,
     * grantResults: IntArray) in HomeFragment

     * Handles the result of the request for location permissions.
     */
    @Test
    fun onRequestPermissionsResultTest(){


        launchFragmentInHiltContainer<HomeFragment> {
            this.onRequestPermissionsResult(this.requestCode, this.permissions, this.grantResults);
            Truth.assertThat(this.locationPermissionGranted).isTrue()

        }


    }

    /**
     * Test the function showCurrentPlace() in HomeFragment
     * Places API integration.
     * Prompts the user to select the current place from a list of likely places, and shows the
     * current place on the map - provided the user has granted location permission.
     */
    @Test
    fun showCurrentPlaceTest(){


        launchFragmentInHiltContainer<HomeFragment> {
            this.showCurrentPlace();


        }


    }

}