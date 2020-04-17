package jp.co.calace.redwing.museum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
	
	@Autowired
	MuseumService service;
	
	// png converter 
	
	@RequestMapping(value = "museum/api/{picId}", method = RequestMethod.GET)
	public ResponseEntity<byte []> sendImageByte(@PathVariable int picId){
		logger.info("image with ID: "+ picId );
		byte[] imgData = null;
		//HttpServletResposne can't be used
		
		try {
			imgData = service.getImageData(picId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// How to serve image with responseEnity
		//http://zetcode.com/springboot/serveimage
		return ResponseEntity
				.ok()
				.contentType(getMediaType(imgData))
				.body(imgData);
		
	}
	
	public static boolean doesMatchHeader(byte[] imgData, byte[] header) {
		for (int i = 0; i < header.length; i++) {
			if(imgData[i] != header[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static MediaType getMediaType(byte[] data) {
		
		byte[] headerPng = {(byte)0x89, (byte)0x50, (byte)0x4E,
							(byte)0x47, (byte)0x0D, (byte)0x0A};
		byte[] headerJpg = {(byte)0xFF, (byte)0xD8};
		
		//if its PNG ?
		if (doesMatchHeader(data, headerPng)) {
			return MediaType.IMAGE_PNG;
		}
		
		if(doesMatchHeader(data, headerJpg)) {
			return MediaType.IMAGE_JPEG;
		}
		
		return null;
	}
}
