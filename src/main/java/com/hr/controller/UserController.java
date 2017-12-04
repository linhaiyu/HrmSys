package com.hr.controller;

import com.hr.common.HrmConstants;
import com.hr.common.PageModel;
import com.hr.entity.User;
import com.hr.service.HrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /** 处理登录请求 */
    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam("loginname") String loginname,
                              @RequestParam("password") String password,
                              HttpSession session,
                              ModelAndView mv) {
        User user = hrmService.login(loginname, password);
        if (user != null) {
            logger.info("user is {}.", user.toString());
            session.setAttribute(HrmConstants.USER_SESSION, user);

            mv.setViewName("redirect:/main");
        } else {
            logger.info("user is null.");
            mv.addObject("message", "登录名或密码错误，请重新输入");
            mv.setViewName("forward:/loginForm");
        }

        return mv;
    }

    @RequestMapping(value = "/main")
    public String getMain(HttpSession session) {
        int userCount = hrmService.getTotalUserCount();
        int deptCount = hrmService.getTotalDeptCount();
        int jobCount = hrmService.getTotalJobCount();
        int employeeCount = hrmService.getTotalEmployeeCount();
        int documentCount = hrmService.getTotalDocumentCount();
        int noticeCount = hrmService.getTotalNoticeCount();
        int trainCount = hrmService.getTotalTrain();

        session.setAttribute("userCount", userCount);
        session.setAttribute("deptCount", deptCount);
        session.setAttribute("jobCount", jobCount);
        session.setAttribute("employeeCount", employeeCount);
        session.setAttribute("documentCount", documentCount);
        session.setAttribute("noticeCount", noticeCount);
        session.setAttribute("trainCount", trainCount);

        logger.info("user-{}, dept-{}, job-{}, employee-{}, document-{}, notice-{}, train-{}",
                userCount, deptCount, jobCount, employeeCount, documentCount, noticeCount, trainCount);
        return "main";
    }

    /** 处理查询用户的请求*/
    @RequestMapping(value = "/user/selectUser")
    public String selectUser(Integer pageIndex, @ModelAttribute User user, Model model) {
        logger.info("pageIndex = {}, user = {}", pageIndex, user);
        PageModel pageModel = new PageModel();

        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        List<User> users = hrmService.findUser(user, pageModel);
        model.addAttribute("users", users);
        model.addAttribute("pageModel", pageModel);

        return "user/user";
    }

    /** 处理删除用户的请求*/
    @RequestMapping(value = "/user/removeUser")
    public ModelAndView removeUser(String ids, ModelAndView mv) {
        // 分解 id 字符串
        logger.info("ids: " + ids);
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            logger.info("remove user by id {}.", id);
            hrmService.removeUserById(Integer.parseInt(id));
        }

        // 跳转到查询请求
        mv.setViewName("redirect:/user/selectUser");
        return mv;
    }


    /** 一个方法处理修改用户请求 GET+POST，ps: 不能用ModelAndView 做参数*/
//    @RequestMapping(value="/user/updateUser")
//    public String updateUser( String flag,  @ModelAttribute User user, Model model){
//        String result;
//        if(flag.equals("1")){
//            User target = hrmService.findUserById(user.getId());
//            model.addAttribute("user", target);
//            // 返回修改员工页面
//            result = ("user/showUpdateUser");
//        }else{
//            hrmService.modifyUser(user);
//            // 设置客户端跳转到查询请求
//            result = ("redirect:/user/selectUser");
//        }
//        return result;
//    }

    /**
     * 处理修改用户请求 GET
     * @param user，要修改的用户对象
     * @param mv 数据模型+视图
     * @return
     */
    @RequestMapping(value = "/user/showUpdateUser")
    public ModelAndView showUpdateUser(@ModelAttribute User user, ModelAndView mv) {
        logger.info("user is {}", user.toString());
        User target = hrmService.findUserById(user.getId());
        mv.addObject("user", target);
        // 进入修改用户的页面
        mv.setViewName("user/showUpdateUser");
        return mv;
    }

    @RequestMapping(value = "/user/updateUser", method = RequestMethod.POST)
    public String updateUser(@RequestParam("id") Integer id,
                           @RequestParam("userName") String userName,
                           @RequestParam("loginName") String loginName,
                           @RequestParam("password") String password,
                           @RequestParam("status") Integer status) {
        User user = new User();
        user.setId(id);
        user.setLoginName(loginName);
        user.setUserName(userName);
        user.setPassword(password);
        user.setStatus(status);
        user.setCreateDate(new Date());
        logger.info("modifyUser: user is {}", user.toString());

        hrmService.modifyUser(user);
        return ("redirect:/user/selectUser");
    }

    /**
     * 处理添加用户的请求
     * @param flag，1 表示跳转到添加用户页面， 2 表示执行添加用户操作
     * @param user 要添加的用户对象
     * @return
     */
    @RequestMapping(value = "/user/addUser")
    public String addUser(@RequestParam("flag") String flag, @ModelAttribute User user) {
        logger.info("GET, flag={}", flag);
        String result ;

        if (user == null) {
            logger.info("GET, user is null.");
        } else {
            logger.info("GET, user: {}", user.toString());
        }

        if (flag.equals("1")) {
            result = ("user/showAddUser");
        } else {
            hrmService.addUser(user);
            result = ("redirect:/user/selectUser");
        }

        return result;
    }

//    @RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
//    public String addUser(@RequestParam("userName") String userName,
//                                @RequestParam("loginName") String loginName,
//                                @RequestParam("password") String password,
//                                @RequestParam("status") Integer status) {
//
//        User user = new User();
//        user.setLoginName(loginName);
//        user.setUserName(userName);
//        user.setPassword(password);
//        user.setStatus(status);
//
//        logger.info("POST, user: {}", user.toString());
//        hrmService.addUser(user);
//        return ("redirect:/user/selectUser");
//    }

    /** 处理用户退出的请求*/
    @RequestMapping(value = "/user/logout")
    public ModelAndView logout(HttpSession session, ModelAndView mv) {
        session.removeAttribute(HrmConstants.USER_SESSION);
        mv.addObject("message", "请登录，谢谢！");
        mv.setViewName("redirect:/loginForm");

        return mv;
    }
}
