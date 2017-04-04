package com.ccc.service;

import com.ccc.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by ccc on 2016/12/9.
 */
public interface UserService{
    /**
     * 添加用户
     * @param user 用户信息
     * @throws Exception
     */
    void addUser(User user) throws Exception;

    /**
     * 修改用户
     * @param user 用户信息
     * @return
     * @throws Exception
     */
    User updateUser(User user) throws Exception;

    /**
     * 根据用户编号删除用户*
     * @param id 用户id
     * @return
     * @throws Exception
     */
    User deleteUser(int id) throws Exception;

    /**
     * 查询单个用户
     * @param id 用户id
     * @return
     */
    User getUser(int id);

    /**
     * 带分页无条件查询(需要得到用户列表并且得到分页信息)
     * @param page 当前页
     * @param pageSize 每页显示的记录数
     * @return
     */
    Page<User> getUsersWithPage(Integer page, Integer pageSize);

    /**
     * 带分页条件查询(需要得到用户列表并且得到分页信息)
     * @return
     */
    List<User> getUsersByConditionWithPage(
            User user);
}
