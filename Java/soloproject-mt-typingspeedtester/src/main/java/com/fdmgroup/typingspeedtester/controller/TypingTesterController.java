package com.fdmgroup.typingspeedtester.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.typingspeedtester.model.RandomWordList;
import com.fdmgroup.typingspeedtester.model.User;
import com.fdmgroup.typingspeedtester.service.RandomWordListService;
import com.fdmgroup.typingspeedtester.service.UserService;
import com.fdmgroup.typingspeedtester.service.WordService;

@Controller
public class TypingTesterController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	WordService wordService;
	
	@Autowired
	RandomWordListService randomWordListService;
	
	@RequestMapping("TypingTester")
	public ModelAndView listWords(@ModelAttribute("randomWordList") RandomWordList randomWordList, ModelMap model, HttpSession session) {
		User userInSession = (User) session.getAttribute("user");
		long userId = userInSession.getUserId();
		ArrayList<String> listOfRandomWords = wordService.findSpecificNumberOfWords(10);
		randomWordList.setRandomWordListId(userId);
		randomWordList.setRandomWordListWords(listOfRandomWords);
		randomWordListService.save(randomWordList);
		List<RandomWordList> listOfRandomWordSets = new ArrayList<RandomWordList>();
		listOfRandomWordSets.add(randomWordList);
		userInSession.setListOfRandomWordSets(listOfRandomWordSets);
		return new ModelAndView("WEB-INF/typingtester.jsp", "listofrandomwordsforuser", userInSession.getListOfRandomWordSets());
	}

//	@PostMapping("AddUserInput")
//	public ModelAndView inputWordsSubmit() {
//		ModelAndView modelAndView = new ModelAndView("WEB-INF/typingtester.jsp");
//		modelAndView.addObject("inputwordlist", new InputWordList());
//		return modelAndView;
//	}

	
}