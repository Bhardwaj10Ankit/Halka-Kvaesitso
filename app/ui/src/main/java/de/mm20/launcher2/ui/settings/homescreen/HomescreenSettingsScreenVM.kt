package de.mm20.launcher2.ui.settings.homescreen

import android.content.Context
import android.content.Intent
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import de.mm20.launcher2.ktx.isAtLeastApiLevel
import de.mm20.launcher2.preferences.LauncherDataStore
import de.mm20.launcher2.preferences.Settings
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class HomescreenSettingsScreenVM(
    private val dataStore: LauncherDataStore,
) : ViewModel() {


    val dimWallpaper = dataStore.data.map { it.appearance.dimWallpaper }
    fun setDimWallpaper(dimWallpaper: Boolean) {
        viewModelScope.launch {
            dataStore.updateData {
                it.toBuilder()
                    .setAppearance(
                        it.appearance.toBuilder()
                            .setDimWallpaper(dimWallpaper)
                    ).build()
            }
        }
    }

    val blurWallpaper = dataStore.data.map { it.appearance.blurWallpaper }
    fun setBlurWallpaper(blurWallpaper: Boolean) {
        viewModelScope.launch {
            dataStore.updateData {
                it.toBuilder()
                    .setAppearance(
                        it.appearance.toBuilder()
                            .setBlurWallpaper(blurWallpaper)
                    ).build()
            }
        }
    }

    fun openWallpaperChooser(context: AppCompatActivity) {
        context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SET_WALLPAPER), null))
    }

    fun isBlurAvailable(context: Context): Boolean {
        if (!isAtLeastApiLevel(31)) return false
        return context.getSystemService<WindowManager>()?.isCrossWindowBlurEnabled == true
    }

    val statusBarIcons = dataStore.data.map { it.systemBars.statusBarColor }
    fun setLightStatusBar(statusBarColor: Settings.SystemBarsSettings.SystemBarColors) {
        viewModelScope.launch {
            dataStore.updateData {
                it.toBuilder()
                    .setSystemBars(
                        it.systemBars.toBuilder()
                            .setStatusBarColor(statusBarColor)
                    )
                    .build()
            }
        }
    }

    val navBarIcons = dataStore.data.map { it.systemBars.navBarColor }
    fun setLightNavBar(navBarColors: Settings.SystemBarsSettings.SystemBarColors) {
        viewModelScope.launch {
            dataStore.updateData {
                it.toBuilder()
                    .setSystemBars(
                        it.systemBars.toBuilder()
                            .setNavBarColor(navBarColors)
                    )
                    .build()
            }
        }
    }

    val hideStatusBar = dataStore.data.map { it.systemBars.hideStatusBar }
    fun setHideStatusBar(hideStatusBar: Boolean) {
        viewModelScope.launch {
            dataStore.updateData {
                it.toBuilder()
                    .setSystemBars(
                        it.systemBars.toBuilder()
                            .setHideStatusBar(hideStatusBar)
                    )
                    .build()
            }
        }
    }

    val hideNavBar = dataStore.data.map { it.systemBars.hideNavBar }
    fun setHideNavBar(hideNavBar: Boolean) {
        viewModelScope.launch {
            dataStore.updateData {
                it.toBuilder()
                    .setSystemBars(
                        it.systemBars.toBuilder()
                            .setHideNavBar(hideNavBar)
                    )
                    .build()
            }
        }
    }

    val searchBarColor = dataStore.data.map { it.searchBar.color }
    fun setSearchBarColor(color: Settings.SearchBarSettings.SearchBarColors) {
        viewModelScope.launch {
            dataStore.updateData {
                it.toBuilder()
                    .setSearchBar(
                        it.searchBar.toBuilder()
                            .setColor(color)
                    )
                    .build()
            }
        }
    }



    val searchBarStyle = dataStore.data.map { it.searchBar.searchBarStyle }
    fun setSearchBarStyle(searchBarStyle: Settings.SearchBarSettings.SearchBarStyle) {
        viewModelScope.launch {
            dataStore.updateData {
                it.toBuilder()
                    .setSearchBar(
                        it.searchBar.toBuilder()
                            .setSearchBarStyle(searchBarStyle)
                    )
                    .build()
            }
        }
    }

    val fixedSearchBar = dataStore.data.map { it.layout.fixedSearchBar }
    fun setFixedSearchBar(fixedSearchBar: Boolean) {
        viewModelScope.launch {
            dataStore.updateData {
                it.toBuilder()
                    .setLayout(it.layout.toBuilder().setFixedSearchBar(fixedSearchBar))
                    .build()
            }
        }
    }

    val bottomSearchBar = dataStore.data.map { it.layout.bottomSearchBar }
    fun setBottomSearchBar(bottomSearchBar: Boolean) {
        viewModelScope.launch {
            dataStore.updateData {
                it.toBuilder()
                    .setLayout(it.layout.toBuilder().setBottomSearchBar(bottomSearchBar))
                    .build()
            }
        }
    }

    val fixedRotation = dataStore.data.map { it.layout.fixedRotation }
    fun setFixedRotation(fixedRotation: Boolean) {
        viewModelScope.launch {
            dataStore.updateData {
                it.toBuilder()
                    .setLayout(it.layout.toBuilder().setFixedRotation(fixedRotation))
                    .build()
            }
        }
    }

    val widgetEditButton = dataStore.data.map { it.widgets.editButton }
    fun setWidgetEditButton(editButton: Boolean) {
        viewModelScope.launch {
            dataStore.updateData {
                it.toBuilder()
                    .setWidgets(
                        it.widgets.toBuilder()
                            .setEditButton(editButton)
                    )
                    .build()
            }
        }
    }


    companion object : KoinComponent {
        val Factory = viewModelFactory {
            initializer {
                HomescreenSettingsScreenVM(
                    dataStore = get()
                )
            }
        }
    }
}