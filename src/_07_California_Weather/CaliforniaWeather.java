package _07_California_Weather;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * OBJECTIVE:
 * 1. Create a program that allows the user to search for the weather
 * conditions of a given city in California. Use the example program below
 * and the Utilities class inside this project to get the temperature data
 * from a day in December 2020.
 * Example: User: Encinitas
 *          Program: Encinitas is Overcast with a tempeature of 59.01 °F
 * 
 * 2. Create a way for the user to specify the weather condition and then
 * list the cities that have those conditions.
 * Example: User: Mostly Cloudy
 *          Program: Long Beach, Pomona, Oceanside, ...
 * 
 * 3. Create a way for the user to enter a minimum and maximum temperature
 * and then list the cities that have temperatures within that range
 * Example: User: minimum temperature °F = 65.0, max temperature °F = 70.0
 *          Program: Fortana, Glendale, Escondido, Del Mar, ...
 *          
 * EXTRA:
 * Feel free to add pictures for specific weather conditions or a thermometer
 * for the temperature. Also If you want your program to get the current day's
 * temperature, you can get a free API key at: https://openweathermap.org/api 
 */

public class CaliforniaWeather implements ActionListener {
    
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton citySearch = new JButton("Search by City");
	JButton weatherSearch = new JButton("Search by Weather");
	JButton tempSearch = new JButton("Search by Temperature Range");
	CaliforniaWeather(){
    	panel.add(citySearch);
    	panel.add(weatherSearch);
    	panel.add(tempSearch);
    	frame.add(panel);
    	
    	citySearch.addActionListener(this);
    	weatherSearch.addActionListener(this);
    	tempSearch.addActionListener(this);
    	
    	frame.setVisible(true);
    	frame.pack();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
    void start() {
        HashMap<String, WeatherData> weatherData = Utilities.getWeatherData();
        
        // All city keys have the first letter capitalized of each word
        String cityName = Utilities.capitalizeWords( "National City" );
        WeatherData datum = weatherData.get(cityName);
        
        if( datum == null ) {
            System.out.println("Unable to find weather data for: " + cityName);
        }
        else {
            System.out.println(cityName + " is " + datum.weatherSummary + " with a temperature of " + datum.temperatureF + " F");
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==citySearch) {
			citySearch();
		}
		else if(e.getSource()==weatherSearch) {
			weatherSearch();
		}
		else if(e.getSource()==tempSearch) {
			tempSearch();
		}
	}
	
	void citySearch() {
		HashMap<String, WeatherData> weatherData = Utilities.getWeatherData();
		String input = JOptionPane.showInputDialog("Enter the name of a city in California: ");
		String cityName = Utilities.capitalizeWords(input);
		WeatherData datum = weatherData.get(cityName);
		if( datum == null ) {
            JOptionPane.showMessageDialog(null, "Unable to find weather data for: " + cityName);
        }
		else {
        	JOptionPane.showMessageDialog(null, cityName + " is " + datum.weatherSummary + " with a temperature of " + datum.temperatureF + " F");
        }
	}
	
	void weatherSearch() {
		int counter=0;
		String finalCities = "";
		HashMap<String, WeatherData> weatherData = Utilities.getWeatherData();
	
		String input = JOptionPane.showInputDialog("Entera type of weather (clear, partly cloudy, mostly cloudy,\novercast, possible drizzle, drizzle, light rain)");
		String weatherType = Utilities.capitalizeWords(input);
		for(String key : weatherData.keySet()) {
			WeatherData data = weatherData.get(key);
			if(weatherType.equals(data.weatherSummary)) {
				if(counter==15) {
					finalCities+=key+", \n";
					counter=0;
				}
				else {
					finalCities+=key+", ";
					counter++;
				}
			}
		}
		JOptionPane.showMessageDialog(null, finalCities);
	}
    
	void tempSearch() {
		HashMap<String, WeatherData> weatherData = Utilities.getWeatherData();
		String finalCities = "";
		int counter=0;
		
		String minput = JOptionPane.showInputDialog("Enter a minimum temperature: ");
		String maxput = JOptionPane.showInputDialog("Enter a maximum temperature: ");
		double minTemp = Double.parseDouble(minput);
		double maxTemp = Double.parseDouble(maxput);
		
		for(String key : weatherData.keySet()) {
			WeatherData data = weatherData.get(key);
			if(data.temperatureF >= minTemp && data.temperatureF <= maxTemp) {
				if(counter==15) {
					finalCities+=key+", \n";
					counter=0;
				}
				else {
					finalCities+=key+", ";
					counter++;
				}
			}
		}
		JOptionPane.showMessageDialog(null, finalCities);
	}
	
}
