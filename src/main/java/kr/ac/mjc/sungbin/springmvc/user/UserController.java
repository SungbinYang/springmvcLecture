package kr.ac.mjc.sungbin.springmvc.user;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.SessionAttribute;
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
	public void mapDefault() {
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
			return "redirect:/app/user/joinComplete";
		} catch (DuplicateKeyException e) { // 이메일 중복으로 가입실패
			return "redirect:/app/user/joinForm?mode=ERROR";
		}
	}

	/**
	 * 로그인
	 */
	@PostMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String returnUrl = request.getParameter("returnUrl");
		try {
			User user = userDao.getUserByEmailAndPassword(email, password);
			// 로그인 성공
			request.getSession().setAttribute("user", user);
			response.sendRedirect(returnUrl);
		} catch (EmptyResultDataAccessException e) { // 로그인 실패
			String loginUrl = String.format(
					"%s/app/user/loginForm?mode=FAILURE&email=%s&returnUrl=%s",
					request.getContextPath(), email,
					URLEncoder.encode(returnUrl, Charset.defaultCharset()));
			response.sendRedirect(loginUrl);
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

	/**
	 * 회원정보 수정<br>
	 * 
	 * @param user        Command object. 폼에서 submit한 사용자 정보
	 * @param sessionUser 세션의 로그인한 유저 정보
	 */
	@PostMapping("/user/s/updateUser")
	public String updateUser(User user,
			@SessionAttribute("user") User sessionUser,
			RedirectAttributes model) {
		user.setId(sessionUser.getId());
		try {
			userDao.updateUser(user);
			// 개인정보 수정에 성공하면 세션값을 변경
			sessionUser.setEmail(user.getEmail());
			sessionUser.setName(user.getName());
			return "redirect:/app/user/s/userInfo?mode=UPDATE";
		} catch (DuplicateKeyException e) {
			// 이메일 중복으로 수정에 실패할 경우
			model.addFlashAttribute("user", user);
			return "redirect:/app/user/s/userEdit?mode=ERROR";
		}
	}

	/**
	 * 비밀번호 수정
	 */
	@PostMapping("/user/s/updatePassword")
	public String updatePassword(String password, String newPassword,
			@SessionAttribute("user") User sessionUser) {
		int updatedRows = userDao.updatePassword(sessionUser.getId(), password,
				newPassword);
		if (updatedRows == 1) { // 비밀번호 변경 성공
			return "redirect:/app/user/s/userInfo?mode=PASSWORD";
		} else { // 현재 비밀번호 틀림
			return "redirect:/app/user/s/passwordEdit?mode=ERROR";
		}
	}

	/**
	 * 회원탈퇴
	 */
	@GetMapping("/user/s/dropUser")
	public String dropUser(HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		userDao.deleteUser(sessionUser.getId());
		logger.info("회원탈퇴 했습니다. {}", sessionUser);
		return logout(session);
	}

	/**
	 * 회원정보 보기
	 */
	@GetMapping("/user/userInfo")
	public void userInfo(String id, Model model) {
		User user = null;
		try {
			user = userDao.getUserById(id);
		} catch (EmptyResultDataAccessException e) {
			user = new User();
			user.setId("등록되지 않은 사용자");
		}
		model.addAttribute("user", user);
	}
}
