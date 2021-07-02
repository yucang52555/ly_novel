package com.lyqiaofu.business.controller;

import com.lyqiaofu.business.domain.BookDO;
import com.lyqiaofu.business.service.BookService;
import com.lyqiaofu.common.annotation.Log;
import com.lyqiaofu.common.config.Constant;
import com.lyqiaofu.common.utils.MD5Utils;
import com.lyqiaofu.common.utils.PageBean;
import com.lyqiaofu.common.utils.Query;
import com.lyqiaofu.common.utils.R;
import com.lyqiaofu.system.domain.RoleDO;
import com.lyqiaofu.system.domain.UserDO;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/business/book")
@Controller
public class BookController {

	private String prefix="business/book"  ;

	@Autowired
	BookService bookService;

	@GetMapping()
	@RequiresPermissions("business:book:book")
	String book() {
		return prefix + "/book";
	}

	@ApiOperation(value = "获取书籍列表", notes = "获取书籍列表")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:book:book")
	R list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BookDO> bookList = bookService.list(params);
		int total = bookService.count(query);
		PageBean pageBean = new PageBean(bookList, total);
		return R.ok().put("data", pageBean);
	}

	@ApiOperation(value = "编辑书籍信息", notes = "编辑书籍信息")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:book:edit")
	String edit(Model model, @PathVariable("id") Long id) {
		BookDO bookDO = bookService.get(id);
		model.addAttribute("book", bookDO);
		return prefix +"/edit";
	}

	@RequiresPermissions("business:book:edit")
	@Log("更新书籍信息")
	@PostMapping("/update")
	@ResponseBody
	R update(BookDO book) {
		if (bookService.update(book) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("business:book:add")
	@Log("新增书籍")
	@GetMapping("/add")
	String add(Model model) {
		return prefix + "/add";
	}

	@RequiresPermissions("business:book:add")
	@Log("保存书籍")
	@PostMapping("/save")
	@ResponseBody
	R save(BookDO book) {
		if (bookService.save(book) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@ApiOperation(value = "获取书籍详情", notes = "获取书籍详情")
	@ResponseBody
	@GetMapping("/detail")
	@RequiresPermissions("business:book:detail")
	R detail(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BookDO> bookList = bookService.list(params);
		int total = bookService.count(query);
		PageBean pageBean = new PageBean(bookList, total);
		return R.ok().put("data", pageBean);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除书籍数据", notes = "删除书籍数据")
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("business:book:remove")
	public R remove(String id) {
		if (bookService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("business:book:batchRemove")
	@Log("批量删除")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] bookIds) {
		int r = bookService.batchremove(bookIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

}
