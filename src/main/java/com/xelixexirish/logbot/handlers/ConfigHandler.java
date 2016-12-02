package com.xelitexirish.logbot.handlers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigHandler {

	private static String TOKEN = "";
	private static String MAINTAINER_ID = "";
	private static boolean ENABLE_DEBUG_MODE = false;
	
	public static void writeConfigFile() {
		try {
			File configFile = FileHandler.getConfigFile();
			org.json.JSONObject jsonObject = new org.json.JSONObject();
			jsonObject.put("token", TOKEN);
			jsonObject.put("maintainer_id", MAINTAINER_ID);
			jsonObject.put("debug_mode", ENABLE_DEBUG_MODE);
			
			FileWriter fileWriter = new FileWriter(configFile);
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
		}
	}
	
	private static void loadConfigFile() {
		try {
			File configFile = FileHandler.getConfigFile();
			JSONParser parser = new JSONParser();
	        FileReader fileReader = new FileReader(configFile);
	        assert configFile.length() > 0;
	        Object obj = parser.parse(fileReader);
	        JSONObject jsonObject = (JSONObject) obj;
	        String maintainer_id = (String) jsonObject.get("maintainer_id");
	        String token = (String) jsonObject.get("token");
	        boolean debug = (Boolean) jsonObject.get("debug_mode");
	        TOKEN = token;
	        MAINTAINER_ID = maintainer_id;
	        ENABLE_DEBUG_MODE = debug;         
		} catch (ParseException | IOException e) {
		}
	}
	
	public static String isToken() {
		loadConfigFile();
		
		return TOKEN;
	}
	
	
	public static String isMaintainer() {
		loadConfigFile();
		
		return MAINTAINER_ID;
	}
	
	public static boolean isDebugMode() {
		loadConfigFile();
		
		return ENABLE_DEBUG_MODE;
	}
}
