package com.meesig.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meesig.model.PhotoInfo;


@Repository
public class MediaDBManager {

    @Autowired
    private SqlSession sqlSession;

    public MediaDBManager() {

    }

	public void insertFileInfo(PhotoInfo pi) {
		sqlSession.insert("MediaMapper.insertPhoto", pi);
	}
}
