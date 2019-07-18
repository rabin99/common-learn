package com.lin0302.domain;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/27 15:12
 * @desc :
 */
public class QueryVo {
    private User user;
    private List<Integer> ids;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
