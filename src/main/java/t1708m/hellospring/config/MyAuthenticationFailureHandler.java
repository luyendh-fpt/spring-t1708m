package t1708m.hellospring.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        HttpSession httpSession = httpServletRequest.getSession();
        int failureCount = 1;
        try {
            failureCount = Integer.parseInt(String.valueOf(httpSession.getAttribute("failureCount")));
            failureCount++;
        } catch (Exception ex) {
            System.out.println("Number format exception!");
        }
        httpSession.setAttribute("failureCount", failureCount);
        super.onAuthenticationFailure(httpServletRequest, httpServletResponse,
                new BadCredentialsException("* Sai thông tin đăng nhập, vui lòng thử lại!"));
    }
}
