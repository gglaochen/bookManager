

计算机与遥感信息技术学院


《基于B/S结构项目实训》设计报告





实训题目：      图书管理系统      
班    级：         B15531         
学    号：      2015405A532       
姓    名：         陈翰林          





 
目录
第1章  需求分析	1
1.1 项目背景	1
1.2 问题的提出	1
1.3 项目意义	1
1.4功能需求分析	2
第2章  系统设计	3
2.1 功能模块结构	3
2.1.1管理员功能模块	3
2.1.2用户功能模块	3
2.2 功能模块说明	4
第3章  数据库设计	6
3.1 ER图	6
3.2 数据库物理结构设计	6
第4章  功能实现	12
4.1 系统关键功能流程图	12
4.1.1借阅图书流程	12
4.1.2续借图书流程	13
4.1.3归还图书流程	14
4.1.4修改图书信息流程	15
4.1.5删除图书信息流程	15
4.1.6借阅卡丢失重办流程	16
4.2 系统关键功能代码	16
4.2.1图书查询控制层代码	16
4.2.2定时任务业务层代码	18
4.2.3获取预计预定图书归还时间持久层代码	20
4.2.4图书信息展示层代码	20
第5章  测试与调试	24
5.1 测试与调试方法	24
5.2 出现的问题与解决	26
第6章  总结与展望	27
参考文献	28
 
第1章  需求分析
1.1 项目背景
A高校拥有一个小型图书馆，为全校师生提供学习、阅读的空间。近几年来，随着生源的不断扩大，图书馆的规模也随之扩大，图书数量也相应地打量增加，有关图书借阅的各种信息成倍增加。面对如此巨大的信息量，图书馆管理人员很难支撑，因此，学校领导决定建立一套合理实用的图书借阅管理系统软件，以对校内的图书借阅信息进行统一、集中的管理。
1.2 问题的提出
随着计算机的普及和信息技术的发展，人们的生活发生了日新月异的变化，各类计算机软件逐渐渗透到了社会的每个角落，大大地改善了人们的生活质量，提高了人们的工作效率。在高校中，图书借阅是学生获取知识的一个很重要的途径，如何既能方便学生借书，又能减轻图书馆管理人员的工作负担，高效地完成图书借阅管理工作，是一件非常重要的事情。
1.3 项目意义
图书借阅管理系统，能够实现计算机化的图书借阅管理，能够提供方便快速的图书信息检索功能和便捷的图书借阅和归还功能，并且能够对图书信息和读者信息进行管理，方便管理员和读者的借阅处理。要求系统具备以下特点：

（1）操作简单，易用。

（2）数据存储可靠，具备较高的处理效率。

（3）系统安全、稳定。

（4）开发技术先进、功能完备、扩展性强。

1.4功能需求分析
1.普通用户能查询图书展示信息，根据索书号了解图书在图书馆的具体位置

2.普通用户能收藏图书，以便以后回看

3.普通用户能对没有库存的图书进行预定

4.管理员能对图书进行借阅管理，包括借阅、续借和归还

5.管理员能对书库信息进行管理，包括查看、添加、修改、删除图书信息

6.管理员能对读者信息进行管理，包括查看、添加、修改、删除图书信息

7.管理员能对押金进行管理，可以进行提交押金和归还押金操作

8.管理员可以对借阅卡进行挂失、解禁和丢失重办
	













第2章  系统设计
2.1 功能模块结构
2.1.1管理员功能模块
 
图2.1.1-1系统功能模块图

本系统核心功能分为借阅管理模块、书库管理模块、读者管理模块、押金模块和挂失解禁管理模块。
2.1.2用户功能模块
 
图2.1.2-1用户功能模块图
本系统核心功能分为收藏模块、预定模块、个人信息管理模块、信息查询模块。
2.2 功能模块说明
2.2.1系统功能模块说明

（1）借阅管理：包括借阅图书、续借图书、归还图书。管理员登录成功之后，可以添加借阅信息，为已借阅读者提供续借功能以及归还借阅图书的功能。具体信息包括读者编号、图书编号、借阅日期、归还日期、借阅图书状态等。
（2）书库管理：包括查看书库、新增图书、修改图书信息、删除图书。管理员登录成功之后，可以查看所有图书的详细信息，并进行修改，或根据新进图书添加具体信息，也可以删除不合法或过期的图书。具体信息包括图书编号、语种、书名、作者、ISBN、类别、状态等。
（3）读者管理：包括查看读者信息、添加读者、修改读者信息、删除读者。管理员登录成功之后，可以查看所有读者的详细信息，并进行修改，或根据新进读者添加具体信息，也可以删除过期已久的读者。具体信息包括用户名、密码、性别、头像、类别、注册日期、状态等。
（4）押金管理：包括提交押金、归还押金。管理员登录成功之后，可以提交用户押金，提交押金后开启借阅功能，管理员也可以退还不再借阅图书的用户押金。
（5）挂失解禁管理：包括挂失、解禁、丢失重办功能。管理员登录成功之后，可以根据读者出示的成员号修改丢失借阅卡的读者状态为挂失，挂失后不可登录、借阅，也可以根据出示的成员号解禁借阅卡，如果读者丢失借阅卡，可以重新办理办卡手续，原来的借阅卡将失效。

2.2.2用户功能模块说明

（1）图书收藏：包括添加收藏、查看收藏。用户登录成功之后，可以在图书详情页添加收藏或取消收藏，也可以在个人信息页查看收藏图书信息。具体信息包括用户编码、收藏图书编码等。
（2）图书预定：包括添加预定、查看预定。用户登录成功之后，可以在图书详情页对没有库存的图书进行预定，也可以在个人信息页查看预定信息并取消预定。具体信息包括预定号、用户编号、图书编号、预定位次等。
（3）个人信息管理：包括查看个人资料、编辑个人资料。用户登录成功后可以在个人信息页面查看读者昵称，读者类型，借阅，收藏，预定，日历，天气等并修改个人资料。
（4）信息查询：包括图书信息查询、公告消息查询、系统消息查询。游客可以查看图书的基本信息，查看图书馆的楼层结构，图书分类的所在楼层，图书馆的提示公告，用户登录成功后可以查看系统消息。

























第3章  数据库设计
3.1 ER图
 
图3.1-1用户功能模块图


3.2 数据库物理结构设计
数据库管理系统选择Mysql、，根据Mysql特点，设计数据表如下：



字段名称	数据类型	字段大小	小数点	允许为空	描述
Book_type	varchar	20	0	否	图书类型编号
Type_name	varchar	255	0	否	类型名称
parent_type	varchar	20	0	否	父类编号，1为根分类
表 3.2-1  book_type表

字段名称	数据类型	字段大小	小数点	允许为空	描述
book_id	int	6	0	否	图书编号
book_country	int	50	0	否	图书语种
book_type	varchar	20	0	否	图书类型编号
book_loc	int	255	0	否	书架位置
ISBN	varchar	255	0	否	ISBN
book_name	varchar	255	0	否	图书名称
author	varchar	255	0	否	作者
is_offprint	int	4	0	否	是否单行本
order_num	int	11	0	否	第几册
series_num	int	11	0	是	共几册
publish	varchar	255	0	否	出版社
Publish_date	date	0	0	否	出版日期
price	decimal	10	2	否	单价
max_num	Int	11	0	否	图书最大数量
borrow_num	int	11	0	否	借出数量
rest_num	Int	11	0	否	余下数量
info	longText	0	0	是	摘要
book_image	varchar	255	0	是	图书照片
表 3.2-2  t_book表

字段名称	数据类型	字段大小	小数点	允许为空	描述
borrow_id	Int	11	0	否	借阅编号
Uid	Int	11	0	否	用户id
Book_id	Int	6	0	否	图书id
Borrow_date	Date	0	0	否	借阅日期
Return_date	Date	0	0	否	归还日期
Is_lost	Int	255	0	是	是否丢失
Fine	Decimal	65	2	否	罚金
Is_padid	Int	255	0	是	是否偿还罚金
Is_renew	Int	255	0	是	是否续借
表 3.2-3  t_borrow表

字段名称	数据类型	字段大小	小数点	允许为空	描述
Favourite_id	Int	6	0	否	收藏编号
Uid	Int	11	0	否	用户id
Book_id	Int	6	0	否	图书id
表 3.2-4  t_favourite表

字段名称	数据类型	字段大小	小数点	允许为空	描述
Member_id	Varchar	255	0	否	学生号、教师编号、职工编号
Type_id	Int	255	0	否	类型编号
Is_used	Int	255	0	否	是否已使用
uid	Int	255	0	是	绑定的读书卡Id
表 3.2-5  t_member表

字段名称	数据类型	字段大小	小数点	允许为空	描述
Message_id	Int	255	0	否	消息编号
Uid	int	11	0	否	用户Id	
Message	Varchar	255	0	是	消息内容
Message_date	Date	0	0	是	消息日期
Is_read	Int	255	0	是	是否已读
表 3.2-6  t_message表

字段名称	数据类型	字段大小	小数点	允许为空	描述
predeter_id	Int	6	0	否	预定号
Uid	int	11	0	否	用户Id	
Book_id	Int	6	0	否	图书Id	
Back_date	Date	0	0	是	归还日期
Order_no	Int	255	0	否	预定顺序号
表 3.2-7  t_predeter表

字段名称	数据类型	字段大小	小数点	允许为空	描述
Uid	Int	11	0	否	读者编号
Username	Varchar	255	0	否	用户名
password	Varchar	255	0	否	密码
Readername	Varchar	255	0	否	读者姓名
Img	Varchar	255	0	是	头像地址
Sex	Int	100	0	否	性别
Type_id	int	255	0	否	读者类型
Phone	Varchar	100	0	否	电话
Cash_pledge	Decimal	50	0	否	押金
Reg_date	Date	0	0	是	注册时间
Is_period	Int	10	0	否	是否有有效期
Period_validity	Date	0	0	是	有效期至
Status	Int	255	0	否	状态
表 3.2-8  t_user表

字段名称	数据类型	字段大小	小数点	允许为空	描述
Type_id	Int	255	0	否	读者类型编号
Type_name	Varchar	255	0	否	类型名称
Max_borrow_num	Int	11	0	是	最多借书数量
Limit_day	Int	11	0	是	最长借书天数
表 3.2-9  user_type表



第4章  功能实现
4.1 系统关键功能流程图
4.1.1借阅图书流程
 
图 4.1.1  借阅图书流程图

4.1.2续借图书流程
 
图 4.1.2  续借图书流程图

4.1.3归还图书流程
 
图 4.1.3  归还图书流程图




4.1.4修改图书信息流程
 
图 4.1.4  修改图书信息流程图
4.1.5删除图书信息流程
 
图 4.1.5  修改图书信息流程图


4.1.6借阅卡丢失重办流程
 
图 4.1.6  修改图书信息流程图

4.2 系统关键功能代码
4.2.1图书查询控制层代码
@GetMapping("{search}")
public String getBooks(@PathVariable String search, Model model,//@PathVariable相当于从http请求里获取这个属性并传入这个参数并作为路径的一部分
                      @RequestParam(defaultValue = "1") Integer pageNo,             //页码
                      @RequestParam(defaultValue = "3") Integer pageSize,          //一页显示数量
                      @RequestParam(defaultValue = "book_id ASC") String orderBy,//排序方式
                      @RequestParam(defaultValue = "1") Integer select,          //排序编号
                      @RequestParam(required = false) String selectYear,   //选择的年份
                      @RequestParam(required = false) String selectAuthor,  //选择的作者
                      @RequestParam(required = false) String selectCountry  //选择的语种
) {
    Example e = new Example(BookInfo.class);
    Example.Criteria c = e.createCriteria();
    if (!StringUtils.isEmpty(search)) {
        c.orLike("bookName", "%" + search + "%").orLike("author", "%" + search + "%");
    }
    List<String> year = null;
    if (selectYear != null && !selectYear.equals("")) {//如果限制年份，则添加限制年份
        Example.Criteria c2 = e.createCriteria();
        year = Arrays.asList(selectYear.split("="));
        year.forEach(item -> c2.orBetween("publishDate", new Date(Integer.parseInt(item.replace(",", ""))-1900, 0, -1), new Date(Integer.parseInt(item.replace(",", ""))-1900, 11, 31))
                );
        e.and(c2);
    }
    List<String> author =null;
    if (selectAuthor != null && !selectAuthor.equals("")) {//如果限制作者，则添加限制作者
        Example.Criteria c3 = e.createCriteria();
        author = Arrays.asList(selectAuthor.split("="));
        author.forEach(item -> c3.orEqualTo("author", item));
        e.and(c3);
    }
    List<String> country = null;
    List<String> a= Arrays.asList(new Const().bookCountryArray);
    if (selectCountry != null && !selectCountry.equals("")) {//如果限制语种，则添加限制语种
        Example.Criteria c4 = e.createCriteria();
        country = Arrays.asList(selectCountry.split("="));
        country.forEach(item -> c4.orEqualTo("bookCountry", a.indexOf(item)));
        e.and(c4);
    }
    PageHelper.startPage(pageNo, pageSize, orderBy);
    List<BookInfo> bookInfos = bookInfoMapper.selectByExample(e);
    Example example = new Example(BookInfo.class);
    Example.Criteria criteria = example.createCriteria();
    if (!StringUtils.isEmpty(search)) {
        criteria.orLike("bookName", "%" + search + "%").orLike("author", "%" + search + "%");
    }
    List<BookInfo> bookAll = bookInfoMapper.selectByExample(example);
    Set<String> years = new HashSet();
    Set<String> authors = new HashSet();
    Set<String> countries = new HashSet();
    bookAll.forEach(item -> {//将查询到的所有年、作者、语种信息添加到set集合中，返回页面作为限制条件
        years.add(String.valueOf(item.getPublishDate().getYear() + 1900));
        authors.add(item.getAuthor());
        countries.add(new Const().getBookCountryName(item.getBookCountry()));
    });
    PageInfo<BookInfo> page = new PageInfo<>(bookInfos);
    model.addAttribute("pageNo", pageNo);//页码
    model.addAttribute("page", page);//页码信息
    model.addAttribute("bookInfos", bookInfos);//当前页面结果
    model.addAttribute("orderBy", orderBy);//排序条件
    model.addAttribute("select", select);//排序编号
    model.addAttribute("search", search);//查找字符串
    model.addAttribute("years", years);//存放年
    model.addAttribute("authors", authors);//存放作者
    model.addAttribute("countries", countries);//存放语种
    model.addAttribute("selectYear", selectYear);//存放选择的年份
    model.addAttribute("selectAuthor", selectAuthor);//存放选择的作者
    model.addAttribute("selectCountry", selectCountry);//存放选择的语种
    return "show";
}
4.2.2定时任务业务层代码
@Scheduled(cron = "0 0 0 * * *")
public void readerOverDue(){//修改过期读者状态
    List<ReaderInfo> readerInfos = readerInfoMapper.selectAll();
    readerInfos.forEach(item->{
                if (item.getPeriodValidity()!=null)
                    if(0<(int)(new Date().getTime()-(item.getPeriodValidity().getTime()))/(24*60*60*1000)){
                        item.setStatus(1);
                        readerInfoMapper.updateByPrimaryKey(item);
                    }
            }
    );
}

@Scheduled(cron = "0 0 0 * * *")
public void outOfPredeter(){//删除过期预定信息
    List<BookPredeter> bookPredeters = bookPredeterMapper.selectAll();
    bookPredeters.forEach(item->{
        if(item.getOrderNo()==-1) {//已经借阅
            if (5 < (int) (new Date().getTime() - (item.getBackDate().getTime())) / (24 * 60 * 60 * 1000)) {//预定到5天后仍没有借阅
                bookPredeterMapper.delete(item);
            }
        }
    });
}
@Scheduled(cron = "0 0 0 * * *")
public void changeFine(){//修改罚金
    Example e = new Example(BookBorrow.class);
    Example.Criteria c = e.createCriteria();
    c.andIsNull("returnDate");
    List<BookBorrow> bookBorrows = bookBorrowMapper.selectByExample(e);//所有未归还图书
    bookBorrows.forEach(item->{
        int day=bookBorrowMapper.AllowedBorrowAgain(item.getUid(),item.getBookId());
        if(day<0){//逾期了
            BigDecimal fine = new BigDecimal(Math.abs(day)).multiply(new BigDecimal(0.5));//逾期天数*0.5
            BigDecimal price = bookInfoMapper.selectByPrimaryKey(item.getBookId()).getPrice();//书价
            if(fine.compareTo(price) > 0) {//罚金超出书价
                item.setFine(price);
            }else {
                item.setFine(fine);
            }
            bookBorrowMapper.updateByPrimaryKey(item);
        }
    });
}
4.2.3获取预计预定图书归还时间持久层代码
<select id="findRecentReturn" resultType="java.util.Date">
    SELECT DATE_ADD(borrow_date,INTERVAL (1+is_renew)*limit_day DAY) FROM t_borrow,t_reader,user_type
    WHERE  t_borrow.uid=t_reader.uid AND t_reader.type_id=user_type.type_id
    AND return_date IS NULL AND book_id=#{bookId}
    ORDER BY DATE_ADD(borrow_date,INTERVAL (1+is_renew)*limit_day DAY)
    LIMIT #{orderNo},1
</select>
4.2.4图书信息展示层代码
排序方式<select name="selectOrder" id="selectOrder">
    <option value="1"<#if select=1> selected="selected"</#if>>图书编号</option>
    <option value="2"<#if select=2> selected="selected"</#if>>图书名称A-Z</option>
    <option value="3"<#if select=3> selected="selected"</#if>>图书作者A-Z</option>
    <option value="4"<#if select=4> selected="selected"</#if>>出版日期(最近的排在前面)</option>
    <option value="5"<#if select=5> selected="selected"</#if>>出版日期(最久的排在前面)</option>
</select>
<b>${pageNo}</b>/${page.lastPage}页<br>

<table border="1" width="800">
    <tr>
        <td>ISBN</td>
        <td>书名</td>
        <td>作者</td>
        <td>书价</td>
        <td>图书位置</td>
        <td>出版社</td>
        <td>出版日期</td>
        <td>载体形态</td>
    </tr>
    <#list bookInfos as item>
        <tr>
        <td>${item.ISBN}</td>
        <td>
    <a href="/book/bookInfo?bookId=${item.bookId}">${item.bookName}</a></td>
        <td>${item.author}</td>
        <td>${item.price?string.currency}</td>
        <td>${item.bookType}/${item.bookLoc}</td>
        <td>${item.publish}</td>
        <td>${item.publishDate?string('yyyy-MM-dd')}</td>
        <td>
        <#if item.isOffprint==1>
            单行本
        <#else>
            ${item.orderNum}册/${item.seriesNum}册
        </#if>
        </td>
        </tr>
    </#list>
</table>
<#if page.isFirstPage==true><!--是否为首页-->
    首页 上一页
</#if>
<#if page.isFirstPage!=true>
    <a href="${search}?pageNo=${page.firstPage}&orderBy=${orderBy}&select=${select}">首页</a>
    <a href="${search}?pageNo=${page.prePage}&orderBy=${orderBy}&select=${select}">上一页</a>
</#if>
<input type="text" id="TZpageNo" onkeyup="keyup(${page.lastPage})"/>
    <button onclick="TZ()">跳转</button><!--跳转-->
<#if page.isLastPage==true><!--是否为尾页-->
    下一页  尾页
</#if>
<#if page.isLastPage!=true>
    <a href="${search}?pageNo=${page.nextPage}&orderBy=${orderBy}&select=${select}">下一页</a>
    <a href="${search}?pageNo=${page.lastPage}&orderBy=${orderBy}&select=${select}">尾页</a>
</#if>
<br>
<div>年份</div>
<ul>
    <#list years as item>
        <li>
        <a href="javascript:choose();">
        <input type="checkbox" class="yearCheck" value="${item}"
        <#if selectYear??>
        <#if selectYear?contains(item)>
        checked="checked"
        </#if>
        </#if>
    > ${item}
        </a>
        </li>
    </#list>
</ul>
<div>作者</div>
<ul>
    <#list authors as item>
        <li>
        <a href="javascript:choose();">
        <input type="checkbox" class="authorCheck" value="${item}"
        <#if selectAuthor??>
    <#if selectAuthor?contains(item)>checked="checked"</#if>
            </#if>
    > ${item}
        </a>
        </li>
    </#list>
</ul>
<div>语种</div>
<ul>
    <#list countries as item>
        <li>
        <a href="javascript:choose();">
        <input type="checkbox" class="countryCheck" value="${item}"
        <#if selectCountry??>
    <#if selectCountry?contains(item)>checked="checked"</#if>
            </#if>
    > ${item}
        </a>
        </li>
    </#list>
</ul>








第5章  测试与调试
5.1 测试与调试方法
测试使用springboot自带测试类，用@RunWith(SpringRunner.class)与@SpringBootTest
注释
调试使用debug断点调试，主要查找空指针异常位置

测试代码：
@Autowired
private BookPredeterMapper bookPredeterMapper;
@Autowired
private BookBorrowMapper bookBorrowMapper;
@Autowired
private BookInfoMapper bookInfoMapper;
@Value("${md5.salt}")
private String salt;
@Autowired
ReaderInfoMapper readerInfoMapper;
@Test
public void contextLoads() {
    System.out.println(DigestUtils.md5Hex("123"+salt));
}
@Test
public void getDifferent(){
    List<BookPredeter> bookPredeters = bookPredeterMapper.selectAll();
    bookPredeters.forEach(item->{
        System.out.println("时间差"+(int)(new Date().getTime()-(item.getBackDate().getTime()))/(24*60*60*1000));
        if(5<(int)(new Date().getTime()-(item.getBackDate().getTime()))/(24*60*60*1000)){
            System.out.println("删除"+item.getPredeterId());
        }
    });
}
@Test
public void getDifferent2(){
    List<ReaderInfo> readerInfos = readerInfoMapper.selectAll();
    readerInfos.forEach(item->{
        if (item.getPeriodValidity()!=null)
        if(0<(int)(new Date().getTime()-(item.getPeriodValidity().getTime()))/(24*60*60*1000)){
            System.out.println("时间差"+(int)(new Date().getTime()-(item.getPeriodValidity().getTime()))/(24*60*60*1000));
            System.out.println("过期了");
        }
        }
    );
}
@Test
public void Borrow(){
    Example e = new Example(BookBorrow.class);
    Example.Criteria c = e.createCriteria();
    c.andIsNull("returnDate");
    List<BookBorrow> bookBorrows = bookBorrowMapper.selectByExample(e);//所有未归还图书
    bookBorrows.forEach(item->{
        int day=bookBorrowMapper.AllowedBorrowAgain(item.getUid(),item.getBookId());
        if(day<0){//逾期了
            BigDecimal fine = new BigDecimal(Math.abs(day)).multiply(new BigDecimal(0.5));//逾期天数*0.5
            BigDecimal price = bookInfoMapper.selectByPrimaryKey(item.getBookId()).getPrice();//书价
            if(fine.compareTo(price) > 0) {//罚金超出书价
                item.setFine(price);
            }else {
                item.setFine(fine);
            }
            bookBorrowMapper.updateByPrimaryKey(item);
        }
    });
}





5.2 出现的问题与解决
1）使用springboot静态资源路径正确却引入不了，原来是配置类继承了WebMvcConfigurer，导致重置了springboot配置的静态资源映射，classpath:/static 
、classpath:/public、classpath:/resources、classpath:/META-INF/resources。
	解决方式：上网搜索
	2)修改js文件后运行不生效，是因为浏览器自动缓存了静态文件，开启无痕模式测试后正常了。
	解决方式：上网搜索
	3)maven里导入了相关依赖但注解使用不存在，原因是maven插件引入失败，删除项目libraries和target重新安装插件并导入后成功。
	解决方式：上网搜索
	4)使用了springboot-starter依赖包后仍然不能使用默认的异常处理页面并配置自己的异常处理页面，原来是版本过高，把2.1.1改成2.0.6后能使用了
	解决方式：对比以前正常项目
	5)在freemarker静态模版引擎尝试直接使用包装类的方法后报错，获取不了String类型的hash值，原因是freemarker调用方法用’?’而不是’.’导致的
	解决方式：网上咨询
	6)通过ajax提交表单后接受不到multipartFile，原来是ajax提交的表单无法设置表单的数据类型，后来改用action直接提交再通过js进行表单验证
	解决方式：上网搜索







第6章  总结与展望
由于项目开发经验的缺乏，项目开发过程中遇到了很多问题，但我认为这是对我有好处的事情，遇到问题越多说明在完成这个项目后我能学到的东西越多。由于时间限制，很多想要使用的技术在项目中来不及设置，之后有时间会对项目进行完善和技术升级，完成项目的过程中发现了自己对前端的知识尤其缺乏，虽然没有想要往前端的方向发展，但还是得具备一定前端基本知识，打算在寒假学习一些前端框架打底，后台也有很多不足的地方比如登录验证模块的代码过于繁复，后期打算改用Spring Security安全框架，还比如数据库用的还是集中式的数据处理，在处理效率和安全上都不尽人意，后期打算改用分布式存储方式。






















参考文献
[1] https://me.csdn.net/travellersY 参考了该博客主关于springboot入门和java学习的博客
[2] https://blog.csdn.net/peterwanghao/article/details/78629145Spring Boot启动依赖分析
[3] https://me.csdn.net/tryandfight 对spring security和spring social进行了简单介绍



