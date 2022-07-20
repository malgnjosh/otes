/*
 * JSP generated by Resin-3.1.15 (built Mon, 13 Oct 2014 06:45:33 PDT)
 */

package _jsp._mypage;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import dao.*;
import malgnsoft.db.*;
import malgnsoft.util.*;

public class _course_0list__jsp extends com.caucho.jsp.JavaPage
{
  private static final java.util.HashMap<String,java.lang.reflect.Method> _jsp_functionMap = new java.util.HashMap<String,java.lang.reflect.Method>();
  private boolean _caucho_isDead;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.WebApp _jsp_application = _caucho_getApplication();
    javax.servlet.ServletContext application = _jsp_application;
    com.caucho.jsp.PageContextImpl pageContext = _jsp_application.getJspApplicationContext().allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);
    javax.servlet.jsp.PageContext _jsp_parentContext = pageContext;
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    final javax.el.ELContext _jsp_env = pageContext.getELContext();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    response.setContentType("text/html; charset=utf-8");
    request.setCharacterEncoding("UTF-8");
    try {
      

//\uac1d\uccb4
Malgn m = new Malgn(request, response, out);

Form f = new Form("form1");
try { f.setRequest(request); }
catch(Exception ex) { out.print("Overflow file size. - " + ex.getMessage()); return; }

Page p = new Page();
p.setRequest(request);
p.setPageContext(pageContext);
p.setWriter(out);

//\ubcc0\uc218
int userId = 0;
String loginId = "";
String userName = "";
String userEmail = "";
String userType = "";
String sysToday = m.time("yyyyMMdd");
String sysNow = m.time("yyyyMMddHHmmss");
boolean loginBlock = false;

//\ub85c\uadf8\uc778 \uc5ec\ubd80 \uccb4\ud06c
Auth auth = new Auth(request, response);
auth.loginURL = "/member/login.jsp";
auth.keyName = "ENTER2022";

if(auth.isValid()) {
    userId = auth.getInt("USERID");
    loginId = auth.getString("LOGINID");
    userName = auth.getString("USERNAME");
    userEmail = auth.getString("EMAIL");
    userType = auth.getString("TYPE");
    loginBlock = true;
}

p.setVar("login_block", loginBlock);
p.setVar("SYS_TITLE", "<ENTER \"_\"> \uc624\ud508 \ub354 \uc774\ub7ec\ub2dd\uc0ac\uc774\ud2b8!");
p.setVar("SYS_LOGINID", loginId);
p.setVar("SYS_USERNAME", userName);
p.setVar("SYS_USEREMAIL", userEmail);
p.setVar("SYS_USERKIND", userType);
p.setVar("SYS_TODAY", sysToday);
p.setVar("SYS_NOW", sysNow);

      

//\ub85c\uadf8\uc778
if(0 == userId) { auth.loginForm(); return; }

//\ucc44\ub110
String ch = "mypage";

//\uc815\ubcf4-\ud68c\uc6d0
UserDao user = new UserDao();
DataSet uinfo = user.find("id = ? AND status = 1", new Object[] {userId});//\uc218\uc815
if(!uinfo.next()) { m.jsError("\ud574\ub2f9 \ud68c\uc6d0 \uc815\ubcf4\uac00 \uc5c6\uc2b5\ub2c8\ub2e4."); return; }


      

//\uac1d\uccb4
CourseUserDao courseUser = new CourseUserDao();
CourseDao course = new CourseDao();

//\uc218\uac15\uc911\uc778 \uacfc\uc815
DataSet list1 = courseUser.query(
    " SELECT a.id, c.course_type, c.course_nm, a.start_date, a.end_date, a.progress_ratio "
    + " FROM " + courseUser.table + " a "
    + " INNER JOIN " + course.table + " c ON a.course_id = c.id AND c.status = 1 "
    + " WHERE a.user_id = " + userId + " AND a.status = 1 AND end_date >= '" + sysToday + "' "
    + " ORDER BY a.reg_date DESC "
);

//\uc218\uac15\uc911\uc778 \uacfc\uc815 \ud3ec\uba67\ud305
while(list1.next()) {
    list1.put("progress_ratio", m.nf(list1.d("progress_ratio"), 1));
    list1.put("start_date_conv", m.time("yyyy.MM.dd", list1.s("start_date")));
    list1.put("end_date_conv", m.time("yyyy.MM.dd", list1.s("end_date")));
}

//\uc885\ub8cc\ub41c \uacfc\uc815
DataSet list2 = courseUser.query(
    " SELECT a.id, c.course_type, c.course_nm, c.id as course_id, a.start_date, a.end_date, a.progress_ratio, a.complete_status "
    + " FROM " + courseUser.table + " a "
    + " INNER JOIN " + course.table + " c ON a.course_id = c.id AND c.status = 1 "
    + " WHERE a.user_id = " + userId + " AND a.status = 1 AND end_date < '" + sysToday + "' "
    + " ORDER BY a.reg_date DESC "
);

//\uc885\ub8cc\ub41c \uacfc\uc815 \ud3ec\uba67\ud305
while(list2.next()) {
    //\ubcc0\uc218
    String completeStatusConv = "";
    boolean completeStatus = false;

    list2.put("progress_ratio", m.nf(list2.d("progress_ratio"), 1));
    list2.put("start_date_conv", m.time("yyyy.MM.dd", list2.s("start_date")));
    list2.put("end_date_conv", m.time("yyyy.MM.dd", list2.s("end_date")));

    if("N".equals(list2.s("complete_status"))) {
        completeStatusConv = "\ubbf8\uc218\ub8cc";
    } else if("Y".equals(list2.s("complete_status"))) {
        completeStatusConv = "\uc218\ub8cc";
        completeStatus = true;
    } else {
        completeStatusConv = "\ubbf8\ucc98\ub9ac";
    }
    list2.put("status_conv", completeStatusConv);
    list2.put("complete_status", completeStatus);
}

//\ucd9c\ub825
p.setLayout(ch);
p.setBody("mypage.course_list");
p.setLoop("list1", list1);//\uc218\uac15\uc911\uc778 \uacfc\uc815
p.setLoop("list2", list2);//\uc885\ub8cc\ub41c \uacfc\uc815
p.setVar("id", userId);
//p.setVar("complete_status", completeStatus);
p.display();


    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      _jsp_application.getJspApplicationContext().freePageContext(pageContext);
    }
  }

  private java.util.ArrayList _caucho_depends = new java.util.ArrayList();

  public java.util.ArrayList _caucho_getDependList()
  {
    return _caucho_depends;
  }

  public void _caucho_addDepend(com.caucho.vfs.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;
    if (com.caucho.server.util.CauchoSystem.getVersionId() != 6749855747778707107L)
      return true;
    for (int i = _caucho_depends.size() - 1; i >= 0; i--) {
      com.caucho.vfs.Dependency depend;
      depend = (com.caucho.vfs.Dependency) _caucho_depends.get(i);
      if (depend.isModified())
        return true;
    }
    return false;
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public java.util.HashMap<String,java.lang.reflect.Method> _caucho_getFunctionMap()
  {
    return _jsp_functionMap;
  }

  public void init(ServletConfig config)
    throws ServletException
  {
    com.caucho.server.webapp.WebApp webApp
      = (com.caucho.server.webapp.WebApp) config.getServletContext();
    super.init(config);
    com.caucho.jsp.TaglibManager manager = webApp.getJspApplicationContext().getTaglibManager();
    com.caucho.jsp.PageContextImpl pageContext = new com.caucho.jsp.PageContextImpl(webApp, this);
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.server.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("mypage/course_list.jsp"), 2231416187305020749L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("mypage/init.jsp"), 3695357507792775531L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("init.jsp"), -2671751158375180785L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }
}
