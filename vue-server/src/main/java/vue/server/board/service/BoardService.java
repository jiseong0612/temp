package vue.server.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vue.server.board.mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	/**
	 * 게시판 목록 조회
	 * @return
	 */
	public List<Map<String, Object>>getBoardList(){
		return boardMapper.getBoardList();
	}

	/**
	 * 게시판 등록
	 * @param dataMap
	 * @return
	 */
	public int insertBoard(Map<String, Object> dataMap) {
		return boardMapper.insertBoard(dataMap);
	}

	/**
	 * 게시글 1건 조회
	 * @param id
	 * @return
	 */
	public Map<String, Object> getBoard(Long id) {
		return boardMapper.getBoard(id);
	}
	
	/**
	 * 게시글 업데이트
	 * @param id
	 * @return
	 */
	public int updateBoard(Map<String, Object> dataMap) {
		return boardMapper.updateBoard(dataMap);
	}
	
	/**
	 * 게시글 삭제
	 * @param id
	 * @return
	 */
	public int deleBoard(Long id) {
		return boardMapper.deleBoard(id);
	}
}
