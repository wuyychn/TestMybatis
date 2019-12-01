package com.lzu.dao;

import com.lzu.domain.QueryVo;
import com.lzu.domain.User;

import java.util.List;

/**
 * 用户持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有操作
     */
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    void deleteUser(Integer userId);

    List<User> findLikeUser(String username);

    /**
     * 查询总的用户数
     * @return
     */
    int findTotal();

    /**
     * 根据QueryVo中的条件查询user
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);


}
