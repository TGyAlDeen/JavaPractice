package jp.co.calace.redwing.museum;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MuseumMapper {
	//Lists
	List<MuseumEntity> getImageList();
	
	List<MuseumEntity> getImageAndTagList();
	
	List<CommentEntity> getCommentList();
	

	// image data
	ImageDataEntity getImageData(int picId);
	int addImageName(MuseumEntity mEntity);
	int addImageData(MuseumEntity mEntity);

	//Tag Data
	int getTagId(String tag);
	void addNewTag(MuseumEntity mEntity);
	
	// join stuff
	void addPicIdAndTagId(@Param("picId")int picId, @Param("tagId")int tagId);
	
	
	//Comment stuff
	void addComment(@Param("picId")int picId
			,@Param("commentator")String commentator
			,@Param("comment")String comment);
	
	
}
