package com.zhou.service;

import com.zhou.domain.Record;
import com.zhou.domain.User;
import com.zhou.enetity.PageResult;
import org.springframework.stereotype.Service;

@Service
public interface RecordService {
    Integer addRecord(Record record);
    PageResult searchRecords(Record record, User user,Integer pageNum,Integer pageSize);
}
