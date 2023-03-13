package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ch.qos.logback.core.status.Status;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

@Controller
public class LocationController {

	public static final Logger LOGGER = LogManager.getLogger(LocationController.class);
//	 Status success = new Status(StatusConstants.HttpConstants.SUCCESS.getCode(),
//		        StatusConstants.HttpConstants.SUCCESS.getDesc());

	@Autowired
	private LocationService locationService;
	
	Map<String,Double> locationMap ;
	
	
//	@GetMapping("/test")
//	@ResponseBody
//	public Map<String,Double> testMethod(){
//		
//		String publicURL = "https://api.wheretheiss.at/v1/satellites/25544";
//		
//		RestTemplate restTemplate = new RestTemplate();
//		
//		JSONObject jsonObject = restTemplate.getForObject(publicURL, JSONObject.class);
//		
//		Double latitude = (Double) jsonObject.get("latitude");
//		Double longitude = (Double) jsonObject.get("longitude");
//          
//        System.out.println("\n latitude" +latitude);
//        System.out.println("\n longitude" +longitude);
//        
//        locationMap = new LinkedHashMap<>(2);
//        locationMap.put("latitude",latitude);
//        locationMap.put("longitude", longitude);
//        
//		return locationMap;
//		
//	}
//	
	
	 @GetMapping("/getLocation")
	 @ResponseBody
	  public Map<String,Double> getLocationOfSatellite() throws Exception {
		 final String method = "getLocationOfSatellite-Controller";
		    LOGGER.info("Entering " + method);
	    locationMap = locationService.getCoordinatesOfSpaceStation();
	    LOGGER.info("Moving Out " + method);
	    return locationMap;
//	    return new ResponseEntity<Response>(new Response<Map<String,Double>>(success, locationMap), HttpStatus.OK);
	  }	
}
