package jp.co.calace.redwing.dailyrec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DailyRecController {
	// log in case of error, for later debugging  
	private static final Logger logger = LoggerFactory.getLogger(DailyRecController.class);
	// encoding 
	private static final String CHARCODE = "UTF-8";
	
	@RequestMapping(value = "/rec/dailyRec",method = RequestMethod.GET)
	public String showInputPage(HttpServletRequest req, Model model) {
		
		logger.info("initial log of activiaty page");
		
		String fileName ="./recData_" + req.getRemoteAddr().replaceAll(":", ".") + ".txt";
		
		List<String> actionList = readFileToList(fileName);
		logger.info("list no:" +actionList.size());
		
		model.addAttribute("recFormValues", new RecBean()); //form data holder 
		model.addAttribute("pastList",actionList); 
		return "dailyRec"; //tagy chage it to /rec 
	}
	
	
	//show recieved activity records
	@RequestMapping(value = "/rec/dailyRec",method = RequestMethod.POST)
	public String dispResult(
			HttpServletRequest req,
			@Validated @ModelAttribute("recFormValues") RecBean formVals,
			BindingResult result,
			Model model) {
		
			logger.info("Show Activity records");
			logger.info("mode:" +formVals.getMode());
			logger.info("date:" + formVals.getUpdDate());
			logger.info("content:"+formVals.getRecAction());
		
			// IPv6 something here, will check it later 
			String fileName ="./recData_" + req.getRemoteAddr().replaceAll(":", ".") + ".txt";
			logger.info("file name"+req.getRemoteAddr());
			StringBuilder buff = new StringBuilder();
			buff.append(formVals.getUpdDate())
				.append("\t")
				.append(formVals.getRecAction());
			logger.info("buff :"+buff.toString()); //tagy
			
			
			if(!result.hasErrors()) appendToFile(fileName,buff.toString());
			
			List<String> actionList = readFileToList(fileName);
			
			if (result.hasErrors()) {
				model.addAttribute("msg","there is error");
				
				model.addAttribute("recFormvalues",formVals);
			}else {
				RecBean newFormVals = new RecBean();
				model.addAttribute("recFormValues",newFormVals);
			}
			logger.info("actionList"+actionList.toString());
			model.addAttribute("pastList",actionList);
			return "dailyRec";
	}
	
	public static boolean appendToFile(String targetFile, String line) {
		FileOutputStream fileStream = null;
		OutputStreamWriter outStream = null;
		BufferedWriter fileOut = null;
		try {
			fileStream = new FileOutputStream(targetFile,true);
			outStream = new OutputStreamWriter(fileStream,CHARCODE);
			fileOut = new BufferedWriter(outStream);
			fileOut.write(line,0,line.length());
			fileOut.newLine();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(fileOut !=null) try {fileOut.close();}catch(Exception ignore) {}
			if(outStream !=null) try {outStream.close();}catch(Exception ignore) {}
			if(fileStream !=null) try {fileStream.close();}catch(Exception ignore) {}

		}
		return false;
	}
	
	public static List<String> readFileToList(String targetFile) {
		List<String> fileLines = new ArrayList<String>();// maybe we don't need to write string? Java 8?
		
		FileInputStream filesStream = null;
		InputStreamReader inStream = null;
		BufferedReader fileIn = null;
		String line;
		
		try {
			filesStream = new FileInputStream(targetFile);
			inStream = new InputStreamReader(filesStream,CHARCODE);
			fileIn = new BufferedReader(inStream);
			while ((line = fileIn.readLine())!= null) {
				fileLines.add(line);
			}
			return fileLines;

		} catch (FileNotFoundException e) {
			logger.info("File couldn't be found !");
//			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//finally close everything 
			if(fileIn != null) try {fileIn.close();}catch(Exception i) {};
			if(inStream != null) try {inStream.close();}catch(Exception i) {};
			if(filesStream != null) try {filesStream.close();}catch(Exception i) {};
		}
		return fileLines; //two times return ?
	}

}
