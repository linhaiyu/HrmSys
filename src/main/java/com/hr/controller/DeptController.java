package com.hr.controller;

import com.hr.common.PageModel;
import com.hr.entity.Dept;
import com.hr.service.HrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DeptController {

    private final static Logger logger = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 查询部门
     * @param model 模型数据
     * @param pageIndex  当前页
     * @param dept  部门对象
     * @return
     */
    @RequestMapping(value = "/dept/selectDept")
    public String selectDept(Model model, Integer pageIndex, Dept dept) {
        logger.info("pageIndex = {}, dept = {}", pageIndex, dept);
        PageModel pageModel = new PageModel();

        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        List<Dept> depts = hrmService.findDept(dept, pageModel);
        model.addAttribute("depts", depts);
        model.addAttribute("pageModel", pageModel);

        return "dept/dept";
    }

    /**
     * 删除部门
     * @param ids 部门id字符串，“，”分隔
     * @return
     */
    @RequestMapping(value = "/dept/removeDept")
    public String removeDept(String ids) {
        String[] idArray = ids.split(",");

        for (String id : idArray ) {
            logger.info("dept's id = {}", id);
            try {
                hrmService.removeDeptById(Integer.parseInt(id));
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return "redirect:/dept/selectDept";
    }

    // TODO: 添加和修改部门，都应该分为两个函数，而不是通过flag来区分操作；
    /**
     * 添加部门
     * @param flag 1-显示添加页面，2-执行添加部门的操作
     * @param dept 要添加的部门对象
     * @return
     */
    @RequestMapping(value = "/dept/addDept")
    public String addDept(String flag, Dept dept) {
        logger.info("flag={}, dept={}", flag, dept.toString());

        if (flag.equals("1")) {
            return "dept/showAddDept";
        }

        hrmService.addDept(dept);
        return "redirect:/dept/selectDept";
    }


    /**
     * 修改部门
     * @param flag 1-显示修改页面， 2-执行修改部门的操作
     * @param dept 修改的部门
     * @param model 数据模型
     * @return
     */
    @RequestMapping(value="/dept/updateDept")
    public String updateDept(String flag, Dept dept, Model model) {
        logger.info("flag={}, dept={}", flag, dept);

        if (flag.equals("1")) {
            Dept target = hrmService.findDeptById(dept.getId());
            model.addAttribute("dept", target);
            return "dept/showUpdateDept";
        }

        hrmService.modifyDept(dept);
        return "redirect:/dept/selectDept";
    }
}
