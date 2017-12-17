import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author hbh
 * @version 1.0.0
 * @since 2017/12/15
 */
public class PrimitiveServlet implements Servlet {
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }
    
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
    
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter out = res.getWriter();
        out.println("hello, rose and red.");
        out.print("Violets are blue");
    }
    
    @Override
    public String getServletInfo() {
        return null;
    }
    
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
