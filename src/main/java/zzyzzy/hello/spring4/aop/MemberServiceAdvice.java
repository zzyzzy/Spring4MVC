package zzyzzy.hello.spring4.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class MemberServiceAdvice {

    private Logger logger = LogManager.getLogger(MemberServiceAdvice.class);

    @Pointcut("execution(* zzyzzy.hello.spring4.controller.MemberController.myinfo(..))")
    public void myInfoPoint() {}

    @Pointcut("execution(* zzyzzy.hello.spring4.controller.MemberController.join(..))")
    public void joinPoint() {}

    @Around("myInfoPoint()")
    public Object myinfoAOPProcess(ProceedingJoinPoint pjp)
                                                throws Throwable {
        logger.info("myinfoAOPProcess 호출!!");
        HttpSession sess = null;

        // join point에서 넘겨준 매개변수에서 원하는 객체 추출
        for(Object o : pjp.getArgs()) {
            if (o instanceof HttpSession)
                sess = (HttpSession) o;
        }

        // 포인트컷 대상 메서드 실행
        if (sess.getAttribute("member") == null)
            return "redirect:/member/login";

        //
        Object obj = pjp.proceed();
        return obj;
    }

    @Around("joinPoint()")
    public Object joinAOPProcess(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("joinAOPProcess 호출!!");
        HttpSession sess = null;

        for(Object o : pjp.getArgs()) {
            if (o instanceof HttpSession)
                sess = (HttpSession) o;
        }

        // 포인트컷 대상 메서드 실행
        // 로그인 안 했다면 -> join으로 이동
        // 로그인 했다면 -> myinfo로 이동
        if (sess.getAttribute("member") != null)
            return "redirect:/member/myinfo";

        //
        Object obj = pjp.proceed();
        return obj;
    }

}







