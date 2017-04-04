package com.ccc.base.Controller;

import com.ccc.service.UserService;

import javax.annotation.Resource;

/**
 * Created by ccc on 2016/12/9.
 */
public class BaseController {

    @Resource
    protected UserService userService;

}
