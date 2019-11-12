package leo.wan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.xmlbeans.impl.xb.xsdschema.LengthDocument;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
/**
 * 实现一个简单的上传下载的demo
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/upload/")
public class UploadController {
	@RequestMapping("toupload")
	public String toUpload(){
		return "demo/upload";
	}
	@RequestMapping("upload1")
	public String upload1(MultipartFile uploadFile1,HttpSession session) throws IllegalStateException, IOException{
		System.out.println(uploadFile1.getOriginalFilename());
		String fileNameString = uploadFile1.getOriginalFilename();
		String path = session.getServletContext().getRealPath("/WEB-INF/uploadFiles");
		File file = new File(path,fileNameString);
		uploadFile1.transferTo(file);
		return "";
	}
	@RequestMapping("/upload/download")
	public String download(HttpSession session,HttpServletResponse  response) throws IOException{
		InputStream is = session.getServletContext().getResourceAsStream("/WEB-INF/uploadFiles/1.jpg");
		OutputStream os = response.getOutputStream();
		byte[] buff = new byte[1024];
		int Length = -1;
		while (( Length= is.read(buff))!=-1) {
			os.write(buff, 0, Length);
		}
		response.setHeader("Content-Disposition", "attachment; filename=1.jpg" );
		os.close();
		is.close();
		return null;
	}
	
}
