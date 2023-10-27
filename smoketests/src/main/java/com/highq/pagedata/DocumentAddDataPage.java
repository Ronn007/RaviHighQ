package com.highq.pagedata;

import com.highq.base.CollaborateLabel.DocumentNotifications;

public class DocumentAddDataPage
{

	private String fileuploadpath;
	private String fileName;
	private DocumentNotifications documentNotifications;
	private String filedisclaimer;
	private String tag;
	private String versionNotes;

	public String getFileuploadpath()
	{
		return fileuploadpath;
	}

	public void setFileuploadpath(String fileuploadpath)
	{
		this.fileuploadpath = fileuploadpath;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public DocumentNotifications getDocumentNotifications()
	{
		return documentNotifications;
	}

	public void setDocumentNotifications(DocumentNotifications documentNotifications)
	{
		this.documentNotifications = documentNotifications;
	}

	public String getFiledisclaimer()
	{
		return filedisclaimer;
	}

	public void setFiledisclaimer(String filedisclaimer)
	{
		this.filedisclaimer = filedisclaimer;
	}

	public String getTag()
	{
		return tag;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

	public String getVersionNotes()
	{
		return versionNotes;
	}

	public void setVersionNotes(String versionNotes)
	{
		this.versionNotes = versionNotes;
	}

	/**
	 * Used to set default value for all variables
	 */
	public void clean()
	{
		fileuploadpath = null;
		fileName = null;
		filedisclaimer = null;
		tag = null;
		documentNotifications = null;
		versionNotes = null;
	}

}
