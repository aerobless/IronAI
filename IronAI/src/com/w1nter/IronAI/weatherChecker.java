package com.w1nter.IronAI;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import de.mbenning.weather.wunderground.api.domain.DataSet;
import de.mbenning.weather.wunderground.api.domain.WeatherStation;
import de.mbenning.weather.wunderground.api.domain.WeatherStations;
import de.mbenning.weather.wunderground.impl.services.HttpDataReaderService;
import de.mbenning.weather.wunderground.impl.services.WeatherStationService;

public class weatherChecker {

	public static Double getWeather() {
		// create a instance of a wunderground data reader
		HttpDataReaderService dataReader = new HttpDataReaderService();
		
		// create an instance of WeatherStationService
		WeatherStationService weatherStationService = new WeatherStationService();

		// find all weather stations
		List<WeatherStation> stations = weatherStationService.findAllWeatherStationsByCountry("Switzerland");

		// iterate over all founded weather stations and write some information to stdout
		Double temperature = (double) 999;
		for (WeatherStation weatherStation : stations) {
			if (weatherStation.getStationId().equals("IZRICHHO2")) {
				dataReader.setWeatherStation(weatherStation);
				// get current (last) weather data set from selected station
				DataSet current = dataReader.getCurrentData();
				// print datetime of measure and temperature ...
				temperature = current.getTemperature();
			    System.out.println(weatherStation.getStationId() + "\t" + "\t" + weatherStation.getCity() + "\t" + weatherStation.getCountry());           
				
			}
		}
		return temperature;
	}
}
