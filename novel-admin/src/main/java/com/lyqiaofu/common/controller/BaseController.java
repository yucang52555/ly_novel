package com.lyqiaofu.common.controller;

import com.lyqiaofu.system.domain.UserToken;
import org.springframework.stereotype.Controller;
import com.lyqiaofu.common.utils.ShiroUtils;
import com.lyqiaofu.system.domain.UserDO;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}