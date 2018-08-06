package org.osii.nwsapp.data.model

data class Properties(
	val radarStation: String? = null,
	val fireWeatherZone: String? = null,
	val type: String? = null,
	val forecastZone: String? = null,
	val county: String? = null,
	val timeZone: String? = null,
	val forecast: String? = null,
	val cwa: String? = null,
	val relativeLocation: RelativeLocation? = null,
	val forecastHourly: String? = null,
	val observationStations: String? = null,
	val gridX: Int? = null,
	val forecastGridData: String? = null,
	val gridY: Int? = null,
	val forecastOffice: String? = null,
	val id: String? = null,
	val distance: Distance? = null,
	val city: String? = null,
	val bearing: Bearing? = null,
	val state: String? = null
)
