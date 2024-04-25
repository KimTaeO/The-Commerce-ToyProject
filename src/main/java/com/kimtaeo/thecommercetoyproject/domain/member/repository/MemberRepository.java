package com.kimtaeo.thecommercetoyproject.domain.member.repository;

import com.kimtaeo.thecommercetoyproject.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends CrudRepository<Member, UUID> {
    Boolean existsByMemberId(String memberId);
    Boolean existsByPhoneNumber(String phoneNumber);
    Boolean existsByEmail(String email);
    Page<Member> findAll(Pageable pageable);
    Optional<Member> findByMemberId(String memberId);
}
