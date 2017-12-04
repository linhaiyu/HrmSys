package com.hr.service;

import com.hr.common.PageModel;
import com.hr.entity.*;

import java.util.List;

public interface HrmService {
    /** 登录 */
    User login(String loginname, String password);

    /** User 增删改查*/
    void addUser(User user);
    void removeUserById(Integer id);
    void modifyUser(User user);
    User findUserById(Integer id);
    List<User> findUser(User user, PageModel pageModel);
    int getTotalUserCount();

    /** Employee 增删改查*/
    void addEmployee(Employee employee);
    void removeEmployeeById(Integer id);
    void modifyEmployee(Employee employee);
    Employee findEmployeeById(Integer id);
    List<Employee> findEmployee(Employee employee, PageModel pageModel);
    int getTotalEmployeeCount();
    List<Employee> findAllEmployee();

    /** Dept 增删改查*/
    void removeDeptById(Integer id) throws Exception;
    void addDept(Dept dept);
    void modifyDept(Dept dept);
    List<Dept> findDept(Dept dept, PageModel pageModel);
    Dept findDeptById(Integer id);
    List<Dept> findAllDept();
    int getTotalDeptCount();

    /** Job 增删改查*/
    void addJob(Job job);
    void removeJobById(Integer id) throws Exception;
    void modifyJob(Job job);
    List<Job> findJob(Job job, PageModel pageModel);
    Job findJobById(Integer id);
    List<Job> findAllJob();
    int getTotalJobCount();

    /** Notice 增删改查*/
    void addNotice(Notice notice);
    void removeNoticeById(Integer id);
    void modifyNotice(Notice notice);
    List<Notice> findNotice(Notice notice, PageModel pageModel);
    Notice findNoticeById(Integer id);
    int getTotalNoticeCount();

    /** Document 增删改查*/
    void addDocument(Document document);
    void removeDocumentById(Integer id);
    void modifyDocument(Document document);
    List<Document> findDocument(Document document, PageModel pageModel);
    Document findDocumentById(Integer id);
    int getTotalDocumentCount();

    /**
     * Train 增删改查
     */
    void addTrain(Train train);

    void removeTrainById(Integer id);

    void modifyTrain(Train train);

    List<Train> findTrain(Train train, PageModel pageModel);

    Train findTrainById(Integer id);

    int getTotalTrain();


}
