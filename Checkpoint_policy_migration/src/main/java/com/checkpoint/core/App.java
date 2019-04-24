package com.checkpoint.core;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.*;

public class App 
{
    public static void main( String[] args )
    {
    	String FilePath = "D:\\2_Work\\Companies\\Vodafone\\Australia\\Docs\\Projects\\2_Trusted_Business_Partner\\Config\\FWL\\show_package\\show_package-2019-03-12_12-18-10\\";
    	String FileName = "TE-TBP Security-NE-TE-MY-LEGACY.json";
    	
    	try {
    		ObjectMapper objectMapper = new ObjectMapper();
    		byte[] mapData = Files.readAllBytes(Paths.get(FilePath+FileName));
    		System.out.println("Size of the input: " + mapData.length);
    		String stringMapData = "[" + new String(mapData) + "]";
    		
    		/*
    		Map<String,String> myMap = new HashMap<String, String>();
    		
    		myMap = objectMapper.readValue(mapData, HashMap.class);
    		System.out.println("Map is: "+myMap);*/
    		
    		
    		
    		/*byte[] modifiedMapData = new byte[mapData.length+2];
    		modifiedMapData[0]=0x5b;
    		modifiedMapData[modifiedMapData.length-1]=0x5d;
    		System.arraycopy(mapData, 0, modifiedMapData, 1, mapData.length);*/
    		
    		//objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
    		
    		Map<String,String>[] myMap = objectMapper.readValue(stringMapData, HashMap[].class);
    		
    		for (int index = 0; index<myMap.length; index++) {
    			Map<String,String> currentMap = myMap[index];
    			if (currentMap.get("type").contentEquals("access-rule")) {
    				Object enabled = currentMap.get("enabled");
    				if (enabled.toString().contentEquals("true")) {	
    					System.out.println("Map " + index + " is: " + currentMap);
    				}
    			}
    		}
    		
    		/*
    		//String jsonString = new String(mapData);
    		    		
    		
    		var mapCollectionType = mapper.getTypeFactory().constructCollectionType(List.class, Map.class);

    		List<Map> value = mapper.readValue(mapData, mapCollectionType);
    		System.out.println(value.get(0));
    		*/
    		
    		
    		
    		/*
    		JsonNode rootNode = objectMapper.readTree(stringMapData);
    		JsonNode idType = rootNode.path("type");
    		System.out.println("type = "+idType.toString());
    		*/
    		
        }
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
