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
public class MainController extends HttpServlet {

    private static final String MAIN_PAGE = "home.html";

    private static final String LOGIN_PAGE = "LoginPage";
    private static final String LOGIN_PAGE_VIEW = "login.jsp";

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";

    private static final String REGISTER_PAGE = "RegisterPage";
    private static final String REGISTER_PAGE_VIEW = "register.jsp";

    private static final String REGISTER = "Register";
    private static final String REGISTER_CONTROLLER = "RegisterController";

    private static final String SEARCH = "Search";
    private static final String SEARCH_CONTROLLER = "SearchController";

    private static final String BAN = "Ban";
    private static final String BAN_CONTROLLER = "BanController";

    private static final String REGISTER_CLASS = "RegisterClass";
    private static final String REGISTER_CLASS_CONTROLLER = "InsertClassServlet";

    private static final String REGISTER_SLOT = "RegisterSlot";
    private static final String REGISTER_SLOT_CONTROLLER = "InsertSlotServlet";

    private static final String CHECK_STUDENT_INFO = "checkStudentInfo";
    private static final String TIME_TABLE = "timeTable";
    private static final String LIST_CLASSES = "listClasses";
    private static final String SCHEDULE = "schedule";
    private static final String FEEDBACK = "feedback";
    private static final String CHAT = "chat";
    
    private static final String CHECK_STUDENT_INFO_CONTROLLER = "CheckStudentInfoController";
    private static final String TIME_TABLE_CONTROLLER = "TimeTableController";
    private static final String LIST_CLASSES_CONTROLLER = "ListClasses";
    private static final String SCHEDULE_CONTROLLER = "ScheduleController";
    private static final String FEEDBACK_CONTROLLER = "FeedbackController";
    private static final String CHAT_CONTROLLER = "ChatController";
    private static final String STUDENT_HOME_PAGE = "StudentPage";
    private static final String STUDENT_HOME_PAGE_VIEW = "student_homepage.jsp";

    private static final String TUTOR_HOME_PAGE = "tutorHomePage";
    private static final String TUTOR_HOME_PAGE_VIEW = "tutor_homepage.jsp";

    private static final String CHECK_TUTOR_INFO = "checkTutorInfo";
    private static final String CHECK_TUTOR_INFO_CONTROLLER = "CheckTutorInfoController";

    private static final String REGISTER_MENU = "registerMenu";
    private static final String REGISTER_MENU_PAGE = "registerMenu.html";

    private static final String VIEW_TUTOR_CALENDAR = "viewTutorCalendar";
    private static final String VIEW_TUTOR_CALENDAR_CONTROLLER = "ViewTutorCalendar";

    private static final String TUTOR_CHAT = "tutorChat";
    private static final String TUTOR_CHAT_CONTROLLER = "ChatController";
    
    private static final String VIEW_FEEDBACK = "ViewFeedback";
    private static final String VIEW_FEEDBACK_CONTROLLER = "ViewFeedbackController";

    private static final String VIEW_REPORT = "ViewReport";
    private static final String VIEW_REPORT_CONTROLLER = "ViewReportController";

    private static final String VIEW_CV = "ViewCv";
    private static final String VIEW_CV_CONTROLLER = "ViewCvController";

    private static final String VIEW_CV_CHECKED = "ViewCvChecked";
    private static final String VIEW_CV_CHECKED_CONTROLLER = "ViewCvCheckedController";

    private static final String VIEW_REJECTED_CV = "ViewRejectedCv";
    private static final String VIEW_REJECTED_CV_CONTROLLER = "ViewRejectedCvController";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = MAIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = MAIN_PAGE;
            } else if (LOGIN_PAGE.equals(action)) {
                url = LOGIN_PAGE_VIEW;
            } else if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (REGISTER_PAGE.equals(action)) {
                url = REGISTER_PAGE_VIEW;
            } else if (REGISTER.equals(action)) {
                url = REGISTER_CONTROLLER;
            } else if (SEARCH.equals(action)) {
                url = SEARCH_CONTROLLER;
            } else if (BAN.equals(action)) {
                url = BAN_CONTROLLER;
            } else if (REGISTER_CLASS.equals(action)) {
                url = REGISTER_CLASS_CONTROLLER;
            } else if (REGISTER_SLOT.equals(action)) {
                url = REGISTER_SLOT_CONTROLLER;
            } else if (CHECK_STUDENT_INFO.equals(action)) {
                url = CHECK_STUDENT_INFO_CONTROLLER;
            } else if (TIME_TABLE.equals(action)) {
                url = TIME_TABLE_CONTROLLER;
            } else if (LIST_CLASSES.equals(action)) {
                url = LIST_CLASSES_CONTROLLER;
            } else if (SCHEDULE.equals(action)) {
                url = SCHEDULE_CONTROLLER;
            } else if (FEEDBACK.equals(action)) {
                url = FEEDBACK_CONTROLLER;
            } else if (CHAT.equals(action)) {
                url = CHAT_CONTROLLER;
            } else if (STUDENT_HOME_PAGE.equals(action)) {
                url = STUDENT_HOME_PAGE_VIEW;
            } else if (TUTOR_HOME_PAGE.equals(action)) {
                url = TUTOR_HOME_PAGE_VIEW;
            } else if (CHECK_TUTOR_INFO.equals(action)) {
                url = CHECK_TUTOR_INFO_CONTROLLER;
            } else if (REGISTER_MENU.equals(action)) {
                url = REGISTER_MENU_PAGE;
            } else if (VIEW_TUTOR_CALENDAR.equals(action)) {
                url = VIEW_TUTOR_CALENDAR_CONTROLLER;
            } else if (TUTOR_CHAT.equals(action)) {
                url = TUTOR_CHAT_CONTROLLER;
            } else if (VIEW_FEEDBACK.equals(action)) {
                url = VIEW_FEEDBACK_CONTROLLER;
            } else if (VIEW_REPORT.equals(action)) {
                url = VIEW_REPORT_CONTROLLER;
            } else if (VIEW_CV.equals(action)) {
                url = VIEW_CV_CONTROLLER;
            } else if (VIEW_CV_CHECKED.equals(action)) {
                url = VIEW_CV_CHECKED_CONTROLLER;
            } else if (VIEW_REJECTED_CV.equals(action)) {
                url = VIEW_REJECTED_CV_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
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
