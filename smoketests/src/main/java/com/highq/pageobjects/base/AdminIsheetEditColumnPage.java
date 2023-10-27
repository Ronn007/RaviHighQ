package com.highq.pageobjects.base;

public interface AdminIsheetEditColumnPage extends AdminIsheetAddColumnPage
{
	public boolean verifySelectedEngines(String commaSeparatedEnginesName);

	public boolean verifyDefaultNothingSelectedInAnalysisEngine();
}
