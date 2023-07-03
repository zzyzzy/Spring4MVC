package zzyzzy.hello.spring4.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zzyzzy.hello.spring4.model.Member;
import zzyzzy.hello.spring4.service.MemberService;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    private Logger logger = LogManager.getLogger(MemberController.class);

    @Autowired
    MemberService msrv;

    @RequestMapping(value = "/member/join", method = RequestMethod.GET)
    public String join(Model m) {

        logger.info("member/join 호출!");

        return "member/join.tiles";
    }

    @RequestMapping(value = "/member/join", method = RequestMethod.POST)
    public String joinok(Member m) {
        logger.info("member/joinok 호출!");
        String viewName = "redirect:/member/fail";

        if (msrv.saveMember(m))
            viewName = "redirect:/member/login";  // 회원가입 처리

        return viewName;
    }

    @RequestMapping(value = "/member/login", method = RequestMethod.GET)
    public String login(Model m) {

        logger.info("member/login 호출!");

        return "member/login.tiles";
    }

    @RequestMapping(value = "/member/login", method = RequestMethod.POST)
    public String loginok(Member m, HttpSession sess) {
        String viewName = "redirect:/member/loginfail";
        logger.info("member/loginok 호출!");

        if (msrv.loginMember(m)) {
            sess.setAttribute("member", m);  // 세션변수
            viewName = "redirect:/member/myinfo";
        }

        return viewName;
    }

    @RequestMapping("/member/myinfo")
    public String myinfo(Model m, HttpSession sess) {
        logger.info("member/myinfo 호출!");
        
        // 세션객체가 없을 경우 로그인 페이지로 이동 - aop로 처리
        //if (sess.getAttribute("member") == null)
        //    return "redirect:/member/login";
        
        String userid =
            ((Member) sess.getAttribute("member")).getUserid();

        m.addAttribute("member", msrv.readOneMember(userid));

        return "member/myinfo.tiles";
    }

    @RequestMapping("/member/logout")
    public String logout(HttpSession sess) {
        logger.info("member/logout 호출!");

        sess.invalidate();  // 세션 객체 제거

        return "redirect:/";
    }


    @RequestMapping("/member/loginfail")
    public String loginfail() {
        logger.info("member/loginfail 호출!");

        return "member/loginfail.tiles";
    }


}
