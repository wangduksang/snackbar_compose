package com.example.weathercompose

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class WeatherViewModelTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `should print date - create local sample data`() {

        val dateList = ArrayList<String>()
        dateList.add("2022-04-01 01:10")
        dateList.add("2022-04-03 03:11")
        dateList.add("2022-04-03 02:10")
        dateList.add("2022-04-02 01:10")
        dateList.add("2022-04-03 03:10")
        dateList.add("2022-04-03 03:12")
        dateList.add("2022-02-01 01:10")
        dateList.add("2022-04-03 01:10")

        println("Before sorted")
        for (date in dateList) {
            println(date)
        }

//        dateList.sortWith { o1, o2 ->
//            o2.compareTo(o1) // Reverse : o1.compareTo(o2)
//        }

//        dateList.sortWith { o1, o2 ->
//            o1.compareTo(o2) // Reverse : o1.compareTo(o2)
//        }
        val sortedDataList = mutableListOf<Int>()
        dateList.forEach {
            val squashedDate = it.take(10).replace("-", "")
            val currentDate =
                LocalDate.parse(squashedDate, DateTimeFormatter.BASIC_ISO_DATE)
            val dtf = DateTimeFormatter.ofPattern("ddMMyyyy")
            val strCurrentDate = currentDate.format(dtf)
            sortedDataList.add(strCurrentDate.toInt())
//            val caomparator = Comparator<String> { a, b -> a.compareTo(b) }
//            dateList.sortedWith(caomparator)
            println("After sorted")
        }
        //sortedDataList.sortedByDescending { it }
        sortedDataList.sortByDescending { it }
//        val caomparator = Comparator<String> { a, b -> a.compareTo(b) }
//        dateList.sortedWith(caomparator)
        for (date in dateList) {
            println(date)
        }
    }
}