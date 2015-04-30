var last_timeout = null;
var xml_data = null;
var xsl_stylesheet = null;

function doSearch(text) {
    document.getElementById('search-results').innerHTML = "Loading...";
    window.clearTimeout(last_timeout);
    last_timeout = window.setTimeout("getSearchResults('" + text + "');", 500);
}

function getSearchResults(text) {
    new Ajax.Request("cgi-bin/search-xml.cgi",
                     { method: "get", parameters: "description=" + text,
                             onComplete: function (transport) { 
                            xml_data = transport.responseXML;
                             updateSearchResults();
                         } });
    new Ajax.Request("cgi-bin/search-xml.xsl",
                     { method: "get", parameters: "description=" + text,
                             onComplete: function (transport) {
                             xsl_stylesheet = transport.responseXML;
                             updateSearchResults();
                         } });
}

function updateSearchResults() {
    if (xml_data != null && xsl_stylesheet != null) {
        document.getElementById('search-results').innerHTML = "";
        var processor = new XSLTProcessor();
        processor.importStylesheet(xsl_stylesheet);
        var html = processor.transformToFragment(xml_data, document);
        document.getElementById('search-results').appendChild(html);
    }
}
