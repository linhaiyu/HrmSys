package com.hr.controller;

import com.hr.common.PageModel;
import com.hr.entity.Dept;
import com.hr.entity.Employee;
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
public class EmployeeController {
    private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /** 查询职员 */
    @RequestMapping(value = "/employee/selectEmployee")
    public String searchEmployee(Integer pageIndex, Integer job_id, Integer dept_id, Employee employee, Model model) {

        logger.info("pageIndex={}, jobId={}, deptId={}, employee={}", pageIndex, job_id, dept_id, employee.toString());
        genericAssociation(job_id, dept_id, employee);

        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        // 查出所有的职位和部门，页面上用于选择
        List<Job> jobs = hrmService.findAllJob();
        List<Dept> depts = hrmService.findAllDept();

        List<Employee> employees = hrmService.findEmployee(employee, pageModel);

        model.addAttribute("employees", employees);
        model.addAttribute("jobs", jobs);
        model.addAttribute("depts", depts);
        model.addAttribute("pageModel",pageModel);

        return "employee/employee";
    }

    /** 显示添加职员页面 */
    @RequestMapping(value = "/employee/addEmployee", method = RequestMethod.GET)
    public String addEmployee(Model model) {
        logger.info("show showAddEmployee");
        // 查出所有的职位和部门，页面上用于选择
        List<Job> jobs = hrmService.findAllJob();
        List<Dept> depts = hrmService.findAllDept();
        model.addAttribute("jobs", jobs);
        model.addAttribute("depts", depts);
        return "employee/showAddEmployee";
    }

    /** 添加职员*/
    @RequestMapping(value = "/employee/addEmployee", method = RequestMethod.POST)
    public String addEmployee(Employee employee, Integer job_id, Integer dept_id) {
        logger.info("job_id={}, dept_id={}, employee={}", job_id, dept_id, employee);

        genericAssociation(job_id, dept_id, employee);

        hrmService.addEmployee(employee);
        return "redirect:/employee/selectEmployee";
    }

    /** 删除职员 */
    @RequestMapping(value = "/employee/removeEmployee")
    public String removeEmployee(String ids) {
        logger.info("ids={}", ids);

        String[] idArray = ids.split(",");
        for (String id : idArray ) {
            logger.info("employee's id = ", id);
            hrmService.removeEmployeeById(Integer.parseInt(id));
        }

        return "redirect:/employee/selectEmployee";
    }

    /** 显示修改职员的页面*/
    @RequestMapping(value = "/employee/updateEmployee", method = RequestMethod.GET)
    public String updateEmployee(Integer id, Model model) {
        logger.info("id={}", id);

        // 查出所有的职位和部门，页面上用于选择
        List<Job> jobs = hrmService.findAllJob();
        List<Dept> depts = hrmService.findAllDept();
        model.addAttribute("jobs", jobs);
        model.addAttribute("depts", depts);

        Employee target = hrmService.findEmployeeById(id);
        model.addAttribute("employee", target);
        return "employee/showUpdateEmployee";
    }

    /** 修改职员*/
    @RequestMapping(value = "employee/updateEmployee", method = RequestMethod.POST)
    public String updateEmployee(Employee employee, Integer dept_id, Integer job_id) {
        logger.info("dept_id={}, job_id={}, employee={}", dept_id, job_id, employee.toString());

        genericAssociation(job_id, dept_id, employee);
        hrmService.modifyEmployee(employee);
        return "redirect:/employee/selectEmployee";
    }

    /**
     * 由于部门和职位在Employee中是对象关联映射，
     * 所以不能直接接收参数，需要创建Job对象和Dept对象
     * */
    private void genericAssociation(Integer job_id, Integer dept_id, Employee employee){
        if(job_id != null){
            Job job = new Job();
            job.setId(job_id);
            employee.setJob(job);
        }
        if(dept_id != null){
            Dept dept = new Dept();
            dept.setId(dept_id);
            employee.setDept(dept);
        }
    }
}
