package com.vodafone.data.mapper

import com.vodafone.data.entity.ConditionEntity
import com.vodafone.data.entity.current.CurrentDayEntity
import com.vodafone.data.entity.current.CurrentWeatherResponseEntity
import com.vodafone.data.entity.forecast.ForecastDayEntity
import com.vodafone.data.entity.forecast.ForecastEntity
import com.vodafone.data.entity.forecast.ForecastResponseEntity
import com.vodafone.data.entity.current.LocationEntity
import com.vodafone.data.entity.forecast.DayEntity
import com.vodafone.domain.models.ConditionModel
import com.vodafone.domain.models.DayModel
import com.vodafone.domain.models.current.CurrentWeatherResponseModel
import com.vodafone.domain.models.forecast.ForecastDayModel
import com.vodafone.domain.models.forecast.ForecastModel
import com.vodafone.domain.models.forecast.ForecastResponseModel
import com.vodafone.domain.models.current.LocationModel

fun CurrentWeatherResponseEntity.toModel(): CurrentWeatherResponseModel =
    CurrentWeatherResponseModel(location = location.toModel(), current = current.toModel())

fun LocationEntity.toModel(): LocationModel = LocationModel(name = name, region = region, country = country)
fun CurrentDayEntity.toModel(): DayModel = DayModel(temp = temp, condition = condition.toModel())

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
fun ForecastResponseEntity.toModel(): ForecastResponseModel =
    ForecastResponseModel(forecast = forecast.toModel())

fun ForecastEntity.toModel(): ForecastModel = ForecastModel(forecastDay = forecastList.toModel())

fun List<ForecastDayEntity>.toModel(): List<ForecastDayModel> = this.map { it.toModel() }
fun ForecastDayEntity.toModel(): ForecastDayModel = ForecastDayModel(date = date, day = day.toModel())
fun DayEntity.toModel(): DayModel = DayModel(temp = temp, condition = condition.toModel())
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

fun ConditionEntity.toModel(): ConditionModel = ConditionModel(text = text, icon = icon)