<%--
  Created by IntelliJ IDEA.
  User: abc
  Date: 2017/11/30
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<html>
<head>
    <title>人事管理系统 ——培训管理</title>
    <link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
    <link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
    <script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">
        $(function(){
            /** 获取选中的项目 */
            var boxs  = $("input[type='checkbox'][id^='box_']");

            /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
            $("tr[id^='data_']").hover(function(){
                $(this).css("backgroundColor","#ecffd3");
            },function(){
                $(this).css("backgroundColor","#ffffff");
            })


            /** 删除员工绑定点击事件 */
            $("#delete").click(function(){
                /** 获取到用户选中的复选框  */
                var checkedBoxs = boxs.filter(":checked");
                if(checkedBoxs.length < 1){
                    $.ligerDialog.error("请选择一个需要删除的培训课程！");
                }else{
                    /** 得到用户选中的所有的需要删除的id */
                    var ids = checkedBoxs.map(function(){
                        return this.value;
                    })

                    $.ligerDialog.confirm("确认要删除吗?","删除课程",function(r){
                        if(r){
                            // 发送请求
                            console.log("ids: " + ids.get());
                            window.location = "${ctx }/train/removeTrain?ids=" + ids.get();
                        }
                    });
                }
            })
        })
    </script>
</head>
<body>
    <!-- 导航 -->
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr><td height="10"></td></tr>
        <tr>
            <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
            <td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：培训管理 &gt; 培训查询</td>
            <td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
        </tr>
    </table>

    <table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
        <!-- 查询区  -->
        <tr valign="top">
            <td height="30">
                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                    <tr>
                        <td class="fftd">
                            <form name="trainForm" method="post" id="trainForm" action="${ctx}/train/selectTrain">
                                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td class="font3">
                                            培训名称：<input type="text" name="title">
                                            培训内容：<input type="text" name="remark">
                                        </td>
                                        <td class="font3 fftd">教师：
                                            <select name="employee_id" style="width:143px;">
                                                <option value="0">--请选择教师--</option>
                                                <c:forEach items="${requestScope.employees }" var="employee">
                                                    <option value="${employee.id }">${employee.name }</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="submit" value="搜索"/>
                                            <input id="delete" type="button" value="删除"/>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>

        <!-- 数据展示区 -->
        <tr valign="top">
            <td height="20">
                <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
                    <tr class="main_trbg_tit" align="center">
                        <td><input type="checkbox" name="checkAll" id="checkAll"></td>
                        <td>培训名称</td>
                        <td>描述</td>
                        <td>教师</td>
                        <td>创建时间</td>
                        <td align="center">操作</td>
                    </tr>
                    <c:forEach items="${requestScope.trains}" var="train" varStatus="stat">
                        <tr id="data_${stat.index}" align="center" class="main_trbg">
                            <td><input type="checkbox" id="box_${stat.index}" value="${train.id}"></td>
                            <td>${train.title}</td>
                            <td>${train.remark}</td>
                            <td>${train.employee.name}</td>
                            <td><f:formatDate value="${train.createDate}" type="date" dateStyle="long"/></td>
                            <td align="center" width="40px;"><a href="${ctx}/train/updateTrain?id=${train.id}">
                                <img title="修改" src="${ctx}/images/update.gif"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
        <!-- 分页标签 -->
        <tr valign="top">
            <td align="center" class="font3">
                <fkjava:pager
                        pageIndex="${requestScope.pageModel.pageIndex}"
                        pageSize="${requestScope.pageModel.pageSize}"
                        recordCount="${requestScope.pageModel.recordCount}"
                        style="digg"
                        submitUrl="${ctx}/train/selectTrain?pageIndex={0}"/>
            </td>
        </tr>
    </table>

    <div style="height:10px;"></div>

</body>
</html>
