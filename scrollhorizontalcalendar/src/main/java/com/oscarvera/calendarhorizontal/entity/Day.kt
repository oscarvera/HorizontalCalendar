package com.oscarvera.calendarhorizontal.entity

import java.util.*

/**
 * Created by oscarvera on 28/12/17.
 */
data class Day (val date: Date, var isSelected: Boolean = false, var isDone: Boolean, var isExtraRange: Boolean = false)