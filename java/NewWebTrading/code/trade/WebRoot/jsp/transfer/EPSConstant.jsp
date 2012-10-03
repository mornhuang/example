<%
    final long 	ServiceEndTime		= 185000;
    final long 	ServiceStartTime	= 70000;
    final int 	ServiceStartDay		= 2;
    final int 	ServiceEndDay		= 6;

    String[] HolidayTable=new String[16];
 
    HolidayTable[0]		= "20040101";
    HolidayTable[1]		= "20040122";
    HolidayTable[2]		= "20040123";
    HolidayTable[3]		= "20040124";
    HolidayTable[4]		= "20040405";
    HolidayTable[5]		= "20040409";
    HolidayTable[6]		= "20040410";
    HolidayTable[7]		= "20040412";
    HolidayTable[8]		= "20040501";
    HolidayTable[9]		= "20040526";
    HolidayTable[10]	= "20040622"; 
    HolidayTable[11]	= "20040701";
    HolidayTable[12]	= "20040929";
    HolidayTable[13]	= "20041001";
    HolidayTable[14]	= "20031225";
    HolidayTable[15]	= "20031226";
  
    final int 		StartTime					= 700;
    final int 		EndTime						= 1850;
    final String 	ReturnPath					= "../split.jsp";

    final String 	ACCashDepositLoginUserID	= "rjdywmP";
    final String 	ACCashDepositLoginPassword	= "000000";

    //Variables to store the path of next page
    final boolean ProdctionEnv			= true;
    String UserConfirm_ReturnPath		= "";
    String EPSServer					= "";
    String AdminToolsSvr				= "";

    if (ProdctionEnv==true) {
        EPSServer				= "https://pa.taifook.com";
        AdminToolsSvr			= "https://pa.taifook.com";
        UserConfirm_ReturnPath	= AdminToolsSvr + "/pps/ppsenq2.asp";
    }else{
        EPSServer				= "http://10.100.192.105";
        AdminToolsSvr			= "http://10.100.192.105";
        UserConfirm_ReturnPath	= AdminToolsSvr + "/pps/ppsenq2.asp";
    }
  
 %>
