package com.arash.arch.data.util

import com.arash.arch.data.model.Date
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DataUtil {
    /**
     * Convert millisecond to persian date format
     * @param timeInMilli time in millisecond format
     * @return returns and instance of [Date] model
     */
    fun getPersianDateFormat(timeInMilli: Long): Date {
        val yearFormatter: DateFormat = SimpleDateFormat("yyyy", Locale.US)
        val monthFormatter: DateFormat = SimpleDateFormat("MM", Locale.US)
        val dayFormatter: DateFormat = SimpleDateFormat("dd", Locale.US)
        val timeFormatter: DateFormat = SimpleDateFormat("HH:mm", Locale.US)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMilli
        val year = yearFormatter.format(calendar.time).toInt()
        val month = monthFormatter.format(calendar.time).toInt()
        val day = dayFormatter.format(calendar.time).toInt()
        val time = timeFormatter.format(calendar.time)
        val dateConverter = DateConverter()
        dateConverter.gregorianToPersian(year, month, day)
        return Date(
            String.format("%s-%s-%s", dateConverter.year, dateConverter.month, dateConverter.day),
            time
        )
    }

    /**
     * Convert gregorian date in format yyyy-mm-dd to the persian data in same format
     * @param gregorianDate gregorian date in yyyy-mm-dd format
     * @return persian date in yyyy-mm-dd format
     */
    fun getPersianDateFormat(gregorianDate: String): String {
        val splitData = gregorianDate.split("-")
        val dateConverter = DateConverter()
        dateConverter.gregorianToPersian(
            splitData[0].toInt(),
            splitData[1].toInt(),
            splitData[2].toInt()
        )
        return String.format("%s-%s-%s", dateConverter.year, dateConverter.month, dateConverter.day)
    }
}