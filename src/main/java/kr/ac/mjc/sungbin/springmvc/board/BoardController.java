package kr.ac.mjc.sungbin.springmvc.board;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.ac.mjc.sungbin.springmvc.user.User;


/**
 * p.371 BoardController.java 수정
 * 
 * @author Sungbin
 */
@Controller
public class BoardController {

	@Autowired
	private BoardDao boardDao;

	private final Logger logger = LogManager.getLogger();

	/**
	 * 게시글 목록
	 */
	@GetMapping("/board/boardList")
	public void boardList(Search search, Model model) {
		List<Board> boardList = null;
		if ("".equals(search.getKeyword()))
			boardList = boardDao.listBoards(search);
		else
			boardList = boardDao.searchBoards(search);
		model.addAttribute("boardList", boardList);
	}

	/**
	 * 게시글 조회
	 */
	@GetMapping("/board/boardInfo")
	public void boardInfo(String seq, Model model) {
		boardDao.increaseCount(seq);
		Board board = boardDao.getBoard(seq);
		model.addAttribute("board", board);
	}

	/**
	 * 게시글 쓰기
	 */
	@PostMapping("/board/s/addBoard")
	public String addBoard(Board board, @SessionAttribute User user) {
		board.setId(user.getId());
		board.setWriter(user.getName());
		boardDao.addBoard(board);
		return "redirect:/app/board/boardList";
	}

	/**
	 * 게시글 수정화면
	 */
	@GetMapping("/board/s/boardEdit")
	public String boardEdit(String seq, @SessionAttribute User user,
			Model model) {
		// 권한 체크
		Board board = boardDao.getBoard(seq);
		if (!user.getId().equals(board.getId()))
			return "error/401";

		model.addAttribute("board", board);
		return "board/s/boardEdit";
	}

	/**
	 * 게시글 수정
	 */
	@PostMapping("/board/s/updateBoard")
	public String updateBoard(Board board, @SessionAttribute User user) {
		// 권한 체크
		Board b = boardDao.getBoard(board.getSeq());
		if (!user.getId().equals(b.getId()))
			return "error/401";

		boardDao.updateBoard(board);
		return "redirect:/app/board/boardInfo?seq=" + board.getSeq();
	}

	/**
	 * 게시글 삭제
	 */
	@GetMapping("/board/s/deleteBoard")
	public String deleteBoard(String seq, @SessionAttribute User user) {
		// 권한 체크
		Board board = boardDao.getBoard(seq);
		if (!user.getId().equals(board.getId()))
			return "error/401";

		boardDao.deleteBoard(seq);
		return "redirect:/app/board/boardList";
	}
}
