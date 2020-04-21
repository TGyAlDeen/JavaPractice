package jp.co.calace.redwing.webapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


// 69
@RestController
public class WebApiController {

	private final static Logger logger = LoggerFactory.getLogger(WebApiController.class);
	
	//dummy data
	public static final ArtistInfo[] ARTIST_LIST = {
			new ArtistInfo(1,"AC/DC"),
			new ArtistInfo(2,"AC/DC"),
			new ArtistInfo(3,"AC/DC"),
			new ArtistInfo(4,"BA/DC"),
			new ArtistInfo(5,"AC/DC"),
			new ArtistInfo(6,"AC/DC"),
			new ArtistInfo(7,"AC/DC"),
			new ArtistInfo(8,"AC/DC"),
			new ArtistInfo(9,"AC/DC"),
			new ArtistInfo(10,"AC/DC")	
			};
	
	// http://chimaera:8080/redwing/api/artist/csv/6
	@RequestMapping(value = "api/artist/csv/{artistId}")
	public String getArtistCsv(@PathVariable int artistId) {
		logger.info("csv, id: "+artistId);
		
		for(ArtistInfo info : ARTIST_LIST) {
			if (info.getId() == artistId) {
				return info.getId()+ ","+info.getName();
			}
		}
		return "0, no match";
	}
	//http://chimaera:8080/redwing/artist/json/5
	@RequestMapping(value = "api/artist/json/{artistId}", produces = "application/json")
	public ArtistInfo getArtistJson(@PathVariable int artistId) {
		
		for(ArtistInfo info : ARTIST_LIST) {
			if (info.getId() == artistId) {
				return info;
			}
		}
		return new ArtistInfo(0, "no match");
	}
	
	
	//http://chimaera:8080/redwing/artist/json/searchKey/BA
	@RequestMapping(value = "api/artist/json/searchKey/{key}")
	public List<ArtistInfo> getArtistsJson(@PathVariable String key){
		List<ArtistInfo> resultList = new ArrayList<ArtistInfo>();
		
		for(ArtistInfo info : ARTIST_LIST) {
			if (info.getName().matches(".*"+key+".*")) {
				resultList.add(info);
			}
		}
		return resultList;
	}
	
	
	@RequestMapping(value = "api/registerList", method = RequestMethod.POST, produces = "application/json")
	public RespStatus receiveArtistsList(@RequestBody List<FormInputBean> form) {
		for (int i = 0; i < form.size(); ++i) {
			logger.info(i+": name"+form.get(i).getName()+", value"+form.get(i).getValue());
		}
		return new RespStatus("ok");
	}
	
	
	@RequestMapping(value = "api/registerStruct", method = RequestMethod.POST, produces = "application/json")
	public RespStatus receiveAtrsitsStruct(@RequestBody AlbumInfo form) {
		logger.info("artistName"+form.getArtistName());
		logger.info("albumName"+form.getAlbumName());
		List<Map<String, String>> songList = form.getSongList();
		for (int i = 0; i < songList.size(); ++i) {
			Set<String> keys = songList.get(i).keySet();
			for(String key : keys) {
				logger.info(key+ "->"+ songList.get(i).get(key));
			}		
		}
		return new RespStatus("ok");
	}
	
	
	

	public static class ArtistInfo{
		private int id;
		private String name;
		public ArtistInfo(int id, String name) {
			this.id = id;
			this.name = name;
		}
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
	}
	
	public static class AlbumInfo {
		String artistName;
		String albumName;
		List<Map<String, String>> songList;
		public String getArtistName() {
			return artistName;
		}
		public void setArtistName(String artistName) {
			this.artistName = artistName;
		}
		public String getAlbumName() {
			return albumName;
		}
		public void setAlbumName(String albumName) {
			this.albumName = albumName;
		}
		public List<Map<String, String>> getSongList() {
			return songList;
		}
		public void setSongList(List<Map<String, String>> songList) {
			this.songList = songList;
		}
		
	}
	
	public static class FormInputBean{
		private String name;
		private String value;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public static class RespStatus {
		private String status;
		private RespStatus(String status) {
			this.status = status;
		}
		public String getStatus() {
			return this.status;
		}
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(
			HttpServletRequest req,
			Exception e,
			Model model) {
		return "{\"errUrl\" : \"" +req.getRequestURI()+"\"}, {\"errMsg\" : \""+e.toString()+"\"}";
	}
}
