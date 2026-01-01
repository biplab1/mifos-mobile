/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifos.mifospay.lint.designsystem

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UQualifiedReferenceExpression

/**
 * A detector that checks for incorrect usages of Compose Material APIs over equivalents in
 * the Now in Android design system module.
 */
class DesignSystemDetector : Detector(), Detector.UastScanner {

    override fun getApplicableUastTypes(): List<Class<out UElement>> = listOf(
        UCallExpression::class.java,
        UQualifiedReferenceExpression::class.java,
    )

    override fun createUastHandler(context: JavaContext): UElementHandler =
        object : UElementHandler() {
            override fun visitCallExpression(node: UCallExpression) {
                val name = node.methodName ?: return
                val preferredName = METHOD_NAMES[name] ?: return
                reportIssue(context, node, name, preferredName)
            }

            override fun visitQualifiedReferenceExpression(node: UQualifiedReferenceExpression) {
                val name = node.receiver.asRenderString()
                val preferredName = RECEIVER_NAMES[name] ?: return
                reportIssue(context, node, name, preferredName)
            }
        }

    companion object {
        @JvmField
        val ISSUE: Issue = Issue.create(
            id = "DesignSystem",
            briefDescription = "Design system",
            explanation = "This check highlights calls in code that use Compose Material " +
                "composables instead of equivalents from the Mifos design system " +
                "module.",
            category = Category.CUSTOM_LINT_CHECKS,
            priority = 7,
            severity = Severity.ERROR,
            implementation = Implementation(
                DesignSystemDetector::class.java,
                Scope.JAVA_FILE_SCOPE,
            ),
        )

        // Unfortunately :lint is a Java module and thus can't depend on the :core-designsystem
        // Android module, so we can't use composable function references (eg. ::Button.name)
        // instead of hardcoded names.
        val METHOD_NAMES = mapOf(
            "MaterialTheme" to "Mifos-mobileMobileTheme",
            "Button" to "Mifos-mobileButton",
            "OutlinedButton" to "Mifos-mobileOutlinedButton",
            "TextButton" to "Mifos-mobileTextButton",
            "FilterChip" to "Mifos-mobileFilterChip",
            "ElevatedFilterChip" to "Mifos-mobileFilterChip",
            "NavigationBar" to "Mifos-mobileNavigationBar",
            "NavigationBarItem" to "Mifos-mobileNavigationBarItem",
            "NavigationRail" to "Mifos-mobileNavigationRail",
            "NavigationRailItem" to "Mifos-mobileNavigationRailItem",
            "TabRow" to "Mifos-mobileTabRow",
            "Tab" to "Mifos-mobileTab",
            "IconToggleButton" to "Mifos-mobileIconToggleButton",
            "FilledIconToggleButton" to "Mifos-mobileIconToggleButton",
            "FilledTonalIconToggleButton" to "Mifos-mobileIconToggleButton",
            "OutlinedIconToggleButton" to "Mifos-mobileIconToggleButton",
            "CenterAlignedTopAppBar" to "Mifos-mobileTopAppBar",
            "SmallTopAppBar" to "Mifos-mobileTopAppBar",
            "MediumTopAppBar" to "Mifos-mobileTopAppBar",
            "LargeTopAppBar" to "Mifos-mobileTopAppBar",
        )
        val RECEIVER_NAMES = mapOf(
            "Icons" to "Mifos-mobileIcons",
        )

        fun reportIssue(
            context: JavaContext,
            node: UElement,
            name: String,
            preferredName: String,
        ) {
            context.report(
                ISSUE,
                node,
                context.getLocation(node),
                "Using $name instead of $preferredName",
            )
        }
    }
}
