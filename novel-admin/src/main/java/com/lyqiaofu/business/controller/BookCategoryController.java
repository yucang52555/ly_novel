package com.lyqiaofu.business.controller;

import com.lyqiaofu.business.domain.BookCategoryDO;
import com.lyqiaofu.business.service.BookCategoryService;
import com.lyqiaofu.common.utils.PageBean;
import com.lyqiaofu.common.utils.Query;
import com.lyqiaofu.common.utils.R;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/business/book/category")
@Controller
public class BookCategoryController {

	@Autowired
	BookCategoryService bookCategoryService;

	@GetMapping()
	@RequiresPermissions("business:book:category")
	String category() {
		return "business/book/category/category";
	}

	@ApiOperation(value = "获取章节列表", notes = "获取章节列表")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:book:category:list")
	R list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BookCategoryDO> bookCategoryList = bookCategoryService.list(params);
		int total = bookCategoryService.count(query);
		PageBean pageBean = new PageBean(bookCategoryList, total);
		return R.ok().put("data", pageBean);
	}

	@ApiOperation(value = "删除章节数据", notes = "删除章节数据")
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("business:book:category:remove")
	public R remove(String id) {
		if (bookCategoryService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

}
