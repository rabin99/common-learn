package com.lin.control;

import com.lin.domain.Account;
import com.lin.service.IAccountService;
import com.lin.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/16 14:38
 * @desc :
 */
@RestController
@RequestMapping("/account")
public class AccountControl {
    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test(){
        System.out.println("test");
    }

    @GetMapping("/get")
    public Account findById(@RequestParam("id") int id){
       return accountService.findById(id);
    }
    @PostMapping("/add")
    public int insert(@RequestBody Account account){
        return accountService.add(account);
    }
}
