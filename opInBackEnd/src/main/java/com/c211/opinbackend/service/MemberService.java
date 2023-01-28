package com.c211.opinbackend.service;

import com.c211.opinbackend.entity.Member;
import com.c211.opinbackend.model.MemberDto;

public interface MemberService {
    Member login(String email, String password);
    Member signUp(MemberDto memberDto);
}
