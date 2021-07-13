package com.lyqiaofu.business.controller;

import com.lyqiaofu.business.domain.BookIndexDO;
import com.lyqiaofu.business.service.BookIndexService;
import com.lyqiaofu.common.annotation.Log;
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

@RequestMapping("/business/index")
@Controller
public class BookIndexController {

	@Autowired
	BookIndexService bookIndexService;

	@GetMapping()
	@RequiresPermissions("business:index:index")
	String bookIndex() {
		return "business/index/index";
	}

	@ApiOperation(value = "获取章节列表", notes = "获取章节列表")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:index:list")
	R list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BookIndexDO> bookList = bookIndexService.list(params);
		int total = bookIndexService.count(query);
		PageBean pageBean = new PageBean(bookList, total);
		return R.ok().put("data", pageBean);
	}

	@ApiOperation(value = "删除章节数据", notes = "删除章节数据")
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("business:index:remove")
	public R remove(String id) {
		if (bookIndexService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("business:index:remove")
	@Log("批量删除")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] bookIds) {
		int r = bookIndexService.batchremove(bookIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

}
