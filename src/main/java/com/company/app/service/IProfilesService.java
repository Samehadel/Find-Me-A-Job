package com.company.app.service;

import java.util.List;

import com.company.app.ui.models.response.ProfileResponseModel;

public interface IProfilesService {

	public List<ProfileResponseModel> retrieveSimilarUsers(long userId) throws Exception;
}
