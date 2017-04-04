package com.ccc.controller;

import com.ccc.base.Controller.BaseController;
import com.ccc.base.constants.Constants;
import com.ccc.base.entity.ResultInfo;
import com.ccc.base.exception.UserException;
import com.ccc.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserController extends BaseController {
    /**
     * 根据id，删除数据
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public User deleteUserById(HttpServletRequest request) throws Exception{
        Integer id = 0;
        try {
            /*获取用户id*/
            id = Integer.parseInt(request.getParameter("id"));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new UserException(Constants.USER_DELETE_ERROR);
        }

        return userService.deleteUser(id);
    }

    /**
     * 根据id，编辑用户
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public User editingUser(@RequestBody User user) throws Exception{
        return userService.updateUser(user);
    }

    /**
     * 添加用户
     * @param user
     * @throws Exception
     */
    @RequestMapping(value="/add", consumes = "application/json", method=RequestMethod.POST)
    public void saveUser(@RequestBody User user) throws Exception{
        userService.addUser(user);
    }

    /**
     * 查找用户
     * @return 全部用户
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultInfo<User> getUsers(HttpServletRequest request,User user) throws Exception{
        ResultInfo<User> result = new ResultInfo();
        String phone = null;
        Integer id = 0;
        try {
            if(user.getId() != null || user.getAddress() != null || user.getName() != null || user.getPhone() != null){
                if(!("".equals(user.getAddress()) && "".equals(user.getName()) && "".equals(user.getPhone()))){
                    List<User> users = userService.getUsersByConditionWithPage(user);
                    result.setRows(users);
                    result.setTotal(users.size());
                    return result;
                }
            }
            /*分页信息*/
            Integer page = Integer.valueOf(request.getParameter("page")) - 1;
            Integer pageSize = Integer.valueOf(request.getParameter("rows"));
            /*进行无条件分页查询*/
            Page<User> users = userService.getUsersWithPage(page, pageSize);
            result.setTotal(users.getTotalElements());
            result.setRows(users.getContent());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new UserException(Constants.USER_SEARCH_ERROR);
        }
        return result;
    }

    /**
     * 查找用户
     * @return 全部用户
     * @throws Exception
     */
    @RequestMapping(value = "/list/condition", method = RequestMethod.GET)
    public ResultInfo<User> getUsersWithCondition(User user) throws Exception{
        ResultInfo<User> result = new ResultInfo();
        try {
            List<User> users = userService.getUsersByConditionWithPage(user);
            result.setTotal(100);
            result.setRows(users);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new UserException(Constants.USER_SEARCH_ERROR);
        }
        return result;
    }





}
