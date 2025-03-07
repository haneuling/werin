package com.multi.werin.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@Autowired
	MainDAOInterface maindao;
	
	/*
	 * @Autowired PointService pointService;
	 */

	@RequestMapping("main/recommendlandmark")
	public void recommendlandmark(MainlandmarkVO mainlandmarkVO, Model model) {
		List<MainlandmarkVO> recommendlandmark = maindao.recommendlandmark(mainlandmarkVO);
		model.addAttribute("recommendlandmark", recommendlandmark);
		/* pointService.userpoint(5, memberVO-->member_id); */	
	}
	
	
	@RequestMapping("main/search")
	public void mainsearch(SearchLandmarkVO searchlandmarkVO, SearchBbsVO searchbbsVO, SearchTripVO searchtripVO, SearchVO searchVO, Model model) {
		List<SearchLandmarkVO> searchlandmark = maindao.searchlandmark(searchlandmarkVO);
		List<SearchBbsVO> searchbbs = maindao.searchbbs(searchbbsVO);
		List<SearchTripVO> searchtrip = maindao.searchtrip(searchtripVO);
		
		//위에 어떤 값들이 들어가는지 확인해볼 필요가 있음
		 int result = 0;
		 if(searchlandmark.isEmpty() && searchbbs.isEmpty() && searchtrip.isEmpty()) { // 아무런 검색 결과가 없는 경우
			 result = 1;
		 }
		 if(searchtripVO.getSearching().isEmpty()) {
			 result = 1;
		 }
		 if(result==0) {
			 model.addAttribute("searchlandmark", searchlandmark);
			 model.addAttribute("searchbbs", searchbbs);
			 model.addAttribute("searchtrip", searchtrip);	
			 model.addAttribute("searching", searchtripVO.getSearching());
		 }
		
		System.out.println(searchlandmark);
		System.out.println(searchbbs);
		System.out.println(searchtrip); 
		System.out.println(result);
		System.out.println(searchtripVO.getSearching());
		
		 model.addAttribute("result", result);
		//views/main/serarch.jsp
		 
		/* 여기까지가 통합검색 */
		
		/* 여기서부터가 페이지버튼 */
		/*
		 * int count = maindao.bbs_count(searchVO); System.out.println("bbs count: "+
		 * count); int pages = count/10; if(count%10!=0) { pages=count/10+1; }
		 * model.addAttribute("pages", pages); model.addAttribute("count", count);
		 */
	}
	
	/* search페이지에서 페이징 사용하기 위한 함수 */
	/*
	 * @RequestMapping("main/searchbbs") public void searchbbs(SearchBbsVO
	 * searchbbsVO, Model model) { List<SearchBbsVO> searchbbs =
	 * maindao.searchbbs(searchbbsVO); model.addAttribute("searchbbs", searchbbs);
	 * System.out.println(searchbbs); }
	 */
	
	@RequestMapping("main/hottravel")
	public void hottravel(MaintripVO maintripVO, Model model) {
		List<MaintripVO> hottravel = maindao.hottravel(maintripVO);
		model.addAttribute("hottravel", hottravel);
	}

	@RequestMapping("main/hotshop")
	public void hotshop(MainintroducestoreVO mainintroducestoreVO, Model model) {
		List<MainintroducestoreVO> hotshop = maindao.hotshop(mainintroducestoreVO);
		model.addAttribute("hotshop", hotshop);
	}

	@RequestMapping("main/hotboard")
	public void hotboard(MainbbsVO mainbbsVO, Model model) {
		List<MainbbsVO> hotboard = maindao.hotboard(mainbbsVO);
		model.addAttribute("hotboard", hotboard);
	}
	
	@RequestMapping("main/morelandmark")
	public void morelandmark(SearchVO searchVO, Model model) {
		searchVO.setStartEnd();
		System.out.println("searchVO : " + searchVO);
		List<SearchLandmarkVO> searchlandmark = maindao.morelandmark(searchVO);
		System.out.println(searchlandmark);
		int count = maindao.landmark_count(searchVO);
		System.out.println("count : " + count); //1?
		int pages = count/2; // 1/10  pages = 0
		if(count%2!=0) { //1 % 10 --->1 
			pages=count/2 + 1; //pages = 0/10 + 1 = 1
		}		
		model.addAttribute("searchlandmark", searchlandmark);
		model.addAttribute("pages", pages);
		model.addAttribute("count", count);
		model.addAttribute("searching", searchVO.getSearching());
	}
	
	@RequestMapping("main/moretrip")
	public void moretrip(SearchVO searchVO, Model model) {
		searchVO.setStartEnd();
		System.out.println("searchVO : " + searchVO);
		List<SearchTripVO> searchtrip = maindao.moretrip(searchVO);
		System.out.println("searchtrip : " + searchtrip.size());
		System.out.println(searchtrip);
		int count = maindao.trip_count(searchVO);
		System.out.println("count: "+count);
		int pages = count/2; //2
		if(count%2!=0) {
			pages=count/2 + 1;
		}
		model.addAttribute("searchtrip", searchtrip);
		model.addAttribute("pages", pages);
		model.addAttribute("count", count);		
		model.addAttribute("searching", searchVO.getSearching());
	}
	
	@RequestMapping("main/morebbs")
	public void morebbs(SearchVO searchVO, Model model) {
		searchVO.setStartEnd();
		System.out.println("searchVO : " + searchVO);
		List<SearchBbsVO> searchbbs = maindao.morebbs(searchVO);
		System.out.println("searchbbs : " + searchbbs.size());
		int count = maindao.bbs_count(searchVO);
		System.out.println("count: "+count);
		int pages = count/2;
		if(count%2!=0) {
			pages=count/2;
		}
		model.addAttribute("searchbbs", searchbbs);
		model.addAttribute("pages", pages);
		model.addAttribute("count", count);		
		model.addAttribute("searching", searchVO.getSearching());
	}
	
	
	/* 복합 페이징 보류 */
	/*
	 * @RequestMapping("main/searchbbs") public void searchbbs(SearchVO searchVO,
	 * Model model) {
	 * 
	 * searchVO.setStartEnd(); System.out.println("searchVO : " + searchVO);
	 * List<SearchBbsVO> searching = maindao.morebbs(searchVO);
	 * System.out.println("searchbbs : " + searching.size());
	 * model.addAttribute("searching", searching); //여기서 검색값을 넘겨줌
	 * 
	 * int count = maindao.bbs_count(searchVO); System.out.println("bbs count: "+
	 * count); int pages = count/10; if(count%10!=0) { pages=count/10+1; }
	 * model.addAttribute("pages", pages); model.addAttribute("count", count); }
	 */

	/*
	 * @RequestMapping("main/hottravel") 다시 재호출하는방법 public String name() {
	 * return"redirect:main.jsp"; }
	 */

}
