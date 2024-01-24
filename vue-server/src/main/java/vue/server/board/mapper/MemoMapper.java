package vue.server.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemoMapper {
	public List<Map<String, Object>> getMemoList(Map<String, Object> dataMap);

	public int getTotalCount(Map<String, Object> dataMap);

	public Map<String, Object> getMemo(Long id);

	public int insertMemo(Map<String, Object> dataMap);

	public int deleMemo(Long id);
}
