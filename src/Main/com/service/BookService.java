package com.service;

import com.Dao.BookDao;
import com.dto.AppointExecution;
import com.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookService {
    book getById(int bookid);
  List<book>getList();
    student1 vaildataStu(int studentId, int password);
    List<book> getSomeList(String name);
    AppointExecution appoint(int book_id, int student_id);
    List<Appointment>getAppointByStu(int studentId);
    void updateLike(int book_id,int sessionSid);
  PageBean<comment> listComment(int curPage,int book_id);


}
