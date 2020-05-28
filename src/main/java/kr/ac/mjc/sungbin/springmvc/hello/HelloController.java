package kr.ac.mjc.sungbin.springmvc.hello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.mjc.sungbin.springmvc.board.Board;
import kr.ac.mjc.sungbin.springmvc.user.User;



@Controller
public class HelloController {

	private final Logger logger = LogManager.getLogger();

	/**
	 * view name : "hello"<br>
	 * forward prefix + view name + suffix : "/WEB-INF/jsp/hello.jsp"
	 */
	@GetMapping("/hello") // mapping with "/app/hello"
	public String hello() {
		return "hello"; // view name : "hello"
	}

	/**
	 * default view name : bonjour<br>
	 * forward prefix + view name + suffix : "/WEB-INF/jsp/bonjour.jsp"
	 */
	@GetMapping("/bonjour") // mapping with "/app/bonjour"
	public void bonjour() {
		// default view name : bonjour
	}

	@GetMapping("/ciao")
	public void ciao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String greeting = "Ciao, " + name;
		request.setAttribute("greeting", greeting);
		request.getRequestDispatcher("/WEB-INF/jsp/greeting.jsp")
				.forward(request, response);
	}

	/**
	 * Argument가 simple type일 경우 request parameter
	 */
	@GetMapping("/hola") // mapping with "/app/hola"
	public String hola(@RequestParam("name") String name, Model model) {
		// String name = request.getParameter("name");
		// String이 simple type이므로 생략하면 @RequestParam
		// @RequestParam("name")을 생략가능함
		String greeting = "Hola, " + name;
		model.addAttribute("greeting", greeting);
		return "greeting"; // forward "/WEB-INF/jsp/greeting.jsp"
	}

	/**
	 * Argument가 command object일 경우 data binding
	 */
	@PostMapping("/addBoard") // mapping with "/app/addBoard"
	public String addBoard(@ModelAttribute("board") Board board, Model model) {
		// board.setTitle(request.getParamater("title");
		// @ModelAttribute annotated 객체를 command object라 한다.
		// command object에는 data binding이 된다.
		// Board는 simple type이 아니므로 생략하면 @ModelAttribute
		logger.debug("request attribute : {}", board);

		User user = new User();
		user.setName("sungbin");
		model.addAttribute("user", user);
		return "result"; // forward "/WEB-INF/jsp/result.jsp"
	}
}