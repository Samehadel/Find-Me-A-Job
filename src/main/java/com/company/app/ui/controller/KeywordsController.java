package com.company.app.ui.controller;

import com.company.app.service.IKeywordService;
import com.company.app.shared.dto.KeywordDto;
import com.company.app.ui.models.response.KeywordResponseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/keywords")
public class KeywordsController {

	@Autowired
	IKeywordService keywordService;
	
	@PostMapping("/create")
	public ResponseEntity createNewKeyword(@RequestBody String description) {
		boolean check = keywordService.addkeyword(description);

		return check ? ResponseEntity.status(HttpStatus.OK).build() :
				ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping
	public ResponseEntity retrieveAllKeywords(){
		
		KeywordResponseModel keywordResponse = new KeywordResponseModel();
		List<KeywordResponseModel> returnKeywords = new ArrayList<>();
		
		// Call the service
		List<KeywordDto> keywordsDto = keywordService.getKeywords();
		
		// Convert DTOs to Response model
		for(KeywordDto dto: keywordsDto) {
			BeanUtils.copyProperties(dto, keywordResponse);
			returnKeywords.add(keywordResponse);
			
			keywordResponse = new KeywordResponseModel();
		}

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(returnKeywords);
	}
}
