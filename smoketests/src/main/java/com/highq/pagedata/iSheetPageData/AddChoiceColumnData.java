package com.highq.pagedata.iSheetPageData;

import java.util.HashMap;
import java.util.Map;

public class AddChoiceColumnData extends AddIsheetColumnData
{
	Map<String, String> choiceData = new HashMap<>();
	String choiceDisplayMethod;

	public String getChoiceDisplayMethod()
	{
		return choiceDisplayMethod;
	}

	public void setChoiceDisplayMethod(String choiceDisplayMethod)
	{
		this.choiceDisplayMethod = choiceDisplayMethod;
	}

	public Map<String, String> getChoiceData()
	{
		return choiceData;
	}

	public void setChoiceData(Map<String, String> choiceData)
	{
		this.choiceData = choiceData;
	}
}
