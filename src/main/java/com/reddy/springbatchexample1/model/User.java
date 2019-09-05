package com.reddy.springbatchexample1.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;

	public User(Integer id, String name, String dept, String cusip, String isin, String sedol, String bbgid,
			Integer salary, Date time) {
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.cusip = cusip;
		this.isin = isin;
		this.sedol = sedol;
		this.bbgid = bbgid;
		this.salary = salary;
		this.time = time;
	}

	private String name;
	private String dept;
	private String cusip;
	private String isin;
	private String sedol;
	private String bbgid;
	private Integer salary;
	private Date time;

	public User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("User{");
		sb.append("id=").append(id);
		sb.append(", name='").append(name).append('\'');
		sb.append(", dept='").append(dept).append('\'');
		sb.append(", cusip=").append(cusip);
		sb.append(", bbgid=").append(bbgid);
		sb.append(", cusip=").append(cusip);
		sb.append(", sedol=").append(sedol);
		sb.append(", isin=").append(isin);
		sb.append('}');
		return sb.toString();
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public String getSedol() {
		return sedol;
	}

	public void setSedol(String sedol) {
		this.sedol = sedol;
	}

	public String getBbgid() {
		return bbgid;
	}

	public void setBbgid(String bbgid) {
		this.bbgid = bbgid;
	}
}
