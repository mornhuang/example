本下载包说明
-------------

本下载包是《深入浅出Hibernate》（http://www.redsaga.com/hibernate_book.html）一书的配套教程程序，目前版本为1.0.

本下载包与书籍的实战篇紧密结合.

下载包中的程序是一个简明的示例论坛程序，按照循序渐进的原则，分为不同的步骤：

步骤1: 
建立最简单的项目基础结构：Board/User/Article三个类，包含最基本的配置文件。同时，在这一步中，建立测试类，刻画项目各个类之间的契约关系。

步骤2:
对文章的种类增加“投票”类型，展示对多型的处理。

步骤3:
增加webwork实现的GUI。
加入中文支持。

步骤4：
升级到Hibernate 3。


环境说明
---------
程序包中的程序经过测试的运行环境为：
1, Tomcat 5.0.25
2, Hibernate 2.1.7
3, Hibernate 3.0.2
4, Eclipse 3.0.1 (作为IDE）
5, ant 1.6（作为build工具）
6, java sdk 1.4.2
7, mysql 4.1 (作为后台数据库)
8, 至少250M硬盘空间（hibernate 2，3和webwork就将占据138M)

虽然在更高版本中应该可以不加修改的运行，但我们对此不加以保证。（v1.2将对Hibernate 3.0.4进行测试）

安装说明
---------
1, 确认jdk 1.4.2已经正确安装;
2，将本程序包解压，假设目录为: x:\rs\hib-samples
3, 从hibernate的sf下载区下载2.1.7与3.0.2版本：
 http://sourceforge.net/project/showfiles.php?group_id=40712
4,将下载的hibernate包解压到x:\rs\hib-samples\hibernate-2.1与x:\rs\hib-samples\hibernate-3，目录结构应该为：
   x:
    + rs
      + hib-samples     <--- 本文件包解压的目录
        + forum-step1-db-first-middlegen
        + forum-step1-db-first-synchronizer
        + forum-step2
        + ...
        + hibernate-2.1 <---  Hibernate 2.1.7解压到这里
          + bin         <---  确认bin与doc目录就在解压后的hibernate-2.1目录中
          + doc
          + ...
        + hibernate-3.0 <---  Hibernate 3.0.2解压到这里
          + bin         <---  确认bin与doc目录就在解压后的hibernate-3目录中
          + doc
          + ...

5，确认tomcat 5 安装正确。检查你的系统中，正确设置了CATALINA_HOME环境变量:
  若您是windows 2000/xp系统,请右键点击"我的电脑","高级","环境变量".
 确认其中的CATALINA_HOME环境变量正确指向您的tomcat 安装目录.
例:
CATALINA_HOME=D:\PROGRA~1\APACHE~2\TOMCAT~1.0

6, 确认mysql 4安装正确.
我们建议两种不同的mysql安装搭配：
  a) 假若您使用windows平台，并且是初次使用mysql ，我们建议采用另一个免费安装包来安装，它就是easy php: http://prdownloads.sourceforge.net/quickeasyphp/easyphp1-8_setup.exe?download
无需预先安装mysql，只需一次安装，它不仅包含了mysql 4.1.9，还包括了apache, php以及phpmyadmin ，而后者几乎是全球采用率最高的基于浏览器的mysql管理平台，非常简单易用。

  b)  mysql 正式安装版 + 客户端
 mysql可以在此下载:http://dev.mysql.com/downloads/mysql/4.1.html
 客户端有多种，可以采用其自己的mysql administrator，也可以采用mysql front等商业产品。mysql front有三十天免费的无限制试用版本，是一个值得推荐的工具：http://www.mysqlfront.de/download.html

 在安装完mysql之后，请使用客户端或者phpmyadmin建立一个名为forum的mysql数据库，其字符集必须为UTF-8（见下面关于中文的说明,在phpmyadmin中应该选择utf8_general_ci）。另外还需要建立名为forum的用户，密码也为forum，并且对forum具有完全访问权限。（假若您希望使用其它用户名如root，您需要修改hibernate的数据库配置中的连接参数——位于hibernate.cfg.xml中，以及创建数据库表时的连接参数——位于build.xml中。）

7, 确认ant安装正确. ant可以在此下载:
 http://ant.apache.org
或 http://www.redsaga.com/down/apache-ant-1.6.1.zip

解压后,请将其bin目录置于系统path中.

8, 注意，我们的成品工程是forum-step3。step1和step2都是中间过程。
确认您可以编译整个step3工程,在命令行下输入,
cd x:\rs\hib-samples (换成您的目录)
cd forum-step3
ant

假若安装正确的话,会给出一个指令说明.
执行：
ant all
它会自动进行数据库初始化、编译、启动tomcat容器.(此命令将会启动tomcat,因此之前tomcat必须处于停止状态)

然后,打开您的浏览器,输入http://localhost:8080/forum 您应该看到我们的示例正常运行.


9, 在您的Eclipse 3中,按照书中的步骤安装hibernate synchronizer.

10,在您的eclipse中，建立两个User Library:
菜单window -> preference -> Java -> Build Path -> User Librarys,分别建立名为Hibernate2,hibernate3,mysql-jdbc的用户库，分别包含对应的lib文件（如hibernate2包含hibernate-2.1目录下的hibernate2.jar及其lib目录下的所有jar文件)；

11, 在您的eclipse 中,导入各个目录下的.project文件,以建立工程.(工程名为step1,step2,step3,step4)。在工程的类路径配置中，使用上一步创建的用户库(step1-step3使用Hibernate2,step4使用hibernate3)。确保没有编译错误。

好了,您现在可以开始阅读代码,作一些自己的改变,看看您能得到什么结果 :)

中文处理说明
------------

关于中文的处理，是在step3加入的，因此在step1以及step2中，请使用英文进行实验。具体进行的处理为全程采用UTF-8编码.
1,mysql创建时，字符集必须选择UTF-8
2,在mysql jdbc连接的url中，必须指定采用utf-8 encoding。
jdbc:mysql://localhost/forum?useUnicode=true&characterEncoding=utf-8&mysqlEncoding=utf8
3，在jsp页面中，指定页面采用UTF-8编码.
 <%@ page contentType="text/html;charset=utf-8"%>

假若在您的项目中，必须使用GBK编码，则在以上的各个地方，都需要把UTF-8更换为GBK方可正常使用。


版本更新
-----------
v1.2 (PLAN,TBD)
 * 转换到JTA
 * 给出一个脱离DAO模式的例子
 * 给出for hibernate 3的xdoclet例子

v1.1 (2005.5.25)
 * 分离db目录下的build.xml
 * 增加了对投票的图形显示
 * 修正了和webwork相关的一个中文bug

v1.0 (2005.4.20)
 * 初始版本

请访问http://www.redsaga.com/hibernate_book.html，检查此下载包的更新。
