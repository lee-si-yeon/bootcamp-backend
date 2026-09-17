package com.back;

import com.back.exception.DomainException;
import com.back.initData.DataInit;
import com.back.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.datasource.url=jdbc:h2:mem:member-test;MODE=MySQL")
class BackApplicationTests {

    @Autowired
    MemberService memberService;

    @Autowired
    DataInit dataInit;

    @Test
    void contextLoads() {
        assertEquals(6, memberService.count());
        dataInit.makeBaseMembers();
        assertEquals(6, memberService.count());
        var member = memberService.findByUsername("user1").orElseThrow();
        assertTrue(member.getId() > 0);
        assertNotNull(member.getCreateDate());
        assertNotNull(member.getModifyDate());
        var exception = assertThrows(DomainException.class,
                () -> memberService.join("user1", "1234", "중복"));
        assertEquals("409-1", exception.getResultCode());
    }

}
