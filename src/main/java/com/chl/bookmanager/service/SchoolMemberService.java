package com.chl.bookmanager.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SchoolMemberService {
    boolean checkMemberIdHasExist(String memberId,Integer userType);
}
