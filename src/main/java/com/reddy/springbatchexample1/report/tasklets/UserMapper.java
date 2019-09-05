package com.reddy.springbatchexample1.report.tasklets;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.reddy.springbatchexample1.model.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet result, int arg1) throws SQLException {
		User usr = new User();
		usr.setName(result.getString("firstname"));
		usr.setId(result.getInt("id"));
		usr.setDept(result.getString("address"));
		usr.setSalary(Integer.parseInt(result.getString("salary")));
		usr.setBbgid(result.getString("bbgid"));
		usr.setCusip(result.getString("cusip"));
		usr.setSedol(result.getString("sedol"));
		usr.setIsin(result.getString("isin"));
		return usr;
	}

}
