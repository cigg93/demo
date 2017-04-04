package com.ccc.dao;

import com.ccc.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ccc on 2016/12/10.
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    /*带分页无条件查询(需要得到用户列表并且得到分页信息)*/
    Page<User> findAll(Pageable pageable);

    /*根据手机号、姓名、地址进行模糊查询*/
    List<User> findByNameContainingAndPhoneContainingAndAddressContaining(String phone, String name, String address, Sort sort);

}
