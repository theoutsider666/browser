package com.example.test2_springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.test2_springboot.controller.utils.R;
import com.example.test2_springboot.model.Gene;
import com.example.test2_springboot.service.IGeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/gene")
public class GeneController {
    private static final Logger logger = LoggerFactory.getLogger(GeneController.class);
    @Autowired(required = false)
    private IGeneService bookService;

    @GetMapping
    public R getAll(){
        return new R(true,bookService.list());
    }

    @PostMapping
    public R save(@RequestBody Gene gene) throws IOException {
//        R r=new R();
//        boolean flag=bookService.save(book);
//        r.setFlag(flag);
//        if(book.getName().equals("123")) throw new IOException();
        boolean flag=bookService.save(gene);
        return new R(flag,flag?"添加成功":"添加失败");
    }

    @PutMapping
    public R update(@RequestBody Gene gene){
        return new R(bookService.modify(gene));
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(bookService.delete(id));

    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        return new R(true,bookService.getById(id));
    }

    @RequestMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, Gene gene){

        IPage<Gene> page=bookService.getPage(currentPage,pageSize, gene);
        if(currentPage>page.getPages()){
            page=bookService.getPage((int)page.getPages(),pageSize, gene);
        }
        return new R(true,page);
    }

}
