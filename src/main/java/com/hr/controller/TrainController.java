package com.hr.controller;

import com.hr.common.PageModel;
import com.hr.entity.Employee;
import com.hr.entity.Train;
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
public class TrainController {

    private final static Logger logger = LoggerFactory.getLogger(TrainController.class);

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 培训 添加
     */
    @RequestMapping(value = "/train/addTrain", method = RequestMethod.GET)
    public String addTrain(Model model) {
        logger.info("show showAddTrain");
        // 查询所有的employee，加入model
        List<Employee> employees = hrmService.findAllEmployee();
        model.addAttribute("employees", employees);
        return "train/showAddTrain";
    }

    @RequestMapping(value = "/train/addTrain", method = RequestMethod.POST)
    public String addTrain(Train train, Integer employee_id) {
        genericAssociation(employee_id, train);
        logger.info("train={}", train);

        // 存入数据库
        hrmService.addTrain(train);
        return "redirect:/train/selectTrain";
    }

    /**
     * 培训 删除
     */
    @RequestMapping(value = "/train/removeTrain")
    public String removeTrain(String ids) {
        logger.info("ids={}", ids);
        String[] idArray = ids.split(",");
        for (String id : idArray ) {
            hrmService.removeTrainById(Integer.parseInt(id));
        }

        return "redirect:/train/selectTrain";
    }

    /** 培训 修改 */
    @RequestMapping(value = "/train/updateTrain", method = RequestMethod.GET)
    public String updateTrain(Integer id, Model model) {
        Train target = hrmService.findTrainById(id);

        // 修改教师
        List<Employee> employees = hrmService.findAllEmployee();
        model.addAttribute("employees", employees);

        logger.info("target={}",target);
        model.addAttribute("train", target);
        return "train/showUpdateTrain";
    }

    @RequestMapping(value = "/train/updateTrain", method = RequestMethod.POST)
    public String updateTrain(Train train, Integer employee_id) {
        genericAssociation(employee_id, train);
        logger.info("new train={}", train);

        hrmService.modifyTrain(train);
        return "redirect:/train/selectTrain";
    }

    /**
     * 培训 查询
     */
    @RequestMapping(value = "/train/selectTrain")
    public String selectTrain(Train train, Integer pageIndex, Integer employee_id, Model model) {

        logger.info("pageIndex={}, employee_id={}, train={}", pageIndex, employee_id, train);

        genericAssociation(employee_id, train);

        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        List<Train> trains = hrmService.findTrain(train, pageModel);

        // 支持按教师查询
        List<Employee> employees = hrmService.findAllEmployee();
        model.addAttribute("employees", employees);

        model.addAttribute("trains", trains);
        model.addAttribute("pageModel", pageModel);
        return "/train/train";
    }


    /**
     * 为 Train对象填充 employee对象
     * @param employee_id employee的id
     * @param train  Train对象
     */
    private void genericAssociation(Integer employee_id, Train train) {
        if (employee_id != null) {
            Employee employee = new Employee();
            employee.setId(employee_id);
            train.setEmployee(employee);
        }
    }
}
