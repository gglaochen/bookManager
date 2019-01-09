package com.chl.bookmanager.service.impl;

import com.chl.bookmanager.domain.SchoolMember;
import com.chl.bookmanager.mapper.SchoolMemberMapper;
import com.chl.bookmanager.service.SchoolMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author:Administrator
 * @date:2018/12/28/028
 */
@Service
public class SchoolMemberServiceImpl implements SchoolMemberService {
    @Autowired
    private SchoolMemberMapper schoolMemberMapper;

    @Override
    public boolean checkMemberIdHasExist(String memberId, Integer userType) {
        SchoolMember member = schoolMemberMapper.selectByPrimaryKey(memberId);
        if (member == null|| !member.getTypeId().equals(userType)) {
            return false;
        }else return member.getMemberId().equals(memberId) && member.getIsUsed() == 0;
    }
}
