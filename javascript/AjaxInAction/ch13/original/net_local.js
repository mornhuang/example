/*
url-loading object and a request queue built on top of it
*/

/* namespacing object */
var net = new Object();

net.READY_STATE_UNINITIALIZED = 0;
net.READY_STATE_LOADING = 1;
net.READY_STATE_LOADED = 2;
net.READY_STATE_INTERACTIVE = 3;
net.READY_STATE_COMPLETE = 4;


/*--- content loader object for cross-browser requests ---*/
net.ContentLoader = function(url, onload, onerror, method, params, contentType) {
    this.req = null;
    net.currentLoader = this;
    this.onload = onload;
    this.onerror = (onerror) ? onerror : this.defaultError;
    this.loadXMLDoc(url, method, params, contentType);
}

net.ContentLoader.prototype.loadXMLDoc = function(url, method, params, contentType) {
/*
    if (window.netscape && window.netscape.security.PrivilegeManager.enablePrivilege)
        netscape.security.PrivilegeManager.enablePrivilege('UniversalBrowserRead');
*/
    if (!method) {
        method = "GET";
    }
    if (!contentType && method == "POST") {
        contentType = 'application/x-www-form-urlencoded';
    }
    if (window.XMLHttpRequest) {
        this.req = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        this.req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    if (this.req) {
        try {
            var loader = this;
            this.req.onreadystatechange = function() {
                net.ContentLoader.onReadyState.call(loader);
            }
            this.req.open(method, url, true);
            if (contentType) {
                this.req.setRequestHeader('Content-Type', contentType);
            }
            this.req.send(params);
        } catch (err) {
            this.onerror.call(this);
        }
    }
}


net.ContentLoader.onReadyState = function() {
/*
if (window.netscape && window.netscape.security.PrivilegeManager.enablePrivilege)
        netscape.security.PrivilegeManager.enablePrivilege('UniversalBrowserRead');
*/
    var req = this.req;
    var ready = req.readyState;
    if (ready == net.READY_STATE_COMPLETE) {
        var httpStatus = req.status;
        //if (status==200 || httpStatus==0){
        if (httpStatus == 200 || httpStatus == 0) {
            this.onload.call(this);
        } else {
            this.onerror.call(this);
        }
    }
}

net.ContentLoader.prototype.defaultError = function() {
    alert("error fetching data!"
            + "\n\nreadyState:" + this.req.readyState
            + "\nstatus: " + this.req.status
            + "\nheaders: " + this.req.getAllResponseHeaders());
}




/*--- queue system for efficient transfer of many small commands in a single request ---*/
net.cmdQueues = new Array();


net.CommandQueue = function(id, url, onUpdate, freq) {
    this.id = id;
    net.cmdQueues[id] = this;
    this.url = url;
    this.queued = new Array();
    this.sent = new Array();
    this.onUpdate = onUpdate;
    if (freq) {
        this.repeat(freq);
    }
    this.lastUpdateTime = 0;
}

net.CommandQueue.STATUS_QUEUED = -1;
net.CommandQueue.STATE_UNINITIALIZED = net.READY_STATE_UNINITIALIZED;
net.CommandQueue.STATE_LOADING = net.READY_STATE_LOADING;
net.CommandQueue.STATE_LOADED = net.READY_STATE_LOADED;
net.CommandQueue.STATE_INTERACTIVE = net.READY_STATE_INTERACTIVE;
net.CommandQueue.STATE_COMPLETE = net.READY_STATE_COMPLETE;
net.CommandQueue.STATE_PROCESSED = 5;

net.CommandQueue.PRIORITY_NORMAL = 0;
net.CommandQueue.PRIORITY_IMMEDIATE = 1;


net.CommandQueue.prototype.addCommand = function(command) {
    if (this.isCommand(command)) {
        this.queue.append(command, true);
        if (command.priority == net.CommandQueue.PRIORITY_IMMEDIATE) {
            this.fireRequest();
        }
    }
}

net.CommandQueue.prototype.fireRequest = function() {
    if (!this.onUpdate && this.queued.length == 0) {
        return;
    }
    var data = "lastUpdate=" + this.lastUpdateTime + "&data=";
    for (var i = 0; i < this.queued.length; i++) {
        var cmd = this.queued[i];
        if (this.isCommand(cmd)) {
            data += cmd.toRequestString();
            this.sent[cmd.id] = cmd;
        }
    }
    this.queued = new Array();
    this.loader = new net.ContentLoader(
            this.url,
            net.CommandQueue.onload,
            net.CommandQueue.onerror,
            "POST", data
            );
}

net.CommandQueue.prototype.isCommand = function(obj) {
    return (
            obj.implementsProp("id")
                    && obj.implementsProp("priority")
                    && obj.implementsFunc("toRequestString")
                    && obj.implementsFunc("parseResponse")
            );
}

net.CommandQueue.onload = function() {
    var xmlDoc = this.req.responseXML;
    var elDocRoot = xmlDoc.getElementsByTagName("responses")[0];
    var lastUpdate = elDocRoot.attributes.getNamedItem("updateTime");
    if (parseInt(lastUpdate) > this.lastUpdateTime) {
        this.lastUpdateTime = lastUpdate;
    }
    if (elDocRoot) {
        for (i = 0; i < elDocRoot.childNodes.length; i++) {
            elChild = elDocRoot.childNodes[i];
            if (elChild.nodeType == 1) {
                if (elChild.tagName == "command") {
                    var attrs = elChild.attributes;
                    var id = attrs.getNamedItem("id").value;
                    var command = net.commandQueue.sent[id];
                    if (command) {
                        command.parseResponse(elChild);
                    }
                } else if (elChild.tagName == "update") {
                    if (this.implementsFunc("onUpdate")) {
                        this.onUpdate.call(this, elChild);
                    }
                }
            }
        }
    }
}

net.CommandQueue.onerror = function() {
    alert("problem sending the data to the server");
}

net.CommandQueue.prototype.repeat = function(freq) {
    this.unrepeat();
    if (freq > 0) {
        this.freq = freq;
        var cmd = "net.cmdQueues[" + this.id + "].fireRequest()";
        this.repeater = setInterval(cmd, freq * 1000);
    }
}
net.CommandQueue.prototype.unrepeat = function() {
    if (this.repeater) {
        clearInterval(this.repeater);
    }
    this.repeater = null;
}