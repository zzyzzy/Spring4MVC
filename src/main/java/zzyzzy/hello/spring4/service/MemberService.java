package zzyzzy.hello.spring4.service;

import zzyzzy.hello.spring4.model.Member;

public interface MemberService {

    boolean saveMember(Member m);
    boolean loginMember(Member m);

}
