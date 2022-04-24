package com;

import model.User;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User")
public class ServiceUser {
	User UserObj = new User();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser() {
		return UserObj.readUser();
	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(
			
			 
			@FormParam("U_Fname") String U_Fname,
			@FormParam("U_Lname") String U_Lname,
			@FormParam("Uemail") String Uemail,
			@FormParam("Uaddress") String Uaddress,
			@FormParam("U_NIC") String U_NIC,
			@FormParam("U_gender") String U_gender,
			@FormParam("U_DoB") String U_DoB) {
		
		String output = UserObj.insertUser(U_Fname, U_Lname, Uemail, Uaddress, U_NIC, U_gender,U_DoB );
		return output;

	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateUser(String userData) {
		// Convert the input string to a JSON object
		JsonObject Uobject = new JsonParser().parse(userData).getAsJsonObject();

		// Read the values from the JSON object
		String UserID = Uobject.get("UserID").getAsString();
		String U_Fname = Uobject.get("U_Fname").getAsString();
		String U_Lname = Uobject.get("U_Lname").getAsString();
		String Uemail = Uobject.get("Uemail").getAsString();
		String Uaddress = Uobject.get("Uaddress").getAsString();
		String U_NIC = Uobject.get("U_NIC").getAsString();
		String U_gender = Uobject.get("U_gender").getAsString();
		String U_DoB = Uobject.get("U_DoB").getAsString();

		String output = UserObj.updateUser(UserID, U_Fname, U_Lname, Uemail, Uaddress, U_NIC, U_gender,U_DoB );
		return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteUser(String userData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

		// Read the value from the element 
		String UserID = doc.select("UserID").text();
		String output = UserObj.deleteUser(UserID);
		return output;
	}
}
