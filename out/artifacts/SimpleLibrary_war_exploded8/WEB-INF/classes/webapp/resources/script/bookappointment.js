var bookappointment={
        URL: {
            appoint:function(book_id,studentId){
                return '/books/'+book_id+'/appoint?studentId='+studentId;
            },

            verify: function () {
                return '/books' + '/verify';
            }
        },
        validataStudent: function (studentId, password,book_id) {
            if (!studentId || !password)
            {

                return "nothing";

            }
            else
            if (studentId.length != 6 || isNaN(studentId) || password.length != 6 || isNaN(password)) {
                return "typeError";
            }
            else if(bookappointment.verifyWithDatabase(studentId,password,book_id)){
                return "success";
            }
            else {
                return "mismatch";
            }

        },

    verifyWithDatabase:function(studentId,password,book_id){
    var result=false;
    var params={};
    params.studentId=studentId;
    params.password=password;
    var verifyUrl=bookappointment.URL.verify();
    $.ajax({
        type:'post',
        url:"/books/verify",
        data:params,
        datatype:'json',
        async:false,
        success:function(data){
            if(data.result=='SUCCESS'){
                $.post('/books/like?studentId='+studentId+'&book_id='+book_id,{},function (result) {
                    if(data.result==true){
                        $('#like1').hide().html('<a class="btn btn-primary btn-lg" href="" id="appoint_like1"></a>').show(300);
                    }
                    else{
                        $('#like2').hide().html('<a class="btn btn-primary btn-lg" href="" id="appoint_like2"></a>').show(300);
                    }

                });
                window.location.reload();
                alert("登陆成功");
                result=true;
            }
            else{
                result=false;
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);

        }
    });

    alert(studentId);
    return result;


},


detail: {
    init: function (params) {


        var book_id=params['book_id'];



        var studentId=$.cookie('studentId');

        var password=$.cookie('password');


        if(!studentId||!password){

            $('#varifyModal').modal({
                show: true,//显示弹出层
                backdrop: 'static',//禁止位置关闭
                keyboard: false//关闭键盘事件
            });




            $('#studentBtn').click(function () {
                studentId=$('#studentIdKey').val();
                password=$('#passwordKey').val();
                var temp=bookappointment.validataStudent(studentId,password,book_id);
                if(temp=="nothing"){
                    $('#studentMessage').hide().html('<label class="label label-danger">学号或密码为空!</label>').show(300);
                }
                else if(temp=="typeError"){
                    $('#studentMessage').hide().html('<label class="label label-danger">格式不正确!</label>').show(300);
                }else if(temp=="mismatch"){
                    console.log("已经调用验证函数！");
                    $('#studentMessage').hide().html('<label class="label label-danger">学号密码不匹配!</label>').show(300);
                }else if(temp=="success"){
                    $.cookie('studentId',studentId),{path:'/books'};
                    $.cookie('password',password,{path:'/books'});
                    var appointbox=$('#appoint-box');
                    bookappointment.appointment(book_id,studentId,appointbox)
                }
            });

        }
        else{
            var appointbox=$('#appoint-box');
            bookappointment.appointment(book_id,studentId,appointbox);
        }



    }



},
    appointment:function(book_id,studentId,node){
        node.html('<button class="btn btn-primary btn-lg" id="appointmentBtn">预约</button>');
      var appointmentUrl=  bookappointment.URL.appoint(book_id,studentId);
        $('#appointmentBtn').one('click',function(){
            $(this).addClass('disabled');
            $.post(appointmentUrl,{},function (result) {
                if(result&&result['success']){
                    var appointResult=result['data'];
                    var state=appointResult['state'];
                    var stateInfo=appointResult['stateInfo'];
                    node.html('<span class="label label-success">'+stateInfo+'</span>');
                }

            });
        });
    }
}