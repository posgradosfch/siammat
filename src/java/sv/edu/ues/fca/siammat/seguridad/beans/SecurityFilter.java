package sv.edu.ues.fca.siammat.seguridad.beans;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import sv.edu.ues.fca.siammat.seguridad.modelo.Usuario;
import sv.edu.ues.fca.siammat.util.Util;

public class SecurityFilter implements Filter {

    private static final String LOGIN_PAGE_URI = "/seguridad/login.xhtml";
    private static final String RESOURCE_VAR = "recurso";

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        String contextPath = ((HttpServletRequest) req).getContextPath();
        String requestUri = ((HttpServletRequest) req).getRequestURI();
        String recurso = requestUri.substring(requestUri.lastIndexOf("/"));
        String uri = requestUri.substring(requestUri.lastIndexOf("/faces") +"/faces".length());
        uri = uri.replace(".xhtml", "");
        if (this.authorize((HttpServletRequest) req)
                || recurso.endsWith("/login.xhtml")) {
            if (uri.endsWith("/list")) {
                ((HttpServletRequest) req).getSession().setAttribute(RESOURCE_VAR, uri);
            }
            chain.doFilter(req, res);
        } else {
            ((HttpServletResponse) res).sendRedirect(contextPath + "/faces" + LOGIN_PAGE_URI);
        }

    }

    private boolean authorize(HttpServletRequest req) {
        Usuario user = (Usuario) req.getSession().getAttribute("usuario");
        return user != null;
    }

    @Override
    public void destroy() {
    }

}
