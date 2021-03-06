<%--
  Created by IntelliJ IDEA.
  User: abc
  Date: 2017/11/30
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>人事管理系统 ——修改培训</title>
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
            /** 表单提交的校验 */
            $("#trainForm").submit(function(){
                var title = $("#title");
                var remark = $("#remark");
                var msg = "";
                if ($.trim(title.val()) == ""){
                    msg = "培训标题不能为空！";
                    title.focus();
                }
                else if($.trim(title.val()).length <= 2){
                    msg = "请输入两个字符以上的标题！";
                    title.focus();
                }
                else if($.trim(remark.val()) == ""){
                    msg = "描述内容不能为空！";
                    remark.focus();
                }
                if (msg != ""){
                    $.ligerDialog.error(msg);
                    return false;
                }else{
                    return true;
                }
                $("#trainForm").submit();
            });
        });
    </script>
</head>

<body>
    <!-- 导航 -->
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr><td height="10"></td></tr>
        <tr>
            <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
            <td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：培训管理 &gt; 培训修改</td>
            <td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
        </tr>
    </table>

    <table width="100%" height="90%" border="0" cellpadding="10" cellspacing="0" class="main_tabbor">
        <tr valign="top">
            <td>
                <form id="trainForm" name="trainForm" action="${ctx }/train/updateTrain" method="post">
                    <input type="hidden" name="id" value="${train.id }">
                    <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                        <tr><td class="font3 fftd">
                            培训标题：<input type="text" name="title" size="30" id="title" value="${train.title }"/>
                            <span style="color: #ff0000;"></span>
                        </td>
                        </tr>
                        <tr><td class="main_tdbor"></td></tr>

                        <tr><td class="font3 fftd">培训内容：<br/>
                            <textarea name="remark" cols="88" rows="11" id="remark" >${train.remark }</textarea>
                        </td></tr>
                        <tr><td class="main_tdbor"></td></tr>
                        <td class="font3 fftd">教师：
                            <select name="employee_id" style="width:143px;">
                                <option value="0">--请选择教师--</option>
                                <c:forEach items="${requestScope.employees }" var="employee">
                                    <option value="${employee.id }">${employee.name }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <tr><td class="main_tdbor"></td></tr>

                        <tr><td class="font3 fftd">
                            <input type="submit" value="修改">
                            <input type="reset" value="重置">
                        </td></tr>
                        <tr><td class="main_tdbor"></td></tr>

                    </table>
                </form>
            </td>
        </tr>
    </table>
    <div style="height:10px;"></div>

</body>
</html>
