package jp.co.calace.redwing.museum;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.calace.redwing.WebMvcConfig;

@Controller
public class MuseumController {

	@Autowired
	MuseumService service;
	
	private static final Logger logger = LoggerFactory.getLogger(MuseumController.class);
	//binary 
	private static final String MODE_FOLDER = "INTO_FOLDER";
	private static final String MODE_DB = "INTO_DB";
	
	//server 
	private static final String IMG_PATH = WebMvcConfig.URL_PATH;
	
	//image list
	List<MuseumDto> imageList;
	
	@RequestMapping(value = "/museum/top", method = RequestMethod.GET)
	public String dispResult(Model model) {
		logger.info("Display Museum with GET");
		imageList = service.getImageList();
		//imageList = service.getImageListWithHash();
		
		model.addAttribute("ImageList",imageList);

		return "museum";
	}
	
	@RequestMapping(value = "/museum/upload",method = RequestMethod.POST)
	public String dispResult(
			@RequestParam(value = "saveplace",defaultValue = "INTO_FOLDER")String saveplace,
			@RequestParam(value = "upFile") MultipartFile multipartFile,
			@RequestParam(value = "tags") String tags,
			@RequestParam(value = "author")String author,
			RedirectAttributes redirecAttrs, Model model) {
		
		logger.info("saveLocation: "+saveplace+" imageFile Name: "+multipartFile.getOriginalFilename()+" Tags: "+tags+"Author: "+author);
		int picId = 0;
		int tagId = 0;
		
		if(MODE_FOLDER.equals(saveplace)) {
			logger.info("put file in folder");
			//
			saveFileToFolder(multipartFile, IMG_PATH);
			
			//DB
			try {
				picId = service.addImageInfo(author, multipartFile.getOriginalFilename());
				logger.info("image ID:"+picId);
				
			} catch (DuplicateKeyException e) {
				logger.info("file name..");
				e.printStackTrace();
			}
		}else if (MODE_DB.equals(saveplace)) {
			logger.info("insert into DB");
			try {
				picId = service.addImageData(author, multipartFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//bring tags
		String[] tagList = tags.split(" ");
		if (tagList.length > 0) {
			logger.info("register tags");
			for(String tag : tagList) {
				//tag table
				try {
					logger.info("TagList member: "+tag);
					tagId = service.getTagId(tag);
					logger.info("insertion mode DB, Tag"+tag+"TagId: "+tagId);
					logger.info("TagList");
		
					} catch (BindingException e) {
					logger.info("tag id couldn't be found");
					e.printStackTrace();
					tagId = service.addNewTag(tag); //wakanai.. why in exception 
					logger.info("insertion mode DB, Tag"+tag+"Tag Id: "+ tagId);

				}
				
				
				
				service.addPicAndTagId(picId, tagId);
			}
		}
		logger.info("redisplay Top page ");
		
		return "redirect:top";
	}
	
	@RequestMapping(value = "/museum/comment",method = RequestMethod.POST)
	public String dispResult(@RequestParam("pic_id") int picId,
			@RequestParam("commentator") String commentator,
			@RequestParam("comment") String comment,
			RedirectAttributes redirectAttrs, Model model) {
		
		//add comment
		service.addComment(picId, commentator, comment);
		logger.info("redisplay top page -- add comment");
		
		return "redirect:top";
	}
			
	
	public static void saveFileToFolder(MultipartFile multipartFile,String imgPass) {
		
		BufferedInputStream in = null;
		FileOutputStream fileOut = null;
		
		try {
			in = new BufferedInputStream(multipartFile.getInputStream());
			fileOut = new FileOutputStream(imgPass+multipartFile.getOriginalFilename());//
			
			byte[] buffer = new byte[1024]; //
			int readLen = 0;
			while ((readLen = in.read(buffer)) != -1) {
				fileOut.write(buffer, 0, readLen);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
//		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(fileOut != null) try {fileOut.close();} catch (Exception ignore) {}
			if(in != null) try {in.close();} catch (Exception ignore) {}
		}
	}
	
}
