package com.example.lab2.util

fun fact(n: Long): Long {
    require(n >= 0) { "n must be positive" }
    return if (n > 0) (1..n).reduce { ret, i -> ret * i } else 1
}