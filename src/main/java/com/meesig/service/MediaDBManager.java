package com.meesig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meesig.mapper.MediaMapper;
import com.meesig.model.PhotoInfo;


@Repository
public class MediaDBManager {

    @Autowired
    private MediaMapper mediaMapper;

    public MediaDBManager() {

    }

	public void insertFileInfo(PhotoInfo pi) {
		mediaMapper.insertPhoto(pi);
	}
}
