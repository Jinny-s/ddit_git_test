package kr.or.ddit.handler.summernote;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.FileUploadResolver;
import kr.or.ddit.dto.AttachVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.utils.GetUploadPath;
import kr.or.ddit.utils.MultipartHttpServletRequestParser;

public class SummernoteUploadImgHandler implements Handler {

	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500;			
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 5;		
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10;	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String url = null;
		
		MultipartHttpServletRequestParser multi = null;
		
		try {
			multi = new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
			
			// 파일 저장 경로 설정
			String uploadPath = GetUploadPath.getUploadPath("summernote.img");
			
			// 사진 이미지 저장
			List<AttachVO> attachList = FileUploadResolver.fileUpload(multi.getFileItems("file"), uploadPath);
			
			if(attachList.size() > 0) {
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				for (AttachVO attach : attachList) {
					out.print(request.getContextPath()+"/getImg.do?fileName="+attach.getFileName());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return url;
	}

}