package com.highq.test.iSheet;

import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.openqa.selenium.WebDriver;
import com.highq.pagedata.iSheetPageData.AddAutoIncrementColumnData;
import com.highq.pagedata.iSheetPageData.AddCalculationColumnData;
import com.highq.pagedata.iSheetPageData.AddChoiceColumnData;
import com.highq.pagedata.iSheetPageData.AddDateAndTimeColumnData;
import com.highq.pagedata.iSheetPageData.AddImageColumnData;
import com.highq.pagedata.iSheetPageData.AddIsheetColumnData;
import com.highq.pagedata.iSheetPageData.AddIsheetLinkColumnData;
import com.highq.pagedata.iSheetPageData.AddJoinColumnData;
import com.highq.pagedata.iSheetPageData.AddLookupColumnData;
import com.highq.pagedata.iSheetPageData.AddMultipleLineTextColumnData;
import com.highq.pagedata.iSheetPageData.AddNumberColumnData;
import com.highq.pagedata.iSheetPageData.AddSingleLineTextColumnData;
import com.highq.pagedata.iSheetPageData.AddUserLookupColumnData;
import com.highq.pageobjects.base.AdminAddIsheetPage;
import com.highq.pageobjects.base.AdminIsheetAddColumnPage;
import com.highq.pageobjects.pages.AdminIsheetManageColumnWeb;
import com.highq.pageobjects.pages.AdminIsheetWeb;
import com.highq.pageobjects.pages.BannerPageWeb;

public class BaseIsheetTest extends BannerPageWeb
{
	public BaseIsheetTest(WebDriver driver)
	{
		this.driver = driver;
	}

	public AdminAddIsheetPage adminAddIsheetWeb;
	public AdminIsheetAddColumnPage adminIsheetAddColumnWeb;

	public AdminIsheetWeb createIsheet(LinkedHashMap<String, String> iSheetData, LinkedHashMap<String, Boolean> options)
	{
		for (Map.Entry<String, String> iSheetMetadata : iSheetData.entrySet())
		{
			switch (iSheetMetadata.getKey().toLowerCase())
			{
				case "title":
					adminAddIsheetWeb.addIsheetTitle(iSheetMetadata.getValue());
					break;
				case "description":
					adminAddIsheetWeb.addIsheetDescription(iSheetMetadata.getValue());
					break;
				case "status":
					adminAddIsheetWeb.clickIsheetStatusDropdown();
					adminAddIsheetWeb.selectIsheetStatus(iSheetMetadata.getValue());
					break;
				case "access type":
					adminAddIsheetWeb.selectIsheetAccessType(iSheetMetadata.getValue());
					break;
				case "field descriptions":
					adminAddIsheetWeb.selectIsheetFieldDescription(iSheetMetadata.getValue());
					break;
				default:
					System.err.println("Enter Valid data");
					break;
			}
		}
		if (options != null)
		{
			for (Map.Entry<String, Boolean> option : options.entrySet())
			{
				adminAddIsheetWeb.addIsheetSelectCheckBoxOption(option.getKey(), option.getValue());
			}
		}
		adminAddIsheetWeb.addIsheetClickSave();
		return new AdminIsheetWeb(driver);
	}

	public AdminIsheetManageColumnWeb addSingleLineTextColumn(AddSingleLineTextColumnData addSingleLineTextColumnData) throws InterruptedException
	{
		addColumn(addSingleLineTextColumnData);
		if (addSingleLineTextColumnData.getMaxChar() != null)
		{
			adminIsheetAddColumnWeb.addColumnMaxCharacters(addSingleLineTextColumnData.getMaxChar());
		}
		adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Allow the field to be populated from another iSheet", addSingleLineTextColumnData.isAllowFieldPopulation());
		if (addSingleLineTextColumnData.getAllowPopulationTargetSheet() != null)
		{
			adminIsheetAddColumnWeb.selectAllowPopulationTargetSheet(addSingleLineTextColumnData.getAllowPopulationTargetSheet());
		}
		if (addSingleLineTextColumnData.getAllowPopulationTargetSheetView() != null)
		{
			adminIsheetAddColumnWeb.selectAllowPopulationTargetSheetView(addSingleLineTextColumnData.getAllowPopulationTargetSheetView());
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addSingleLineTextColumn(Map<String, Object> singleLineTextColumnData) throws InterruptedException
	{
		addColumn(singleLineTextColumnData);
		for (Entry<String, Object> columnData : singleLineTextColumnData.entrySet())
		{
			switch (columnData.getKey().toLowerCase())
			{
				case "maximum characters":
					adminIsheetAddColumnWeb.addColumnMaxCharacters((String) columnData.getValue());
					break;
				case "allow the field to be populated from another iSheet":
					adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Allow the field to be populated from another iSheet", (boolean) columnData.getValue());
					break;
				case "select isheet":
					adminIsheetAddColumnWeb.selectAllowPopulationTargetSheet((String) columnData.getValue());
					break;
				case "select view":
					adminIsheetAddColumnWeb.selectAllowPopulationTargetSheetView((String) columnData.getValue());
					break;
			}
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addMultipleLineTextColumn(AddMultipleLineTextColumnData addMultipleLineTextColumnData) throws InterruptedException
	{
		addColumn(addMultipleLineTextColumnData);
		if (addMultipleLineTextColumnData.getNumberOfLines() != null)
		{
			adminIsheetAddColumnWeb.addColumnNoOfLines(addMultipleLineTextColumnData.getNumberOfLines());
		}
		if (addMultipleLineTextColumnData.getWidth() != null)
		{
			adminIsheetAddColumnWeb.addColumnWidth(addMultipleLineTextColumnData.getWidth());
		}
		adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Allow rich HTML text", addMultipleLineTextColumnData.isAllowRichHtmlext());
		adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Allow the field to be populated from another iSheet", addMultipleLineTextColumnData.isAllowFieldPopulation());
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addMultipleLineTextColumn(Map<String, Object> multiLineTextColumnData) throws InterruptedException
	{
		addColumn(multiLineTextColumnData);
		for (Entry<String, Object> columnData : multiLineTextColumnData.entrySet())
		{
			switch (columnData.getKey().toLowerCase())
			{
				case "number of lines":
					adminIsheetAddColumnWeb.addColumnNoOfLines((String) columnData.getValue());
					break;
				case "width":
					adminIsheetAddColumnWeb.addColumnWidth((String) columnData.getValue());
					break;
				case "allow rich html text":
					adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Allow rich HTML text", (boolean) columnData.getValue());
					break;
				case "allow the field to be populated from another iSheet":
					adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Allow the field to be populated from another iSheet", (boolean) columnData.getValue());
					break;
			}
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addChoiceColumn(AddChoiceColumnData choiceColumnData) throws InterruptedException
	{
		addColumn(choiceColumnData);

		if (choiceColumnData.getChoiceData() != null)
		{
			// for (Map.Entry<String, String> choice : choiceColumnData.getChoiceData().entrySet())
			// {
			adminIsheetAddColumnWeb.enterMultipleChoiceForSameColor(choiceColumnData.getChoiceData().values().toArray()[0].toString(), choiceColumnData.getChoiceData().keySet().toArray(new String[choiceColumnData.getChoiceData().keySet().size()]));
			// }
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addNumberColumn(AddNumberColumnData addNumberColumnData) throws InterruptedException
	{
		addColumn(addNumberColumnData);
		if (addNumberColumnData.getMinValue() != null)
		{
			adminIsheetAddColumnWeb.addColumnMinCharacters(addNumberColumnData.getMinValue());
		}
		if (addNumberColumnData.getMaxValue() != null)
		{
			adminIsheetAddColumnWeb.addColumnMaxCharacters(addNumberColumnData.getMaxValue());
		}
		if (addNumberColumnData.getDecimalValue() != null)
		{
			adminIsheetAddColumnWeb.addColumnSelectDecimalPlaces(addNumberColumnData.getDecimalValue());
		}
		adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Show as percentage", addNumberColumnData.isShowAsPercentage());
		adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Show thousand separators", addNumberColumnData.isShowThousandSeparator());
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addNumberColumn(Map<String, Object> numberColumnData) throws InterruptedException
	{
		addColumn(numberColumnData);
		for (Entry<String, Object> columnData : numberColumnData.entrySet())
		{
			switch (columnData.getKey().toLowerCase())
			{
				case "minimum":
					adminIsheetAddColumnWeb.addColumnMinCharacters((String) columnData.getValue());
					break;
				case "maximum":
					adminIsheetAddColumnWeb.addColumnMaxCharacters((String) columnData.getValue());
					break;
				case "default value":
					adminIsheetAddColumnWeb.addColumnSelectDecimalPlaces((String) columnData.getValue());
					break;
				case "show as percentage":
					adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Show as percentage", (boolean) columnData.getValue());
					break;
				case "show thousand separators":
					adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Show thousand separators", (boolean) columnData.getValue());
					break;
			}
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addDateAndTimeColumn(AddDateAndTimeColumnData addDateAndTimeColumnData) throws InterruptedException, ParseException
	{
		addColumn(addDateAndTimeColumnData);
		if (addDateAndTimeColumnData.getDateFormat() != null)
		{
			adminIsheetAddColumnWeb.selectDateFormat(addDateAndTimeColumnData.getDateFormat());
		}
		if (addDateAndTimeColumnData.getSelectTheFormat() != null)
		{
			adminIsheetAddColumnWeb.selectDateColumnFormat(addDateAndTimeColumnData.getSelectTheFormat());
		}
		if (addDateAndTimeColumnData.getDefaultDateOption() != null)
		{
			adminIsheetAddColumnWeb.setDefaultDateValue(addDateAndTimeColumnData.getDefaultDateOption());
		}
		if (addDateAndTimeColumnData.getDefaultTime() != null)
		{
			adminIsheetAddColumnWeb.setDefaultTime(addDateAndTimeColumnData.getDefaultTime().split(":")[0], addDateAndTimeColumnData.getDefaultTime().split(":")[1]);
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addDateAndTimeColumn(Map<String, Object> dateAndTimeColumnData) throws InterruptedException, ParseException
	{
		addColumn(dateAndTimeColumnData);
		for (Entry<String, Object> columnData : dateAndTimeColumnData.entrySet())
		{
			switch (columnData.getKey().toLowerCase())
			{
				case "select the format":
					adminIsheetAddColumnWeb.selectDateColumnFormat((String) columnData.getValue());
					break;
				case "date format":
					adminIsheetAddColumnWeb.selectDateFormat((String) columnData.getValue());
					break;
				case "default value":
					adminIsheetAddColumnWeb.setDefaultDateValue((String) columnData.getValue());
					break;
				case "default time":
					adminIsheetAddColumnWeb.setDefaultTime(columnData.getValue().toString().split(":")[0], columnData.getValue().toString().split(":")[1]);
					break;
			}
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addUserLookupColumn(AddUserLookupColumnData addUserLookupColumnData) throws InterruptedException
	{
		addColumn(addUserLookupColumnData);
		if (addUserLookupColumnData.getSelectLookup() != null)
		{
			adminIsheetAddColumnWeb.addColumnSelectUserLookup(addUserLookupColumnData.getSelectLookup());
		}
		if (addUserLookupColumnData.getFieldDisplay() != null)
		{
			adminIsheetAddColumnWeb.addColumnSelectUserLookupField(addUserLookupColumnData.getFieldDisplay());
		}
		adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Allow multiple users", addUserLookupColumnData.isAllowMultipleUsers());
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addUserLookupColumn(Map<String, Object> userLookupColumnData) throws InterruptedException
	{
		addColumn(userLookupColumnData);
		for (Entry<String, Object> columnData : userLookupColumnData.entrySet())
		{
			switch (columnData.getKey().toLowerCase())
			{
				case "select lookup":
					adminIsheetAddColumnWeb.addColumnSelectUserLookup((String) columnData.getValue());
					break;
				case "field display":
					adminIsheetAddColumnWeb.addColumnSelectUserLookupField((String) columnData.getValue());
					break;
				case "allow multiple users":
					adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Allow multiple users", (boolean) columnData.getValue());
					break;
			}
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addImageColumn(AddImageColumnData addImageColumnData) throws InterruptedException
	{
		addColumn(addImageColumnData);
		if (addImageColumnData.getSelectMethod() != null)
		{
			adminIsheetAddColumnWeb.addColumnSelectImageColumnType(addImageColumnData.getSelectMethod());
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addImageColumn(Map<String, Object> imageColumnData) throws InterruptedException
	{
		addColumn(imageColumnData);
		for (Entry<String, Object> columnData : imageColumnData.entrySet())
		{
			switch (columnData.getKey().toLowerCase())
			{
				case "select method":
					adminIsheetAddColumnWeb.addColumnSelectImageColumnType((String) columnData.getValue());
					break;
			}
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addIsheetLinkColumn(AddIsheetLinkColumnData addIsheetLinkColumnData) throws InterruptedException
	{
		addColumn(addIsheetLinkColumnData);
		if (addIsheetLinkColumnData.getiSheetName() != null)
		{
			adminIsheetAddColumnWeb.selectIsheetForIsheetLink(addIsheetLinkColumnData.getiSheetName(), true);
		}
		if (addIsheetLinkColumnData.getDefaultLinkValue() != null)
		{
			adminIsheetAddColumnWeb.addColumnDefaultValue(addIsheetLinkColumnData.getDefaultLinkValue());
		}
		adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Allow users to rename links", addIsheetLinkColumnData.isAllowUsersToRenameLinks());
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addIsheetLinkColumn(Map<String, Object> isheetLinkColumnData) throws InterruptedException
	{
		addColumn(isheetLinkColumnData);
		for (Entry<String, Object> columnData : isheetLinkColumnData.entrySet())
		{
			System.out.println(columnData.getKey().toLowerCase());
			switch (columnData.getKey().toLowerCase())
			{
				case "isheet":
					adminIsheetAddColumnWeb.selectIsheetForIsheetLink(Arrays.asList(columnData.getValue().toString()), true);
					break;
				case "default link value":
					adminIsheetAddColumnWeb.addColumnDefaultValue((String) columnData.getValue());
					break;
				case "allow users to rename links":
					adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Allow users to rename links", (boolean) columnData.getValue());
					break;
			}
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addHyperlinkColumn(AddIsheetColumnData addIsheetColumnData) throws InterruptedException
	{
		addColumn(addIsheetColumnData);
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addHyperlinkColumn(Map<String, Object> isheetColumnData) throws InterruptedException
	{
		addColumn(isheetColumnData);
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addAttachmentColumn(AddIsheetColumnData addIsheetColumnData) throws InterruptedException
	{
		addColumn(addIsheetColumnData);
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addAttachmentColumn(Map<String, Object> isheetColumnData) throws InterruptedException
	{
		addColumn(isheetColumnData);
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addFileLinkColumn(AddIsheetColumnData addIsheetColumnData) throws InterruptedException
	{
		addColumn(addIsheetColumnData);
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addFileLinkColumn(Map<String, Object> isheetColumnData) throws InterruptedException
	{
		addColumn(isheetColumnData);
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addFolderLinkColumn(AddIsheetColumnData addIsheetColumnData) throws InterruptedException
	{
		addColumn(addIsheetColumnData);
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addFolderLinkColumn(Map<String, Object> isheetColumnData) throws InterruptedException
	{
		addColumn(isheetColumnData);
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addLookupColumn(AddLookupColumnData addLookupColumnData) throws InterruptedException
	{
		addColumn(addLookupColumnData);
		if (addLookupColumnData.getSheet() != null)
		{
			adminIsheetAddColumnWeb.addColumnSelectSheet(addLookupColumnData.getSheet());
		}
		if (addLookupColumnData.getSelectColumns() != null)
		{
			adminIsheetAddColumnWeb.selectLookupColumn(addLookupColumnData.getSelectColumns(), true);
		}
		if (addLookupColumnData.getView() != null)
		{
			adminIsheetAddColumnWeb.addLookupColumnSelectView(addLookupColumnData.getView());
		}
		adminIsheetAddColumnWeb.selectRestrictDelete(addLookupColumnData.isEnforceRelationshipBehavior());
		adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Allow multiple values", addLookupColumnData.isAllowMultipleValues());
		adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Display column name prefix", addLookupColumnData.isDisplayColumnNameprefix());
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addLookupColumn(Map<String, Object> lookupColumnData) throws InterruptedException
	{
		addColumn(lookupColumnData);
		for (Entry<String, Object> columnData : lookupColumnData.entrySet())
		{
			switch (columnData.getKey().toLowerCase())
			{
				case "sheet":
					adminIsheetAddColumnWeb.addColumnSelectSheet((String) columnData.getValue());
					break;
				case "select columns":
					adminIsheetAddColumnWeb.selectLookupColumn(Arrays.asList(columnData.getValue().toString()), true);
					break;
				case "view":
					adminIsheetAddColumnWeb.addLookupColumnSelectView((String) columnData.getValue());
					break;
				case "enforce relationship behavior":
					adminIsheetAddColumnWeb.selectRestrictDelete((boolean) columnData.getValue());
					break;
				case "allow multiple values":
					adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Allow multiple values", (boolean) columnData.getValue());
					break;
				case "display column name prefix":
					adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Display column name prefix", (boolean) columnData.getValue());
					break;
			}
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addJoinColumn(AddJoinColumnData addJoinColumnData) throws InterruptedException
	{
		addColumn(addJoinColumnData);
		if (addJoinColumnData.getLinkName() != null)
		{
			adminIsheetAddColumnWeb.addJoinColumnLinkName(addJoinColumnData.getLinkName());
		}
		if (addJoinColumnData.getSheet() != null)
		{
			adminIsheetAddColumnWeb.addColumnSelectSheet(addJoinColumnData.getSheet());
		}
		if (addJoinColumnData.getDisplayView() != null)
		{
			adminIsheetAddColumnWeb.addJoinColumnDisplayView(addJoinColumnData.getDisplayView());
		}
		addJoinCondition(addJoinColumnData);
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addJoinColumn(Map<String, Object> joinColumnData) throws InterruptedException
	{
		addColumn(joinColumnData);
		for (Entry<String, Object> columnData : joinColumnData.entrySet())
		{
			switch (columnData.getKey().toLowerCase())
			{
				case "link name":
					adminIsheetAddColumnWeb.addJoinColumnLinkName((String) columnData.getValue());
					break;
				case "list of sheet":
					adminIsheetAddColumnWeb.addColumnSelectSheet((String) columnData.getValue());
					break;
				case "display view":
					adminIsheetAddColumnWeb.addJoinColumnDisplayView((String) columnData.getValue());
					break;
			}
		}
		addJoinCondition(joinColumnData);
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public void addJoinCondition(AddJoinColumnData addJoinColumnData) throws InterruptedException
	{
		if (addJoinColumnData.getJoinConditionCurrentSheet() != null)
		{
			adminIsheetAddColumnWeb.selectCurrentSheetColumn(addJoinColumnData.getJoinConditionCurrentSheet());
		}
		if (addJoinColumnData.getJoinConditionTargetSheet() != null)
		{
			adminIsheetAddColumnWeb.selectTargetSheetColumn(addJoinColumnData.getJoinConditionTargetSheet());
			adminIsheetAddColumnWeb.clickAddJoin();
		}

	}

	public void addJoinCondition(Map<String, Object> joinColumnData) throws InterruptedException
	{
		for (Entry<String, Object> columnData : joinColumnData.entrySet())
		{
			switch (columnData.getKey().toLowerCase())
			{
				case "current sheet":
					adminIsheetAddColumnWeb.selectCurrentSheetColumn((String) columnData.getValue());
					break;
				case "target sheet":
					adminIsheetAddColumnWeb.selectTargetSheetColumn((String) columnData.getValue());
					break;
			}
		}
		adminIsheetAddColumnWeb.clickAddJoin();
	}

	public AdminIsheetManageColumnWeb addCalculationColumn(AddCalculationColumnData addCalculationColumnData) throws InterruptedException
	{
		addColumn(addCalculationColumnData);
		if (addCalculationColumnData.getDecimalPlaces() != null)
		{
			adminIsheetAddColumnWeb.addColumnSelectDecimalPlaces(addCalculationColumnData.getDecimalPlaces());
		}
		if (addCalculationColumnData.getFormula() != null && addCalculationColumnData.getColumnNamesOfFormula() != null)
		{
			adminIsheetAddColumnWeb.checkFormula(addCalculationColumnData.getFormula(), addCalculationColumnData.getColumnNamesOfFormula(), addCalculationColumnData.getExpectedResultOfFormula());
		}
		adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Show as percentage", addCalculationColumnData.isShowAsPercentage());
		adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Show thousand separators", addCalculationColumnData.isShowThousandSeparators());
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addCalculationColumn(Map<String, Object> calculationColumnData) throws InterruptedException
	{
		addColumn(calculationColumnData);
		for (Entry<String, Object> columnData : calculationColumnData.entrySet())
		{
			switch (columnData.getKey().toLowerCase())
			{
				case "select decimal places":
					adminIsheetAddColumnWeb.addColumnSelectDecimalPlaces((String) columnData.getValue());
					break;
				case "formula":
					adminIsheetAddColumnWeb.enterFormula((String) columnData.getValue(), calculationColumnData.get("formulaColumns").toString());
					break;
				case "show as percentage":
					adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Show as percentage", (boolean) columnData.getValue());
					break;
				case "show thousand separators":
					adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Show thousand separators", (boolean) columnData.getValue());
					break;
			}
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addAutoIncrementColumn(AddAutoIncrementColumnData addAutoIncrementColumnData) throws InterruptedException
	{
		addColumn(addAutoIncrementColumnData);
		if (addAutoIncrementColumnData.getStartValue() != null)
		{
			adminIsheetAddColumnWeb.enterStartValue(addAutoIncrementColumnData.getStartValue());
		}
		if (addAutoIncrementColumnData.getPrefix() != null)
		{
			adminIsheetAddColumnWeb.enterPrefix(addAutoIncrementColumnData.getPrefix());
		}
		if (addAutoIncrementColumnData.getSuffix() != null)
		{
			adminIsheetAddColumnWeb.enterSuffix(addAutoIncrementColumnData.getSuffix());
		}
		if (addAutoIncrementColumnData.getMinLength() != null)
		{
			adminIsheetAddColumnWeb.enterMinLength(addAutoIncrementColumnData.getMinLength());
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	public AdminIsheetManageColumnWeb addAutoIncrementColumn(Map<String, Object> autoIncrementColumnData) throws InterruptedException
	{
		addColumn(autoIncrementColumnData);
		for (Entry<String, Object> columnData : autoIncrementColumnData.entrySet())
		{
			switch (columnData.getKey().toLowerCase())
			{
				case "start value":
					adminIsheetAddColumnWeb.enterStartValue((String) columnData.getValue());
					break;
				case "prefix":
					adminIsheetAddColumnWeb.enterPrefix((String) columnData.getValue());
					break;
				case "suffix":
					adminIsheetAddColumnWeb.enterSuffix((String) columnData.getValue());
					break;
				case "min length":
					adminIsheetAddColumnWeb.enterMinLength((String) columnData.getValue());
					break;
			}
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}

	private void addColumn(AddIsheetColumnData addIsheetColumnData) throws InterruptedException
	{
		if (addIsheetColumnData.getColumnName() != null)
		{
			adminIsheetAddColumnWeb.addColumnName(addIsheetColumnData.getColumnName());
		}
		if (addIsheetColumnData.getColumnType() != null)
		{
			adminIsheetAddColumnWeb.selectColumnType(addIsheetColumnData.getColumnType());
		}
		if (addIsheetColumnData.getSection() != null)
		{
			adminIsheetAddColumnWeb.selectSection(addIsheetColumnData.getSection());
		}
		if (addIsheetColumnData.getDescription() != null)
		{
			adminIsheetAddColumnWeb.addColumnDescription(addIsheetColumnData.getDescription());
		}
		if (addIsheetColumnData.getDefaultValue() != null)
		{
			adminIsheetAddColumnWeb.addColumnDefaultValue(addIsheetColumnData.getDefaultValue());
		}
		if (addIsheetColumnData.getColumnWidth() != null)
		{
			adminIsheetAddColumnWeb.addColumnWidth(addIsheetColumnData.getColumnWidth());
		}

		adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Mandatory", addIsheetColumnData.isMandatory());
		adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Allow search", addIsheetColumnData.isAllowSearch());
		adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Add to the default view", addIsheetColumnData.isAddToDefaultView());
		addColumnCondition(addIsheetColumnData);
	}

	private void addColumn(Map<String, Object> iSheetColumnData) throws InterruptedException
	{
		for (Entry<String, Object> columnData : iSheetColumnData.entrySet())
		{
			switch (columnData.getKey().toLowerCase())
			{
				case "column name":
					adminIsheetAddColumnWeb.addColumnName((String) columnData.getValue());
					break;
				case "column type":
					adminIsheetAddColumnWeb.selectColumnType((String) columnData.getValue());
					break;
				case "select section":
					adminIsheetAddColumnWeb.selectSection((String) columnData.getValue());
					break;
				case "description":
					adminIsheetAddColumnWeb.addColumnDescription((String) columnData.getValue());
					break;
				case "default value":
					adminIsheetAddColumnWeb.addColumnDefaultValue((String) columnData.getValue());
					break;
				case "column width":
					adminIsheetAddColumnWeb.addColumnWidth((String) columnData.getValue());
					break;
				case "mandatory":
					adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Mandatory", (boolean) columnData.getValue());
					break;
				case "allow search":
					adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Allow search", (boolean) columnData.getValue());
					break;
				case "add to the default view":
					adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions("Add to the default view", (boolean) columnData.getValue());
					break;
			}
		}
		addColumnCondition(iSheetColumnData);
	}

	void addColumnCondition(AddIsheetColumnData addIsheetColumnData) throws InterruptedException
	{
		if (addIsheetColumnData.getColumnConditionDisplayOption() != null)
		{
			adminIsheetAddColumnWeb.setColumnDisplaySetting(addIsheetColumnData.getColumnConditionDisplayOption());
		}
		if (addIsheetColumnData.getColumnConditionType() != null)
		{
			adminIsheetAddColumnWeb.setColumnConditionType(addIsheetColumnData.getColumnConditionType());
		}
		if (addIsheetColumnData.getColumnConditionFilterOption() != null)
		{
			adminIsheetAddColumnWeb.setColumnConditionFilterBy(addIsheetColumnData.getColumnConditionFilterOption());
		}
		if (addIsheetColumnData.getColumnConditionFilterOperator() != null)
		{
			adminIsheetAddColumnWeb.setColumnConditionFilterOperator(addIsheetColumnData.getColumnConditionFilterOperator());
		}
		if (addIsheetColumnData.getColumnConditionFilterValue() != null)
		{
			adminIsheetAddColumnWeb.setColumnConditionFilterValue(addIsheetColumnData.getColumnConditionFilterValue());
			adminIsheetAddColumnWeb.clickAddColumnCondition();
		}
		if (addIsheetColumnData.getColumnConditionFilterDate() != null)
		{
			adminIsheetAddColumnWeb.setColumnConditionFilterDate(addIsheetColumnData.getColumnConditionFilterDate());
			adminIsheetAddColumnWeb.clickAddColumnCondition();
		}
		if (addIsheetColumnData.getColumnConditionFilterTime() != null)
		{
			adminIsheetAddColumnWeb.setColumnConditionFilterTime(addIsheetColumnData.getColumnConditionFilterTime());
			adminIsheetAddColumnWeb.clickAddColumnCondition();
		}
	}

	void addColumnCondition(Map<String, Object> iSheetColumnData) throws InterruptedException
	{
		for (Entry<String, Object> columnData : iSheetColumnData.entrySet())
		{
			switch (columnData.getKey().toLowerCase())
			{
				case "column condition display option":
					adminIsheetAddColumnWeb.setColumnDisplaySetting((String) columnData.getValue());
					break;
				case "column condition type":
					adminIsheetAddColumnWeb.setColumnConditionType((String) columnData.getValue());
					break;
				case "filter option":
					adminIsheetAddColumnWeb.setColumnConditionFilterBy((String) columnData.getValue());
					break;
				case "filter operator":
					adminIsheetAddColumnWeb.setColumnConditionFilterOperator((String) columnData.getValue());
					break;
				case "filter value":
					adminIsheetAddColumnWeb.setColumnConditionFilterValue((String) columnData.getValue());
					adminIsheetAddColumnWeb.clickAddColumnCondition();
					break;
				case "filter date":
					adminIsheetAddColumnWeb.setColumnConditionFilterDate(columnData.getValue());
					adminIsheetAddColumnWeb.clickAddColumnCondition();
					break;
				case "filter time":
					adminIsheetAddColumnWeb.setColumnConditionFilterTime(columnData.getValue());
					adminIsheetAddColumnWeb.clickAddColumnCondition();
					break;
			}
		}
	}

	public AdminIsheetManageColumnWeb addChoiceColumn(Map<String, Object> choiceColumnData) throws Exception
	{
		addColumn(choiceColumnData);
		for (Entry<String, Object> columnData : choiceColumnData.entrySet())
		{
			switch (columnData.getKey().toLowerCase())
			{
				case "add choice":
					System.out.println("get Key" + columnData.getKey() + "get value" + columnData.getValue());
					for (Entry<String, String> columnData1 : ((LinkedHashMap<String, String>) columnData.getValue()).entrySet())
					{
						adminIsheetAddColumnWeb.addChoice(columnData1.getKey(), columnData1.getValue());
					}
					break;
			}
		}
		return adminIsheetAddColumnWeb.clickSaveOnAddColumn();
	}
}
