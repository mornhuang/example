var server_path='';

function cs_request(action, params)
{
	var idx = 0;
	var url = server_path + action;
	for(i in params)
	{
		if(idx++ > 0)
			url += "&" + i + '=' + params[i];
		else
			url += "?" + i + '=' + params[i];
	}
	return url;
}

function cs_start(ipPort, path)
{	
	var req = new Array;
	req['path'] = path;
		
	return cs_request('http://'+ipPort+'/manager/start', req);
}

function cs_stop(ipPort,path)
{
	var req = new Array;
	req['path'] = path;

	return cs_request('http://'+ipPort+'/manager/stop', req);
}

function cs_upload(ipPort, warfile)
{
	var req = new Array;
	req['deployWar'] = warfile;
	
	return cs_request('http://'+ipPort+'/manager/upload', req);
}

function cs_unDeploy(ipPort,path)
{
	var req = new Array;
	req['path'] = path;
	
	return cs_request('http://'+ipPort+'/manager/undeploy', req);
}

function cs_toExcelUserActionDetail(userid,actionid,ip,operationtime)
{
	var req = new Array;
	req['userid'] = userid;
	req['actionid'] = actionid;
	req['ip'] = ip;
	req['operationtime'] = operationtime;
	
	return cs_request('../exportExcelUserActionDetail.do', req);
}

