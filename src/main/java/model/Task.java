package model;

import java.sql.Date;

public class Task {
	private int id;
	private String title;
	private String description;
	private Date deadline;
	private Boolean status;
	
	
	public Task(String title, String description, Date deadline, Boolean status) {
		this.title = title;
		this.description = description;
		this.deadline = deadline;
		this.status = status;
	}

	public Task(int id, String title, String description, Date deadline, Boolean status) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.deadline = deadline;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Date getDeadline() {
		return deadline;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
}
