/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-mobile/blob/master/LICENSE.md
 */
package cmp.navigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import cmp.navigation.callHelpline
import cmp.navigation.mailHelpline
import cmp.navigation.ui.AppState
import org.mifos.mobile.core.model.enums.AccountType
import org.mifos.mobile.feature.about.navigation.aboutUsNavGraph
import org.mifos.mobile.feature.about.navigation.navigateToAboutUsScreen
import org.mifos.mobile.feature.accounts.navigation.accountsNavGraph
import org.mifos.mobile.feature.accounts.navigation.navigateToAccountsScreen
import org.mifos.mobile.feature.home.navigation.HomeDestinations
import org.mifos.mobile.feature.home.navigation.HomeNavigation
import org.mifos.mobile.feature.home.navigation.homeNavGraph
import org.mifos.mobile.feature.recent.transaction.navigation.navigateToRecentTransactionScreen
import org.mifos.mobile.feature.recent.transaction.navigation.recentTransactionNavGraph

@Composable
internal fun FeatureNavHost(
    appState: AppState,
    onClickLogout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        route = NavGraphRoute.MAIN_GRAPH,
        startDestination = HomeNavigation.HomeBase.route,
        navController = appState.navController,
        modifier = modifier,
    ) {
        homeNavGraph(
            onNavigate = { handleHomeNavigation(appState.navController, it, onClickLogout) },
            callHelpline = { callHelpline() },
            mailHelpline = { mailHelpline() },
        )

        accountsNavGraph(
            navController = appState.navController,
            navigateToLoanApplicationScreen = { },
            navigateToSavingsApplicationScreen = { },
            navigateToAccountDetail = { _, _ -> },
        )

        aboutUsNavGraph(navController = appState.navController, navigateToOssLicense = { })

        recentTransactionNavGraph(appState.navController)
    }
}

fun handleHomeNavigation(
    navController: NavHostController,
    homeDestinations: HomeDestinations,
    onClickLogout: () -> Unit,
) {
    when (homeDestinations) {
        HomeDestinations.LOGOUT -> onClickLogout.invoke()
        HomeDestinations.HOME -> Unit
        HomeDestinations.ACCOUNTS -> navController.navigateToAccountsScreen()
        HomeDestinations.LOAN_ACCOUNT -> navController.navigateToAccountsScreen(accountType = AccountType.LOAN)
        HomeDestinations.SAVINGS_ACCOUNT -> navController.navigateToAccountsScreen(accountType = AccountType.SAVINGS)
        HomeDestinations.RECENT_TRANSACTIONS -> navController.navigateToRecentTransactionScreen()
        HomeDestinations.CHARGES -> { }
        HomeDestinations.THIRD_PARTY_TRANSFER -> { }
        HomeDestinations.SETTINGS -> { }
        HomeDestinations.ABOUT_US -> navController.navigateToAboutUsScreen()
        HomeDestinations.HELP -> { }
        HomeDestinations.SHARE -> { }
        HomeDestinations.APP_INFO -> { }
        HomeDestinations.TRANSFER -> { }
        HomeDestinations.BENEFICIARIES -> { }
        HomeDestinations.SURVEY -> { }
        HomeDestinations.NOTIFICATIONS -> { }
        HomeDestinations.PROFILE -> { }
    }
}
