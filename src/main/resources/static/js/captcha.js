var code = "";
var scoure = "123456789qwertyuioplkjhgfdsazxcvbnmQA0ZXSWEDCVFRTGBNHYUJMKIOLP";
function create(){
    for(var i = 0;i<5;i++){
        code += scoure.charAt(Math.floor(Math.random() * scoure.length));
    }
    return code;
}
document.getElementById("codeCaptcha").innerHTML = create();