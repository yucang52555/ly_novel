package com.lyqiaofu.business.service.impl;

import com.lyqiaofu.business.dao.BookDao;
import com.lyqiaofu.business.domain.BookDO;
import com.lyqiaofu.business.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public List<BookDO> list(Map<String, Object> map) {
        return bookDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return bookDao.count(map);
    }

    @Override
    public int remove(String bookId) {
        return bookDao.remove(bookId);
    }
}
