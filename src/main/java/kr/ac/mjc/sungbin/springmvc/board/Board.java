package kr.ac.mjc.sungbin.springmvc.board;

import org.owasp.encoder.Encode;

import lombok.Data;

@Data
public class Board {
	private String seq;
	private String title;
	private String content;
	private String id;
	private String regdate;
	private String writer;
	private int cnt;

	public String getContentHtml() {
		return Encode.forHtml(content).replace("\n", "<br>");
	}
}
