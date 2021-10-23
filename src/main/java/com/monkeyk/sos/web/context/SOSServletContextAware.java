package com.monkeyk.sos.web.context;

import com.monkeyk.sos.web.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 2021/10/23 12:16 PM
 *
 * @author Shengzhao Li
 * @since 2.1.1
 */
@Component
public class SOSServletContextAware implements ServletContextAware {


    @Override
    public void setServletContext(ServletContext servletContext) {
        //主版本号
        servletContext.setAttribute("mainVersion", WebUtils.VERSION);
    }
}
