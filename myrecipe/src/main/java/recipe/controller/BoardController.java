package recipe.controller;

import java.io.File;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import recipe.model.board.BoardDao;
import recipe.model.board.BoardDto;
import recipe.model.member.MemberDao;
import recipe.model.member.MemberDto;

@Controller
public class BoardController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BoardDao bdao;
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping("/blist")
	public String list(HttpServletRequest request, Model model) {
		String ckValue=null;
		Cookie[] ck =  request.getCookies();
		if(ck != null) {
			for(Cookie c : ck) {
//				log.info("쿠키 이름 : "+c.getName());
				if(c.getName().equals("memberEmail")) {
					ckValue = c.getValue();
//					log.info("쿠키 값  = "+ckValue);
					model.addAttribute("ckValue", ckValue);
					break;
				}
			}
		}
		String type = request.getParameter("type");
		String key = request.getParameter("key");
//		log.info("type = "+type);
//		log.info("key = "+key);
		String pageStr = request.getParameter("page");
		int pageNo;
		try {
			pageNo = Integer.parseInt(pageStr);
			if(pageNo <= 0) throw new Exception();
		}catch(Exception e) {
			pageNo=1;
		}
		
		int boardSize = 10;
		int boardCount = bdao.count(type, key);
		
//		System.out.println("boardCount = "+boardCount);
		int start = boardSize * pageNo - boardSize+1;
		int end = start+boardSize-1;
		if(end > boardCount) end = boardCount;
		
		
		List<BoardDto> list = bdao.list(type, key, start, end);
		
		
		int blockSize = 10;
		int blockTotal = (boardCount + boardSize -1)/boardSize;
		int startBlock = (pageNo -1) / blockSize * blockSize+1;
		int endBlock = startBlock + blockSize -1;
		if(endBlock > blockTotal) endBlock = blockTotal;
		
		String url = "blist?";
		if(type!=null && key!=null) 
			url += "type="+type+"&key="+key;
		
		model.addAttribute("type", type);
		model.addAttribute("key", key);
		model.addAttribute("url", url);
		model.addAttribute("startBlock", startBlock);
		model.addAttribute("endBlock", endBlock);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("blockTotal", blockTotal);
		
//		if(type==null) type="name";
//		if(key==null) key="";
		
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@RequestMapping("/me")
	public String list(@RequestParam("email") String email, Model model) {
		List<BoardDto> list = bdao.myQnA(email);
		model.addAttribute("list", list);
		return "board/list";
	}

	
	@RequestMapping("/bwrite")
	public String write(HttpServletRequest request, Model model) {
		String ckValue=null;
		Cookie[] ck =  request.getCookies();
		if(ck != null) {
			for(Cookie c : ck) {
				if(c.getName().equals("memberEmail")) {
					ckValue = c.getValue();
					List<MemberDto> mdto = memberDao.info(ckValue);
				    model.addAttribute("mdto", mdto.get(0));
					model.addAttribute("ckValue", ckValue);
					break;
				}
			}
		}
		return "board/write";
	}
	
	private String[] typeFilter = new String[] {
			"image/png", "image/jpeg", "image/gif"
	};
	
	@RequestMapping(value="/bwrite", method=RequestMethod.POST)
	public String write(MultipartHttpServletRequest request) throws Exception {
		MultipartFile file = request.getFile("file");
		String title = file.getOriginalFilename();
		title = title.substring(0, title.indexOf("."));
		long size = file.getSize();
//		log.debug("업도르할 파일 이름  = "+title);
//		log.debug("파일 크기 = "+size);
		
		//확장자 판별 하는 라이브러리 이용
		String mime = Magic.getMagicMatch(file.getBytes()).getMimeType();
		log.debug("mime = "+mime);
		if((Arrays.binarySearch(typeFilter, mime)) < 0 ) {
			throw new MagicMatchNotFoundException("GIF, JPG, PNG만 업로드가 가능합니다.");
		}
		
		String save = request.getServletContext().getRealPath("/file/"+request.getParameter("name"));
		log.debug("저장 위치  = "+save);
		BoardDto bdto = new BoardDto(request);
		bdto.setFilename(title);
		bdto.setFilesize(size);
		bdto.setFiletype(mime);
		int no = bdao.write(bdto);
		
		File target = new File(save, title);
		if(!target.exists()) target.mkdirs();
		file.transferTo(target);
//		return "redirect:/binfo?no="+no;
		return "redirect:/blist";
	}
	
	@RequestMapping("/binfo")
	public String info(@RequestParam("no") int no, Model model) {
		bdao.read(no);
		BoardDto bdto = bdao.info(no);
		model.addAttribute("bdto", bdto);
		log.debug("업로드된 filename = "+bdto.getFilename());
		return "board/info";
	}
	
	@RequestMapping("/bedit")
	public String edit(@RequestParam("no") int no,
					   Model model) {
		BoardDto bdto = bdao.info(no);
		model.addAttribute("bdto", bdto);
		model.addAttribute("no", no);
		return "board/bedit";
	}
	
	@RequestMapping(value="/bedit", method=RequestMethod.POST)
	public String edit(HttpServletRequest request,
					   @RequestParam("board_no") int num) {
		int no = bdao.edit(new BoardDto(request));
		return "redirect:binfo?no="+no;
	}
	
	@RequestMapping("/bdelete")
	public String delete(@RequestParam("no") int no, Model model) {
		bdao.delete(no);
		return "redirect:/blist";
	}
	
	
	@RequestMapping("/download/{filename}")
	public void download(@PathVariable String filename,
					HttpServletRequest request,
					HttpServletResponse response) throws Exception { 
		
		String save = request.getServletContext().getRealPath("/file/"+request.getParameter("name"));
		log.debug("다운 로드 받을 filename = "+filename);
		File target = new File(save, filename);
		byte[] data = FileUtils.readFileToByteArray(target);
		
		response.setContentType("application/octet-stream");		//전송 할 유형
		response.setContentLength(data.length);						//전송 할 크기
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Disposition",
							"attachment:fileName=\""+filename+"\";");
		
		OutputStream out = response.getOutputStream();
		out.write(data);
		out.close();
	}
}















