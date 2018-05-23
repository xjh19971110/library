package com.service.Impl;

import com.Dao.AppointmentDao;
import com.Dao.BookDao;
import com.Dao.CommentDao;
import com.Dao.StudentDao;
import com.dto.AppointExecution;
import com.dto.AppointStateEnum;
import com.entity.*;
import com.exception.AppointException;
import com.exception.NoNumberException;
import com.exception.RepeatAppoint;
import com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

@Autowired
    private BookDao bookDao;
@Autowired
private StudentDao studentDao;
@Autowired
private AppointmentDao appointmentDao;
@Autowired
private CommentDao commentDao;
@Autowired
private JedisPool jedisPool;
   /* public BookDao getBookDao() {
        return bookDao;
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao=bookDao;
    }*/
    @Override
    public book getById(int bookid){
        return bookDao.qureyById(bookid);
    }
    @Override
    public  student1 vaildataStu(int studentId, int password){
        return studentDao.queryStudent(studentId,password);

    }
    @Override
    public  List<book> getSomeList(String name){
        return bookDao.querySome(name);
    }
    @Override
   public List<book>getList(){
        return bookDao.queryAll(0,1000);
    }
    @Override
    @Transactional
    public AppointExecution appoint(int book_id,int studentId){
       try{
           System.out.println(12);
           int update=bookDao.reduceNumber(book_id);
           System.out.println(11);
           if(update<=0){
               throw new NoNumberException("no number");
           }
           else{
               System.out.println(14);
               int insert=appointmentDao.insertAppointment(book_id,studentId);
               System.out.println(15);
               if(insert<=0){
                   throw new RepeatAppoint("repeatappoint");
               }
               else{
                   return new AppointExecution(book_id,AppointStateEnum.SUCCESS);
               }
           }
       }catch(NoNumberException e1){
           throw e1;
       }catch(RepeatAppoint e2){
           throw e2;
       }catch(Exception e){
           throw new AppointException("appoint inner error:"+e.getMessage());
       }
    }
    @Override
    public List<Appointment>getAppointByStu(int studentId){
        return appointmentDao.quaryAndReturn(studentId);
    }
    //判断是否点过赞
    public boolean ifLike(int book_id,int sessionSid){
        System.out.println(book_id);
        Jedis jedis=jedisPool.getResource();
        System.out.println(jedis.ping());
        System.out.println(sessionSid);
        boolean result=jedis.sismember(book_id+":like",String.valueOf(sessionSid));
        System.out.println(104);
        if(jedis!=null) {
            jedisPool.returnResource(jedis);
        }
        return result;

    }
    @Override
    public void updateLike(int book_id,int sessionSid){
        System.out.println(200);
        Jedis jedis=jedisPool.getResource();
        System.out.println(210);
        jedis.sadd(book_id+":like",String.valueOf(sessionSid));
        System.out.println(202);
        int update=bookDao.addLike(book_id);
        System.out.println(update);
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }


    }
    @Override
public PageBean<comment>listComment(int curPage,int book_id){
        int limit=8;
        int offset=(curPage-1)*limit;
        int allCount=bookDao.getCommentcount(book_id);
        int allPage=0;
        if(allCount<=limit){
            allPage=1;
        }
        else if(allCount/limit==0){
            allPage=allCount/limit;
        }
        else{
            allPage=allCount/limit+1;
        }
        List<comment>commentList=commentDao.queryComment(offset,limit);
        PageBean<comment>commentPageBean=new PageBean<>(allPage,curPage);
        commentPageBean.setList(commentList);
        return commentPageBean;
    }

}
