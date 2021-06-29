package com.lyqiaofu.business.service.impl;

import com.lyqiaofu.business.dao.BookDao;
import com.lyqiaofu.business.dao.BookSettingDao;
import com.lyqiaofu.business.domain.BookDO;
import com.lyqiaofu.business.domain.BookSettingDO;
import com.lyqiaofu.business.service.BookSettingService;
import com.lyqiaofu.common.enums.RecommendTypeEnum;
import com.lyqiaofu.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookSettingServiceImpl implements BookSettingService {

    @Autowired
    BookSettingDao bookSettingDao;

    @Autowired
    BookDao bookDao;

    @Override
    public List<BookSettingDO> list(Map<String, Object> params) {
        List<BookSettingDO> bookSettingList = bookSettingDao.list(params);
        bookSettingList.stream().forEach(o -> {
            o.setTypeName(RecommendTypeEnum.getNameByCode(o.getType()));
            BookDO bookDo = bookDao.get(o.getBookId());
            o.setBookName(bookDo.getBookName());
        });
        return bookSettingList;
    }

    @Override
    public int count(Query query) {
        return bookSettingDao.count(query);
    }

    @Override
    public BookSettingDO get(Long id) {
        return bookSettingDao.get(id);
    }

    @Override
    public int remove(Long id) {
        return bookSettingDao.remove(id);
    }

    @Override
    public int update(BookSettingDO bookSetting) {
        return 0;
    }
}
