$(document).ready(function(){
    $.getJSON("/checkLogin",function(data){
        if(data.userName!=null){
          $("#loginBtn").hide();
          $("#info-message h5").html("Welcome Back!&nbsp;&nbsp;"+data.userName+"&nbsp;&nbsp;&nbsp;");
          $("#loginInfo").show();
      }

  });

if(window.location.href.endsWith("news.html")){
    $.ajax("/newsInTopic", {
        method: "GET",
        dataType: "xml",
        contentType: "text/xml; charset=\"utf-8\"",
        success: function (xmlDoc) {
            var container = $('#newsInUser');
            $(xmlDoc).find('news').each(function () {

                var firstTitle = $(this).find('firstTitle').text(),
                secondTitle = $(this).find('secondTitle').text(),
                content = $(this).find('content').text(),
                firstTopic = $(this).find('firstTopic').text(),
                secondTopic = $(this).find('secondTopic').text(),
                thirdTopic = $(this).find('thirdTopic').text();
                picUrl = $(this).find('picUrl').text();

                var htmlText = '<div class="first">'+
                '\t<div class="page-header">'+
                '\t\t<h1>'+ firstTitle + '<small>'+secondTitle+'</small>'+
                '\t\t</h1>'+
                '\t<div>'+
                '<img alt="140x140" class="news-image" src='+picUrl+'/>'+
                '<p>'+content+'</p>'+
                '<span class="label label-primary">'+firstTopic+'</span>'+
                '<span class="label label-primary">'+secondTopic+'</span>'+
                '<span class="label label-primary">'+thirdTopic+'</span>'+
                '</div>';

                container.append(htmlText);
            });

}
});
}


//register
$("#register-button").click(function(){
  var userName = $('#userName').val(),
  email = $('#email').val(),
  pwd1 = $('#pwd1').val(),
  pwd2 = $('#pwd2').val(),
  gender = $('#gender').val(),
  firstStar = $('#firstStar').val(),
  secondStar = $('#secondStar').val(),
  thirdStar = $('#thirdStar').val();
  if (inputValidate(userName,email, pwd1, pwd2)) {
    var data = {
        userName: userName,
        email:email,
        password: pwd1,
        gender: gender,
        firstStar :firstStar , 
        secondStar : secondStar,
        thirdStar : thirdStar
    };
    $.post("/register",data,function(data){
      window.location = "../homepage.html";
  });
  }
});
// Sign in
$("#btn_signIn").click(function(){
    var userName = $('#sign-in-userAccount').val(),
    password = $('#sign-in-pwd').val();

    if(emailValidate(userName)){

        $.getJSON("/login", {"userName":userName,"password":password},function (data) {
            {
                window.location = "../homepage.html";
            }
        });
    }
});
//sign OUT
$('.signOut').on("click", function () {
   $.get("/logOut",function (data) {
     window.location = "../homepage.html";
 });
});
//news in topic
$('.news').on("click", function () {
   $.getJSON("/checkLogin",function(data){
    if(data.userName!=null){
       window.location = "../news.html";
   }
   else{
       window.location = "/newsRandom";
   }
});
});



function inputValidate(userName, email,pwd1, pwd2) {
    var allClear = true;
    var RED = "#dd4b39";
    var GREY = "#d2d6de";
    if (userName === "") {
        $('#userName').css("border-color", RED);
        allClear = false;
    } else {
        $('#userName').css("border-color", GREY);
    }
    if (!emailValidate(email)) {
        $('#email').css("border-color", RED);
        $('#emailError').show();
        alert("wrong emailAddress");
        allClear = false;
    } else {
        $('#email').css("border-color", "#d2d6de");
    }
    if (pwd1 === "") {
        $('#pwd1').css("border-color", RED);
         alert("empty password");
        allClear = false;
    } else {
        $('#pwd1').css("border-color", GREY);
    }
    if (pwd2 === "") {
        $('#pwd2').css("border-color", RED);
        alert("empty Repassword");
        allClear = false;
    } else {
        $('#pwd2').css("border-color", GREY);
    }
    if ((pwd1 === "" && pwd2 === "") || pwd1 !== pwd2) {
        $('#pwd1').css("border-color", RED);
        $('#pwd2').css("border-color", RED);
        allClear = false;
    } else {
        $('#pwd1').css("border-color", GREY);
        $('#pwd2').css("border-color", GREY);
    }

    return allClear;
}
function emailValidate(email) {
 var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
 return re.test(email);
}
});


