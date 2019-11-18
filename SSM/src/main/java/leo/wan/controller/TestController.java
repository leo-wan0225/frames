package leo.wan.controller;

import leo.wan.utils.SessionUtils;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/test/")
public class TestController {
    @RequestMapping("session")
    public String toUserList(HttpServletRequest request) {
        HttpServletRequest myRequest = SessionUtils.getRequest();
        String myname = (String) request.getParameter("myname");
        String myname1 = (String) myRequest.getParameter("myname");
       HttpSession session1=  SessionUtils.getSession();
        return  "admin/userList";
    }
    @RequestMapping("session2")
    public String toUserList2(HttpServletRequest request, HttpServletResponse response) {
        HttpServletRequest myRequest = SessionUtils.getRequest();
        String myname1 = (String) myRequest.getParameter("myname");
        String myname = (String) request.getParameter("myname");
        HttpSession session=  SessionUtils.getRequest().getSession(true);
        Cookie cookie = new Cookie("name","value");
        cookie.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(cookie);
        session.getId();
       return  "admin/userList";
    }
}
