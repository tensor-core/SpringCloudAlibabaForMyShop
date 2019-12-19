package com.funtl.myshop.commons.mapper;

import com.funtl.myshop.commons.domain.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;



public interface TbUserMapper extends MyMapper<TbUser> {
}