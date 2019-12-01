package com.lzu.test;



import com.lzu.dao.IUserDao;
import com.lzu.domain.QueryVo;
import com.lzu.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * mybatis的入门案例
 */
public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init()throws Exception{
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //        2.使用SqlSessionFactory对象
//        SqlSessionFactory factory = builder.build(in);
        sqlSession = factory.openSession();
//        4.使用SqlSession创建Dao接口的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy()throws Exception{
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }
    /**
     * 入门案例,查找所有
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 保存测试
     */
    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("wuyuyao");
        user.setAddress("lanzhou");
        user.setSex("m");
        user.setBirthday(new Date());

        userDao.saveUser(user);

    }

    /**
     * 删除测试
     */
    @Test
    public void deleteUser(){

        userDao.deleteUser(49);

    }

    /**
     * 测试模糊查询
     */
    @Test
    public void testFindLikeUser(){

        List<User> users = userDao.findLikeUser("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * 查找所有用户数
     */
    @Test
    public void testFindTotal(){
        int count = userDao.findTotal();
        System.out.println(count);
    }

    /**
     * 使用QueryVo中的条件查询
     */
    @Test
    public void testFindUserByVo(){

        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);

        List<User> users = userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }



}
