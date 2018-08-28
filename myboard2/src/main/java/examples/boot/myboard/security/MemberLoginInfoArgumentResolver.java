package examples.boot.myboard.security;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class MemberLoginInfoArgumentResolver
    implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        // methodParameter의 정보를 이용하여 파라미터 타입이
        // MemberLoginInfo & @AuthUser 냐? 검사해서 맞으면 true를 리턴
        AuthUser authUser =
                methodParameter.getParameterAnnotation(AuthUser.class);
        if(authUser != null
                && methodParameter.getParameterType() == MemberLoginInfo.class){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // SecurityContextHolder를 이용하여 결과를 얻어서 리턴.
        Authentication authentication =
              SecurityContextHolder.getContext().getAuthentication();
        Object p = authentication.getPrincipal();
        if(p == null || p.getClass() == String.class){
            return null;
        }
//        HttpServletRequest request =
//                (HttpServletRequest)nativeWebRequest.getNativeRequest();
//        request.setAttribute("memberLoginInfo", p);
        return p;
    }
}
