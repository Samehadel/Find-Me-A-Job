package com.company.app.service.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.io.entities.KeywordEntity;
import com.company.app.repositery.KeywordRepository;
import com.company.app.service.IKeywordService;
import com.company.app.shared.dto.KeywordDto;

@Service
public class KeywordServiceImplementation implements IKeywordService {

	@Autowired
	KeywordRepository keywordRepo;
	
	
	@Override
	public boolean addkeyword(String keyword) {

		KeywordEntity keywordEntity = new KeywordEntity();

		keywordEntity.setDescription(keyword);
		
		//Save and check back object
		if(keywordRepo.save(keywordEntity) == null)
			return false;
		
		return true;
	}
	
	@Override
	public KeywordEntity getKeywordById(int id) {
		return keywordRepo.findById(id);
	}

	@Override
	public List<KeywordDto> getKeywords() {

		//List of keyword DTOs
		ArrayList<KeywordDto> keywordsDto = new ArrayList<KeywordDto>();
		KeywordDto dto = new KeywordDto();
		
		//Use Repository
		Iterable<KeywordEntity> keywords = keywordRepo.findAll();
		
		Iterator<KeywordEntity> itr = keywords.iterator();
		
		while(itr.hasNext()) {
			BeanUtils.copyProperties(itr.next(), dto);
			keywordsDto.add(dto);
			
			dto = new KeywordDto();
		}
	
		return keywordsDto;
	}

}
