package com.oscarvera.calendarhorizontal.data

import android.support.annotation.ColorRes

/**
 * Created by oscarvera on 29/12/17.
 */

data class StyleCalendar( val basicStyle: BasicStyle, var textStyle: BasicStyle?, var selectedStyle: SelectedStyle?, var daysSelectedStyle: DaysSelectedStyle?, @ColorRes var colorNameText : Int?)

data class BasicStyle(@ColorRes val colorDaysBefore : Int, @ColorRes val colorDaysAfter : Int, @ColorRes val colorDayToday : Int )

data class SelectedStyle(@ColorRes val colorRange : Int, @ColorRes val textcolorRange : Int )

data class DaysSelectedStyle(@ColorRes val colorDot : Int)