package com.highq.pagedata;

import java.util.ArrayList;
import java.util.List;

public class TaskAddDataPage
{
	/** Declare for task title */
	private String taskTitle = "";
	
	/** Declare for task Description */
	private String taskDescription = "";

	/** Declare for description of task title */
	private String taskContents = "";

	/** Declare for task list */
	private String taskList = "";

	/** Declare for task assignee */
	private String assignee = "";

	/** Declare for task due date */
	private String taskDueDate = "";

	/** Declare for task start date */
	private String taskStartDate = "";

	/** Declare for task remainder */
	private int taskReminder = 0;

	/** Declare for task priority */
	private String taskPriority = "";

	/** Declare for site from which task belong */
	private String taskSite = "";

	/** Declare for task status */
	private String taskStatus = "";

	/** Declare for task tag */
	private String taskTag = "";

	/** Declare for task attachment */
	private String attachment = "";

	/** Declare for multiple attachement */
	private List<String> attachments = new ArrayList<>();

	/** Declare for task comment */
	private String taskComment = "";

	/** Declare for attachment count */
	private int attachmentCount = 0;

	/** Declare for comment count */
	private int commentCount = 0;

	/** Declare for attachment size */
	private int attachmentSize = 0;

	/** Declare for attachment author */
	private String attachmentAuthor = "";

	/** Declare for attachment date */
	private String attachmentDate = "";

	/** Declare for attachment version */
	private String attachmentVersion = "";

	/** Declare for created Task UserName */
	private String createdTaskUserName = "";

	/** Declare for updated Task UserName */
	private String updatedTaskUserName = "";

	/** Declare for created Task date */
	private String createdTaskDate = "";

	/** Declare for updated Task UserName */
	private String updatedTaskDate = "";

	private String color = "";

	private String id = null;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getCreatedTaskUserName()
	{
		return createdTaskUserName;
	}

	public void setCreatedTaskUserName(String createdTaskUserName)
	{
		this.createdTaskUserName = createdTaskUserName;
	}

	public String getUpdatedTaskUserName()
	{
		return updatedTaskUserName;
	}

	public void setUpdatedTaskUserName(String updatedTaskUserName)
	{
		this.updatedTaskUserName = updatedTaskUserName;
	}

	public String getUpdatedTaskDate()
	{
		return updatedTaskDate;
	}

	public void setUpdatedTaskDate(String updatedTaskDate)
	{
		this.updatedTaskDate = updatedTaskDate;
	}

	public String getCreatedTaskDate()
	{
		return createdTaskDate;
	}

	public void setCreatedTaskDate(String createdTaskDate)
	{
		this.createdTaskDate = createdTaskDate;
	}

	public String getTaskUserName()
	{
		return createdTaskUserName;
	}

	public void setTaskUserName(String taskUserName)
	{
		this.createdTaskUserName = taskUserName;
	}

	public int getAttachmentSize()
	{
		return attachmentSize;
	}

	public void setAttachmentSize(int attachmentSize)
	{
		this.attachmentSize = attachmentSize;
	}

	public String getAttachmentAuthor()
	{
		return attachmentAuthor;
	}

	public void setAttachmentAuthor(String attachmentAuthor)
	{
		this.attachmentAuthor = attachmentAuthor;
	}

	public String getAttachmentDate()
	{
		return attachmentDate;
	}

	public void setAttachmentDate(String attachmentDate)
	{
		this.attachmentDate = attachmentDate;
	}

	public String getAttachmentVersion()
	{
		return attachmentVersion;
	}

	public void setAttachmentVersion(String attachmentVersion)
	{
		this.attachmentVersion = attachmentVersion;
	}

	public int getCommentCount()
	{
		return commentCount;
	}

	public void setCommentCount(int commentCount)
	{
		this.commentCount = commentCount;
	}

	public int getAttachmentCount()
	{
		return attachmentCount;
	}

	public void setAttachmentCount(int attachmentCount)
	{
		this.attachmentCount = attachmentCount;
	}

	public String getTaskComment()
	{
		return taskComment;
	}

	public void setTaskComment(String taskComment)
	{
		this.taskComment = taskComment;
	}

	public String getAttachment()
	{
		return attachment;
	}

	public void setAttachment(String attachment)
	{
		this.attachment = attachment;
	}

	public String getTaskTag()
	{
		return taskTag;
	}

	public void setTaskTag(String taskTag)
	{
		this.taskTag = taskTag;
	}

	public String getTaskStatus()
	{
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus)
	{
		this.taskStatus = taskStatus;
	}

	public String getTaskTitle()
	{
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle)
	{
		this.taskTitle = taskTitle;
	}

	public String getTaskContents()
	{
		return taskContents;
	}

	public void setTaskContents(String taskContents)
	{
		this.taskContents = taskContents;
	}

	public String getTaskList()
	{
		return taskList;
	}

	public void setTaskList(String taskList)
	{
		this.taskList = taskList;
	}

	public String getAssignee()
	{
		return assignee;
	}

	public void setAssignee(String assignee)
	{
		this.assignee = assignee;
	}

	public String getTaskDueDate()
	{
		return taskDueDate;
	}

	public void setTaskDueDate(String taskDueDate)
	{
		this.taskDueDate = taskDueDate;
	}

	public String getTaskStartDate()
	{
		return taskStartDate;
	}

	public void setTaskStartDate(String taskStartDate)
	{
		this.taskStartDate = taskStartDate;
	}

	public int getTaskReminder()
	{
		return taskReminder;
	}

	public void setTaskReminder(int taskReminder)
	{
		this.taskReminder = taskReminder;
	}

	public String getTaskPriority()
	{
		return taskPriority;
	}

	public void setTaskPriority(String priority)
	{
		this.taskPriority = priority.toString();
	}

	public String getTaskSite()
	{
		return taskSite;
	}

	public void setTaskSite(String taskSite)
	{
		this.taskSite = taskSite;
	}

	public List<String> getAttachments()
	{
		return attachments;
	}

	public void setAttachments(List<String> attachments)
	{
		this.attachments.addAll(attachments);
	}
	
	/**
	 * @return the taskDescription
	 */
	public String getTaskDescription()
	{
		return taskDescription;
	}

	/**
	 * @param taskDescription the taskDescription to set
	 */
	public void setTaskDescription(String taskDescription)
	{
		this.taskDescription = taskDescription;
	}

	/**
	 * Used to set default value for all variables
	 */
	public void clean()
	{
		taskTitle = "";
		taskContents = "";
		taskList = "";
		assignee = "";
		taskDueDate = "";
		taskStartDate = "";
		taskReminder = 0;
		taskPriority = "";
		taskSite = "";
		taskStatus = "";
		taskTag = "";
		attachment = "";
		attachments.clear();
	}

}
