package com.taotao.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestPageHelper {

    @Test
    public void testPageHelper() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext-*.xml");
        // 从spring容器中获得Mapper的代理对象
        TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
        // 执行查询，并分页
        // TbItemExample example = new TbItemExample();
        // // example.createCriteria().andIdEqualTo(927779L);
        // // 分页处理
        // PageHelper.startPage(2, 30);
        // List<TbItem> list = mapper.selectByExample(example);
        // // 取商品列表
        // for (TbItem tbItem : list) {
        // System.out.println(tbItem.getTitle());
        // }
        // // 取分页信息
        // PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        // long total = pageInfo.getTotal();
        // System.out.println("共有商品：" + total);
        test(mapper, 1, 10);
        System.out.println("------------------");
        test(mapper, 2, 10);
    }

    private void test(TbItemMapper mapper, int page, int rows) {
        TbItemExample example = new TbItemExample();
        // example.createCriteria().andIdEqualTo(927779L);
        // 分页处理
        PageHelper.startPage(2, 30);
        List<TbItem> list = mapper.selectByExample(example);
        // 取商品列表
        for (TbItem tbItem : list) {
            System.out.println(tbItem.getTitle());
        }
        // 取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        System.out.println("共有商品：" + total);
    }
}
