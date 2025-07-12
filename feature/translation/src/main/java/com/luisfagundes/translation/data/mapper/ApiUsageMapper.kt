package com.luisfagundes.translation.data.mapper

import com.luisfagundes.common.mapper.Mapper
import com.luisfagundes.translation.data.model.response.ApiUsageResponse
import com.luisfagundes.translation.domain.model.ApiUsage

class ApiUsageMapper : Mapper<ApiUsageResponse, ApiUsage> {
    override fun map(source: ApiUsageResponse): ApiUsage {
        return ApiUsage(
            characterCount = source.characterCount,
            characterLimit = source.characterLimit
        )
    }
}