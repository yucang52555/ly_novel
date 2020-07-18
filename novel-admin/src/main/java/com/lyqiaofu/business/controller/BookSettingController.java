package com.lyqiaofu.business.controller;

import com.lyqiaofu.business.domain.BookSettingDO;
import com.lyqiaofu.business.service.BookSettingService;
import com.lyqiaofu.common.utils.PageBean;
import com.lyqiaofu.common.utils.Query;
import com.lyqiaofu.common.utils.R;
import com.lyqiaofu.test.domain.OrderDO;
import com.lyqiaofu.test.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Desc:书籍页面配置
 * @Author ktc
 * @Date  2020/6/18 14:19
 **/
@Controller
@RequestMapping("/business/book/setting")
public class BookSettingController {
    @Autowired
    private BookSettingService bookSettingService;

    @GetMapping()
    @RequiresPermissions("business:book:setting")
<<<<<<< HEAD
    String bookSetting() {
        return "business/book/setting";
=======
    String Order() {
        return "business/book/setting/setting";
>>>>>>> 0b3706a0f828eef233936de786f07a22e2a3f509
    }

    @ApiOperation(value = "获取书籍-设置列表", notes = "获取书籍-设置列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("business:book:setting")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<BookSettingDO> bookSettingList = bookSettingService.list(params);
        int total = bookSettingService.count(query);
        PageBean pageBean = new PageBean(bookSettingList, total);
        return R.ok().put("data", pageBean);
    }

    @ApiOperation(value = "新增书籍-设置列表页面", notes = "新增书籍-设置列表页面")
    @GetMapping("/add")
    @RequiresPermissions("business:booksetting:add")
    String add() {
        return "business/booksetting/add";
    }

    @ApiOperation(value = "修改书籍-设置列表页面", notes = "修改书籍-设置列表页面")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("business:booksetting:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        BookSettingDO bookSetting = bookSettingService.get(id);
        model.addAttribute("bookSetting", bookSetting);
        return "business/booksetting/edit";
    }

    @ApiOperation(value = "查看书籍详情-设置列表", notes = "查看书籍详情-设置列表")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("business:booksetting:detail")
    String detail(@PathVariable("id") Long id, Model model) {
        BookSettingDO bookSetting = bookSettingService.get(id);
        model.addAttribute("bookSetting", bookSetting);
        return "business/booksetting/detail";
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除付呗-订单信息表", notes = "删除书籍详情-设置列表")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("business:booksetting:remove")
    public R remove( Long id) {
        if (bookSettingService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

}
