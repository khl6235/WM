package com.intern.assignment.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.intern.assignment.common.JDBCUtil;
import com.intern.assignment.user.UserVO;

@Repository
public class UserDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	//sql
	private final String USER_GET = "select * from user where id=? and password=?";
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
	//methods
	//enroll user
	public UserVO getUser(UserVO vo) {
		UserVO user = null;
//		Object[] args = {vo.getId(), vo.getPassword()};
		try {
			System.out.println("===> JDBC getUser()!");
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
			}
//			return jdbcTemplate.queryForObject(USER_GET, args, new UserRowMapper());
		} catch(Exception e) {
			e.printStackTrace();
//			return null;
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return user;
		
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