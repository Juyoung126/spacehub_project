package com.spring.admin.memberManagement.service;

import java.util.List;

import com.spring.client.domain.Member;

public interface MemberManagementService {

	List<Member> memberList(Member member);
	Member getMemberByMemberNo(Long memberNo);

}
