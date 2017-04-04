package com.ccc.service.impl;

import com.ccc.base.constants.Constants;
import com.ccc.base.exception.UserException;
import com.ccc.dao.UserDao;
import com.ccc.entity.User;
import com.ccc.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ccc on 2016/12/9.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    /**
     * 添加用户
     * @param user 用户信息
     * @throws Exception
     */
    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public User updateUser(User user) throws Exception {
        /*根据用户id,获取查询用户*/
        User userUpdate = userDao.findOne(user.getId());
        /*判断用户是否存在，否则抛出用户不存在异常*/
        if (userUpdate==null) {
            throw new UserException(Constants.USER_NOT_FOUND);
        }
        /*如果用户名不为空，更新用户名*/
        if (user.getName()!=null) {
            userUpdate.setName(user.getName());
        }
        /*如果地址不为空，更新地址*/
        if (user.getAddress()!=null) {
            userUpdate.setAddress(user.getAddress());
        }
        /*如果电话不为空，更新电话*/
        if (user.getPhone()!=null) {
            userUpdate.setPhone(user.getPhone());
        }
        /*更新用户信息*/
        userDao.save(userUpdate);
        return userUpdate;
    }
    /**
     * 根据用户编号删除用户*
     * @param id 用户id
     * @return
     * @throws Exception
     */
    @Override
    public User deleteUser(int id) throws Exception {
        /*根据用户id,获取查询用户*/
        User userDelete = userDao.findOne(id);
        /*判断用户是否存在，否则抛出用户不存在异常*/
        if (userDelete==null)
            throw new UserException(Constants.USER_NOT_FOUND);
        /*删除用户*/
        userDao.delete(userDelete);
        return userDelete;
    }

    /**
     * 查询单个用户
     * @param id 用户id
     * @return
     */
    @Override
    public User getUser(int id) {
        return userDao.findOne(id);
    }

    /**
     * 带分页无条件查询(需要得到用户列表并且得到分页信息)
     * @param page 当前页
     * @param pageSize 每页显示的记录数
     * @return
     */
    public Page<User> getUsersWithPage(Integer page,Integer pageSize) {
        /*按照id升序排序*/
        Page<User> userPage = userDao.findAll(new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "id")));
        return userPage;
    }

    /**
     * 条件查询(需要得到用户列表并且得到分页信息)
     * @return
     */
    @Override
    public List<User> getUsersByConditionWithPage(User user) {
        /*设置默认值*/
        if (user.getName() == null) {
            user.setName("");
        }
        if (user.getPhone() == null) {
            user.setPhone("");
        }
        if (user.getAddress() == null) {
            user.setAddress("");
        }
        /*根据手机号、姓名、地址进行模糊查询*/
        List<User> users = userDao.findByNameContainingAndPhoneContainingAndAddressContaining(
                user.getName(), user.getPhone(), user.getAddress(), new Sort(Sort.Direction.ASC, "id"));
        return users;
    }

}
