package com.Controller;

import com.dto.AppointExecution;
import com.dto.AppointStateEnum;
import com.dto.Result;
import com.entity.*;
import com.exception.AppointException;
import com.exception.NoNumberException;
import com.exception.RepeatAppoint;
import com.service.Impl.BookServiceImpl;
import com.service.Impl.BookServiceImpl;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/books")

public class Test {

@Autowired
    private BookServiceImpl bookService;
@RequestMapping(value = "/hello")
@ResponseBody
public String hi(){
    return "hi";
}
    @RequestMapping(value = "user/{id}",method = RequestMethod.GET)
    @ResponseBody

        public book getBook(@PathVariable(value = "id")int id){
        book book1=bookService.getById(id);
        System.out.println(book1.getName());

        return book1;

    }
    @RequestMapping(value="/list",method = RequestMethod.GET)

    private String list(Model model){
        List<book>list=bookService.getList();
        model.addAttribute("list",list);
       // int i=list.size();
        return "list";
    }
    @RequestMapping(value="/search",method =RequestMethod.GET)
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String name=new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
        System.out.println(name);
        request.setAttribute("name",name);
        request.setAttribute("list",bookService.getSomeList(name));
        List<book> list1=(List<book>) request.getAttribute("list");
        System.out.println(list1);
        request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request,response);
    }
    @RequestMapping(value="/{book_id}/detail",method = RequestMethod.GET)
    private String detail(@PathVariable("book_id")Integer book_id,Model model){
        System.out.println(2);
        if(book_id==null){
            return "redict:/list";
        }
        book book1=bookService.getById(book_id);
        if(book1==null){
            return "forward:/list";
        }

        PageBean<comment>commentPageBean=bookService.listComment(1, book_id);
        model.addAttribute("book",book1);
        System.out.println(book1.getNumber());
        return "detail";
    }
    @RequestMapping(value = "/like",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    private boolean adujLike(int studentId,int book_id){
        System.out.println(100);
        System.out.println(studentId);
        boolean result;
        result=bookService.ifLike(book_id,studentId);
        System.out.println(result);
        return result;
    }
    @RequestMapping(value="/verify",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    private Map vaildata(int studentId, int password, HttpSession httpSession){

        Map resultMap=new HashMap();
        student1 student=null;
        System.out.println("验证函数");
        student=bookService.vaildataStu(studentId,password);
        System.out.println(student);
        System.out.println("输入的学号和密码："+studentId+","+password);
      // System.out.println("查询到的学号和密码："+student.getStudent_id()+"和"+student.getPassword());
        if(student!=null){
            System.out.println("SUCCESS");
            resultMap.put("result","SUCCESS");
            httpSession.setAttribute("studentId",studentId);//将学生id存入session
            return resultMap;

        }
        else{
            resultMap.put("result","FAILED");
            return resultMap;
        }
    }
    @RequestMapping(value="/addLike",method = RequestMethod.POST)
    @ResponseBody
    private void addlike(@RequestParam("studentId")int studentId,@RequestParam("book_id")int book_id){
        System.out.println(10000);
        bookService.updateLike(book_id,studentId);

    }
    @RequestMapping(value = "/{book_id}/appoint",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    private Result<AppointExecution>execute(@PathVariable("book_id") int book_id,@RequestParam("studentId")int studentId){
        System.out.println(7);
        Result<AppointExecution>result;
        AppointExecution appointExecution=null;

        try{
           appointExecution=bookService.appoint(book_id,studentId);
           result=new Result<AppointExecution>(true,appointExecution);
           return result;
        }catch(NoNumberException e1) {
            appointExecution=new AppointExecution(book_id, AppointStateEnum.NO_NUMBER);
            result=new Result<AppointExecution>(true,appointExecution);
            return result;
        }catch(RepeatAppoint e2){
           appointExecution=new AppointExecution(book_id,AppointStateEnum.REPEAT_APPOINT);
            result=new Result<AppointExecution>(true,appointExecution);
            return result;
        }catch (Exception e){
            appointExecution=new AppointExecution(book_id,AppointStateEnum.INNER_ERROR);
            result=new Result<AppointExecution>(true,appointExecution);
            return result;
        }

    }
    @RequestMapping(value="/appoint")
    private String appointBooks(@RequestParam("studentId")int studentId,Model model){
        System.out.println(10);
        List<Appointment>appointments=new ArrayList<Appointment>();
        System.out.println(21);
        appointments=bookService.getAppointByStu(studentId);
        System.out.println(22);
        model.addAttribute("appointList",appointments);
        return "appointBookList";
    }




}

