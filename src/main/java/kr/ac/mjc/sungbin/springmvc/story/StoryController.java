package kr.ac.mjc.sungbin.springmvc.story;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class StoryController {
	
	@Autowired
	private StoryDao storyDao;

	private final Logger logger = LogManager.getLogger();
	
	@GetMapping("/story/storyList")
	public void storyList(
			@RequestParam(name = "count", required = false, defaultValue = "10") int count,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			Model model, Story story) {
		int offset = (page - 1) * count;
		List<Story> storyList = storyDao.listStories(offset, count);
		model.addAttribute("storyList", storyList);
	}
	
	@PostMapping("/story/addStory")
	public String addStory(Story story) {

		storyDao.addStory(story);
		
		return "redirect:/app/story/storyList";
	}

}
