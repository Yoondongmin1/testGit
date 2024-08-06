package kr.co.softsoldesk.service;


import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.softsoldesk.DAO.BoardDAO;
import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.PageBean;
import kr.co.softsoldesk.beans.UserBean;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	
	@Value("${path.upload}")
	private String path_upload;
	
	@Value("${page.listcnt}")
	private int page_list;
	
	@Value("${page.paginationcnt}")
	private int paginationcnt;
	
	
	
	@Autowired
	BoardDAO boardDAO;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	private String saveUploadFile(MultipartFile upload_file) {
		
		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		
		try {
			upload_file.transferTo(new File(path_upload + "/" + file_name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file_name;
	}

	public void addContentInfo(ContentBean writeContentBean) {
		
		MultipartFile upload_file = writeContentBean.getUpload_file();
		
		if(upload_file.getSize()>0) {
			
			String file_name = saveUploadFile(upload_file);
			writeContentBean.setContent_file(file_name);
		}
		
		writeContentBean.setContent_writer_idx(loginUserBean.getUser_idx());
		boardDAO.addContentInfo(writeContentBean);
		
	}
	
	public String getBoardInfoName(int board_info_idx) {
		
		return boardDAO.getBoardInfoName(board_info_idx);
	}
	
	public List<ContentBean> getContentList(int board_info_idx, int page){
		//컨트롤러에서오는 page 값을 받아서
		
		int start = (page - 1) * page_list;
		/*
		 1페이지 링크, 시작 0/*
		 2페이지 링크, 10
		 3페이지 링크, 20
		 */
		RowBounds rowBounds = new RowBounds(start, page_list);
		
		
		return boardDAO.getContentList(board_info_idx,rowBounds);
	}
	
	public ContentBean getContentInfo(int content_idx) {
		
		return boardDAO.getContentInfo(content_idx);
	}
	
	public void modifyContentBean(ContentBean modifyContentBean) {
		
		MultipartFile upload_file = modifyContentBean.getUpload_file();
		
		if(upload_file.getSize() >= 0) {
			String file_name = saveUploadFile(upload_file);
			modifyContentBean.setContent_file(file_name);
		}
		
		boardDAO.modifyContentInfo(modifyContentBean);
	}
	
	public void deleteContentInfo(int content_idx) {
		
		boardDAO.deleteContentInfo(content_idx);
	}
	
	public  PageBean getContentCnt(int content_board_idx, int currentPage) {
		
		int content_cnt = boardDAO.getContentCnt(content_board_idx); //게시판의 전체 글 개수
		
		PageBean pageBean = new PageBean(content_cnt, currentPage, page_list, paginationcnt);
		
		return pageBean;
			
	}	
}
