/*
 * JSP generated by Resin-3.1.15 (built Mon, 13 Oct 2014 06:45:33 PDT)
 */

package _jsp._sysop._content;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import dao.*;
import malgnsoft.db.*;
import malgnsoft.util.*;

public class _lesson_0insert__jsp extends com.caucho.jsp.JavaPage
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


      

//\uae30\ubcf8\ud0a4
int cid = m.ri("cid");
if(cid == 0) { m.jsError("\uae30\ubcf8\ud0a4\ub294 \ubc18\ub4dc\uc2dc \uc9c0\uc815\ud574\uc57c \ud569\ub2c8\ub2e4."); return; }

//\uac1d\uccb4
LessonDao lesson = new LessonDao();

//\ud3fc\uccb4\ud06c
f.addElement("lesson_nm", null, "hname:'\uac15\uc758\uba85', required:'Y'");
f.addElement("lesson_url", null, "hname:'\uac15\uc758url', required:'Y'");
f.addElement("lesson_url_mobile", null, "hname:'\uac15\uc758\ubaa8\ubc14\uc77curl'");
f.addElement("total_time", null, "hname:'\ud559\uc2b5\uc2dc\uac04', option:'number'");
f.addElement("complete_time", null , "hname:'\uc778\uc815\uc2dc\uac04', option:'number'");
f.addElement("description", null, "hname:'\uac15\uc758\uc124\uba85'");


//\ub4f1\ub85d
if(m.isPost() && f.validate()) {

    int maxSort = lesson.getMaxSort(cid);

    lesson.item("course_id", cid);
    lesson.item("lesson_nm", f.get("lesson_nm"));
    lesson.item("lesson_type", "mp4");
    lesson.item("lesson_url", f.get("lesson_url"));
    lesson.item("lesson_url_mobile", f.get("lesson_url_mobile"));
    lesson.item("total_time", f.getInt("total_time"));
    lesson.item("complete_time", f.getInt("complete_time"));
    lesson.item("description", f.get("description"));
    lesson.item("reg_date", m.time("yyyyMMddHHmmss"));
    lesson.item("sort", maxSort);

    if(!lesson.insert()) { m.jsAlert("\ub4f1\ub85d\ud558\ub294 \uc911 \uc624\ub958\uac00 \ubc1c\uc0dd\ud588\uc2b5\ub2c8\ub2e4."); return; }

    m.jsAlert("\uc131\uacf5\uc801\uc73c\ub85c \ub4f1\ub85d\ud588\uc2b5\ub2c8\ub2e4.");
    m.js("try { parent.opener.location.href = parent.opener.location.href; } catch(e) { } window.close();");
    return;

}

//\ucd9c\ub825
p.setLayout("pop");
p.setVar("p_title", "\uc628\ub77c\uc778\uac15\uc758\uad00\ub9ac");
p.setBody("content.lesson_insert");
p.setVar("query", m.qs());
p.setVar("list_query", m.qs("id"));
p.setVar("form_script", f.getScript());

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
    depend = new com.caucho.vfs.Depend(appDir.lookup("sysop/content/lesson_insert.jsp"), -5878447187011984160L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("sysop/content/init.jsp"), -1378701833033443315L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("sysop/init.jsp"), -2757950905718429357L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }
}
