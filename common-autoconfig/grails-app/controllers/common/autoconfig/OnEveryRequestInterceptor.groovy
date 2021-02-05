package common.autoconfig

import ch.qos.logback.classic.ClassicConstants
import common.Constants
import groovy.util.logging.Slf4j
import org.slf4j.MDC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher

import javax.servlet.ServletRequest
import javax.servlet.http.HttpServletRequest


@Slf4j
class OnEveryRequestInterceptor {

    int order = HIGHEST_PRECEDENCE + 200


    OnEveryRequestInterceptor() {

        log.debug " OnEveryRequestInterceptor - CREATED"
        // match all requests except requests to the auth controller
        matchAll()
                .excludes(controller: 'login')

    }


    boolean before() {

        Boolean resultado = true

        insertIntoMDC(request)

        flash.model = new HashMap()

        MDC.put(Constants.LOG_SESSIONID_KEY, session.id)
        MDC.put(Constants.USERNAME_ID_KEY  , "-")


        if (session.isNew()) {
            log.debug "OnEveryRequestInterceptor - session.isNew (${session.id})"
        } else {
            log.debug "OnEveryRequestInterceptor - session.alreadyCreated (${session.id})"
        }


        MDC.put(Constants.LOG_SESSIONID_KEY, session.id)

        log.debug "Controller: ${controllerName}.${actionName}   - before"

        clearMDC()


        return resultado
    }

    boolean after() {

        Boolean resultado = true

        if(model == null){
            model = new HashMap()
        }

        if(flash?.model != null) {
            model << flash?.model
        }

        return resultado

    }

    void afterView() {
        // no-op
    }


    void insertIntoMDC(ServletRequest request) {

        MDC.put(ClassicConstants.REQUEST_REMOTE_HOST_MDC_KEY, request.getRemoteAddr());

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            MDC.put(ClassicConstants.REQUEST_REQUEST_URI, httpServletRequest.getRequestURI());
            StringBuffer requestURL = httpServletRequest.getRequestURL();
            if (requestURL != null) {
                MDC.put(ClassicConstants.REQUEST_REQUEST_URL, requestURL.toString());
            }
            MDC.put(ClassicConstants.REQUEST_METHOD, httpServletRequest.getMethod());
            MDC.put(ClassicConstants.REQUEST_QUERY_STRING, httpServletRequest.getQueryString());
            MDC.put(ClassicConstants.REQUEST_USER_AGENT_MDC_KEY, httpServletRequest.getHeader("User-Agent"));
            MDC.put(ClassicConstants.REQUEST_X_FORWARDED_FOR, httpServletRequest.getHeader("X-Forwarded-For"));
        }

    }

    void clearMDC() {
        MDC.remove(ClassicConstants.REQUEST_REMOTE_HOST_MDC_KEY);
        MDC.remove(ClassicConstants.REQUEST_REQUEST_URI);
        MDC.remove(ClassicConstants.REQUEST_QUERY_STRING);
        // removing possibly inexistent item is OK
        MDC.remove(ClassicConstants.REQUEST_REQUEST_URL);
        MDC.remove(ClassicConstants.REQUEST_METHOD);
        MDC.remove(ClassicConstants.REQUEST_USER_AGENT_MDC_KEY);
        MDC.remove(ClassicConstants.REQUEST_X_FORWARDED_FOR);
    }
}
