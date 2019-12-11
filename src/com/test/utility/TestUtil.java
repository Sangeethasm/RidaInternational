package com.test.utility;

import java.util.ArrayList;

import com.excel.utility.Xls_Reader;

public class TestUtil {
	static Xls_Reader reader;

public static ArrayList<Object[]> getDataFromExcelForLogin() {
	ArrayList<Object[]> loginData = new ArrayList<Object[]>();
	try
	{
		reader = new Xls_Reader("/home/sangeetha/Downloads/TestData.xlsx");
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	for (int rowNum = 2; rowNum <= reader.getRowCount("logindata"); rowNum++) {
		String Username = reader.getCellData("logindata", "Username", rowNum);
		String Password = reader.getCellData("logindata", "Password", rowNum);
		
		Object loginob[] = {Username,Password};
		loginData.add(loginob);
	}
	return loginData;
}

public static ArrayList<Object[]> getDataFromExcelForAgent() {
	ArrayList<Object[]> agentData = new ArrayList<Object[]>();
	try
	{
		reader = new Xls_Reader("/home/sangeetha/Downloads/TestData.xlsx");
	}catch (Exception e) {
		e.printStackTrace();
	}
	for (int rowNum = 2; rowNum <= reader.getRowCount("agent"); rowNum++) {
		String Company = reader.getCellData("agent", "Company", rowNum);
		String Currency = reader.getCellData("agent", "Currency", rowNum);
		String Markuptype = reader.getCellData("agent", "Markuptype", rowNum);
		String Markupvalue = reader.getCellData("agent", "Markupvalue", rowNum);
		String Companystatus = reader.getCellData("agent", "Companystatus", rowNum);
		String Companylogo = reader.getCellData("agent", "Companylogo", rowNum);
		String Street = reader.getCellData("agent", "Street", rowNum);
		String Country = reader.getCellData("Agent", "Country", rowNum);
		String City = reader.getCellData("Agent", "City", rowNum);
		String Postalcode = reader.getCellData("agent", "Postalcode", rowNum);
		String Website = reader.getCellData("agent", "Website", rowNum);
		String Mobile = reader.getCellData("agent", "Mobile", rowNum);
		String Telephone = reader.getCellData("agent", "Telephone", rowNum);
		String Fax = reader.getCellData("agent", "Fax", rowNum);
		String Email = reader.getCellData("agent", "Email", rowNum);
		String Skypeid = reader.getCellData("agent", "Skypeid", rowNum);
		
		Object agentob[] = {Company,Currency,Markuptype,Markupvalue,Companystatus,Companylogo,Street,Country,City,Postalcode,Website,Mobile,Telephone,Fax,Email,Skypeid};
		agentData.add(agentob);
	}
	return agentData;
}
}
	