var bookappointment={
        URL: {
            verify: function () {
                return '/books' + '/verify';
            }
        },
        validataStudent: function (studentId, password) {
            if (!studentId || !password)
            {
                return "nothing";
            }
            else
            if (studentId.length != 6 || isNaN(studentId) || password.length != 6 || isNaN(password)) {
                return "typeError";
            }
            else if(bookappointment.verifyWithDatabase(studentId,password)){
                return "success";
            }
            else {
                return "mismatch";
            }

        },

    verifyWithDatabase:function(studentId,password){
    var result=false;
    var params={};
    params.studentId=studentId;
    params.password=password;
    var verifyUrl=bookappointment.URL.verify();
    $.ajax({
        type:'post',
        url:verifyUrl,
        data:params,
        datatype:'json',
        async:false,
        success:function(data){
            if(data.result=='SUCCESS'){
                window.location.reload();
                alert("登陆成功");
                result=true;
            }
            else{
                result=false;
            }
        }
    });
    return result;


},


detail: {
    init: function (params) {
        var book_id=params['book_id'];
        var studentId=$.cookie('studentId');
        var password=$.cookie('password');
        if(!studentId||!password){
            var IdAndPasswordModal=$('#varifyModal');
            IdAndPasswordModal.modal({
                show: true,//显示弹出层
                backdrop: 'static',//禁止位置关闭
                keyboard: false//关闭键盘事件
            });
            $('#stduentBtn').click(function () {
                alert(3);
                studentId=$('studentIdKey').val();
                password=$('passwordKey').val();
                var temp=bookappointment.validataStudent(studentId,password);
                if(temp=="nothing"){
                    $('#studentMessage').hide().html('<label class="label label-danger">学号或密码为空!</label>').show(300);
                }
                else if(temp=="typerror"){
                    $('#studentMessage').hide().html('<label class="label label-danger">格式不正确!</label>').show(300);
                }else if(temp=="mismatch"){
                    console.log("已经调用验证函数！");
                    $('#studentMessage').hide().html('<label class="label label-danger">学号密码不匹配!</label>').show(300);
                }else if(temp=="success"){
                    $.cookie('studentId',studentId),{path:'/books'};
                    $.cookie('password',password,{path:'/books'});
                }
            })

        }



    }



},
}