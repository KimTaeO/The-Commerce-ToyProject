package com.kimtaeo.thecommercetoyproject.domain.member.repository;

import com.kimtaeo.thecommercetoyproject.domain.member.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MemberRepository extends CrudRepository<Member, UUID> {
    Boolean existsByMemberId(String memberId);
    Boolean existsByPhoneNumber(String phoneNumber);
    Boolean existsByEmail(String email);
}
