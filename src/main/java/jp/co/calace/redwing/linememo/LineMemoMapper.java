package jp.co.calace.redwing.linememo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

// content processor
// processing content is based on xml file 
// no class implement it 
public interface LineMemoMapper {
	
	LineMemoEntity getMemoById(int memoId);
	
	List<LineMemoEntity> getMemoListByKeyword(String keyword);
	
	int addOneMemo(String insMemo);
	
	int updOneMemo(@Param("id") int memoId, @Param("updMemo") String memo);
	
}
