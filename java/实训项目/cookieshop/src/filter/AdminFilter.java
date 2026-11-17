//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        filterName = "AdminFilter",
        urlPatterns = {"/admin/*"}
)
public class AdminFilter implements Filter {
    public AdminFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse requestp = (HttpServletResponse)resp;
        User u = (User)request.getSession().getAttribute("user");
        if (u != null && u.isIsadmin()) {
            chain.doFilter(req, resp);
        } else {
            requestp.sendRedirect("../index.jsp");
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }
}
