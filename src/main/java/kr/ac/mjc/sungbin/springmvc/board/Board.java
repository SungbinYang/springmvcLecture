package kr.ac.mjc.sungbin.springmvc.board;

import lombok.Data;

@Data
public class Board {
	private String seq;
	private String title;
	private String content;
	private String regdate;
	private String writer;
	private int cnt;
}
