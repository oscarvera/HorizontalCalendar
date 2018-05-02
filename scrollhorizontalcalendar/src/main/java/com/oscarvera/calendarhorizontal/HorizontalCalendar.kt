package com.oscarvera.calendarhorizontal

import android.app.Activity
import android.support.v7.widget.GridLayoutManager
import com.oscarvera.calendarhorizontal.adapter.CalendarAdapter
import java.util.*
import android.R.attr.x
import android.content.Context
import android.graphics.Point
import android.support.annotation.ColorRes
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import com.oscarvera.calendarhorizontal.data.*
import com.oscarvera.calendarhorizontal.entity.Day
import com.oscarvera.calendarhorizontal.interfaz.OnClickDateCalendar
import kotlinx.android.synthetic.*
import kotlin.collections.ArrayList




/**
 * Created by oscarvera on 28/12/17.
 */
class HorizontalCalendar(build : Build){

    private var view : HorizontalCalendarView
    private val build  = build
    private var totalDays : Int? = null

    init {

        view = build.calendarView
        view.layoutManager = GridLayoutManager(build.context,1,GridLayoutManager.HORIZONTAL,false)

        //Division de la pantalla en x numero de items
        val size = Point()
        (build.context as Activity).windowManager.defaultDisplay.getSize(size)
        val screenWidth = size.x

        view.adapter = CalendarAdapter(
                getDays(null),
                screenWidth/build.daysInScreen,
                build.styleCalendar,
                { date: Date, b: Boolean, b1: Boolean, b2: Boolean ->

                    build.onClick?.let {
                        it.onClickDate(date,b,b1,b2)
                    }
                    view.adapter.notifyDataSetChanged()
                    //refreshAdapter(date)

                }
        )

        totalDays?.let {

            var midleDays = Math.floor(it/2.toDouble())
            Log.d("prueba","mitad days: "+midleDays)
            var positionCenter = midleDays

            when(build.gravityDaySelected){

                GRAVITY.RIGHT -> {
                    var rigthScreen = Math.floor((build.daysInScreen).toDouble())
                    Log.d("prueba","rigthScreen screen: "+rigthScreen)
                    positionCenter = midleDays - rigthScreen
                }

                GRAVITY.CENTER -> {
                    var midleScreen = Math.floor((build.daysInScreen/2).toDouble())
                    Log.d("prueba","mitad screen: "+midleScreen)
                    positionCenter = midleDays - midleScreen
                }
            }


            view.layoutManager.scrollToPosition(positionCenter.toInt()- 1)
        }


    }

    fun getDays(dateSelect: Date?): ArrayList<Day>{

        var rangeNum = Constants.DEFAULT_DAYS_RANGE //Por defecto son 30 dias de rango
        build.rangeMaxNum?.let {
            //Numero de dias totales
            rangeNum = it
        }

        val calendarStart = Calendar.getInstance()
        val calendarEnd = Calendar.getInstance()
        val calendarNow = Calendar.getInstance()

        dateSelect?.let {
            calendarNow.time = dateSelect
        }

        when (build.rangeMaxType){
            TIMEMEASURE.DAY -> {
                calendarStart.add(Calendar.DAY_OF_MONTH,rangeNum * -1)
                calendarEnd.add(Calendar.DAY_OF_MONTH,rangeNum)
            }
            TIMEMEASURE.MONTH ->{
                calendarStart.add(Calendar.MONTH,rangeNum * -1)
                calendarEnd.add(Calendar.MONTH,rangeNum)
            }
            TIMEMEASURE.YEAR -> {
                calendarStart.add(Calendar.YEAR,rangeNum * -1)
                calendarEnd.add(Calendar.YEAR,rangeNum)
            }
            else -> { //Esta nulo no ha elegido. Opcion por defecto
                calendarStart.add(Calendar.DAY_OF_MONTH,rangeNum * -1)
                calendarEnd.add(Calendar.DAY_OF_MONTH,rangeNum)
            }
        }

        var listDays = ArrayList<Day>()

        val difference = calendarEnd.time.time - calendarStart.time.time
        val daysBetween = (difference / (1000 * 60 * 60 * 24)).toFloat()

        Log.d("prueba","Dias diferencia: "+daysBetween)
        totalDays = daysBetween.toInt()

        var calendarRange : Calendar = calendarStart

        for (i in 0..totalDays!!) {


            calendarRange.add(Calendar.DAY_OF_MONTH,1)
            var isDone = false
            if (calendarRange.before(calendarNow)){
                isDone = true
            }


            //Comprobar si esta entre el rango extra
            var isExtraRange = false
            build.periodStart?.let {

                val start = Calendar.getInstance()
                start.time = it
                val end = Calendar.getInstance()
                end.time = build.periodEnd
                if (calendarRange.after(start)&&calendarRange.before(end)){
                    isExtraRange = true
                }

            }

            //Comprobar si esta seleccionado
            var isSelected = false
            build.selectedDays?.let {

                it.onEach {

                    var daySelected = Calendar.getInstance()
                    daySelected.time = it
                    if (calendarRange.isSameDay(daySelected)){
                        isSelected = true
                    }
                }

            }


            listDays.add(Day(calendarRange.time,isSelected,isDone,isExtraRange))
        }



        return listDays
    }


    class Build(internal var calendarView: HorizontalCalendarView, root : Context, style : BasicStyle){


        internal var context : Context = root
        internal var rangeMaxType : TIMEMEASURE? = null
        internal var gravityDaySelected : GRAVITY? = null
        internal var rangeMaxNum : Int? = null

        internal var daysInScreen : Int = Constants.DEFAULT_DAYS_IN_SCREEN

        internal var periodStart : Date? = null
        internal var periodEnd : Date? = null

        internal var selectedDays : ArrayList<Date>? = null

        internal var styleCalendar : StyleCalendar = StyleCalendar(style, null,null, null, null)

        internal var onClick : OnClickDateCalendar? = null

        fun colorNameDay(@ColorRes colorname : Int):Build{
            styleCalendar.colorNameText = colorname
            return this
        }

        fun colorText(styleText : BasicStyle) : Build{

            styleCalendar.textStyle = styleText
            return this

        }

        fun rangeMax(maxnum : Int,maxtype : TIMEMEASURE) : Build{

            this.rangeMaxNum = maxnum
            this.rangeMaxType = maxtype
            return this
        }

        fun periodSelected(periodStart : Date, periodEnd : Date, @ColorRes colorSelected : Int, @ColorRes textcolorSelected : Int) : Build{

            this.periodStart = periodStart
            this.periodEnd = periodEnd
            this.styleCalendar.selectedStyle = SelectedStyle(colorSelected, textcolorSelected)
            return this
        }

        fun selectedDays(listdays : ArrayList<Date>, @ColorRes colorSelected : Int): Build{
            this.selectedDays = listdays
            this.styleCalendar.daysSelectedStyle = DaysSelectedStyle(colorSelected)
            return this
        }

        fun daysInScreen(numDays : Int) : Build{
            this.daysInScreen = numDays
            return this
        }

        fun onClickDay(listener : OnClickDateCalendar) : Build{
            this.onClick = listener
            return this

        }

        fun setGravityDaySelected(gravity : GRAVITY):Build{
            this.gravityDaySelected = gravity
            return this
        }

        fun build(): HorizontalCalendar{
            return HorizontalCalendar(this)
        }



    }

    enum class TIMEMEASURE{
        DAY,MONTH,YEAR
    }
    enum class GRAVITY{
        LEFT,CENTER,RIGHT
    }


}

