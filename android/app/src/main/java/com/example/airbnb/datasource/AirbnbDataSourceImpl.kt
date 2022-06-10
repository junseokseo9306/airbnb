package com.example.airbnb.datasource

import com.example.airbnb.data.Accommodation
import com.example.airbnb.dto.AccommodationDto
import com.example.airbnb.dto.CityDto
import com.example.airbnb.dto.PriceRangeDto
import com.example.airbnb.dto.SearchFilterDto
import com.example.airbnb.network.AirbnbApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirbnbDataSourceImpl @Inject constructor(private val api: AirbnbApi) : AirbnbDataSource {

    override suspend fun getCityDto(): CityDto {
//        return api.getCityDto()

        return CityDto(
            listOf(
                CityDto.Local(
                    "서울",
                    CityDto.Coordinate(
                        127.333,
                        37.111
                    ),
                    null
                )
            )
        )
    }

    override suspend fun getAccommodations(
        searchFilterDto: SearchFilterDto,
        dispatcher: CoroutineDispatcher
    ): AccommodationDto {
//        return withContext(dispatcher) {
//            api.getAccommodations(
//                searchFilterDto.location,
//                searchFilterDto.checkIn,
//                searchFilterDto.checkOut,
//                searchFilterDto.guests,
//                searchFilterDto.minPrice,
//                searchFilterDto.maxPrice
//            )
//        }

        return AccommodationDto(
            3,
            listOf(
                Accommodation(
                    1,
                    "헬스장이 딸려있는 피오의 집",
                    100000,
                    1000000,
                    4.8,
                    473,
                    true,
                    true,
                    "image/A_1.png",
                    37.476941,
                    127.040453
                ),
                Accommodation(
                    2,
                    "헬스장이 딸려있는 반스의 집",
                    110000,
                    1100000,
                    4.9,
                    411,
                    false,
                    true,
                    "image/B_1.png",
                    37.475238,
                    127.037706
                ),
                Accommodation(
                    3,
                    "비싼 집",
                    200000,
                    2000000,
                    4.6,
                    811,
                    true,
                    false,
                    "image/C_1.png",
                    37.473399,
                    127.039423
                ),
            )
        )
    }

    // TODO : Price Range API Request
    override suspend fun getPriceRange(
        location: String,
        startDate: String?,
        endDate: String?
    ): PriceRangeDto {

        return PriceRangeDto(
            10000,
            100000
        )
    }
}