package com.enviance.test;
import static com.jayway.restassured.RestAssured.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class EnvianceUserNameTestCases {
	Properties envianceProp=null;
	private static final String SPLIT_CHAR=",";

	@Test(groups = { "functest"},priority = 1,description="Test User By Name")
	private void getUserByNameTest(){
		envianceProp=PropertiesFileFactory.getProperties();
		String userNameURL=envianceProp.getProperty("user_name_url");
		String name=envianceProp.getProperty("name");
		int statusSucceed=Integer.valueOf(envianceProp.getProperty("status_succed"));
		userNameURL+=name;
		
		Response resp = given().contentType("application/json").get(userNameURL);
		Assert.assertEquals(resp.getStatusCode(),statusSucceed );
		Assert.assertNotEquals(resp.asString(),"[ ]" );

		JsonPath jsonPath = new JsonPath(resp.asString());
		List<Map<String, Object>> resultMap=jsonPath.getList("");
		verifyComment(resultMap);
		
	}
	private void verifyComment(List<Map<String, Object> >resultMap) {
		for (Map <String ,Object> map :resultMap){
		Set <String> keys = map.keySet();
		Iterator<String> iter=keys.iterator();
		 String [] idValue=envianceProp.getProperty("name_test_value_id").split(SPLIT_CHAR);
		 int index=0;
		while (iter.hasNext()){
			String key =iter.next();
			Object value =map.get(key);
			String strValue=value.toString();
			if (key.equals("id") && strValue.equals(idValue[0])){
				  index=0;
			}
			else if (key.equals("id") && strValue.equals(idValue[1])){
				  index=1;
			}
			  System.out.println(" Key : "+key +" value : "+value+" index : "+index);
			  controlValues(index,key,strValue);
		  	}
	}
	}
	private void controlValues(int index,String key, String actualValue){
		  String  nameValue=envianceProp.getProperty("name_test_value_name");
		  String [] surnameValue=envianceProp.getProperty("name_test_value_surname").split(SPLIT_CHAR);
		  String [] addressValue=envianceProp.getProperty("name_test_value_address").split(SPLIT_CHAR);
		  String [] phoneValue=envianceProp.getProperty("name_test_value_phone").split(SPLIT_CHAR);
		  String [] occupationValue=envianceProp.getProperty("name_test_value_occupation").split(SPLIT_CHAR);
		  switch (key){
		  case "name"		: Assert.assertEquals(actualValue, nameValue);
		  					break;
		  case "surname" 	: Assert.assertEquals(actualValue, surnameValue[index]);
		  					break;
		  case "address" 	: Assert.assertEquals(actualValue, addressValue[index]);
							break;
		  case "phone"		: Assert.assertEquals(actualValue, phoneValue[index]);
		  					break;
		  case "occupation": Assert.assertEquals(actualValue, occupationValue[index]);
		  					break;
		             
		  }
	}
}
