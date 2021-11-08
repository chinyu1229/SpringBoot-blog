package com.mszlu.blog.service;

import com.mszlu.blog.dao.pojo.SysUser;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.LoginParam;
import org.springframework.transaction.annotation.Transactional;

public interface LoginService {
    /**
     * 登入功能
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);

    SysUser checkToken(String token);

    /**
     * 退出
     * @param token
     * @return
     */
    Result logout(String token);

    /**
     * 註冊
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);
}
