package com.learn.jdbc.tools;

import java.util.List;

public class Page<T> {
    private List<T> list; // 每页查询出来的数据存放的集合
    public static final int PAGE_SIZE = 4; // 每页显示的记录数
    private int pageNo; // 当前页，通过用户传入
    private int totalRecord; //总记录数，通过查询数据库得到
    private String path; //设置请求的地址

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNo() {
        if (pageNo < 1) {
            // 如果当前页码小于1，直接返回1
            return 1;
        }else if(getTotalPageNo() == 0){
            return 1;
        } else if (pageNo > getTotalPageNo()) {
            // 如果当前页码大于总页数，返回总页数
            return getTotalPageNo();
        } else {
            return pageNo;
        }
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    // 总页数是由总记录数和每页显示的条数计算得到
    public int getTotalPageNo() {
        if (totalRecord % PAGE_SIZE == 0) {
            return totalRecord / PAGE_SIZE;
        } else {
            return totalRecord / PAGE_SIZE + 1;
        }
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    // 判断是否有上一页
    public boolean hasPrev() {
        return getPageNo() > 1;
    }

    // 获取上一页
    public int getPrev() {
        return hasPrev() ? getPageNo() - 1 : 1;
    }

    // 判断是否有下一页
    public boolean hasNext() {
        return getPageNo() < getTotalPageNo();
    }

    // 获取下一页
    public int getNext() {
        return hasNext() ? getPageNo() + 1 : getTotalPageNo();
    }
}