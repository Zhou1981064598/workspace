package com.zhou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhou.dao.RecordMapper;
import com.zhou.domain.Record;
import com.zhou.domain.User;
import com.zhou.enetity.PageResult;
import com.zhou.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class RecordServiceImpl implements RecordService {
    @Resource RecordMapper recordMapper;
    @Override
    public Integer addRecord(Record record) {

            return recordMapper.addRecord(record);
    }

    /**
     * 查询借阅记录
     * @param record 借阅记录
     * @param user 当前登录用户
     * @param pageNum   当前的页码
     * @param pageSize  每页显示的记录数量
     * @return 记录结果
     */
    @Override
    public PageResult searchRecords(Record record, User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        //如果不是管理员,则查询条件中的借阅人设置为当前登录用户
        if(!"admin".equals(user.getRole())){
            record.setBorrower(user.getName());
        }
        Page<Record> page=recordMapper.searchRecord(record);
        return new PageResult(page.getTotal(),page.getResult());

    }
}
