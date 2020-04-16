package jp.co.calace.redwing.linememo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LineMemoController {
	
	private static final Logger logger = LoggerFactory.getLogger(LineMemoController.class);
	
	//automatic binding .. new something isn't needed 
	@Autowired
	LineMemoService service;
	
	//for html forms, there are three models for insertion,update and search .
		//maybe i should try delete later 
		@ModelAttribute("memoInsModel")
		public MemoInsModel initInsModel() {
			return new MemoInsModel();
		}
		@ModelAttribute("memoEditModel")
		public MemoUpdModel initUpdModel() {
			return new MemoUpdModel();
		}
		@ModelAttribute("memoSelModel")
		public MemoSearchModel initSearchModel() {
			return new MemoSearchModel();
		}
	
	
		//first display with Get REquest 
		@RequestMapping(value="/lineMemo/top",method = RequestMethod.GET)
		public String disp(Model model) {
			logger.info("Get request display");
			//display on jsp
			model.addAttribute("msg","DB experiment project");
			return "lineMemo";
		}
		
		//POST request for memo addition 
		@RequestMapping(value="/lineMemo/add",method = RequestMethod.POST)
		public String processInsert(@ModelAttribute("memoInsModel") MemoInsModel form,Model model) {
			logger.info("POST request for memmo addition");
			
			int insCount =service.addOneMemo(form.memoStr);
			//display message on jsp
			model.addAttribute("msg","Insertion complete" + insCount);
			return "lineMemo";
		}
		
		//POST request for memo update
		@RequestMapping(value="/lineMemo/top",method = RequestMethod.POST)
		public String processUpdate(@ModelAttribute("memoEditModel") MemoUpdModel form,Model model) {
			logger.info("POST request for memmo update "+ form.memoStr);
			
			int updCount =service.editOneMemo(form.memoId,form.memoStr);
			//display message on jsp
			model.addAttribute("msg","Memo with id:" + form.memoId + "has been updated. return value: "+updCount );
			return "lineMemo";
		}
		
		@RequestMapping(value="/lineMemo/edit",method = RequestMethod.POST)
		public String processSearch(@ModelAttribute("memoSelModel") MemoSearchModel form,Model model) {
			logger.info("POST request for memmo search "+ form.keywordStr);
			
			List<LineMemoDto> memoList = service.getMemoListBykeyword(form.keywordStr);
			model.addAttribute("msg","return results are "+memoList.size());
			model.addAttribute("memos",memoList);
			
			return "lineMemo";
		}
		
		
		
		
		
		
	// why three classes ?
	public static class MemoInsModel {
		private String memoStr;

		public String getMemoStr() {
			return memoStr;
		}

		public void setMemoStr(String memoStr) {
			this.memoStr = memoStr;
		}	
	}
	
	public static class MemoUpdModel {
		private int memoId;
		private String memoStr;
		
		public int getMemoId() {
			return memoId;
		}
		public void setMemoId(int memoId) {
			this.memoId = memoId;
		}
		public String getMemoStr() {
			return memoStr;
		}
		public void setMemoStr(String memoStr) {
			this.memoStr = memoStr;
		}
	}
	
	public static class MemoSearchModel{
		private String keywordStr;

		public String getKeywordStr() {
			return keywordStr;
		}

		public void setKeywordStr(String keywordStr) {
			this.keywordStr = keywordStr;
		}
		
		
		
	}

}
