package com.aaron.raleytime.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aaron.raleytime.R
import com.aaron.raleytime.ui.common.composable.BottomNavigationBar
import com.aaron.raleytime.ui.common.composable.CommonAppDialog
import com.aaron.raleytime.ui.common.screenstate.BottomBarScreens
import com.aaron.raleytime.ui.screen.appraleytime.AppRaleyTimeScreen
import com.aaron.raleytime.ui.screen.appraleytime.AppRaleyTimeViewModel
import com.aaron.raleytime.ui.screen.appstt.AppSttScreen
import com.aaron.raleytime.ui.screen.appstt.AppSttScreenViewModel
import com.aaron.raleytime.ui.screen.apptts.AppTtsScreen
import com.aaron.raleytime.ui.screen.apptts.AppTtsScreenViewModel
import com.aaron.raleytime.utlits.constant.AppConstants

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    var currentScreen: String = AppScreens.AppRaleyTime.route
    backStackEntry?.destination?.route?.let {
        currentScreen = it
    }
    var showCoreDialog by rememberSaveable { mutableStateOf(false) }
    val statusBarHeightDp = with(LocalDensity.current) {
        WindowInsets.systemBars.getTop(this).toDp()
    }
    val navigationBarHeightDp = with(LocalDensity.current) {
        WindowInsets.navigationBars.getBottom(this).toDp()
    }
    val bottomNavigationItem = listOf(
        BottomBarScreens.RaleyTime,
        BottomBarScreens.Stt,
        BottomBarScreens.Tts,

        )
    if (showCoreDialog) {
        CommonAppDialog(
            showDialog = remember {
                mutableStateOf(showCoreDialog)
            },
            onConfirm = {
                showCoreDialog = false
            },
            onDismiss = {
                showCoreDialog = false
            },
            buttonLabel = stringResource(id = R.string.core_button_label),
            content = stringResource(id = R.string.core_body),
        )
    }
    Scaffold(
        modifier = Modifier
            .consumeWindowInsets(WindowInsets.systemBars)
            .background(Color.Blue)
            .padding(
                top = statusBarHeightDp,
                bottom = navigationBarHeightDp
            ),
        containerColor = Color.Blue,
        topBar = {
            CommonAppBar(
                currentScreen = getScreenTitle(currentScreen),

                ) {
                showCoreDialog = true
            }

        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                bottomNavigationItem = bottomNavigationItem

            )
        }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.AppRaleyTime.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppScreens.AppRaleyTime.route) {
                val viewModel = hiltViewModel<AppRaleyTimeViewModel>()
                val screenState = viewModel.screenState.collectAsStateWithLifecycle()
                AppRaleyTimeScreen(
                    screenState = screenState,
                    onEvent = viewModel::onAppRaleyTimeScreenEvents
                )

            }
            composable(AppScreens.AppStt.route) {
                val viewModel = hiltViewModel<AppSttScreenViewModel>()
                val screenState = viewModel.screenState.collectAsStateWithLifecycle()
                AppSttScreen(
                    screenState = screenState,
                    onEvent = viewModel::onAppSttScreenEvents
                )
            }
            composable(AppScreens.AppTts.route) {
                val viewModel = hiltViewModel<AppTtsScreenViewModel>()
                val screenState = viewModel.screenState.collectAsStateWithLifecycle()
                AppTtsScreen(
                    screenState = screenState,
                    onEvent = viewModel::onAppTtsScreenEvents
                )

            }

        }

    }
}

fun getScreenTitle(appRoute: String): String {
    var title = AppConstants.EMPTY_STRING
    when (appRoute) {
        AppScreenRout.RALEY_TIME -> title = AppScreenTitle.RALEY_TIME_TITLE
        AppScreenRout.STT -> title = AppScreenTitle.STT
        AppScreenRout.TTS -> title = AppScreenTitle.TTS
        else -> {}
    }
    return title
}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun CommonAppBar(
    currentScreen: String,
    onCoreClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray)
    ) {
        CenterAlignedTopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 0.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Blue,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White,
                navigationIconContentColor = Color.Unspecified,
                scrolledContainerColor = Color.Unspecified,
            ),
            title = {
                Row {
                    Text(
                        text = currentScreen,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            },
            navigationIcon = {
                Row(
                    modifier =
                        Modifier
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                            ) {
                                onCoreClick()
                            },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Core",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            },
            actions = {},
            scrollBehavior = null
        )
    }
}
