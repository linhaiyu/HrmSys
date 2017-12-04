package com.hr.controller;

import com.hr.common.HrmConstants;
import com.hr.common.PageModel;
import com.hr.entity.Document;
import com.hr.entity.User;
import com.hr.service.HrmService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class DocumentController {

    private final static Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * Document 增加
     */
    @RequestMapping(value = "/document/addDocument", method = RequestMethod.GET)
    public String addDocument() {
        logger.info("show showAddDocument");
        return "document/showAddDocument";
    }

    @RequestMapping(value = "/document/addDocument", method = RequestMethod.POST)
    public String addDocument(Document document, HttpSession session) throws IOException {

        logger.info("document={}", document);
        // 上传文件的存放路径
        String path = session.getServletContext().getRealPath("/upload/");
        logger.info("save file in: {}", path);

        // 将文件保存到文件系统
        String fileName = document.getFile().getOriginalFilename();
        document.getFile().transferTo(new File(path + File.separator + fileName));

        // 将文件信息存入数据库
        document.setFileName(fileName);
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        document.setUser(user);

        logger.info("saved document={}", document);
        hrmService.addDocument(document);
        return "redirect:/document/selectDocument";
    }

    /**
     * Document 删除
     */
    @RequestMapping(value = "/document/removeDocument")
    public String removeDocument(String ids) {
        logger.info("ids={}", ids);
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            hrmService.removeDocumentById(Integer.parseInt(id));
        }

        return "redirect:/document/selectDocument";
    }

    /**
     * Document 修改
     */
    @RequestMapping(value = "/document/updateDocument", method = RequestMethod.GET)
    public String updateDocument(Integer id, Model model) {
        logger.info("id={}", id);
        Document document = hrmService.findDocumentById(id);
        logger.info("target document={}", document);
        model.addAttribute("document", document);
        return "/document/showUpdateDocument";
    }

    @RequestMapping(value = "/document/updateDocument", method = RequestMethod.POST)
    public String updateDocument(Document document, HttpSession session) throws IOException {
        // 支持修改上传的文件？

        // 上传文件的存放路径
        String path = session.getServletContext().getRealPath("/upload/");
        logger.info("save file in: {}", path);

        // 将文件保存到文件系统
        String fileName = document.getFile().getOriginalFilename();
        document.getFile().transferTo(new File(path + File.separator + fileName));

        document.setFileName(fileName);
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        document.setUser(user);

        logger.info("new document={} ", document);
        hrmService.modifyDocument(document);
        return "redirect:/document/selectDocument";
    }

    /**
     * Document 查找
     */
    @RequestMapping(value = "/document/selectDocument")
    public String selectDocument(Model model, Integer pageIndex, Document document) {
        logger.info("pageIndex = {}, document = {}.", pageIndex, document);
        PageModel pageModel = new PageModel();

        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        List<Document> documents = hrmService.findDocument(document, pageModel);
        model.addAttribute("documents", documents);
        model.addAttribute("pageModel", pageModel);

        return "document/document";
    }

    /**
     * Document 下载
     */
    @RequestMapping(value = "/document/downLoad")
    public ResponseEntity<byte[]> downLoad(Integer id, HttpSession session) throws IOException {
        logger.info("id={}", id);

        // 根据Id查询文档
        Document target = hrmService.findDocumentById(id);
        logger.info("target={}",target);
        String fileName = target.getFileName();

        // 上传文件的路径
        String path = session.getServletContext().getRealPath("/upload/");

        // 根据路径和文件名创建文件对象
        File file = new File(path + File.separator + fileName);

        // 创建SpringFramework的 HttpHeader对象
        HttpHeaders headers = new HttpHeaders();

        // 下载显示的文件名，要解决中文名称乱码问题
        String downloadFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");

        // 通知浏览器以attachment（下载方式）打开文件
        headers.setContentDispositionFormData("attachment", downloadFileName);

        // application/octet-stream: 二进制流数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}
