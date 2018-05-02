package com.oscarvera.calendarhorizontal.adapter

import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.oscarvera.calendarhorizontal.R
import com.oscarvera.calendarhorizontal.data.StyleCalendar
import com.oscarvera.calendarhorizontal.entity.Day
import com.oscarvera.calendarhorizontal.inflate
import com.oscarvera.calendarhorizontal.isSameDay
import kotlinx.android.synthetic.main.item_calendar_day.view.*
import java.util.*


/**
 * Created by oscarvera on 28/12/17.
 */
class CalendarAdapter(var days: ArrayList<Day>, var width: Int, style: StyleCalendar, val onclickDate: (date: Date, isExtraRange: Boolean, isSelected: Boolean, isDayPast: Boolean) -> Unit) : RecyclerView.Adapter<CalendarAdapter.ViewHolderDay>() {

    val styleBasic = style
    var daySelected = Calendar.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarAdapter.ViewHolderDay = ViewHolderDay(parent.inflate(R.layout.item_calendar_day), styleBasic, onclickDate, daySelected, { date: Date ->
        daySelected.time = date
    })


    override fun onBindViewHolder(holder: CalendarAdapter.ViewHolderDay, position: Int) = holder.bind(days[position], width)


    override fun getItemCount(): Int = days.size


    class ViewHolderDay(itemView: View, style: StyleCalendar, val onclickdate: (date: Date, isExtraRange: Boolean, isSelected: Boolean, isDayPast: Boolean) -> Unit, val daySelected: Calendar, changeDay: (Date) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val style = style
        private val onclick = onclickdate
        val changeDay = changeDay

        fun bind(day: Day, width: Int) = with(itemView) {
            itemView.minimumWidth = width

            var calendar = Calendar.getInstance()
            calendar.time = day.date

            val dayofweek = calendar[Calendar.DAY_OF_WEEK]
            txtDay.text = LETTERDAY.values()[dayofweek - 1].toString()
            style.colorNameText?.let {
                txtDay.setTextColor(ContextCompat.getColor(context,it))
            }

            txtNumDay.text = calendar.get(Calendar.DAY_OF_MONTH).toString()



            if (day.isDone) { //Comprueba si es un dia pasado
                val bgShape = backDay.background as GradientDrawable
                bgShape.setColor(ContextCompat.getColor(context, style.basicStyle.colorDaysBefore))
                style.textStyle?.let {
                    txtNumDay.setTextColor(ContextCompat.getColor(context, it.colorDaysBefore))
                }

            } else { //El dia es futuro
                val bgShape = backDay.background as GradientDrawable
                bgShape.setColor(ContextCompat.getColor(context, style.basicStyle.colorDaysAfter))
                style.textStyle?.let {
                    txtNumDay.setTextColor(ContextCompat.getColor(context, it.colorDaysAfter))
                }
            }


            if (day.isSelected) {
                viewSelected.visibility = View.VISIBLE
                style.daysSelectedStyle?.let {
                    val bgShape = viewSelected.background as GradientDrawable
                    bgShape.setColor(ContextCompat.getColor(context, it.colorDot))
                }
            } else {
                viewSelected.visibility = View.INVISIBLE
            }

            if (day.isExtraRange) {
                style.selectedStyle?.let {
                    val bgShape = backDay.background as GradientDrawable
                    bgShape.setColor(ContextCompat.getColor(context, it.colorRange))
                    txtNumDay.setTextColor(ContextCompat.getColor(context, it.textcolorRange))
                }

            }

            if (calendar.isSameDay(daySelected)) { //Comprueba si es el mismo dia
                val bgShape = backDay.background as GradientDrawable
                bgShape.setColor(ContextCompat.getColor(context, style.basicStyle.colorDayToday))
                style.textStyle?.let {
                    txtNumDay.setTextColor(ContextCompat.getColor(context, it.colorDayToday))
                }


            }


            itemDay.setOnClickListener {
                if (!day.isDone) {
                    changeDay.invoke(day.date)
                }
                onclick.invoke(day.date, day.isExtraRange, day.isSelected, day.isDone)
            }

        }
    }

    enum class LETTERDAY {
        D, L, M, X, J, V, S
    }
}

