package jp.co.calace.redwing.museum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
		for(CommentEntity cEntity: commentList) {
			if(!commentMap.containsKey(cEntity.getPicId())) {
				commentMap.put(cEntity.getPicId(), new ArrayList<CommentInfo>());
			}
			//
			commentMap.get(cEntity.getPicId()).add(new CommentInfo(cEntity.getId(), cEntity.getCommentator(), cEntity.getComment()));
			logger.info(cEntity.getPicId() + "comment :"+cEntity.getComment());	
		}
		
		//from entity to dto 
		for(MuseumEntity entity : entityList) {
			// tag comment and dto 
			if (picId != entity.getId()) {
				picId = entity.getId();
				
				//dto contruciton 
				dto = new MuseumDto();
				imageList.add(dto);
				BeanUtils.copyProperties(entity, dto);
				dto.setSqlTimestamp(entity.getUpdDate());
				
				// comment list
				if (commentMap.containsKey(picId)) {
					dto.setCommentList(commentMap.get(picId));
				}
				
				//tag list
				dto.setTagList(new ArrayList<String>());
			}
			if (entity.getTagId() != null && tagId != entity.getTagId()) {
				tagId = entity.getTagId();
				
				dto.getTagList().add(entity.getTag());
				logger.info(dto.getId()+ "tag"+ entity.getTag());
			}
			
		}
		return imageList;
	}
	
	public byte[] getImageData(int picId) {
		ImageDataEntity imageEntity = mapper.getImageData(picId);
		byte[] imagedata = imageEntity.getImagedata();
		
		if (imagedata == null) {
			return null;
		}
		
		return imagedata;
	}
	
	public int addImageInfo(String author, String imagename) throws DuplicateKeyException {
		MuseumEntity mEntity = new MuseumEntity();
		mEntity.setAuthor(author);
		mEntity.setImagename(imagename);
		mapper.addImageName(mEntity);
		return mEntity.getId(); // return ?
	}
	
	public int  getTagId(String tag) throws BindingException{
		return mapper.getTagId(tag);
	}
	
	public int addNewTag (String tag) {
		//zenzen wakaranai // ma ma tx toka wakaru 
		DefaultTransactionDefinition txDefnition = new DefaultTransactionDefinition();
		txDefnition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus txStatus = txManager.getTransaction(txDefnition);
		
		try {
			MuseumEntity mEntity = new MuseumEntity();
			mEntity.setTag(tag);
			mapper.addNewTag(mEntity);
			// Db somthing
			txManager.commit(txStatus);
			return mEntity.getId();
			
		} catch (Exception e) {
			// TODO: handle exception
			txManager.rollback(txStatus);
			return -1;
		}
		
	}
	
	// image id and tag id 
	public void addPicAndTagId(int picId,int tagId) {
		mapper.addPicAndTagId(picId, tagId);
	}
	
	public void addComment(int picId,String commentator,String comment) {
		mapper.addComment(picId, commentator, comment);
		
	}

}
