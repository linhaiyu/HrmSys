package com.hr.controller;

import com.hr.common.HrmConstants;
import com.hr.common.PageModel;
import com.hr.entity.Notice;
import com.hr.entity.User;
import com.hr.service.HrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NoticeController {
    private final static Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * Notice 增加
     */
    @RequestMapping(value = "/notice/addNotice", method = RequestMethod.GET)
    public String addNotice() {
        logger.info("show showAddNotice");
        return "notice/showAddNotice";
    }

    @RequestMapping(value = "/notice/addNotice", method = RequestMethod.POST)
    public String addNotice(Notice notice, HttpSession session) {
        User curUser = (User) session.getAttribute(HrmConstants.USER_SESSION);
        notice.setUser(curUser);
        logger.info("notice={}", notice.toString());

        hrmService.addNotice(notice);
        return "redirect:/notice/selectNotice";
    }

    /**
     * Notice 删除
     */
    @RequestMapping(value = "/notice/deleteNotice")
    public String deleteNotice(String ids) {
        logger.info("ids={}", ids);

        String[] idArray = ids.split(",");
        for (String id : idArray) {
            hrmService.removeNoticeById(Integer.parseInt(id));
        }

        return "redirect:/notice/selectNotice";
    }

    /**
     * Notice 修改
     */
    @RequestMapping(value = "/notice/updateNotice", method = RequestMethod.GET)
    public String updateNotice(Integer id, Model model) {
        Notice notice = hrmService.findNoticeById(id);
        logger.info("target notice={}", notice.toString());

        model.addAttribute("notice", notice);
        return "notice/showUpdateNotice";
    }

    @RequestMapping(value = "/notice/updateNotice", method = RequestMethod.POST)
    public String updateNotice(Notice notice) {
        logger.info("new notice={}", notice);
        hrmService.modifyNotice(notice);
        return "redirect:/notice/selectNotice";
    }

    /**
     * Notice 查找
     */
    @RequestMapping(value = "/notice/selectNotice")
    public String selectNotice(Model model, Integer pageIndex, Notice notice) {
        logger.info("pageIndex={}, notice={}", pageIndex, notice);
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        List<Notice> notices = hrmService.findNotice(notice, pageModel);
        model.addAttribute("notices", notices);
        model.addAttribute("pageModel", pageModel);

        return "notice/notice";
    }

    @RequestMapping(value = "/notice/previewNotice")
    public String previewNotice(Integer id, Model model) {
        logger.info("id={}", id);

        Notice notice = hrmService.findNoticeById(id);
        logger.info("notice={}", notice);
        model.addAttribute("notice", notice);
        return "notice/previewNotice";
    }
}
