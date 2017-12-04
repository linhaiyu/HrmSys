package com.hr.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class PagerTag extends SimpleTagSupport {
    /** 定义请求URL中的占位符常量 */
    private static final String TAG = "{0}";
    /** 当前页码 */
    private int pageIndex;
    /** 每页显示的数量 */
    private int pageSize;
    /** 总记录条数 */
    private int recordCount;
    /** 请求URL page.action?pageIndex={0}*/
    private String submitUrl;
    /** 样式 */
    private String style = "sabrosus";

    /** 定义总页数 */
    private int totalPage = 0;

    private final static Logger logger = LoggerFactory.getLogger(PagerTag.class);

    @Override
    public void doTag() throws JspException, IOException {
        StringBuilder res = new StringBuilder();
        StringBuilder str = new StringBuilder();

        if (recordCount > 0) {
            // 计算总页数
            totalPage = (this.recordCount - 1)/this.pageSize + 1;

            if (this.pageIndex == 1) {
                // 当前页是首页，“上一页”不使能
                str.append("<span class='disabled'>上一页</span>");

                // 计算中间的页码
                this.calcPage(str);

                // "下一页" 是否需要使能
                if (this.pageIndex == totalPage) {
                    str.append("<span class='disabled'>下一页</span>");
                } else {
                    String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
                    str.append("<a href='" + tempUrl + "'>下一页</a>");
                }
            } else if (this.pageIndex == totalPage) {
                // 当前页是尾页
                String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
                str.append("<a href='"+ tempUrl +"'>上一页</a>");

                // 计算中间的页码
                this.calcPage(str);

                str.append("<span class='disabled'>下一页</span>");
            } else {
                // 当前页为中间页面
                String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
                str.append("<a href='"+ tempUrl +"'>上一页</a>");

                // 计算中间的页码
                this.calcPage(str);

                tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
                str.append("<a href='" + tempUrl + "'>下一页</a>");
            }

            /** 拼接其它的页面信息*/
            res.append("<table width='100%' align='center' style='font-size:13px;' class='"+ style +"'>");
            res.append("<tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>" + str.toString());
            res.append("&nbsp;跳转到&nbsp;&nbsp;<input style='text-align: center;BORDER-RIGHT: #aaaadd 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #aaaadd 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #aaaadd 1px solid; COLOR: #000099; PADDING-TOP: 2px; BORDER-BOTTOM: #aaaadd 1px solid; TEXT-DECORATION: none' type='text' size='2' id='pager_jump_page_size'/>");
            res.append("&nbsp;<input type='button' style='text-align: center;BORDER-RIGHT: #dedfde 1px solid; PADDING-RIGHT: 6px; BACKGROUND-POSITION: 50% bottom; BORDER-TOP: #dedfde 1px solid; PADDING-LEFT: 6px; PADDING-BOTTOM: 2px; BORDER-LEFT: #dedfde 1px solid; COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; BORDER-BOTTOM: #dedfde 1px solid; TEXT-DECORATION: none' value='确定' id='pager_jump_btn'/>");
            res.append("</td></tr>");
            res.append("<tr align='center'><td style='font-size:13px;'><tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>");
            /** 开始条数 */
            int startNum = (this.pageIndex - 1) * this.pageSize + 1;
            /** 结束条数 */
            int endNum = (this.pageIndex == this.totalPage) ? this.recordCount : this.pageIndex * this.pageSize;

            res.append("总共<font color='red'>"+ this.recordCount +"</font>条记录，当前显示"+ startNum +"-"+ endNum +"条记录。");
            res.append("</td></tr>");
            res.append("</table>");
            res.append("<script type='text/javascript'>");
            res.append("   document.getElementById('pager_jump_btn').onclick = function(){");
            res.append("      var page_size = document.getElementById('pager_jump_page_size').value;");
            res.append("      if (!/^[1-9]\\d*$/.test(page_size) || page_size < 1 || page_size > "+ this.totalPage +"){");
            res.append("          alert('请输入[1-"+ this.totalPage +"]之间的页码！');");
            res.append("      }else{");
            res.append("         var submit_url = '" + this.submitUrl + "';");
            res.append("         window.location = submit_url.replace('"+ TAG +"', page_size);");
            res.append("      }");
            res.append("}");
            res.append("</script>");

            /**  拼接出来的语句大概如下（请忽略style）
            <table>
                <tr><td>
                    str.toString()) 跳转到 <input type='text' id='pager_jump_page_size'/>
                    <input type='button' value='确定' id='pager_jump_btn'/>
                </td></tr>
                <tr><td>
                <tr><td>
                    "总共<font color='red'>"+ this.recordCount +"</font>条记录，当前显示"+ startNum +"-"+ endNum +"条记录。
                </td></tr>
            </table>

            <script type='text/javascript'>
               document.getElementById('pager_jump_btn').onclick = function(){
                  var page_size = document.getElementById('pager_jump_page_size').value;
                  if (!/^[1-9]\\d*$/.test(page_size) || page_size < 1 || page_size > "+ this.totalPage +"){
                      alert('请输入[1-"+ this.totalPage +"]之间的页码！');
                  }else{
                     var submit_url = this.submitUrl ;
                     window.location = submit_url.replace('"+ TAG +"', page_size);
                  }
                }
            </script> */
        } else {
            res.append("<table align='center' style='font-size:13px;'><tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>总共<font color='red'>0</font>条记录，当前显示0-0条记录。</td></tr></table>");
        }

        this.getJspContext().getOut().print(res.toString());
    }


    /** 计算中间页码*/
    private void calcPage(StringBuilder str) {

        logger.info("this.totalPage = {}, this.pageIndex = {}", this.totalPage, this.pageIndex);
        if (this.totalPage <= 11 ) {
            // 一次显示全部页码
            for (int i = 1; i <= this.totalPage; i++) {
                if (this.pageIndex == i) {
                    str.append("<span class='current'>" + i + "</span>");
                } else {
                    String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
                    str.append("<a href='" + tempUrl + "'>" + i + "</a>");
                }
            }
        } else {
            if (this.pageIndex <= 8) {
                // 当前页靠近首页
                for (int i = 1; i <= 10 ; i++) {
                    if (this.pageIndex == i) {
                        str.append("<span class='current'>" + i + "</span>");
                    } else {
                        String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
                        str.append("<a href='" + tempUrl + "'>" + i + "</a>");
                    }
                }

                str.append("...");
                String tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
                str.append("<a href='" + tempUrl + "'>" + this.totalPage + "</a>");
            } else if (this.pageIndex + 8 >= this.totalPage) {
                // 当前页靠近尾页
                String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
                str.append("<a href='" + tempUrl + "'>1</a>");
                str.append("...");

                for (int i = this.totalPage - 10; i <= this.totalPage ; i++) {
                    if (this.pageIndex == i) {
                        str.append("<span class='current'>" + i + "</span>");
                    } else {
                        tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
                        str.append("<a href='" + tempUrl + "'>" + i + "</a>");
                    }
                }
            } else {
                // 当前页在中间
                String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
                str.append("<a href='" + tempUrl + "'>1</a>");
                str.append("...");

                for (int i = this.pageIndex - 4; i <= this.pageIndex + 4 ; i++) {
                    if (this.pageIndex == i) {
                        str.append("<span class='current'>" + i + "</span>");
                    } else {
                        tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
                        str.append("<a href='" + tempUrl + "'>" + i + "</a>");
                    }
                }

                str.append("...");
                tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
                str.append("<a href='" + tempUrl + "'>" + this.totalPage + "</a>");
            }
        }
    }


    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public void setSubmitUrl(String submitUrl) {
        this.submitUrl = submitUrl;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
