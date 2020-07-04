package kr.ac.mjc.sungbin.springmvc.board;

import lombok.Data;

/**
 * 검색폼과 데이터 바인딩하는 커맨드 오브젝트
 * 
 * @author Jacob
 */
@Data
public class Search {

	private int count = 10;
	private int page = 1;
	private String keyword = "";

	public int getOffset() {
		return (page - 1) * count;
	}
}