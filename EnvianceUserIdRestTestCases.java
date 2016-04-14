package com.enviance.test;
import static com.jayway.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class EnvianceUserIdRestTestCases {
	Properties envianceProp=null;
	@Test(groups = { "functest"},priority = 1,description="Test User By Id")
	private void getUserByIdTest(){
		envianceProp=PropertiesFileFactory.getProperties();
		String userIdURL=envianceProp.getProperty("user_id_url");
		String id=envianceProp.getProperty("id");
		int statusSucceed=Integer.valueOf(envianceProp.getProperty("status_succed"));
		userIdURL+=id;
		
		Response resp = given().contentType("application/json").get(userIdURL);
		Assert.assertEquals(resp.getStatusCode(),statusSucceed );
		Assert.assertNotEquals(resp.asString(),"[ ]" );

		JsonPath jsonPath = new JsonPath(resp.asString());
		Map<String, Object> resultMap=jsonPath.getMap("");
		verifyComment(resultMap);
		
	}
	
	private void verifyComment(Map<String, Object> resultMap) {
		Set <String> keys = resultMap.keySet();
		Iterator<String> iter=keys.iterator();
		while (iter.hasNext()){
		  String key =iter.next();
		  Object value =resultMap.get(key);
		  if (value instanceof String){
			  String strValue=value.toString();
			  switch (key){
			  case "name" 		: Assert.assertEquals(strValue, envianceProp.getProperty("id_test_value_name"));
			                	break;
			  case "surname" 	: Assert.assertEquals(strValue, envianceProp.getProperty("id_test_value_surname"));
			  					break;
			  case "occupation": Assert.assertEquals(strValue, envianceProp.getProperty("id_test_value_occupation"));
			  					break;
			  case "phone"		: Assert.assertEquals(strValue, envianceProp.getProperty("id_test_value_phone"));
			  					break;
			  case "address" 	: Assert.assertEquals(strValue, envianceProp.getProperty("id_test_value_address"));
			  					break;
			             
			  }
		  }
		  
		}

	}
}
