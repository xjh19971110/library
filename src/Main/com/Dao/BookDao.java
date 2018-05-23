package com.Dao;

import com.entity.book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookDao {

    book qureyById(int id);
   List<book>querySome(String name);
   List<book>queryAll(@Param("offset")int offset,@Param("limit")int limit);
    int reduceNumber(int bookid);
    int reduceLike(int book_id);
    int addLike(int book_id);
    int getCommentcount(int book_id);
    int addComment(int book_id);
}

