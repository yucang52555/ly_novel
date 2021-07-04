package com.lyqiaofu.business.controller;

import com.lyqiaofu.business.domain.BookCategoryDO;
import com.lyqiaofu.business.domain.BookCommentDO;
import com.lyqiaofu.business.service.BookCategoryService;
import com.lyqiaofu.business.service.BookCommentService;
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

@RequestMapping("/business/comment")
@Controller
public class BookCommentController {

	@Autowired
	BookCommentService bookCommentService;

	@GetMapping()
	@RequiresPermissions("business:comment:comment")
	String comment() {
		return "business/comment/comment";
	}

	@ApiOperation(value = "获取评论列表", notes = "获取评论列表")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:comment:list")
	R list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BookCommentDO> bookCategoryList = bookCommentService.list(params);
		int total = bookCommentService.count(query);
		PageBean pageBean = new PageBean(bookCategoryList, total);
		return R.ok().put("data", pageBean);
	}

	@ApiOperation(value = "删除评论数据", notes = "删除评论数据")
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("business:comment:remove")
	public R remove(String id) {
		if (bookCommentService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

}
