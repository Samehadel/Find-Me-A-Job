package com.company.app.ws.ui.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.ws.ui.models.response.KeywordResponseModel;

@RestController
@RequestMapping("/keywords")
public class KeywordsController {

	@PostMapping("/create")
	public String createNewKeyword(@RequestBody String decription) {
		return decription + " Created";
	}
	
	@GetMapping
	public List<KeywordResponseModel> retrieveAllKeywords(){
		return null;
	}
}
