package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {

    /**
     * 检查类型是否有效
     *
     * @param content
     * @param type
     *            1.username;2.phone;3.email
     * @return
     */
    TaotaoResult checkData(String content, int type);

    TaotaoResult createUser(TbUser user);

    TaotaoResult login(String username, String password);

    TaotaoResult getUserByToken(String token);
}
