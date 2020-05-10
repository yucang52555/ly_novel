package com.lyqiaofu.system.service;

import java.io.PrintStream;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

import com.lyqiaofu.system.domain.UserDO;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.lyqiaofu.system.domain.UserOnline;

@Service
public interface SessionService {
	List<UserOnline> list();

	List<UserDO> listOnlineUser();

	Collection<Session> sessionList();
	
	boolean forceLogout(String sessionId);
}
