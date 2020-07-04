package kr.ac.mjc.sungbin.springmvc.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	// 회원목록
	private final String LIST_USERS = "select id, email, name from user order by id desc limit ?,?";
	// 회원정보 조회 (아이디로 조회)
	private final String GET_USER_BY_ID = "select id, email, name from user where id=?";
	// 회원가입
	private final String ADD_USER = "insert user(email, password, name) values(:email, sha2(:password,256), :name)";
	// 회원정보 수정
	private final String UPDATE_USER = "update user set email=:email, name=:name where id=:id";
	// 회원탈퇴
	private final String DELETE_USER = "delete from user where id=?";

	// 이메일, 비밀번호로 조회 (로그인 할 때 사용)
	private final String GET_USER_BY_EMAIL_PASSWORD = "select id, email, name from user where (email, password) = (?, sha2(?,256))";
	// 비밀번호 수정
	private final String UPDATE_PASSWORD = "update user set password=sha2(?,256) where (id, password) = (?, sha2(?,256))";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * User rowMapper
	 */
	private final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(
			User.class);

	/**
	 * 회원목록
	 */
	public List<User> listUsers(int offset, int count) {
		return jdbcTemplate.query(LIST_USERS, USER_ROW_MAPPER, offset, count);
	}

	/**
	 * 회원정보 조회 (아이디로 조회)
	 */
	public User getUserById(String id) {
		return jdbcTemplate.queryForObject(GET_USER_BY_ID, USER_ROW_MAPPER, id);
	}

	/**
	 * 회원가입
	 */
	public void addUser(User user) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		namedParameterJdbcTemplate.update(ADD_USER, params);
	}

	/**
	 * 회원정보 수정
	 */
	public void updateUser(User user) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		namedParameterJdbcTemplate.update(UPDATE_USER, params);
	}

	/**
	 * 회원탈퇴
	 */
	public void deleteUser(String id) {
		jdbcTemplate.update(DELETE_USER, id);
	}

	/**
	 * 이메일, 비밀번호로 조회 (로그인 할 때 사용)
	 */
	public User getUserByEmailAndPassword(String email, String password) {
		return jdbcTemplate.queryForObject(GET_USER_BY_EMAIL_PASSWORD,
				USER_ROW_MAPPER, email, password);
	}

	/**
	 * 비밀번호 수정
	 */
	public int updatePassword(String id, String currentPassword,
			String newPassword) {
		return jdbcTemplate.update(UPDATE_PASSWORD, newPassword, id,
				currentPassword);
	}
}
