package com.intern.assignment.user.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.intern.assignment.user.UserVO;

@Repository
public class UserDAO {
	
	//sql
	private final String USER_GET = "select * from user where id=? and password=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//methods
	//enroll user
	public UserVO getUser(UserVO vo) {
		System.out.println("===> JDBC getUser()!");
		Object[] args = {vo.getId(), vo.getPassword()};
		
		return jdbcTemplate.queryForObject(USER_GET, args, new UserRowMapper());
	}

}

class UserRowMapper implements RowMapper<UserVO>{
	public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException{
		UserVO user = new UserVO();
		user.setId(rs.getString("ID"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setUserIdx(rs.getInt("USERIDX"));
		
		return user;
	}
}