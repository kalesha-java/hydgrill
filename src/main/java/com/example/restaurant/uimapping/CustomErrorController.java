package com.example.restaurant.uimapping;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Get the error code from the request
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if (statusCode == 404) {
            // Return the path to index.html for 404 errors
            return "redirect:/index.html";
        }

        // For other errors, you can return a different error page or handle accordingly
        return "error"; // Default error page
    }

}

