package com.hware.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public class JModelAndView extends ModelAndView
{
    public JModelAndView(String viewName)
    {
        super.setViewName(viewName);
    }

    public JModelAndView(String viewName, HttpServletRequest request, HttpServletResponse response)
    {
        String contextPath = request.getContextPath().equals("/") ? "" :
                request.getContextPath();
        String webPath = CommUtil.getURL(request);
        String port = ":" +
                CommUtil.null2Int(Integer.valueOf(request.getServerPort()));

        super.setViewName(viewName);
        super.addObject("domainPath", CommUtil.generic_domain(request));

        super.addObject("webPath", webPath);

        String query_url = "";
        if ((request.getQueryString() != null) &&
                (!request.getQueryString().equals(""))) {
            query_url = "?" + request.getQueryString();
        }
        super.addObject("current_url", request.getRequestURI() + query_url);
        boolean second_domain_view = false;
        String serverName = request.getServerName().toLowerCase();
        super.addObject("second_domain_view", Boolean.valueOf(second_domain_view));
    }


}