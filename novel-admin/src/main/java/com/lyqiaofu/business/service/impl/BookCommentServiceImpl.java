package com.lyqiaofu.business.service.impl;

import com.lyqiaofu.business.dao.BookCategoryDao;
import com.lyqiaofu.business.dao.BookCommentDao;
import com.lyqiaofu.business.domain.BookCategoryDO;
import com.lyqiaofu.business.domain.BookCommentDO;
import com.lyqiaofu.business.service.BookCategoryService;
import com.lyqiaofu.business.service.BookCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookCommentServiceImpl implements BookCommentService {

    @Autowired
    BookCommentDao bookCommentDao;

    @Override
    public List<BookCommentDO> list(Map<String, Object> map) {
        return bookCommentDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return bookCommentDao.count(map);
    }

    @Override
    public int remove(String bookId) {
        return bookCommentDao.remove(bookId);
    }
}
