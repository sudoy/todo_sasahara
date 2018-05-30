package todo.beans;

import java.sql.Date;

public class Todo {
	private int id;
	private String title;
	private String detail;
	private String imp;
	private Date limit_date;

	public Todo(int id, String title, String imp, Date limit_date) {
		super();
		this.id = id;
		this.title = title;
		this.imp = imp;
		this.limit_date = limit_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImp() {
		return imp;
	}

	public void setImp(String imp) {
		this.imp = imp;
	}

	public Date getLimit_date() {
		return limit_date;
	}

	public void setLimit_date(Date limit_date) {
		this.limit_date = limit_date;
	}


}
