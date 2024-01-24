package vue.server.board;

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
import vue.server.board.service.MemoService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class MemoController {
	private final MemoService memoService;

	/**
	 * 메모 목록 조회
	 * 
	 * @return
	 */
	@GetMapping("/memo/page/{currentPage}")	
	public ResponseEntity<Map<String, Object>> getBoardList(@PathVariable int currentPage, @RequestParam Map<String, String>inMap) {
		log.debug(">>>>>>>>>> currentPage = {}", currentPage);
		log.debug("inMap = {}", inMap);
		
		int amount = 10;
		int page = currentPage -1;
		
		Map<String, Object>dataMap = new HashMap<String, Object>();
		dataMap.put("page", page * amount);
		dataMap.put("amount", amount);
		dataMap.put("keyword", inMap.get("keyword"));
		dataMap.put("searchType", inMap.get("searchType"));
		
		List<Map<String, Object>> boardList = memoService.getMemoList(dataMap);
		int totalCount = memoService.getTotalCount(dataMap);
		
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("boardList", boardList);
		outMap.put("totalCount", totalCount);
		
		log.debug("outMap = {}", outMap);
		return new ResponseEntity<Map<String, Object>>(outMap, HttpStatus.OK);
	}

	/**
	 * 메모 1건 조회
	 * 
	 * @return
	 */
	@GetMapping("/memo/{id}")
	public ResponseEntity<Map<String, Object>> getBoard(@PathVariable Long id) {
		Map<String, Object> board = memoService.getMemo(id);

		log.debug("getBoard.result = {}", board);
		return board != null
				? new ResponseEntity<Map<String, Object>>(board, HttpStatus.OK)
				: new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * 메모 등록
	 * 
	 * @param inMap
	 * @return
	 */
	@PostMapping("/memo")
	public ResponseEntity<Integer> insertMemo(@RequestBody Map<String, Object> inMap) {
		log.debug("inMap = {}", inMap);


		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("title", inMap.get("title"));
		dataMap.put("writer", inMap.get("writer"));
		dataMap.put("content", inMap.get("content"));

		int result = memoService.insertMemo(dataMap);

		log.debug("result = {}", result);
		return result == 1 
				? new ResponseEntity<Integer>(result, HttpStatus.OK)
				: new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 메모 1건 삭제
	 * @param id
	 * @return
	 */
	@DeleteMapping("/memo/{id}")
	public ResponseEntity<Integer> deleBoard(@PathVariable Long id) {
		log.debug("inMap = {}", id);

		int result = memoService.deleMemo(id);

		log.debug("deleBoard.result = {}", result);
		return result == 1 
				? new ResponseEntity<Integer>(result, HttpStatus.OK)
				: new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
