package kr.ac.mjc.sungbin.springmvc.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;

	private final Logger logger = LogManager.getLogger();

	/**
	 * Default mapping.<br>
	 * 다른 매핑이 안된 URI는 모두 여기에 매핑된다. 기본 뷰로 forward
	 */
	@GetMapping("/**")
	public void mapDefault(HttpServletRequest request) {
		logger.debug("Default mapping : {}", request.getPathInfo());
	}

	/**
	 * 회원목록
	 */
	@GetMapping("/user/userList")
	public void userList(
			@RequestParam(name = "count", required = false, defaultValue = "10") int count,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			Model model) {
		int offset = (page - 1) * count;
		List<User> userList = userDao.listUsers(offset, count);
		model.addAttribute("userList", userList);
		// forward /WEB-INF/jsp/user/userList.jsp
	}

	/**
	 * 회원가입. 회원목록으로 redirect
	 */
	@PostMapping("/user/join")
	public String join(User user, RedirectAttributes model) {
		model.addFlashAttribute("user", user);
		try {
			userDao.addUser(user);
			// 회원가입 성공
			return "redirect:/app/user/joinComplete";
		} catch (DuplicateKeyException e) { // 이메일 중복으로 가입실패
			return "redirect:/app/user/joinForm?mode=ERROR";
		}
	}

	/**
	 * 로그인
	 */
	@PostMapping("/login")
	public String login(String email, String password, HttpSession session,
			RedirectAttributes model) {
		try {
			User user = userDao.getUserByEmailAndPassword(email, password);
			// 로그인 성공
			session.setAttribute("user", user);
			return "redirect:/app/user/s/userInfo?mode=LOGIN";
		} catch (EmptyResultDataAccessException e) { // 로그인 실패
			model.addFlashAttribute("email", email);
			return "redirect:/app/user/loginForm?mode=ERROR";
		}
	}

	/**
	 * 로그아웃
	 */
	@GetMapping("/s/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
