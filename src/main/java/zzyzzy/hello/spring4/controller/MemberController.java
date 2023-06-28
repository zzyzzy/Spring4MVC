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
        String viewName = "/member/fail";

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
    public String loginok(Model m) {

        logger.info("member/loginok 호출!");

        return "redirect:/member/myinfo";
    }

    @RequestMapping("/member/myinfo")
    public String myinfo(Model m) {

        logger.info("member/myinfo 호출!");

        return "member/myinfo.tiles";
    }

}
