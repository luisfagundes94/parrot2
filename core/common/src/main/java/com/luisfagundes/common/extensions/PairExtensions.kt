package com.luisfagundes.common.extensions

fun <T, U> Pair<T, U>.swap(): Pair<U, T> {
    return Pair(second, first)
}