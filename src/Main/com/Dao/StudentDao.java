package com.Dao;

import com.entity.student1;
import org.apache.ibatis.annotations.Param;

public interface StudentDao {
    student1 queryStudent(@Param("stduentId")int studentId,@Param("password")int password);
}
