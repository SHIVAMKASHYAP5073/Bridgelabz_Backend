package com.greet.servlet;

import com.greet.model.Greeting;
import com.greet.service.GreetingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class GreetingServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ApplicationContext context;
    private GreetingService greetingService;
    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("=== GreetingServlet.init() — Loading Spring Context ===");
        String xmlPath = getServletContext().getRealPath("/WEB-INF/applicationContext.xml");
        context = new ClassPathXmlApplicationContext("file:" + xmlPath);
        greetingService = (GreetingService) context.getBean("greetingService");
        System.out.println("=== Spring Context loaded successfully ===");
        System.out.println("    GreetingService bean: " + greetingService);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("=== GreetingServlet.doGet() — Forwarding to index.jsp ===");
        // Forward to the input form page
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        System.out.println("=== GreetingServlet.doPost() — userName: " + userName + " ===");
        Greeting greeting = greetingService.greet(userName);
        System.out.println("    Greeting generated: " + greeting);
        request.setAttribute("greeting", greeting);
        request.getRequestDispatcher("/greeting.jsp").forward(request, response);
    }
}