package com.lin.domain;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @auther : lin
 * @date : 2019/7/3 13:45
 * @desc : 项目中禁止使用以User命名的类！
 */
@Data
public class User implements Serializable {
    @Id  // 必须指定id，否则每个字段都加入匹配
    private Integer id;
    private String username;
    private String password;
}

