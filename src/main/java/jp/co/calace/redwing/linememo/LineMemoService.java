package jp.co.calace.redwing.linememo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//Role of combine multiple mappers
//database integration 
//this class might not be needed in case of simple system, controller can be used for calling 

// maybe just a converter ?
@Service
public class LineMemoService {
	
	@Autowired
	LineMemoMapper mapper;
	
	// get 1 memo with ID
	public LineMemoDto getMemoById(Integer memoId) {
		
		LineMemoEntity entity = mapper.getMemoById(memoId);
		LineMemoDto dto = new LineMemoDto();
		// convert from entity to Dto
		BeanUtils.copyProperties(entity, dto);
		//different datatype conversion is required 
		dto.setSqlTimestamp(entity.getUpdDate());
		
		return dto;
	}
	
	// get list of matching keyword
	public List<LineMemoDto> getMemoListBykeyword(String keyword) {
		List<LineMemoDto> memoList = new ArrayList<LineMemoDto>();
		List<LineMemoEntity> entityList = mapper.getMemoListByKeyword("%"+ keyword + "%");
		for(LineMemoEntity entity : entityList) {
			LineMemoDto dto = new LineMemoDto();
			//convert from entity to dto
			BeanUtils.copyProperties(entity, dto);
			dto.setSqlTimestamp(entity.getUpdDate());
			memoList.add(dto);
			}
		// final return is dto list.
		//converted from entity 
		return memoList;
		}

	//add one memo
	public int addOneMemo(String memo) {
		int count = mapper.addOneMemo(memo); // connect to database ?!
		return count;
		
	}
	
	//edit one memo
	public int editOneMemo(Integer memoId, String memo) {
		int count = mapper.updOneMemo(memoId, memo);
		return count;
	}
		
	

}
