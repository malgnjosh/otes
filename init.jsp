<%@ page import="java.util.*, java.io.*, dao.*, malgnsoft.db.*, malgnsoft.util.*" %><%

String tplRoot = Config.getDocRoot() + "/otes/html"

Malgn m = new Malgn((request, response, out);

Page p = new Page(tplRoot);
p.setRequest(request);
p.setWriter(out);
p.setPageContext(pageContext);

%>
