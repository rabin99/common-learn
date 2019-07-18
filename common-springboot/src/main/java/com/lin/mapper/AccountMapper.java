package com.lin.mapper;

import com.lin.domain.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/16 13:45
 * @desc :
 */
@Mapper
@Repository
public interface AccountMapper {
    @Select("select * from account")
    List<Account> list();

    @Select("select * from account where id=#{id}")
    Account findById(@Param("id") int id);

    int update();

 /*    <selectKey keyProperty="userId" keyColumn="id" resultType="int" order="AFTER">
    select last_insert_id();
        </selectKey>*/
    @Insert("insert into account(id,name,money) values(#{id},#{name},#{money})")
    @SelectKey(resultType = Integer.class,keyProperty = "id",keyColumn = "id",before = false,statement = "select last_insert_id() as id")
    int add(@Param("id") int id,@Param("name") String name, @Param("money") BigDecimal money);

    @Delete("delete from account where id=#{id}")
    int delete(int id);


}
