package com.luisfagundes.translation.domain.model

data class ApiUsage(
    val characterCount: Long,
    val characterLimit: Long
) {
    val usagePercentage: Float
        get() = (characterCount.toFloat() / characterLimit.toFloat()) * 100f
        
    val remainingCharacters: Long
        get() = characterLimit - characterCount
}