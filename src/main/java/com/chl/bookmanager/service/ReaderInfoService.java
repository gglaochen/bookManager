package com.chl.bookmanager.service;

import com.chl.bookmanager.domain.BookBorrowVO;
import com.chl.bookmanager.domain.ReaderInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ReaderInfoService {
    ReaderInfo login(String username, String password);
    int register(ReaderInfo readerInfo,String memberId);
    boolean checkUsernameHasExist(String username);
    boolean checkPhoneHaxExist(String phone);
    List<BookBorrowVO> findBorrow(Integer uid);

    List<ReaderInfo> showAllUsers();

    boolean checkPassword(String userName, String oldPassword);

    void changePassword(String userName, String newPassword);

    void rebuild(Integer uid);
}
