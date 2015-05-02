// JavaScript Document
window.onload = function () {
    var hello = document.getElementById('hello');
    hello.className = 'dd';

    var empty = document.getElementById('empty');
    addNode(empty, "reader of");
    addNode(empty, "Ajax in Action!");

    var children = empty.childNodes;
    for (var i = 0; i < children.length; i++) {
        children[i].className = 'pp';
    }
    empty.style.border = 'solid green 2px';
    empty.style.width = "200px";
}

function addNode(el, text) {
    var childEl = document.createElement("div");
    el.appendChild(childEl);
    var txtNode = document.createTextNode(text);
    childEl.appendChild(txtNode);
}

