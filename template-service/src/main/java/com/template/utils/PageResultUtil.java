package com.template.utils;

import com.github.pagehelper.Page;
import com.template.pojo.PageResult;

import java.util.List;

/**
 * @author frank.xiong
 * @date 2020/3/23 14:21
 */
public class PageResultUtil {

    public static <E> PageResult<E> pageResult(List<E> list) {
        PageResult<E> pageResult = new PageResult<>();
        if (list == null) {
            return pageResult;
        }
        if (list instanceof Page) {
            Page<E> page = (Page<E>) list;
            pageResult.setPageNo(page.getPageNum());
            pageResult.setPageSize(page.getPageSize());
            pageResult.setTotalPages(page.getPages());
            pageResult.setTotalRecords(page.getTotal());
            pageResult.setRecords(page.getResult());
        } else {
            pageResult.setPageNo(1);
            pageResult.setPageSize(list.size());
            pageResult.setTotalPages(1);
            pageResult.setTotalRecords(list.size());
            pageResult.setRecords(list);
        }
        return pageResult;
    }

    public static <E, T> PageResult<T> pageResult(List<E> list, Class<T> clazz) {
        PageResult<T> pageResult = new PageResult<>();
        if (list == null) {
            return pageResult;
        }
        if (list instanceof Page) {
            Page<E> page = (Page<E>) list;
            pageResult.setPageNo(page.getPageNum());
            pageResult.setPageSize(page.getPageSize());
            pageResult.setTotalPages(page.getPages());
            pageResult.setTotalRecords(page.getTotal());
            pageResult.setRecords(BeanUtils.copyProperties(page.getResult(), clazz));
        } else {
            pageResult.setPageNo(1);
            pageResult.setPageSize(list.size());
            pageResult.setTotalPages(1);
            pageResult.setTotalRecords(list.size());
            pageResult.setRecords(BeanUtils.copyProperties(list, clazz));
        }
        return pageResult;
    }
}

