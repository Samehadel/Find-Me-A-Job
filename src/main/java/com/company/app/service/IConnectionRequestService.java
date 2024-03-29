package com.company.app.service;

import com.company.app.shared.dto.RequestDto;

public interface IConnectionRequestService {
	
	public RequestDto createConnectionRequest(RequestDto requestDto) throws Exception;
	
	public boolean acceptConnectionRequest(long id);
	
	public void rejectConnectionRequest(long id);
}
