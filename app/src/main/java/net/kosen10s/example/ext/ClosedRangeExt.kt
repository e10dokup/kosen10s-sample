package net.kosen10s.example.ext

import java.util.*

fun ClosedRange<Int>.random() =
       Random().nextInt(endInclusive - start) +  start
