package com.lyqiaofu.business.service.impl;

import com.lyqiaofu.business.dao.BookCommentDao;
import com.lyqiaofu.business.dao.BookContentDao;
import com.lyqiaofu.business.domain.BookCommentDO;
import com.lyqiaofu.business.domain.BookContentDO;
import com.lyqiaofu.business.service.BookCommentService;
import com.lyqiaofu.business.service.BookContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookContentServiceImpl implements BookContentService {

    @Autowired
    BookContentDao bookContentDao;

    @Override
    public int remove(String indexId) {
        return bookContentDao.remove(indexId);
    }

    @Override
    public int batchremove(Long[] indexIds) {
        int count = bookContentDao.batchRemove(indexIds);
        return count;
    }
}
