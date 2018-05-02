package com.oscarvera.scrollhorizontalcalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.oscarvera.calendarhorizontal.HorizontalCalendar;
import com.oscarvera.calendarhorizontal.HorizontalCalendarView;
import com.oscarvera.calendarhorizontal.data.BasicStyle;
import com.oscarvera.calendarhorizontal.interfaz.OnClickDateCalendar;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Created by oscarvera on 31/12/17.
 */

public class MainActivityJava extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new HorizontalCalendar.Build((HorizontalCalendarView) findViewById(R.id.calendarview),this,new BasicStyle(R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorAccent)).colorText(new BasicStyle(R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorAccent)).onClickDay(new OnClickDateCalendar() {
            @Override
            public void onClickDate(@NotNull Date date, boolean isInExtraRange, boolean isSelected, boolean isDayPast) {

            }
        }).periodSelected(new Date(),new Date(),R.color.red,R.color.colorPrimary).build();


    }
}
