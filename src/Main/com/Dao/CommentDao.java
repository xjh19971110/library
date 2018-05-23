package com.Dao;

import com.entity.comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao {


    List<comment>queryComment(@Param("offset")int offset,@Param("limit")int limit);
    int addComment(int book_id,String content,int studentId);

}
