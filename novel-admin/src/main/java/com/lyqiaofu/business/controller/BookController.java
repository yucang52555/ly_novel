package com.lyqiaofu.business.controller;

import com.lyqiaofu.business.domain.BookDO;
import com.lyqiaofu.business.service.BookService;
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

@RequestMapping("/business/book")
@Controller
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping()
	@RequiresPermissions("business:book:book")
	String Order() {
		return "business/book/book";
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

}
