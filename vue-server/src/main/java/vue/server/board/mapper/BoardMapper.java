package vue.server.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
	public List<Map<String, Object>> getBoardList(Map<String, Object>dataMap);
	
	public int getTotalCount(Map<String, Object>dataMap);
	
	public Map<String, Object> getBoard(Long id);

	public int insertBoard(Map<String, Object> dataMap);
	
	public int updateBoard(Map<String, Object> dataMap);
	
	public int deleBoard(Long id);

}
