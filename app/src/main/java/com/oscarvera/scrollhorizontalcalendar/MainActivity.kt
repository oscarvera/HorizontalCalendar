package com.oscarvera.scrollhorizontalcalendar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.oscarvera.calendarhorizontal.HorizontalCalendar
import com.oscarvera.calendarhorizontal.data.BasicStyle
import com.oscarvera.calendarhorizontal.interfaz.OnClickDateCalendar
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), OnClickDateCalendar {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cal1 = Calendar.getInstance()
        var cal2 = Calendar.getInstance()
        var cal3 = Calendar.getInstance()
        cal2.add(Calendar.DAY_OF_YEAR,4)
        cal1.add(Calendar.DAY_OF_YEAR,2)
        cal3.add(Calendar.DAY_OF_YEAR,-2)
        var daysSelected = ArrayList<Date>()
        daysSelected.add(cal1.time)
        daysSelected.add(cal3.time)



        HorizontalCalendar.Build(findViewById(R.id.calendarview),this, BasicStyle(R.color.grey1,R.color.grey2,R.color.red)).colorText(BasicStyle(R.color.white,R.color.white,R.color.white)).rangeMax(7,HorizontalCalendar.TIMEMEASURE.DAY).daysInScreen(7)
                .onClickDay(this).setGravityDaySelected(HorizontalCalendar.GRAVITY.CENTER).periodSelected(cal1.time,cal2.time,R.color.green,R.color.black).selectedDays(daysSelected,R.color.orange).build()
    }

    override fun onClickDate(date: Date, isInExtraRange: Boolean, isSelected: Boolean, isDayPast : Boolean) {

    }
}
