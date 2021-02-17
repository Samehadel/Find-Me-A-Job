package com.company.app.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.service.IKeywordService;
import com.company.app.shared.dto.KeywordDto;
import com.company.app.ui.models.response.KeywordResponseModel;

@RestController
@RequestMapping("/keywords")
public class KeywordsController {

	@Autowired
	IKeywordService keywordService;
	
	@PostMapping("/create")
	public void createNewKeyword(@RequestBody String decription, HttpServletResponse res) {
		boolean check = keywordService.addkeyword(decription);
		
		if(!check)
			res.setStatus(500);
	}
	
	@GetMapping
	public List<KeywordResponseModel> retrieveAllKeywords(){
		
		KeywordResponseModel keywordResponse = new KeywordResponseModel();
		List<KeywordResponseModel> returnKeywords = new ArrayList<KeywordResponseModel>();
		
		//Call the service
		List<KeywordDto> keywordsDto = keywordService.getKeywords();
		
		//Convert DTOs to Response model
		for(KeywordDto dto: keywordsDto) {
			BeanUtils.copyProperties(dto, keywordResponse);
			returnKeywords.add(keywordResponse);
			
			keywordResponse = new KeywordResponseModel();
		}
		
		return returnKeywords;
	}
}
