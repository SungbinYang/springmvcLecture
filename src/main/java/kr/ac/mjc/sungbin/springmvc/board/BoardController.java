package kr.ac.mjc.sungbin.springmvc.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

	@Autowired
	private BoardDao boardDao;

	/**
	 * 게시물목록
	 */
	@GetMapping("/board/boardList")
	public void boardList(
			@RequestParam(name = "count", required = false, defaultValue = "10") int count,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			Model model) {
		int offset = (page - 1) * count;
		List<Board> boardList = boardDao.listBoards(offset, count);
		model.addAttribute("boardList", boardList);
	}
}
