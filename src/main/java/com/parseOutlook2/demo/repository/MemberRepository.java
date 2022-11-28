package com.parseOutlook2.demo.repository;

import com.parseOutlook2.demo.model.Member;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, String> {
    Member findMemberByEmail(@NotNull String email);
    Iterable<Member> findAllByNameIsNullOrderByEmail();

    @Query("update Member m set m.name = 'NONE' where m.email = ?1")
    void setNameToEmail(String email);
}
