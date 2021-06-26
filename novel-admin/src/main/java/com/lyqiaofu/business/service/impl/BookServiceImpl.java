package com.lyqiaofu.business.service.impl;

import com.lyqiaofu.business.dao.BookDao;
import com.lyqiaofu.business.domain.BookDO;
import com.lyqiaofu.business.service.BookService;
import com.lyqiaofu.common.utils.IdWorker;
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

    @Override
    public BookDO get(Long id) {
        return bookDao.get(id);
    }

    @Override
    public int update(BookDO book) {
        return bookDao.update(book);
    }

    @Override
    public int save(BookDO book) {
        book.setId(new IdWorker().nextId());
        book.setCommentCount(100);
        book.setVisitCount(100L);
        book.setWordCount(100000);
        return bookDao.save(book);
    }

    @Override
    public int batchremove(Long[] bookIds) {
        // todo 20210625
        return 0;
    }
}
