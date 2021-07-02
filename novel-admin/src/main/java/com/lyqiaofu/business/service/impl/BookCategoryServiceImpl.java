package com.lyqiaofu.business.service.impl;

import com.lyqiaofu.business.dao.BookCategoryDao;
import com.lyqiaofu.business.dao.BookIndexDao;
import com.lyqiaofu.business.domain.BookCategoryDO;
import com.lyqiaofu.business.domain.BookDO;
import com.lyqiaofu.business.domain.BookIndexDO;
import com.lyqiaofu.business.service.BookCategoryService;
import com.lyqiaofu.business.service.BookIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    @Autowired
    BookCategoryDao bookCategoryDao;

    @Override
    public List<BookCategoryDO> list(Map<String, Object> map) {
        return bookCategoryDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return bookCategoryDao.count(map);
    }

    @Override
    public int remove(String bookId) {
        return bookCategoryDao.remove(bookId);
    }

    @Override
    public int save(BookCategoryDO bookCategory) {
        bookCategory.setSort(10);
        return bookCategoryDao.save(bookCategory);
    }
}
