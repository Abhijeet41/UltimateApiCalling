package com.abhi41.ultimateapicalling.utils

import java.util.Random

object Util {

    fun generateRandom6DigitNumber(): Int {
        val random = Random(System.currentTimeMillis())
        return 100000 + random.nextInt(900000)
    }


}