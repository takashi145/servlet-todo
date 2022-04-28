package model;

import java.util.Date;


public class Task {
	private int id;
	private String task;
	private String explanation;
	private Date deadline;
	
	public Task(int id, String task, String explanation, Date deadline2) {
		this.id = id;
		this.task = task;
		this.explanation = explanation;
		this.deadline = deadline2;
	}

	public int getId() {
		return id;
	}

	public String getTask() {
		return task;
	}

	public String getExplanation() {
		return explanation;
	}

	public Date getDeadline() {
		return deadline;
	}
	
	
	
	
}
