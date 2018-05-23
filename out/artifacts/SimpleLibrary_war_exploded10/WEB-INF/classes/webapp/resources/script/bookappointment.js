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
        window.location.reload();
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
                    var like1=$('#like1');
                    var like2=$('#like2');

                    bookappointment.appointment(book_id,studentId,appointbox,like1,like2)
}
});

        }
        else{
            var appointbox=$('#appoint-box');
            var like1=$('#like1');

            bookappointment.appointment(book_id,studentId,appointbox,like1);
        }




    }



},
    appointment:function(book_id,studentId,node,node1){
        node.html('<button class="btn btn-primary btn-lg" id="appointmentBtn">预约</button>');
        var param={};
        param.studentId=studentId;
        param.book_id=book_id;
        $.ajax({
            type:'post',
            url:"/books/like",
            data:param,
            datatype:'json',
            async:false,
            success:function(data1){
                if(data1==false){



                    node1.html('<button class="btn btn-primary btn-lg" id="like3">赞</button>');


                }
                else{
                    node1.html('<button class="btn btn-primary btn-lg" id="like4">已赞</button>');

                }
            },

        });





      var appointmentUrl=  bookappointment.URL.appoint(book_id,studentId);
        $('#appointmentBtn').one('click',function(){
            $(this).addClass('disabled');
            $.post(appointmentUrl,{},function (result) {

                if(result&&result['success']){
                    var appointResult=result['data'];
                    alert(appointResult);
                    var state=appointResult['state'];
                    var stateInfo=appointResult['stateInfo'];
                    node.html('<span class="label label-success">'+stateInfo+'</span>');
                }

            });
        });
        $('#like3').one('click',function(){
            alert(1000);
            $(this).addClass('disable');
            $.post('/books/addLike?studentId='+studentId+'&book_id='+book_id,{},function (data2){
                alert(1001);



             var int=  Number(  $('#like5').text())+1;

                $('#like5').text(int);


               alert(addlike1);




            })
        })
    }
}