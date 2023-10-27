package com.highq.pagedata;

import java.util.List;
import java.util.Map;

public class IsheetData
{

	private Map<String, String> singleLineText;

	private Map<String, String> multiLineText;

	private Map<String, String> number;

	private Map<String, String> dateAndTime;

	private Map<String, String[]> addUserLookUpRecord;

	private Map<String, String[]> removeUserLookUpRecord;

	private Map<String, String> hyperLinkData;

	private Map<String, String[]> attachmentData;

	private Map<String, String[]> removeAttachmentData;

	private Map<String, String[]> fileLinkData;

	private Map<String, String[]> removeFileLinkData;

	private Map<String, String[]> folderLinkData;

	private Map<String, String[]> removeFolderLinkData;

	private Map<String, Map<String, List<List<String>>>> isheetLinkData;

	private List<List<String>> removeIsheetLinkData;

	private Map<String, String[]> joinData;

	private Map<String, String> calculationData;

	private Map<String, String> autoIncrementData;

	private Map<String, Map<List<String>, Boolean>> lookUpData;

	private Map<String, Map<List<String>, Boolean>> removeLookUpData;

	private Map<String, String> imageColumnData;

	private Map<String, String> choiceColumnData;

	public Map<String, String> getChoiceColumnData()
	{
		return choiceColumnData;
	}

	public void setChoiceColumnData(Map<String, String> choiceColumnData)
	{
		this.choiceColumnData = choiceColumnData;
	}

	private String[] removeImageData;

	// Choice Column Pending

	public Map<String, String> getImageColumnData()
	{
		return imageColumnData;
	}

	public void setImageColumnData(Map<String, String> imageColumnData)
	{
		this.imageColumnData = imageColumnData;
	}

	public Map<String, Map<List<String>, Boolean>> getLookUpData()
	{
		return lookUpData;
	}

	public void setLookUpData(Map<String, Map<List<String>, Boolean>> lookUpData)
	{
		this.lookUpData = lookUpData;
	}

	public Map<String, Map<List<String>, Boolean>> getRemoveLookUpData()
	{
		return removeLookUpData;
	}

	public void setRemoveLookUpData(Map<String, Map<List<String>, Boolean>> removeLookUpData)
	{
		this.removeLookUpData = removeLookUpData;
	}

	public Map<String, String> getSingleLineText()
	{
		return singleLineText;
	}

	public void setSingleLineText(Map<String, String> singleLineText)
	{
		this.singleLineText = singleLineText;
	}

	public Map<String, String> getMultiLineText()
	{
		return multiLineText;
	}

	public void setMultiLineText(Map<String, String> multiLineText)
	{
		this.multiLineText = multiLineText;
	}

	public Map<String, String> getNumber()
	{
		return number;
	}

	public void setNumber(Map<String, String> number)
	{
		this.number = number;
	}

	public Map<String, String> getDateAndTime()
	{
		return dateAndTime;
	}

	public void setDateAndTime(Map<String, String> dateAndTime)
	{
		this.dateAndTime = dateAndTime;
	}

	public Map<String, String> getHyperLinkData()
	{
		return hyperLinkData;
	}

	public void setHyperLinkData(Map<String, String> hyperLinkData)
	{
		this.hyperLinkData = hyperLinkData;
	}

	public Map<String, String[]> getAttachmentData()
	{
		return attachmentData;
	}

	public void setAttachmentData(Map<String, String[]> attachmentData)
	{
		this.attachmentData = attachmentData;
	}

	public Map<String, String[]> getRemoveAttachmentData()
	{
		return removeAttachmentData;
	}

	public void setRemoveAttachmentData(Map<String, String[]> removeAttachmentData)
	{
		this.removeAttachmentData = removeAttachmentData;
	}

	public Map<String, String[]> getFileLinkData()
	{
		return fileLinkData;
	}

	public void setFileLinkData(Map<String, String[]> fileLinkData)
	{
		this.fileLinkData = fileLinkData;
	}

	public Map<String, String[]> getRemoveFileLinkData()
	{
		return removeFileLinkData;
	}

	public void setRemoveFileLinkData(Map<String, String[]> removeFileLinkData)
	{
		this.removeFileLinkData = removeFileLinkData;
	}

	public Map<String, String[]> getFolderLinkData()
	{
		return folderLinkData;
	}

	public void setFolderLinkData(Map<String, String[]> folderLinkData)
	{
		this.folderLinkData = folderLinkData;
	}

	public Map<String, String[]> getRemoveFolderLinkData()
	{
		return removeFolderLinkData;
	}

	public void setRemoveFolderLinkData(Map<String, String[]> removeFolderLinkData)
	{
		this.removeFolderLinkData = removeFolderLinkData;
	}

	public Map<String, String[]> getJoinData()
	{
		return joinData;
	}

	public void setJoinData(Map<String, String[]> joinData)
	{
		this.joinData = joinData;
	}

	public Map<String, String> getCalculationData()
	{
		return calculationData;
	}

	public void setCalculationData(Map<String, String> calculationData)
	{
		this.calculationData = calculationData;
	}

	public Map<String, String> getAutoIncrementData()
	{
		return autoIncrementData;
	}

	public void setAutoIncrementData(Map<String, String> autoIncrementData)
	{
		this.autoIncrementData = autoIncrementData;
	}

	public Map<String, String[]> getAddUserLookUpRecord()
	{
		return addUserLookUpRecord;
	}

	public void setAddUserLookUpRecord(Map<String, String[]> addUserLookUpRecord)
	{
		this.addUserLookUpRecord = addUserLookUpRecord;
	}

	public Map<String, String[]> getRemoveUserLookUpRecord()
	{
		return removeUserLookUpRecord;
	}

	public void setRemoveUserLookUpRecord(Map<String, String[]> removeUserLookUpRecord)
	{
		this.removeUserLookUpRecord = removeUserLookUpRecord;
	}

	public Map<String, Map<String, List<List<String>>>> getIsheetLinkData()
	{
		return isheetLinkData;
	}

	public void setIsheetLinkData(Map<String, Map<String, List<List<String>>>> isheetLinkData)
	{
		this.isheetLinkData = isheetLinkData;
	}

	public List<List<String>> getRemoveIsheetLinkData()
	{
		return removeIsheetLinkData;
	}

	public void setRemoveIsheetLinkData(List<List<String>> removeIsheetLinkData)
	{
		this.removeIsheetLinkData = removeIsheetLinkData;
	}

	public String[] getRemoveImageData()
	{
		return removeImageData;
	}

	public void setRemoveImageData(String[] removeImageData)
	{
		this.removeImageData = removeImageData;
	}
}
