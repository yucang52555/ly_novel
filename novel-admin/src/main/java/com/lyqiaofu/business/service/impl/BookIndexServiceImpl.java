package com.lyqiaofu.business.service.impl;

import com.lyqiaofu.business.dao.BookIndexDao;
import com.lyqiaofu.business.domain.BookIndexDO;
import com.lyqiaofu.business.service.BookIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookIndexServiceImpl implements BookIndexService {

    @Autowired
    BookIndexDao bookIndexDao;

    @Override
    public List<BookIndexDO> list(Map<String, Object> map) {
        return bookIndexDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return bookIndexDao.count(map);
    }

    @Override
    public int remove(String bookId) {
        return bookIndexDao.remove(bookId);
    }
}
