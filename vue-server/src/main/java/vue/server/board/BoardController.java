package vue.server.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vue.server.board.service.BoardService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	private final BoardService boardService;

	/**
	 * 게시판 목록 조회
	 * 
	 * @return
	 */
	@GetMapping("/board/page/{currentPage}")	
	public ResponseEntity<Map<String, Object>> getBoardList(@PathVariable int currentPage, @RequestParam Map<String, String>inMap) {
		//http://localhost:8080/api/board
		int amount = 10;
		int page = currentPage -1;
		
		Map<String, Object>dataMap = new HashMap<String, Object>();
		dataMap.put("page", page * amount);
		dataMap.put("amount", amount);
		dataMap.put("keyword", inMap.get("keyword"));
		dataMap.put("searchType", inMap.get("searchType"));
		
		List<Map<String, Object>> boardList = boardService.getBoardList(dataMap);
		int totalCount = boardService.getTotalCount(dataMap);
		
		//페이징
		Map<String, Object> pageMap = new HashMap<String, Object>();
		int endPage = (int)(Math.ceil(currentPage / 10.0) * 10);
		int startPage = endPage - 9;
		int realEnd = (int)(Math.ceil(totalCount * 1.0 /amount));
		endPage = realEnd < endPage ? realEnd : endPage;
		boolean prev = startPage > 1;
		boolean next = realEnd > endPage;
		int[] pageNumberArr = Arrays.copyOfRange(new int[endPage - startPage + 1], 0, endPage - startPage + 1);
        for (int i = 0; i < pageNumberArr.length; i++) {
        	pageNumberArr[i] = startPage + i;
        }
		pageMap.put("endPage", endPage);
		pageMap.put("startPage", startPage);
		pageMap.put("prev", prev);
		pageMap.put("next", next);
		pageMap.put("currentPage", currentPage);
		pageMap.put("pageNumberArr", pageNumberArr);
		
		
		
		
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("boardList", boardList);
		outMap.put("totalCount", totalCount);
		outMap.put("pageMap", pageMap);
		
		log.debug("outMap = {}", outMap);
		return boardList != null
				? new ResponseEntity<Map<String, Object>>(outMap, HttpStatus.OK)
				: new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 게시글 1건 조회
	 * 
	 * @return
	 */
	@GetMapping("/board/{id}")
	public ResponseEntity<Map<String, Object>> getBoard(@PathVariable Long id) {
		//localhost:8080/api/board/4
		Map<String, Object> board = boardService.getBoard(id);

		log.debug("getBoard.result = {}", board);
		return board != null
				? new ResponseEntity<Map<String, Object>>(board, HttpStatus.OK)
				: new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * 게시판 등록
	 * 
	 * @param inMap
	 * @return
	 */
	@PostMapping("/board")
	public ResponseEntity<Integer> insertBoard(@RequestBody Map<String, Object> inMap) {
		// {"title":"한글한글 test title", "writer" : 123, "content": "test content 한글한글"}
		log.debug("inMap = {}", inMap);


		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("title", inMap.get("title"));
		dataMap.put("writer", inMap.get("writer"));
		dataMap.put("content", inMap.get("content"));

		int result = boardService.insertBoard(dataMap);

		log.debug("result = {}", result);
		return result == 1 
				? new ResponseEntity<Integer>(result, HttpStatus.OK)
				: new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 게시글 1건 업데이트
	 * 
	 * @param id
	 * @param inMap
	 * @return
	 */
	@PatchMapping("/board/{id}")
	public ResponseEntity<Integer> updateBoard(@PathVariable Integer id, @RequestBody Map<String, Object> inMap) {
		//{"id" : 4, "title" : "업데이트 title", "content" : "업데이트 content"}
		log.debug("inMap = {}", inMap);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("id", id);
		dataMap.put("title", inMap.get("title"));
		dataMap.put("writer", inMap.get("writer"));
		dataMap.put("content", inMap.get("content"));

		int result = boardService.updateBoard(dataMap);

		log.debug("updateBoard.result = {}", result);
		return result == 1 
				? new ResponseEntity<Integer>(result, HttpStatus.OK)
				: new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 게시글 1건 삭제
	 * @param id
	 * @return
	 */
	@DeleteMapping("/board/{id}")
	public ResponseEntity<Integer> deleBoard(@PathVariable Long id) {
		log.debug("inMap = {}", id);

		int result = boardService.deleBoard(id);

		log.debug("deleBoard.result = {}", result);
		return result == 1 
				? new ResponseEntity<Integer>(result, HttpStatus.OK)
				: new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
