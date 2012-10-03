var LOGIN_ID;
/*----------------------------
 *  delete confirm
 *---------------------------*/
function delConfirm(){
	return confirm("您确认要进行删除操作");
}
/*----------------------------
 *  ajax call
 *---------------------------*/
function ajaxPostDate(data, url, fn, async) {
	$.ajax({
		type: "post",
		url: url,
		data: data,
		async: async && true,
		dataType: "json",
		success: function(response) {
			fn(response);
		}
	});
}
/*----------------------------
 *  user operator
 *---------------------------*/
var resText;
function checkBeforeSubmit(flag){
	if(flag && resText=="true"){
		str="此登录名已存在";
		alert(str);
		return;
	}
	
	if(flag && $("#loginId").val()==''){
		str="请输入登录名";
		alert(str);
		return;
	}
	if(flag && !validLogined()){
		return;
	}
	if($("#passWord").val()==''){
		str="请输入登录密码";
		alert(str);
		return;
	}
	if(!validPassword()){
		return;
	}
	if($("#telephone").val()==''){
		str="请输入电话号码";
		alert(str);
		return;
	}
	if(!validMobil()){
		return;
	}
	if($("#email").val()==''){
		str="请输入邮箱";
		alert(str);
		return;
	}
	if(!validEmail()){
		return;
	}
	if($("#username").val()==''){
		str="请输入姓名";
		alert(str);
		return;
	}
	if(!validNickName()){
		return;
	}

	if(!$("#rbYes").attr('checked')&&!$("#rbNo").attr('checked')){
			str="请选择是否是海通客户"
			alert(str);
			return;
	}
	if($("#rbYes").attr('checked')){
		if($("#clientNo").val()==""){
			str="请输入客户号码";
			alert(str);
			return;
		}
		
		if(!validTFACCOUNT()){
			return;
		}
	}else{
		$("#clientNo").val('');
	}
 
	resText='false';
	var action = ContextPath;
	if (flag){
		action += '/nadmin/userAdd.do';
		var obj = $("#registerForm");
		obj.attr('action', action);
		obj.submit();
		obj = null;
	}
	else{
		action += '/nadmin/userUpdate.do';
		var u = $("#registerForm").serialize() + "&token=" + new Date().getTime();
		ajaxPostDate(u, action, cbUpdateUser);
		u = null;
	}
}
function cbUpdateUser(p) {
	resText = p+"";
	if(resText==""||resText==null){
		alert("系统暂时未能提供服务...");
	} else if(resText=="true"){
		resetUserInfo();
		alert("编辑用户成功");
	} else {
		alert("编辑用户失败");
	}
}
/* ajax check user exist or not */
function isUserExist(){
	val=$("#loginId").val();
	if(val!=''){
		url=ContextPath + "/isUserExist.do";
		ajaxPostDate("loginId="+ val, url, cbIsUserExist);
	}
}
function cbIsUserExist(p){
	//if (response.)
	resText = p+"";
	if(resText==""||resText==null){
		alert("系统暂时未能提供服务...");
	}
	if(resText=="true"){
		alert("此登录名已存在");
	}
}
function validLogined(){
	var reg=/^[A-Za-z0-9]{6,8}$/;
	val=reg.test($('#loginId').val());
	if(!val)
	{
		str="登入名称必须是6-8位英文/数字";
		alert(str);
		return false;
	}
	return true;
}
function validNickName(){
	var reg=/^[A-Za-z0-9]{1,8}$/;
	val=reg.test($('#username').val());
	if(!val)
	{
		str="姓名必须是少于8位英文/数字";
		alert(str);
		return false;
	}
	return true;	
}
function validPassword(){
	var reg=/^[A-Za-z0-9]{6,8}$/;
	val=reg.test($('#passWord').val());
	if(!val)
	{
		str="密码必须是6-8位英文/数字";
		alert(str);
		return false;
	}
	return true;
}
function validEmail(){
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	val=reg.test($('#email').val());
	if(!val)
	{
		str="请输入正确的电邮";
		alert(str);
		return false;
	}
	return true;
}
function validMobil(){
	var reg=/^[0-9]{6,15}$/; 
	val=reg.test($('#telephone').val());
	if(!val)
	{
		str="手机号码必须大于6位,少于15位数字";
		alert(str);
		return false;
	}
	return true;
}
function validTFACCOUNT(){
	var reg=/^[0-9]{1,8}$/;
	val=reg.test($('#clientNo').val());
	if(!val)
	{
		str="客户帐号必须是少于8位数字";
		alert(str);
		return false;
	}
	return true;	
}

/*----------------------------
 * parameter operator
 *---------------------------*/
function checkBeforeSubmitParam(flag){
	if(flag && resText=="true"){
		str="此参数已存在";
		alert(str);
		return;
	}
	
	if(flag && $("#pname").val()==''){
		alert("请输入参数名称");
		return;
	}
	if(flag && !validPname()){
		return;
	}
	if($("#pvalue").val()==''){
		alert("请输入参数数值");
		return;
	}
	if(!validPvalue()){
		return;
	}
	if($("#pdesc").val()==''){
		alert("请输入参数描述");
		return;
	}
	var obj = $("#paramForm");
	var action;
	if (flag) action = '/nadmin/paramAdd.do';
	else action = '/nadmin/paramUpdate.do';
	obj.attr('action',ContextPath + action);
	obj.submit();
	action = null;
	obj = null;
}
/* ajax check parameter exist or not */
function isParamExist(){
	val=$("#pname").val();
	if(val!=''){
		url=ContextPath + "/nadmin/isParamExist.do";
		ajaxPostDate("pname="+ val, url, cbIsParamExist);
	}
}
function cbIsParamExist(p){
	//if (response.)
	resText = p+"";
	if(resText==""||resText==null){
		alert("系统暂时未能提供服务...");
	}
	if(resText=="true"){
		alert("此参数已存在");
	}
}
function validPname(){
	if(!/^[a-zA-Z]\w{0,10}$/.test($('#pname').val())){
		alert("参数名称必须是1-10位英文/数字且以字母开头");
		return false;
	}
	return true;
}
function validPvalue(){
	if(!/^\w{1,12}$/.test($('#pvalue').val())){
		alert("参数数值必须是1-12位英文/数字");
		return false;
	}
	return true;
}
function validPdesc(){
	return false;
}

/*----------------------------
 *  admin operator
 *---------------------------*/
function checkBeforeSubmitAdminPwd(flag){
	if(flag && $("#uname").val()==''){
		alert("请输入用户名");
		return;
	}
	var upass = $("#upass").val();
	if(upass==''){
		alert("请输旧密码");
		return;
	}
	if(!validUpass(upass)){
		return;
	}
	upass = $("#newUpass").val();
	if(upass==''){
		alert("请输新密码");
		return;
	}
	if(!validUpass(upass)){
		return;
	}
	upass = $("#newUpassConf").val();
	if(upass==''){
		alert("请确认新密码");
		return;
	}
	if(!validUpass(upass)){
		return;
	}
	
	if (!validPassConfirm()) {
		return;
	}
	
	var obj = $("#adminInfo");
	var action;
	if (flag) action = '#';
	else action = '/nadmin/adminChangePwd.do';
	obj.attr('action',ContextPath + action);
	obj.submit();
	action = null;
	obj = null;
}
function validUpass(value){
	if(!/^\w{6,8}$/.test(value)){
		alert("密码必须是6-8位英文/数字");
		return false;
	}
	return true;
}
function validPassConfirm(){
	if ($("#newUpass").val() == $("#upass").val()) {
		alert("新密码不能与旧密码相同");
		return false;
	}
	if ($("#newUpass").val() != $("#newUpassConf").val()) {
		alert("新密码不一致,请确认");
		return false;
	}
	return true;
}

/*----------------------------
 *  search operator
 *---------------------------*/
function checkBeforeSubmitSearch(flag){
	var action;
	if (flag) action = '#';//other action
	else action = '/nadmin/userSearch.do';
	
	var key = $('#key').val();
	if (!validKeyWord(key)) {
		if (flag) action = '#';
		else action = '/nadmin/userList.do';
	}
	var obj = $("#searchForm");
	obj.attr('action',ContextPath + action);
	obj.submit();
	obj = null;
}
function validKeyWord(value) {
	if (null==value || ''==value) {
		return false;
	}
	return true;
}

/*----------------------------
 *  init
 *---------------------------*/
function admin_init() {
	//Init pop dialogs
	$("#userEditDialog").dialog({
	    autoOpen: false,
	    modal: true,
	    width: 565,
	    height: 390
	});
}
function closeDialog(o) {
	$('#' + o).dialog("close");
}
function editUser(loginId) {
	LOGIN_ID = loginId;
	url=ContextPath + "/nadmin/userQueryAsy.do";
	ajaxPostDate("loginId="+ loginId, url, cbEditUser);
	url = null;
}
function cbEditUser(resp) {
	var p = resp.userProfile;
	$('#userEditDialog #loginId').html(p.loginId + '<input type="hidden" name="loginId" value="'+p.loginId+'"/>');
	$('#userEditDialog #passWord').val('abcdefg1');
	$('#userEditDialog #addNo').attr('value',p.addNo);
	$('#userEditDialog #telephone').val(p.telephone);
	$('#userEditDialog #email').val(p.email);
	$('#userEditDialog #username').val(p.username);
	var o = p.client=='true' ? 'Yes':'No';
	$('#userEditDialog #rb'+o+'').attr('checked',true);
	$('#userEditDialog #clientNo').val(p.client=='true' ? p.clientNo : '');
	$("#userEditDialog").dialog("open");
	p = null;
	o = null;
}
function resetUserInfo() {
	var row = $('#st' + LOGIN_ID +'');
	row.find("td:eq(1)").text($('#userEditDialog #username').val());
	row.find("td:eq(2)").text($('#userEditDialog #telephone').val());
	var rb = $('#userEditDialog input[type=radio][name=isClient]:checked').val();
	row.find("td:eq(3)").text(rb == 'true' ? '是' : '否');
	row = null;rb = null;
}
