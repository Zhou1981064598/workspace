package com.zhou.controller;
import com.github.pagehelper.Page;
import com.zhou.domain.Book;
import com.zhou.domain.Record;
import com.zhou.domain.User;
import com.zhou.enetity.PageResult;
import com.zhou.enetity.Result;
import com.zhou.service.BookService;

import com.zhou.service.RecordService;
import com.zhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
@Autowired
    private  BookService bookService;
@ResponseBody
@RequestMapping("/selectNewbooks")
    public ModelAndView selectNewbooks(HttpServletRequest request){
    PageResult pageResult =bookService.selectNewbooks(1,5);
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.setViewName("books_new.");
    modelAndView.addObject("pageResult",pageResult);
    return modelAndView;
}
@ResponseBody
@RequestMapping("findById")
    public Result<Book> findById(String id){
        try{
           Book book=bookService.findById(id);
           if(book==null){
               return new Result(false,"查询图书失败");
           }
               return new Result(true,"查询图书成功",book);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"查询图书失败！");
        }

    }
    @ResponseBody
    @RequestMapping("/borrowBook")
    public Result<Book> borrowBook(Book book, HttpSession session){
        //获取当前登录的用户名
        String pname=((User)session.getAttribute("USER_SESSION")).getName();
        book.setBorrower(pname);
        try {
            Integer conunt=bookService.borrowBook(book);
            if(conunt!=1){
                return new Result(false,"借阅失败");
            }
            return new Result(true,"借阅成功，请到管理员处领取图书！");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"借阅失败！");
        }
    }
    @RequestMapping("/search")
    public ModelAndView search(Book book,HttpServletRequest request,Integer pageNum,Integer pageSize){
    //如果当前的页码为空，就默认显示1页数据，这1页数据默认显示10条
    if(pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=10;
        }
        PageResult pageResult=bookService.search(book,pageNum,pageSize);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("books.");
        modelAndView.addObject("pageResult",pageResult);
        //将查询到的页数返回到页面用于回显到查询的输入框中
        modelAndView.addObject("search",book);
        //将当前页码返回到页面，用于分页插件的分页显示
        modelAndView.addObject("pageNum",pageNum);
        //将当前查询到的控制器路劲返回到页面，页码变化时继续向该路径发送请求
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;
}
/**
 * 新增图书
 * book：页面提交的新增图书信息将新增的结果和向页面传递信息封装到Result对象中返回
 */
    @ResponseBody
    @RequestMapping("addBook")
    public Result addBook(Book book){
        try {
            Integer count=bookService.addBook(book);
            if (count!=1){
                return new Result(false,"新增图书失败");
            }
            return new Result(true,"新增图书成功！");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"异常：新增图书失败！");
        }
    }
    @ResponseBody
    @RequestMapping("/editBook")
    public Result editBook(Book book){
        try {
            Integer count=bookService.editBook(book);
            if (count!=1){
                return new Result(false,"编辑失败！");
            }
            return new Result(true,"编辑成功！");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"异常：编辑失败！");
        }
    }

    @ResponseBody
    @RequestMapping("/searchBorrowed")
    public ModelAndView searchBorrowed(Book book,HttpServletRequest request,Integer pageNum,Integer pageSize){
        if (pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=10;
        }
        User user=(User) request.getSession().getAttribute("USER_SESSION");
        PageResult pageResult=bookService.searchBorrowed(book,user,pageNum,pageSize);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("book_borrowed.");
        modelAndView.addObject("pageResult",pageResult);
        modelAndView.addObject("search",book);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;

    }
    /**归还图书
     * @parm id 归还的图书id
     */
    @ResponseBody
    @RequestMapping("/returnBook")
    public Result returnBook(String id,HttpSession session){
        User user=(User) session.getAttribute("USER_SESSION");
        try{
            boolean flag=bookService.returnBook(id,user);
            if (!flag){
                return new Result(false,"还书失败");
            }return new Result(true,"还书确认中,请到管理员处归还图书");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"异常：还书失败！");
        }
    }
    /**
     * 本方法获取当前登录的用户信息，将归还的图书id和当前登录的用户信息作为returnBook方法实参，通过BookService对象
     * 调用returnBook方法执行
     */
    @ResponseBody
    @RequestMapping("/returnConfirm")
    public Result returnConfirm(String id){
        try {
            Integer count=bookService.returnConfirm(id);
            if (count!=1){
                return new Result(false,"确认失败！");
            }
                return new Result(true,"确认成功！");
        }catch (Exception e){
            e.printStackTrace();
                return new Result(false,"异常：确认失败！");
        }

    }
}
