package jp.co.calace.redwing.museum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

@Service
public class MuseumService {
	
	private static final Logger logger = LoggerFactory.getLogger(MuseumService.class);
	
	@Autowired
	private MuseumMapper mapper;
	
	@Autowired
	PlatformTransactionManager txManager; // ??
	
	//for top page ?
	
	public List<MuseumDto> getImageList() {
		List<MuseumDto> imageList = new ArrayList<MuseumDto>();
		List<MuseumEntity> entityList = mapper.getImageList();
		
		int picId = -1;
		int commentId = -1;
		
		boolean isTagProcessing = false;
		
		MuseumDto dto = null;
		for(MuseumEntity entity : entityList) {
			if (entity.getCommentId() != null && commentId != entity.getCommentId()) {
				isTagProcessing =  false;
			}
			if (picId != entity.getId()) {
				picId = entity.getId();
				
				isTagProcessing= true;
				
				dto = new MuseumDto();
				imageList.add(dto);
				//from entity to dto
				BeanUtils.copyProperties(entity, dto);
				
				//if file name found use is is not ...
				if (entity.getImagename() ==null) {
					dto.setImagename("api/"+dto.getId());
				}else {
					dto.setImagename("../upl/"+entity.getImagename());
				}
				
				dto.setSqlTimestamp(entity.getUpdDate());
				//Comment list
				dto.setCommentList(new ArrayList<CommentInfo>());
				//tag list
				dto.setTagList(new ArrayList<String>());
			}// end of picId pic statement 
			
			if (entity.getCommentId() != null && commentId != entity.getCommentId()) {
				commentId = entity.getCommentId();
				dto.getCommentList().add((new CommentInfo(entity.getCommentId(),
									entity.getCommentator(),
									entity.getComment())));
			logger.info(dto.getId() + "comment:" + entity.getComment());
				
			}//end of if
			
			if(isTagProcessing && entity.getTagId() != null) {
				dto.getTagList().add(entity.getTag());
				logger.info(dto.getTagList()+ "Tag:"+ entity.getTag());
			}
		}//end of for
		
		return imageList; // yoku wakaranai.. process wa
	}
	
	public List<MuseumDto> getImageListWithHash() {
		List<MuseumDto> imageList = new ArrayList<MuseumDto>();
		//image and tag together
		List<MuseumEntity> entityList = mapper.getImageAndTagList();
		
		//comment list
		HashMap<Integer, List<CommentInfo>> commentMap = new HashMap<Integer,List<CommentInfo>>();
		List<CommentEntity> commentList = mapper.getCommentList();
		// wakaranai
		int picId = -1;
		int tagId = -1;
		MuseumDto dto = null;
		
	}

}
