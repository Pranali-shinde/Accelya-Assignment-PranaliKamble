package com.example;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationService {

	
	public static final Logger LOGGER = LogManager.getLogger(LocationService.class);
	
	/* Method which return the coordinates
	 *  of the space station
	 * */
	public Map<String,Double> getCoordinatesOfSpaceStation() throws Exception{
		 final String method = "getCoordinatesOfSpaceStation-Service";
		    LOGGER.info("Entering " + method);
		    
		    try {
		    	//The current location of space station retrieved by using following Public API  
		    	String publicURL = "https://api.wheretheiss.at/v1/satellites/25544";
				
		    	//Storing response of the public
				RestTemplate restTemplate = new RestTemplate();
				
				JSONObject jsonObject = restTemplate.getForObject(publicURL, JSONObject.class);
				
				Double latitude = (Double) jsonObject.get("latitude");
				Double longitude = (Double) jsonObject.get("longitude");
		          
		        Map<String,Double> map = new LinkedHashMap<>(2);

				if(latitude != 0 && longitude != 0 ) {
					 map.put("latitude",latitude);
				        map.put("longitude", longitude);
				}else{
					 map.put("latitude",0.0);
				        map.put("longitude", 0.0);
				}
		        System.out.println("\n latitude" +latitude);
		        System.out.println("\n longitude" +longitude);
		        LOGGER.info("Moving Out " + method);

				return map;
//		        Map<String,Double> map = new LinkedHashMap<>(2);
//		        map.put("latitude",latitude);
//		        map.put("longitude", longitude);

		    }catch(Exception ex) {
		    	LOGGER.error("Error", ex);
		        throw new Exception(ex);
		    }
			    
		
	}
}
