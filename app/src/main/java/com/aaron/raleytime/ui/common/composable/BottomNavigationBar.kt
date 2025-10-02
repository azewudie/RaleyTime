package com.aaron.raleytime.ui.common.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.aaron.raleytime.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aaron.raleytime.ui.common.screenstate.BottomBarScreens
import com.aaron.raleytime.utlits.constant.AppConstants
import kotlin.collections.forEach
import kotlin.let

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    bottomNavigationItem: List<BottomBarScreens>
) {
    Column {
        NavigationBar(
            containerColor = Color.Blue,
        ) {
            val currentRoute = currentRoute(navController = navController)
            bottomNavigationItem.forEach { screen ->
                val isSelected = currentRoute?.let { screen.route.contains(it) }
                if (isSelected != null) {
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Transparent,
                            selectedTextColor = Color.Transparent,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color.Transparent,
                            disabledIconColor = Color.Transparent,
                            disabledTextColor = Color.Transparent,
                            unselectedTextColor = Color.Transparent,
                        ),
                        selected = isSelected,
                        onClick = {
                            if (!screen.route.contains(currentRoute)) {
                                navController.navigate(screen.route[0]) {
                                    launchSingleTop = true
                                    navController.graph.startDestinationRoute?.let {
                                        popUpTo(currentRoute) {
                                            saveState = true
                                        }
                                    }
                                }
                            }
                        },
                        icon = {
                            when (isSelected) {
                                true -> {
                                    when (screen.sectionName) {
                                        "Raley Time" -> {
                                            Icon(
                                                painter = painterResource(R.drawable.ic_raley_time),
                                                contentDescription = AppConstants.EMPTY_STRING,
                                                tint = Color(0xFF00bcd4),
                                            )
                                        }

                                        "STT" -> {
                                            Icon(
                                                painter = painterResource(R.drawable.ic_stt),
                                                contentDescription = AppConstants.EMPTY_STRING,
                                                tint = Color(0xFF00bcd4),
                                            )
                                        }

                                        "TTS" -> {
                                            Icon(
                                                painter = painterResource(R.drawable.ic_tts),
                                                contentDescription = AppConstants.EMPTY_STRING,
                                                tint = Color(0xFF00bcd4),
                                            )
                                        }
                                    }
                                }

                                else -> {
                                    when (screen.sectionName) {
                                        "Raley Time" -> {
                                            Icon(
                                                painter = painterResource(R.drawable.ic_raley_time),
                                                contentDescription = AppConstants.EMPTY_STRING,
                                                tint = Color.White,
                                            )
                                        }

                                        "STT" -> {
                                            Icon(
                                                painter = painterResource(R.drawable.ic_stt),
                                                contentDescription = AppConstants.EMPTY_STRING,
                                                tint = Color.White,
                                            )
                                        }

                                        "TTS" -> {
                                            Icon(
                                                painter = painterResource(R.drawable.ic_tts),
                                                contentDescription = AppConstants.EMPTY_STRING,
                                                tint = Color.White,
                                            )
                                        }
                                    }
                                }
                            }

                        },
                        label = {
                            ResponsiveText(
                                text = screen.sectionName,
                                color = when (isSelected) {
                                    true -> Color(0XFF00bcd4)
                                    else -> Color.White
                                },
                                textStyle = MaterialTheme.typography.bodyMedium
                            )
                        },
                    )
                }
            }

        }
    }

}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}