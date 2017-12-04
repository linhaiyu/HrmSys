package com.hr.service;

import com.hr.common.PageModel;
import com.hr.entity.*;
import com.hr.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("hrmService")
public class HrmServiceImpl implements HrmService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DocumentMapper documentMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private TrainMapper trainMapper;

    /*****************User 接口实现*************************************/
    @Override
    public User login(String loginname, String password) {
        return userMapper.selectByLoginNameAndPassword(loginname, password);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);

        int recordCount = userMapper.count(params);
        pageModel.setRecordCount(recordCount);

        if (recordCount > 0) {
            // 分页查询数据
            params.put("pageModel", pageModel);
        }

        List<User> users = userMapper.selectByPage(params);
        return users;
    }

    @Override
    public int getTotalUserCount() {
        return userMapper.getTotalCount();
    }

    @Override
    public void removeUserById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void modifyUser(User user) {
        userMapper.update(user);
    }

    @Override
    public void addUser(User user) {
        userMapper.save(user);
    }

    /*****************Employee 接口实现*************************************/
    @Override
    public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("employee", employee);

        int recordCount = employeeMapper.count(params);
        pageModel.setRecordCount(recordCount);

        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }

        List<Employee> employees = employeeMapper.selectByPage(params);
        return employees;
    }

    @Override
    public int getTotalEmployeeCount() {
        return employeeMapper.getTotalCount();
    }

    @Override
    public void removeEmployeeById(Integer id) {
        employeeMapper.deleteById(id);
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeMapper.selectById(id);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMapper.save(employee);
    }

    @Override
    public void modifyEmployee(Employee employee) {
        employeeMapper.update(employee);
    }

    @Override
    public List<Employee> findAllEmployee(){
        return employeeMapper.selectAllEmployee();
    }

    /*****************Dept 接口实现*************************************/
    @Override
    public List<Dept> findDept(Dept dept, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("dept", dept);

        int recordCount = deptMapper.count(params);
        pageModel.setRecordCount(recordCount);

        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }

        List<Dept> depts = deptMapper.selectByPage(params);
        return depts;
    }

    @Override
    public List<Dept> findAllDept() {
        return deptMapper.selectAllDept();
    }

    @Override
    public int getTotalDeptCount() {
        return deptMapper.getTotalCount();
    }

    @Override
    public void removeDeptById(Integer id)  throws Exception {
        deptMapper.deleteById(id);
    }

    @Override
    public void addDept(Dept dept) {
        deptMapper.save(dept);
    }

    @Override
    public Dept findDeptById(Integer id) {
        return deptMapper.selectById(id);
    }

    @Override
    public void modifyDept(Dept dept) {
        deptMapper.update(dept);
    }

    /*****************Job 接口实现*************************************/
    @Override
    public List<Job> findAllJob() {
        return jobMapper.selectAllJob();
    }

    @Override
    public int getTotalJobCount() {
        return jobMapper.getTotalCount();
    }

    @Override
    public List<Job> findJob(Job job, PageModel pageModel) {

        Map<String, Object> params = new HashMap<>();
        params.put("job", job);
        int recordCount = jobMapper.count(params);
        pageModel.setRecordCount(recordCount);

        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }

        List<Job> jobs = jobMapper.selectByPage(params);

        return jobs;
    }

    @Override
    public void removeJobById(Integer id) throws Exception {
        jobMapper.deleteById(id);
    }

    @Override
    public void addJob(Job job) {
        jobMapper.save(job);
    }

    @Override
    public Job findJobById(Integer id) {
        return jobMapper.selectById(id);
    }

    @Override
    public void modifyJob(Job job) {
        jobMapper.update(job);
    }

    /*****************Notice 接口实现*************************************/
    @Override
    public List<Notice> findNotice(Notice notice, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("notice", notice);

        int recordCount = noticeMapper.count(params);
        pageModel.setRecordCount(recordCount);

        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }

        List<Notice> notices = noticeMapper.selectByPage(params);
        return notices;
    }

    @Override
    public Notice findNoticeById(Integer id) {
        return noticeMapper.selectById(id);
    }

    @Override
    public int getTotalNoticeCount() {
        return noticeMapper.getTotalCount();
    }

    @Override
    public void removeNoticeById(Integer id) {
        noticeMapper.deleteById(id);
    }

    @Override
    public void addNotice(Notice notice) {
        noticeMapper.save(notice);
    }

    @Override
    public void modifyNotice(Notice notice) {
        noticeMapper.update(notice);
    }

    /*****************Document 接口实现*************************************/
    @Override
    public List<Document> findDocument(Document document, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("document", document);

        int recordCount = documentMapper.count(params);
        pageModel.setRecordCount(recordCount);

        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }

        return documentMapper.selectByPage(params);
    }

    @Override
    public void addDocument(Document document) {
        documentMapper.save(document);
    }

    @Override
    public Document findDocumentById(Integer id) {
        return documentMapper.selectById(id);
    }

    @Override
    public int getTotalDocumentCount() {
        return documentMapper.getTotalCount();
    }

    @Override
    public void removeDocumentById(Integer id) {
        documentMapper.deleteById(id);
    }

    @Override
    public void modifyDocument(Document document) {
        documentMapper.update(document);
    }

    /*****************Train 接口实现*************************************/
    @Override
    public void addTrain(Train train) {
        trainMapper.save(train);
    }

    @Override
    public void removeTrainById(Integer id) {
        trainMapper.deleteById(id);
    }

    @Override
    public void modifyTrain(Train train) {
        trainMapper.update(train);
    }

    @Override
    public List<Train> findTrain(Train train, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("train", train);

        int recordCount = trainMapper.count(params);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }

        return trainMapper.selectByPage(params);
    }

    @Override
    public Train findTrainById(Integer id) {
        return trainMapper.selectById(id);
    }

    @Override
    public int getTotalTrain() {
        return trainMapper.getTotalCount();
    }
}
