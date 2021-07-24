//获取验证码
function getCode(){
    $("#verifyimg").attr("src","/c/getcode");
}

$('.message a').click(function () {
    $('form').animate({ height: "toggle", opacity: "toggle" }, "slow");
});

//登录
function log(){
    $.ajax({
        type:"post",
        url:"/c/clogin",
        data:$('.login-form').serialize(),
        success:function (data){
            if (data=="codeerror") alert("验证码错误！");
            else if(data=="success") window.location.href="cmain";
            else alert("用户名或密码错误！");
        }
    })
}

//注册
function reg(){
    $.ajax({
        type:"post",
        url:"/c/cregister",
        data:$('.register-form').serialize(),
        success:function (data){
            if (data=="error") alert("用户已存在！");
            else if(data=="success") window.location.href="hello";
            else alert("用户名或密码错误！");
        }
    })
}



//登录、注册表单切换
function changePage(val){
    var rform = document.getElementsByClassName("register-form")[0];
    var lform = document.getElementsByClassName("login-form")[0];
    if(val == 'login'){
        lform.style.display = "block";
        rform.style.display = "none";
    }else if(val == 'register'){
        lform.style.display = 'none';
        rform.style.display = 'block';
    }
}
