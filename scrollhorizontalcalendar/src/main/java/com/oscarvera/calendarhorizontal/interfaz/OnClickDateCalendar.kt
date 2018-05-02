package com.oscarvera.calendarhorizontal.interfaz

import java.util.*

/**
 * Created by oscarvera on 29/12/17.
 */
interface OnClickDateCalendar {
    fun onClickDate(date: Date, isInExtraRange : Boolean, isSelected : Boolean, isDayPast : Boolean)
}