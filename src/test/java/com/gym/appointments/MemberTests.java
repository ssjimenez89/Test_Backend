package com.gym.appointments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.appointments.Model.Member;
import com.gym.appointments.Model.Sex;
import com.gym.appointments.Service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;

    LocalDate ld = LocalDate.now();
    String dateString = ld.toString();


    // This object will be magically initialized by the initFields method below.
    private JacksonTester<Member> jsonMember;

    @Before
    public void setup() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
    }


    @Test
    public void findAllTest() throws Exception {
        List<Member> memberList = new ArrayList<Member>();
        Member appmember1 = new Member();
        appmember1.setName("Jesus"); appmember1.setFirstSurname("Perez"); appmember1.setSecondSurname("Dominguez"); appmember1.setSex(Sex.M); appmember1.setAge(25); appmember1.setPhone(58963254); appmember1.setId(2); appmember1.setRegistrationDate(dateString);
        Member appmember2 = new Member();
        appmember2.setName("Pedro"); appmember2.setFirstSurname("Diaz"); appmember2.setSecondSurname("Perez"); appmember2.setSex(Sex.M); appmember2.setAge(35); appmember2.setPhone(55896325); appmember2.setId(3); appmember2.setRegistrationDate(dateString);
        memberList.add(appmember1);
        memberList.add(appmember2);

        // given
        given(memberService.findAll())
                .willReturn(memberList);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/member/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        String memberListString = "[{\"name\":\"Jesus\",\"firstSurname\":\"Perez\",\"secondSurname\":\"Dominguez\",\"sex\":\"M\",\"age\":25,\"phone\":58963254,\"id\":2,\"registrationDate\":\"" + dateString + "\"},{\"name\":\"Pedro\",\"firstSurname\":\"Diaz\",\"secondSurname\":\"Perez\",\"sex\":\"M\",\"age\":35,\"phone\":55896325,\"id\":3,\"registrationDate\":\"" + dateString + "\"}]";

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotEmpty();
        assertThat(response.getContentAsString()).isEqualTo(memberListString);
    }

    @Test
    public void getMemberByIdTest() throws Exception {
        Member m = new Member();
        m.setId(2); m.setName("Jesus"); m.setFirstSurname("Perez"); m.setSecondSurname("Dominguez"); m.setSex(Sex.M); m.setAge(25); m.setPhone(58963254); m.setRegistrationDate(dateString);
        // given
        given(memberService.getMemberById(2))
                .willReturn(m);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/member/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        String memberString = "{\"name\":\"Jesus\",\"firstSurname\":\"Perez\",\"secondSurname\":\"Dominguez\",\"sex\":\"M\",\"age\":25,\"phone\":58963254,\"id\":2,\"registrationDate\":\"" + dateString + "\"}";

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(memberString);
    }

    @Test
    public void createMemberTest() throws Exception {
        Member m = new Member();
        m.setName("Pepe"); m.setFirstSurname("Perez"); m.setSecondSurname("Gonzalez"); m.setSex(Sex.M); m.setAge(28); m.setPhone(53268974); m.setRegistrationDate(dateString);
        // when
        MockHttpServletResponse response = mvc.perform(
                post("/api/member/").contentType(MediaType.APPLICATION_JSON).content(
                        jsonMember.write(m).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void editMemberTest() throws Exception {
        Member m = new Member();
        m.setId(2); m.setName("Jesus"); m.setFirstSurname("Perez"); m.setSecondSurname("Dominguez"); m.setSex(Sex.M); m.setAge(25); m.setPhone(58963254); m.setRegistrationDate(dateString);

        given(memberService.getMemberById(2))
                .willReturn(m);

        m.setName("Pepe"); m.setFirstSurname("Perez"); m.setSecondSurname("Gonzalez"); m.setSex(Sex.M); m.setAge(28); m.setPhone(53268974);

        MockHttpServletResponse response = mvc.perform(
                put("/api/member/2").contentType(MediaType.APPLICATION_JSON).content(
                        jsonMember.write(m).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void deleteMemberTest() throws Exception {
        Member m = new Member();
        m.setId(2); m.setName("Jesus"); m.setFirstSurname("Perez"); m.setSecondSurname("Dominguez"); m.setSex(Sex.M); m.setAge(25); m.setPhone(58963254); m.setRegistrationDate(dateString);

        // given
        given(memberService.getMemberById(2))
                .willReturn(m);

        // when
        MockHttpServletResponse response = mvc.perform(
                delete("/api/member/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
