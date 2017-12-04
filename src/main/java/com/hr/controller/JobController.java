package com.hr.controller;

import com.hr.common.PageModel;
import com.hr.entity.Job;
import com.hr.service.HrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class JobController {
    private final static Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 查询职位
     * @param pageIndex 当前页
     * @param job 职位对象
     * @param model 模型数据
     * @return
     */
    @RequestMapping(value = "/job/selectJob")
    public String selectJob(Integer pageIndex, Job job, Model model) {
        logger.info("pageIndex={}, job={}", pageIndex, job);
        PageModel pageModel = new PageModel();

        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        List<Job> jobs = hrmService.findJob(job, pageModel);
        model.addAttribute("jobs", jobs);
        model.addAttribute("pageModel", pageModel);

        return "job/job";
    }

    /** 删除职位 */
    @RequestMapping(value = "/job/removeJob")
    public String removeJob(String ids) {
        logger.info("ids={}", ids);

        String[] idArray = ids.split(",");
        for (String id : idArray ) {
            logger.info("job's id={}", id);
            try {
                hrmService.removeJobById(Integer.parseInt(id));
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return "redirect:/job/selectJob";
    }

    /** 显示添加职位页面*/
    @RequestMapping(value = "/job/addJob", method = RequestMethod.GET)
    public String addJob() {
        logger.info("show showAddJob");
        return "job/showAddJob";
    }

    /** 添加职位*/
    @RequestMapping(value = "/job/addJob", method = RequestMethod.POST)
    public String addJob(Job job) {
        logger.info("new job={}",job);

        hrmService.addJob(job);
        return "redirect:/job/selectJob";
    }

    /**  显示更新职位页面 */
    @RequestMapping(value = "/job/updateJob", method = RequestMethod.GET)
    public String updateJob(Integer id, Model model) {
        logger.info("show showUpdateJob, id={}", id);

        // 查找待修改的职位，加入数据模型
        Job target = hrmService.findJobById(id);
        model.addAttribute("job", target);
        return "job/showUpdateJob";
    }

    /**  更新职位 */
    @RequestMapping(value = "/job/updateJob", method = RequestMethod.POST)
    public String updateJob(Job job) {
        logger.info("job={}", job);
        hrmService.modifyJob(job);
        return "redirect:/job/selectJob";
    }

}
