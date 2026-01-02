/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-mobile/blob/master/LICENSE.md
 */
package org.mifos.mobile.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import template.core.base.designsystem.core.KptElevation
import template.core.base.designsystem.core.KptShapes
import template.core.base.designsystem.core.KptSpacing

/**
 * App-specific extensions for KptTheme to preserve all special DesignToken values
 * not natively available in KptTheme or KptSpacing/KptShapes/KptElevation.
 */

// --- Spacing Extensions ---
val KptSpacing.negativeDp7: Dp get() = (-7).dp
val KptSpacing.dp0: Dp get() = 0.dp
val KptSpacing.dp2: Dp get() = 2.dp
val KptSpacing.dp5: Dp get() = 5.dp
val KptSpacing.dp6: Dp get() = 6.dp
val KptSpacing.dp10: Dp get() = 10.dp
val KptSpacing.dp12: Dp get() = 12.dp
val KptSpacing.dp14: Dp get() = 14.dp
val KptSpacing.dp20: Dp get() = 20.dp
val KptSpacing.dp24: Dp get() = 24.dp
val KptSpacing.dp28: Dp get() = 28.dp
val KptSpacing.dp40: Dp get() = 40.dp
val KptSpacing.dp48: Dp get() = 48.dp
val KptSpacing.dp50: Dp get() = 50.dp
val KptSpacing.dp56: Dp get() = 56.dp
val KptSpacing.dp75: Dp get() = 75.dp
val KptSpacing.dp100: Dp get() = 100.dp

// --- Shapes Extensions ---
val KptShapes.dp2: Shape get() = RoundedCornerShape(2.dp)
val KptShapes.dp20: Shape get() = RoundedCornerShape(20.dp)
val KptShapes.dp25: Shape get() = RoundedCornerShape(25.dp)
val KptShapes.dp100: Shape get() = RoundedCornerShape(100.dp)
val KptShapes.topCornerDp24: Shape get() = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
val KptShapes.bottomCornerDp16: Shape get() = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
val KptShapes.circle: Shape get() = RoundedCornerShape(50)
val KptShapes.topCornerDp8: Shape get() = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
val KptShapes.topCornerDp16: Shape get() = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
val KptShapes.bottomCornerDp12: Shape get() = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)

// --- Elevation Extensions ---
val KptElevation.dp2: Dp get() = 2.dp
val KptElevation.dp6: Dp get() = 6.dp
val KptElevation.dp25: Dp get() = 25.dp
