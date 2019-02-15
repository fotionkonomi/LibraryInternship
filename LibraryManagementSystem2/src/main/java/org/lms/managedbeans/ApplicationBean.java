package org.lms.managedbeans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "applicationBean")
public class ApplicationBean {

	private Date date;
    private DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public DateFormat getSdf() {
		return sdf;
	}

	public void setSdf(DateFormat sdf) {
		this.sdf = sdf;
	}
	
	public void test() {
		date = new Date();
	}
	
	
	
}
