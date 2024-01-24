package vue.server.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vue.server.board.mapper.MemoMapper;

@Service
public class MemoService {
	@Autowired
	private MemoMapper memoMapper;

	public List<Map<String, Object>> getMemoList(Map<String, Object> dataMap) {
		return memoMapper.getMemoList(dataMap);
	}

	public int getTotalCount(Map<String, Object> dataMap) {
		return memoMapper.getTotalCount(dataMap);
	}

	public int insertMemo(Map<String, Object> dataMap) {
		return memoMapper.insertMemo(dataMap);
	}

	public Map<String, Object> getMemo(Long id) {
		return memoMapper.getMemo(id);
	}

	public int deleMemo(Long id) {
		return memoMapper.deleMemo(id);
	}
}
