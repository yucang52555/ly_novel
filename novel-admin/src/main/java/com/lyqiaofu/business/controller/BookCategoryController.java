package com.lyqiaofu.business.controller;

import com.lyqiaofu.business.domain.BookCategoryDO;
import com.lyqiaofu.business.domain.BookDO;
import com.lyqiaofu.business.service.BookCategoryService;
import com.lyqiaofu.common.annotation.Log;
import com.lyqiaofu.common.domain.DictDO;
import com.lyqiaofu.common.service.DictService;
import com.lyqiaofu.common.utils.PageBean;
import com.lyqiaofu.common.utils.Query;
import com.lyqiaofu.common.utils.R;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/business/category")
@Controller
public class BookCategoryController {

	private String prefix="business/category"  ;

	@Autowired
	BookCategoryService bookCategoryService;

	@Autowired
	DictService dictService;

	@GetMapping()
	@RequiresPermissions("business:category:category")
	String category() {
		return "business/category/category";
	}

	@ApiOperation(value = "获取类别列表", notes = "获取类别列表")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:category:list")
	R list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BookCategoryDO> bookCategoryList = bookCategoryService.list(params);
		List<DictDO> dictDOList = dictService.listByType("work_direction");
		bookCategoryList.stream().forEach(o -> {
			Optional<DictDO> dictOptional = dictDOList.stream().filter(p -> p.getValue().equals(o.getWorkDirection().toString())).distinct().findFirst();
			if (dictOptional.isPresent()) {
				o.setWorkDirectionDesc(dictOptional.get().getName());
			}
		});
		int total = bookCategoryService.count(query);
		PageBean pageBean = new PageBean(bookCategoryList, total);
		return R.ok().put("data", pageBean);
	}

	@RequiresPermissions("business:category:add")
	@Log("新增书籍类别")
	@GetMapping("/add")
	String add(Model model) {
		return prefix + "/add";
	}

	@RequiresPermissions("business:category:add")
	@Log("保存书籍类别")
	@PostMapping("/save")
	@ResponseBody
	R save(BookCategoryDO bookCategory) {
		if (bookCategoryService.save(bookCategory) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@ApiOperation(value = "删除类别数据", notes = "删除类别数据")
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("business:category:remove")
	public R remove(String id) {
		if (bookCategoryService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

}
