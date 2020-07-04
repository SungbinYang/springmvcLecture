package kr.ac.mjc.sungbin.springmvc.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * p.224 BoardDAOSpring.java 수정
 * 
 * @author Jacob
 */
@Repository
public class BoardDao {

	private final String LIST_BOARDS = "select seq, title, left(regdate,16) regdate, id, writer, cnt from board order by seq desc limit ?, ?";
	private final String SEARCH_BOARDS = "select seq, title, left(regdate,16) regdate, id, writer, cnt from board "
			+ "where match(title) against(:keyword) or match(content) against(:keyword) order by seq desc limit :offset, :count";

	private final String GET_BOARD = "select seq, title,content, left(regdate,16) regdate, id, writer, cnt from board where seq=?";
	private final String ADD_BOARD = "insert board(title, content, id, writer) values(:title, :content, :id, :writer)";
	private final String UPDATE_BOARD = "update board set title=:title, content=:content where seq=:seq";
	private final String DELETE_BOARD = "delete from board where seq=?";
	private final String INCREASE_COUNT = "update board set cnt=cnt+1 where seq=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * board 테이블과 board 오브젝트를 매핑
	 */
	private final RowMapper<Board> BOARD_ROW_MAPPER = new BeanPropertyRowMapper<>(
			Board.class);

	/**
	 * 게시글 목록 조회
	 * 
	 * @param page  페이지
	 * @param count 갯수
	 */
	public List<Board> listBoards(Search search) {
		return jdbcTemplate.query(LIST_BOARDS, BOARD_ROW_MAPPER,
				search.getOffset(), search.getCount());
	}

	/**
	 * 게시글 검색
	 */
	public List<Board> searchBoards(Search search) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(search);
		return namedParameterJdbcTemplate.query(SEARCH_BOARDS, params,
				BOARD_ROW_MAPPER);
	}

	/**
	 * 게시글 1개 조회
	 */
	public Board getBoard(String seq) {
		return jdbcTemplate.queryForObject(this.GET_BOARD, BOARD_ROW_MAPPER,
				seq);
	}

	/**
	 * 게시글 추가
	 */
	public int addBoard(Board board) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(board);
		return namedParameterJdbcTemplate.update(ADD_BOARD, params);
	}

	/**
	 * 게시글 수정
	 */
	public int updateBoard(Board board) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(board);
		return namedParameterJdbcTemplate.update(UPDATE_BOARD, params);
	}

	/**
	 * 게시글 삭제
	 */
	public int deleteBoard(String seq) {
		return jdbcTemplate.update(DELETE_BOARD, seq);
	}

	/**
	 * 조회수 증가
	 */
	public void increaseCount(String seq) {
		jdbcTemplate.update(INCREASE_COUNT, seq);
	}
}
