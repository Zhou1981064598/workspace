package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(
        filterName = "EncodeFilter",
        urlPatterns = {"/*"}
)
public class EncodeFilter implements Filter {
    public EncodeFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }
}


/**
 * 本类通过注解的urlPatterns属性值为/*可以知道，本项目所有的请求都会经过EncodeFilter类，从而实现全站统一编码
 */

