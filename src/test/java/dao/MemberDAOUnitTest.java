package dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import zzyzzy.hello.spring4.dao.MemberDAO;
import zzyzzy.hello.spring4.model.Member;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/servlet-context.xml",
    "classpath:spring/root-context.xml"})
@WebAppConfiguration
public class MemberDAOUnitTest {

    // 테스트할 대상 객체를 주입받음
    @Autowired MemberDAO mdao;

    @Test
    @Transactional  // 데이터 입력후 자동 삭제
    public void insertMember() throws Exception {
        Member m = new Member(
            null, "abc123a","987xyz",
            "abc123","abc123@987xyz.co.kr",null);

        // assertEquals(테스트메서드, 검증값)
        assertEquals(mdao.insertMember(m), 1);
    }

}







