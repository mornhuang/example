存放源代码类
admin         是管理端平台
contigency    是Trade的应急系统
sim           一个模拟交易平台
trade         iWeb网络交易平台 （代码中包括NPSServer<iWin>的代码, 
              iWeb + iWin = iTrade)
trade.jtest   用JTest对trade进行测试的工程

               
[重要]   1、  NPSServer同trade共用了一部分代码。
         2、  trade/admin/npsserver使用同一个数据库，数据库脚本在admin/db目录下
         3、  如果有数据库中参数变更，记得在doc\base\Delivery下
              的<NewWebTrading 参数整理.xls>中进行修改。
         4、  要去看下安装部署文档
         5、  MCS的jar包是自己打的，具体如何操作，请看相关文档
         6、  请注意修改配置文件后同步sit/uat/production下的配置文件