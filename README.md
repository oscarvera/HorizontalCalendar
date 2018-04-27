![header](/readme/headerReadme.png)

A horizontal calendar scrollable and customizable. Programmed 100% in Kotlin. Calendar available in Spanish and English.

## Installation
Very easy! The library is hosted on jcenter. You just have to add this to your project in **build.gradle** :

```gradle
repositories {
      jcenter()
}
    
dependencies {
      compile 'com.rudo:horizontalcalendar:0.3.1'
}
```

## Usage

1. First add the view in the layout you want: 

```xml
<com.rudo.calendarhorizontal.HorizontalCalendarView
    android:id="@+id/horizontalcalendarview"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>

```

2. Initialize the calendar with the basic fields in your Activity or Fragment:

  Kotlin:
```kotlin
HorizontalCalendar.Build(YOUR_ACTIVITY,
                   R.id.horizontalcalendarview, //Id of the calendar view
                   BasicStyle( //Basic Style of the calendar
                      R.color.YOUR_COLOR,  //Color of the days before the current date
                      R.color.YOUR_COLOR, //Color of the days after the current date
                      R.color.YOUR_COLOR)) //Color of the current day
                   .build() 
```
  Java: 
```java
new HorizontalCalendar.Build(YOUR_ACTIVITY,
                   R.id.horizontalcalendarview, //Id of the calendar view
                   new BasicStyle( //Basic Style of the calendar
                      R.color.YOUR_COLOR,  //Color of the days before the current date
                      R.color.YOUR_COLOR, //Color of the days after the current date
                      R.color.YOUR_COLOR)) //Color of the current day
                   .build() 
```
This option will take the default values

## Customization
You can customize the default values of the calendar setting this fields in Build.

- Days in screen. You can choose the number of days shown on the screen at the same time:

```java
   ...
   .daysInScreen(NUMBER_OF_DAYS)
   ...
```

- Maximum days range. Maximum number of days / months that can be selected before and after the current date.

```java
   ...
   .rangeMax(7,HorizontalCalendar.TIMEMEASURE.DAY) //You can choose months directly with TIMEMEASURE.MONTH
   ...
```

- Extra period. You can select an extra period in the calendar. This period is going to show in a different color.

```java
   ...
   .periodSelected(DATE_START,
                   DATE_END,
                   R.color.YOUR_COLOR) //different color of that period of days
   ...
```

- Selected days. You can highlight calendar days with a small dot bellow. The days do not need to be correlated.

```java
  ...
  .selectedDays(LIST_DATES, //ArrayList of Dates to select
                R.color.YOUR_COLOR) //Color of the lower icon
  ...
```

 - Listener. It collects all the clicks and data of the day clicked on the calendar.
 
  Java: 
 ```java 
 ...
 .onClickDay(new OnClickDateCalendar() {
            @Override
            public void onClickDate(@NotNull Date date, boolean isInExtraRange, boolean isSelected) {
              //Class date of the day clicked.
              //If has extra period
              //If is selected
            }
        })
 ...
 ```
 
  Kotlin (Implement OnClickDateCalendar interface in the Activity): 
 ```kotlin
 ...
 .onClickDay(this)
 ...
 ...
 override fun onClickDate(date: Date, isInExtraRange: Boolean, isSelected: Boolean) {
       // date: Class date of the day clicked.
       //isInExtraRange: If has extra period
       //isSelected: If is selected
    }
 ...

 ```
 
 ## Versions
We are currently in the process of development. **The current version is in beta phase**.
 
## License
```
Copyright 2018
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


