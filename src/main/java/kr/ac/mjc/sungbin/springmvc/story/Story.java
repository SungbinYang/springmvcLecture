package kr.ac.mjc.sungbin.springmvc.story;

import org.owasp.encoder.Encode;

import lombok.Data;

@Data
public class Story {
	private String id;
	private String content;
	private String name;
	private String date;

	public String getContentHtml() {
		return Encode.forHtml(content).replace("\n", "<br>");
	}
}