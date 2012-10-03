function onlyInputNumber(el,ev){
    reg=/\d{0,8}/;
    var v = el.value.match(reg);
    el.value = v ? v : "";
}