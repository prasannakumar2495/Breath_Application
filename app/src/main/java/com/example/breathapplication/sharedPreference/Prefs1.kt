package com.example.breathapplication.sharedPreference

import android.app.Activity
import android.content.SharedPreferences
import java.util.*

class Prefs1(activity: Activity) {
    private val preference: SharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE)
    val date: String
        get() {
            val milliDate = preference.getLong("seconds", 0)
            val amORpm: String
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = milliDate
            val a = calendar.get(Calendar.AM_PM)
            if (a == Calendar.AM) {
                amORpm = "AM"
            } else {
                amORpm = "PM"
            }
            return ("Last "
                    + calendar.get(Calendar.HOUR_OF_DAY)
                    + ":"
                    + calendar.get(Calendar.MINUTE)
                    + " " +
                    amORpm)
        }

    fun setDate(milliseconds: Long) {
        preference.edit().putLong("seconds", milliseconds).apply()
    }

    var sessions: Int
        get() = preference.getInt("sessions", 0)
        set(session) = preference.edit().putInt("sessions", session).apply()

    var breath: Int
        get() = preference.getInt("breaths", 0)
        set(breath) = preference.edit().putInt("breaths", breath).apply()
}