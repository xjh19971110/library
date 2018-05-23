package com.Dao;

import com.entity.Appointment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppointmentDao {
   int insertAppointment(@Param("book_id")int book_id,@Param("student_id")int student_id);
   List<Appointment>quaryAndReturn(int student_id);
}
