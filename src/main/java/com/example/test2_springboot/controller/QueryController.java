package com.example.test2_springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.test2_springboot.controller.utils.R;
import com.example.test2_springboot.model.Query;
import com.example.test2_springboot.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired(required = false)
    private QueryService bookService;

    @GetMapping
    public R getAll() {
        return new R(true, bookService.list());
    }

    @PostMapping
    public R save(@RequestBody Query query) throws IOException {
//        R r=new R();
//        boolean flag=bookService.save(book);
//        r.setFlag(flag);
//        if(book.getName().equals("123")) throw new IOException();
        boolean flag = bookService.save(query);
        return new R(flag, flag ? "添加成功" : "添加失败");
    }

    @PutMapping
    public R update(@RequestBody Query query) {
        return new R(bookService.modify(query));
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id) {
        return new R(bookService.delete(id));

    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id) {
        return new R(true, bookService.getById(id));
    }

    @RequestMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, Query query) {

        IPage<Query> page = bookService.getPage(currentPage, pageSize, query);
        if (currentPage > page.getPages()) {
            page = bookService.getPage((int) page.getPages(), pageSize, query);
        }
        return new R(true, page);
    }

}
