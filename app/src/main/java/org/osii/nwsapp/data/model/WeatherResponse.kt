package org.osii.nwsapp.data.model

data class WeatherResponse(
	val geometry: Geometry? = null,
	val id: String? = null,
	val type: String? = null,
	val context: List<String?>? = null,
	val properties: Properties? = null
)
