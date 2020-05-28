package kr.ac.mjc.sungbin.springmvc.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	
	private final String LIST_BOARDS = "select seq, title, writer, cnt from board order by seq desc limit ?,?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * User rowMapper
	 */
	private final RowMapper<Board> BOARD_ROW_MAPPER = new BeanPropertyRowMapper<>(
			Board.class);
	
	public List<Board> listBoards(int offset, int count) {
		return jdbcTemplate.query(LIST_BOARDS, BOARD_ROW_MAPPER, offset, count);
	}


}
