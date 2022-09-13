package com.firstservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        description = "Login ServLet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "user", value = "Rajendhar"),
                @WebInitParam(name = "password", value = "Raj@0987")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");

        String nameValidation = "^[A-Z]{1}[A-Za-z]{2,}$";
        String passwordValidation = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        if (userID.equals(user) && userID.matches(nameValidation) && password.matches(passwordValidation) && password.equals(pwd)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("loginSuccess.jsp").forward(request, response);
        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color = red>Either user name or password is wrong.</font>");
            rd.include(request,response);
        }
    }
}

