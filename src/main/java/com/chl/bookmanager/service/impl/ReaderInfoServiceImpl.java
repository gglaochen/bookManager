package com.chl.bookmanager.service.impl;

import com.chl.bookmanager.domain.*;
import com.chl.bookmanager.exception.PasswordNotMatchException;
import com.chl.bookmanager.exception.UserNotFoundException;
import com.chl.bookmanager.exception.UsernameAlreadyExistException;
import com.chl.bookmanager.mapper.*;
import com.chl.bookmanager.service.ReaderInfoService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author:Administrator
 * @date:2018/12/27/027
 */
@Service
public class ReaderInfoServiceImpl implements ReaderInfoService {
    @Autowired
    private ReaderInfoMapper readerInfoMapper;
    @Autowired
    private SchoolMemberMapper schoolMemberMapper;
    @Autowired
    private BookBorrowMapper bookBorrowMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private FavouriteMapper favouriteMapper;
    @Autowired
    private BookPredeterMapper bookPredeterMapper;

    @Value("${md5.salt}")
    private String salt;
    @Override
    public int register(ReaderInfo readerInfo,String memberId) {
        Example e = new Example(ReaderInfo.class);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("username",readerInfo.getUsername());
        ReaderInfo user =  readerInfoMapper.selectOneByExample(e);
        //用户名不存在
        if(user==null){
            String pwd = readerInfo.getPassword();
            //生成md5密码
            String md5 = DigestUtils.md5Hex(pwd+salt);
            readerInfo.setPassword(md5);
            int insert = readerInfoMapper.insert(readerInfo);
            //修改成员编号已使用
            ReaderInfo user1 =  readerInfoMapper.selectOneByExample(e);
            SchoolMember schoolMember = schoolMemberMapper.selectByPrimaryKey(memberId);
            schoolMember.setIsUsed(1);
            schoolMember.setUid(user1.getUid());
            schoolMemberMapper.updateByPrimaryKey(schoolMember);
            return insert;
        }else{
            //用户名存在,抛出异常
            throw new UsernameAlreadyExistException("用户名已经存在,请重新输入!");
        }
    }
    @Override
    public ReaderInfo login(String username, String password) {
        ReaderInfo user=example(username);
        if(user==null){
            throw new UserNotFoundException(""
                    + "用户名不存在!");
        }else{
            //user.getPassword()
            /**
             * String str = user.getPassword();
             * str += "dddddd";
             * String strMd5 = DigestUtils.md5Hex(str)
             */

            //生成md5密码
            String md5 = DigestUtils.md5Hex(password+salt);

            if(user.getPassword().equals(md5)){
                return user;
            }else{
                throw new PasswordNotMatchException("密码错误");
            }
        }
    }

    @Override
    public boolean checkUsernameHasExist(String username) {
        ReaderInfo user = example(username);
        if(user==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean checkPhoneHaxExist(String phone) {
        Example example = new Example(ReaderInfo.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("phone",phone);
        ReaderInfo readerInfo = readerInfoMapper.selectOneByExample(example);
        if (readerInfo == null){
            return false;
        }else {
            return true;
        }
    }

    private ReaderInfo example(String username) {
        Example e = new Example(ReaderInfo.class);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("username",username);
        c.andEqualTo("status",0);//用户名存在且正常
       return readerInfoMapper.selectOneByExample(e);
    }
    public List<BookBorrowVO> findBorrow(Integer uid){
        return bookBorrowMapper.selectBorrowVO(uid);
    }

    @Override
    public List<ReaderInfo> showAllUsers() {
        return readerInfoMapper.selectAll();
    }

    @Override
    public boolean checkPassword(String userName, String oldPassword) {
        Example e =new Example(ReaderInfo.class);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("username",userName);
        ReaderInfo readerInfo1 = readerInfoMapper.selectOneByExample(e);
        ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(readerInfo1.getUid());
        if(readerInfo.getPassword().equals(oldPassword)) {
            return true;
        }
        return false;
    }

    @Override
    public void changePassword(String userName, String newPassword) {
        Example e =new Example(ReaderInfo.class);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("username",userName).andEqualTo("status",0);
        ReaderInfo readerInfo1 = readerInfoMapper.selectOneByExample(e);
        ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(readerInfo1.getUid());
        readerInfo.setPassword(newPassword);
        readerInfoMapper.updateByPrimaryKey(readerInfo);
    }

    @Override
    public void rebuild(Integer uid) {
        ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(uid);
        ReaderInfo newReaderInfo = new ReaderInfo(null,readerInfo.getUsername(),readerInfo.getPassword(),readerInfo.getReadername(),readerInfo.getImg(),readerInfo.getSex(),readerInfo.getTypeId(),readerInfo.getPhone(),readerInfo.getCashPledge(),readerInfo.getRegDate(),readerInfo.getIsPeriod(),readerInfo.getPeriodValidity(),0);
        readerInfoMapper.insert(newReaderInfo);
        Example example = new Example(ReaderInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",readerInfo.getUsername()).andEqualTo("status",0);
        Integer userid = readerInfoMapper.selectOneByExample(example).getUid();
        readerInfo.setStatus(3);
        readerInfoMapper.updateByPrimaryKey(readerInfo);//修改原读者id状态为注销
        //member\message
        Example e = new Example(SchoolMember.class);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("uid",uid);
        List<SchoolMember> schoolMembers = schoolMemberMapper.selectByExample(e);
        schoolMembers.forEach(item->{
            item.setUid(userid);
            schoolMemberMapper.updateByPrimaryKey(item);
        });
        Example e2 = new Example(Message.class);
        Example.Criteria c2 = e2.createCriteria();
        c2.andEqualTo("uid",uid);
        List<Message> messages = messageMapper.selectByExample(e2);
        messages.forEach(item->{
            item.setUid(userid);
            messageMapper.updateByPrimaryKey(item);
        });
        Example e3 = new Example(Favourite.class);
        Example.Criteria c3 = e3.createCriteria();
        c3.andEqualTo("uid",uid);
        List<Favourite> favourites = favouriteMapper.selectByExample(e2);
        favourites.forEach(item->{
            item.setUid(userid);
            favouriteMapper.updateByPrimaryKey(item);
        });
        Example e4 = new Example(BookPredeter.class);
        Example.Criteria c4 = e4.createCriteria();
        c4.andEqualTo("uid",uid);
        List<BookPredeter> bookPredeters= bookPredeterMapper.selectByExample(e4);
        bookPredeters.forEach(item->{
            item.setUid(userid);
            bookPredeterMapper.updateByPrimaryKey(item);
        });
        Example e5 = new Example(BookBorrow.class);
        Example.Criteria c5 = e5.createCriteria();
        c5.andEqualTo("uid",uid);
        List<BookBorrow> bookBorrows = bookBorrowMapper.selectByExample(e2);
        bookBorrows.forEach(item->{
            item.setUid(userid);
            bookBorrowMapper.updateByPrimaryKey(item);
        });
    }

}
