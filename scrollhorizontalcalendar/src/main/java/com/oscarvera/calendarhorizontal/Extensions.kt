package com.oscarvera.calendarhorizontal

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import java.util.*


/**
 * Created by oscarvera on 18/7/17.
 */

fun Int.toString(context: Context): String {
    return context.getString(this)
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.loadDrawable(idDrawable: Int){

    this.setImageDrawable(ContextCompat.getDrawable(context,idDrawable))

}

fun String.fromHtml(): Spanned {

    if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N){
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY) // for 24 api and more
    }else{
        return Html.fromHtml(this)  // or for older api
    }

}

fun View.setBackground( background : Drawable){
    if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
        setBackground(background)
    }else{
        setBackgroundDrawable(background)
    }

}

fun String.trimOnlyLeadingandTrailing() : String{
    return this.replace("^\\s+|\\s+$", "")
}

fun Calendar.isSameDay(otherdate : Calendar): Boolean{

    return (this.get(Calendar.YEAR)==otherdate.get(Calendar.YEAR)
            &&this.get(Calendar.MONTH)==otherdate.get(Calendar.MONTH)
            &&this.get(Calendar.DAY_OF_MONTH)==otherdate.get(Calendar.DAY_OF_MONTH))

}




