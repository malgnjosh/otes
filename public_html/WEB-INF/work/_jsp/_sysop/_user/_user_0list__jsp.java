/*
 * JSP generated by Resin-3.1.15 (built Mon, 13 Oct 2014 06:45:33 PDT)
 */

package _jsp._sysop._user;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import dao.*;
import malgnsoft.db.*;
import malgnsoft.util.*;

public class _user_0list__jsp extends com.caucho.jsp.JavaPage
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
      
//
//\ubcc0\uc218
String docRoot = Config.getDocRoot();
String tplRoot = docRoot + "/sysop/html";

//\uac1d\uccb4
Malgn m = new Malgn(request, response, out);

Form f = new Form("form1");
f.setRequest(request);

Page p = new Page(tplRoot);
p.setRequest(request);
p.setPageContext(pageContext);
p.setWriter(out);

// \uae30\ubcf8 \uad00\ub9ac\uc790 \uc815\ubcf4
int userId = 0;
String loginId = "";
String userName = "";
String userType = "";

// \ub85c\uadf8\uc778 \uc5ec\ubd80 \uccb4\ud06c
Auth auth = new Auth(request, response);
auth.loginURL = "/sysop/main/login.jsp";
auth.keyName = "ENTER2022BO";
if(auth.isValid()) {
    userId = m.parseInt(auth.getString("ID"));
    loginId = auth.getString("LOGINID");
    userName = auth.getString("NAME");
    userType = auth.getString("TYPE");

} else {
    if(request.getRequestURI().indexOf("/main/login.jsp") == -1) { //\ub85c\uadf8\uc778 \ud398\uc774\uc9c0\uba74 \uc81c\uc678
        m.jsReplace(auth.loginURL, "top");
        return;
    }
}

p.setVar("SYS_TITLE", "[\uad00\ub9ac\uc790] enter \uc624\ud508 \ub354 \uc774\ub7ec\ub2dd \uc0ac\uc774\ud2b8");


      

String ch = "sysop";
boolean isSuperAdmin = "S".equals(userType);


      

//\uac1d\uccb4
UserDao user = new UserDao();
CourseUserDao courseUser = new CourseUserDao();

//\ud3fc\uccb4\ud06c
f.addElement("s_user_type", null, null);
f.addElement("s_status", null, null);
f.addElement("s_field", null, null);
f.addElement("s_keyword", null, null);
f.addElement("s_listnum", null, null);

//\ubaa9\ub85d
ListManager lm = new ListManager();
lm.setRequest(request);
lm.setListNum("excel".equals(m.rs("mode")) ? 1000 : f.getInt("s_listnum", 20));
lm.setTable(user.table + " a ");
lm.setFields(
    "a.* "
    + ", (SELECT COUNT(*) FROM "
    + courseUser.table
    + " WHERE user_id = a.id AND status != -1) course_cnt "
);
lm.addWhere("a.status != -1");
lm.addSearch("a.user_type", f.get("s_user_type"));
lm.addSearch("a.status", f.get("s_status"));
if(!"".equals(f.get("s_field"))) lm.addSearch(f.get("s_field"), f.get("s_keyword"), "LIKE");
else lm.addSearch("a.login_id, a.user_nm", f.get("s_keyword"), "LIKE");
lm.setOrderBy(!"".equals(m.rs("ord")) ? m.rs("ord") : "a.id DESC");

//\ud3ec\ub9f7\ud305
DataSet list = lm.getDataSet();

while(list.next()) {
    list.put("conn_date_conv", m.time("yyyy.MM.dd HH:mm", list.s("conn_date")));
    list.put("reg_date_conv", m.time("yyyy.MM.dd", list.s("reg_date")));
    list.put("status_conv", m.getItem(list.i("status"), user.statusList));
    list.put("user_type_conv", m.getItem(list.s("user_type"), user.types));
}

//\uc5d1\uc140
if("excel".equals(m.rs("mode"))) {
    String[] cols = {"__ord=>No", "user_type_conv=>\uad6c\ubd84", "user_nm=>\uc774\ub984", "login_id=>\ub85c\uadf8\uc778\uc544\uc774\ub514", "mobile=>\ud734\ub300\uc804\ud654\ubc88\ud638", "email=>\uc774\uba54\uc77c", "course_cnt=>\uc218\uac15\uac74\uc218", "conn_date_conv=>\ucd5c\uadfc\uc811\uc18d\uc77c", "reg_date_conv=>\uac00\uc785\uc77c", "status_conv=>\uc0c1\ud0dc"};

    ExcelWriter ex = new ExcelWriter(response, "\ud68c\uc6d0\uad00\ub9ac(" + m.time("yyyy-MM-dd") + ").xls");
    ex.setData(list, cols);
    ex.write();
    return;
}

//\ucd9c\ub825
p.setLayout(ch);
p.setBody("user.user_list");
p.setVar("p_title", "\ud68c\uc6d0\uad00\ub9ac");
p.setVar("query", m.qs());
p.setVar("list_query", m.qs("id"));
p.setVar("form_script", f.getScript());

p.setLoop("list", list);
p.setVar("pagebar", lm.getPaging());
p.setVar("list_total", lm.getTotalString());

p.setLoop("types", m.arr2loop(user.types));
p.setLoop("status_list", m.arr2loop(user.statusList));
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
    depend = new com.caucho.vfs.Depend(appDir.lookup("sysop/user/user_list.jsp"), 6384828105067796877L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("sysop/user/init.jsp"), -1760606300909008588L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("sysop/init.jsp"), -2253010914700824916L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }
}
