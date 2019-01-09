package com.chl.bookmanager;

import com.chl.bookmanager.domain.BookBorrow;
import com.chl.bookmanager.domain.BookInfo;
import com.chl.bookmanager.domain.BookType;
import com.chl.bookmanager.logging.TestLogger;
import com.chl.bookmanager.mapper.BookBorrowMapper;
import com.chl.bookmanager.mapper.BookInfoMapper;
import com.chl.bookmanager.mapper.BookTypeMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.util.Arrays;

@ServletComponentScan//启用servlet-api:过滤器，监听器，servlet映射
@SpringBootApplication//(exclude = ErrorMvcAutoConfiguration.class)不使用springboot默认错误配置文件
@MapperScan("com.chl.bookmanager.mapper")
@EnableScheduling
public class BookmanagerApplication {
    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext context = SpringApplication.run(BookmanagerApplication.class, args);
//        BookTypeMapper bookInfoMapper = context.getBean(BookTypeMapper.class);
//        BookType bookBorrow = bookInfoMapper.selectByPrimaryKey("F");
//        System.out.println(bookBorrow.toString());
//        System.out.println(context.getBean(DataSource.class).getClass());//默认数据源是HikariDataSource
//        DataSource dataSource = context.getBean(DataSource.class);
//        System.out.println(dataSource.getConnection().getCatalog());//获取连接的数据库名
//        TestLogger con = context.getBean(TestLogger.class);
//        con.testLog();
    }

}

