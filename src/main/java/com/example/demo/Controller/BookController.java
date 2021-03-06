package com.example.demo.Controller;

import com.example.demo.Dao.BookDao;
import com.example.demo.Dao.UserDao;
import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.view.HttpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Transactional
@Controller
@RequestMapping
public class BookController {
    @Autowired
    UserDao userDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    HttpGenerator generator;

    @ResponseBody
    @RequestMapping("showBooks")
    public String showAllBooks() {
        return generator.getAllBooks();
    }

    @ResponseBody
    @RequestMapping("selectBorrowedBook")
    public String showBorrowedBooks(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return generator.getByMessage("您未登录，请前往登录", "login.html");
        }
        return generator.getBorrowedBooks((User) session.getAttribute("User"));
    }



    @ResponseBody
    @RequestMapping("/Admin/AddBook")
    public String AddBook(@RequestParam(value = "bookname") String bookname,
                          @RequestParam(value = "author") String author,
                          @RequestParam(value = "remarks") String remarks,
                          @RequestParam(value = "body") String body,
                          HttpServletRequest request) {


        Book book = new Book();
        book.setBookname(bookname);
        book.setAuthor(author);
        book.setRemarks(remarks);
        book.setBody(body);
        bookDao.save(book);
        return "新增成功!";
    }

    @ResponseBody
    @RequestMapping("bookDetail")
    public String bookDetail(@RequestParam(value = "bookid") String bookid,
                             HttpServletRequest request) {


        return generator.getBookBodyHtml(bookDao.getOne(Long.valueOf(bookid)));
    }


    @ResponseBody
    @RequestMapping("borrowBook")
    public String borrowBook(@RequestParam(value = "bookid") Long bookid,
                             HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Book book = bookDao.getOne(bookid);
        if (book.isBorrowed())
            return "书籍已经被人借走了！";
        book.setUser((User) session.getAttribute("User"));
        book.setBorrowed(true);
        bookDao.save(book);
        return "借阅成功！";
    }

    @ResponseBody
    @RequestMapping("returnBook")
    public String returnBook(@RequestParam(value = "bookid") Long bookid,
                             HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("User");
        Book book = bookDao.getOne(bookid);
        if (book.getUser().getUserId() != user.getUserId())
            return "不能归还不是你借的书！";
        book.setUser(null);
        book.setBorrowed(false);
        bookDao.save(book);
        return "归还成功!";
    }

    @ResponseBody
    @RequestMapping("/Admin/DeleteBook")
    public String DeleteBook(@RequestParam(value = "bookid") Long bookid,
                             HttpServletRequest request) {
        Book book = bookDao.getOne(bookid);
        bookDao.delete(book);
        return "删除成功!";
    }


}
