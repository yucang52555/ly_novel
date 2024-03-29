package com.lyqiaofu.novel.service;


import com.lyqiaofu.novel.core.bean.UserDetails;
import com.lyqiaofu.novel.form.UserForm;
import com.lyqiaofu.novel.vo.BookReadHistoryVO;
import com.lyqiaofu.novel.vo.BookShelfVO;
import com.lyqiaofu.novel.entity.User;
import com.lyqiaofu.novel.vo.UserFeedbackVO;

import java.util.List;

/**
 * @author 11797
 */
public interface UserService {

    /**
     * 用户注册
     * @param form 用户注册提交信息类
     * @return jwt载体信息类
     * */
    UserDetails register(UserForm form);

    /**
     * 用户登陆
     * @param form 用户登陆提交信息类
     * @return jwt载体信息类
     * */
    UserDetails login(UserForm form);

    /**
     * 查询小说是否已加入书架
     * @param userId 用户ID
     * @param bookId 小说ID
     * @return true:已加入书架，未加入书架
     * */
    Boolean queryIsInShelf(Long userId, Long bookId);

    /**
     * 加入书架
     * @param userId 用户ID
     * @param bookId 小说ID
     * @param preContentId 阅读的内容ID
     * */
    void addToBookShelf(Long userId, Long bookId, Long preContentId);

    /**
     * 移出书架
     * @param userId 用户ID
     * @param bookId 小说ID
     * */
    void removeFromBookShelf(Long userId, Long bookId);

    /**
     * 查询书架
     * @param userId 用户ID
     * @param page
     * @param pageSize
     * @return 书架集合
     * */
    List<BookShelfVO> listBookShelfByPage(Long userId, int page, int pageSize);

    /**
     * 添加阅读记录
     * @param userId 用户id
     * @param bookId 书籍id
     * @param preContentId 阅读的目录id
     * */
    void addReadHistory(Long userId, Long bookId, Long preContentId);

    /**
     * 添加反馈
     * @param userId 用户id
     * @param content 反馈内容
     * */
    void addFeedBack(Long userId, String content);

    /**
     * 分页查询我的反馈列表
     * @param userId 用户ID
     * @param page 页码
     * @param pageSize 分页大小
     * @return 反馈集合
     * */
    List<UserFeedbackVO> listUserFeedBackByPage(Long userId, int page, int pageSize);

    /**
     * 查询个人信息
     * @param userId 用户id
     * @return 用户信息
     * */
    User userInfo(Long userId);

    /**
     * 分页查询阅读记录
     * @param userId 用户id
     * @param page 页码
     * @param pageSize 分页大小
     * @return
     * */
    List<BookReadHistoryVO> listReadHistoryByPage(Long userId, int page, int pageSize);

    /**
     * 更新个人信息
     * @param userId 用户id
     * @param user 需要更新的信息
     * */
    void updateUserInfo(Long userId, User user);

    /**
     * 更新密码
     * @param userId 用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * */
    void updatePassword(Long userId, String oldPassword, String newPassword);


}
