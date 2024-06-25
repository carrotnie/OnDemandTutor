package controller;



import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MSI
 */
public class MainController extends HttpServlet{
    private static final String MAIN_PAGE ="home.html";
    
    private static final String LOGIN_PAGE="LoginPage";
    private static final String LOGIN_PAGE_VIEW="login.jsp";
    
    private static final String LOGIN="Login";
    private static final String LOGIN_CONTROLLER="LoginController";
   
    private static final String REGISTER_PAGE="RegisterPage";
    private static final String REGISTER_PAGE_VIEW="register.jsp";
    
    private static final String REGISTER="Register";
    private static final String REGISTER_CONTROLLER="RegisterController";
    
    private static final String SEARCH="Search";
    private static final String SEARCH_CONTROLLER="SearchController";
    
    private static final String BAN="Ban";
    private static final String BAN_CONTROLLER="BanController";
    
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = MAIN_PAGE;
        try{
            String action=request.getParameter("action");
            if (action==null){
                url=MAIN_PAGE;
            }else if (LOGIN_PAGE.equals(action)){
                url=LOGIN_PAGE_VIEW;                       
            }else if (LOGIN.equals(action)){
                url=LOGIN_CONTROLLER;                       
            }else if (REGISTER_PAGE.equals(action)){
                url=REGISTER_PAGE_VIEW;
            }else if (REGISTER.equals(action)){
                url=REGISTER_CONTROLLER;
            }else if (SEARCH.equals(action)){
                url=SEARCH_CONTROLLER;
            }else if (BAN.equals(action)){
                url=BAN_CONTROLLER;
            }
        }catch (Exception e){
            log("Error at MainController: " + e.toString());
        }finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}