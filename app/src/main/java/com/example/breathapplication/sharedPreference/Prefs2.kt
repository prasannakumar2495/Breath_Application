package com.example.breathapplication.sharedPreference

import android.app.Activity
import android.content.SharedPreferences
import java.util.*

class Prefs(activity: Activity) {
    lateinit var preference: SharedPreferences
    private fun setDate(milliseconds: Long) {
        preference.edit().putLong("seconds", milliseconds)
            .apply()
    }

    private fun getDate(): String {
        val milliDate = preference.getLong("seconds", 0)
        val amOrpm: String

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliDate

        val a = calendar.get(Calendar.AM_PM)
        amOrpm = (if (a == Calendar.AM) {
            "AM"
        } else "PM")

        val time =
            "Last ${calendar.get(Calendar.HOUR_OF_DAY)} : ${calendar.get(Calendar.MINUTE)} $amOrpm"

        return time
    }
}