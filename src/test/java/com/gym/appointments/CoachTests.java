package com.gym.appointments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.appointments.Model.Coach;
import com.gym.appointments.Model.Sex;
import com.gym.appointments.Service.AppointmentTypeService;
import com.gym.appointments.Service.CoachService;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CoachTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CoachService coachService;


    // This object will be magically initialized by the initFields method below.
    private JacksonTester<Coach> jsonCoach;

    @Before
    public void setup() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void findAllTest() throws Exception {

        List<Coach> coachList = new ArrayList<Coach>();
        Coach appcoach1 = new Coach();
        appcoach1.setName("Jesus"); appcoach1.setFirstSurname("Perez"); appcoach1.setSecondSurname("Dominguez"); appcoach1.setSex(Sex.M); appcoach1.setAge(25); appcoach1.setPhone(58963254); appcoach1.setId(2);
        Coach appcoach2 = new Coach();
        appcoach2.setName("Pedro"); appcoach2.setFirstSurname("Diaz"); appcoach2.setSecondSurname("Perez"); appcoach2.setSex(Sex.M); appcoach2.setAge(35); appcoach2.setPhone(55896325); appcoach2.setId(3);
        coachList.add(appcoach1);
        coachList.add(appcoach2);

        // given
        given(coachService.findAll())
                .willReturn(coachList);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/coach/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        String coachListString = "[{\"name\":\"Jesus\",\"firstSurname\":\"Perez\",\"secondSurname\":\"Dominguez\",\"sex\":\"M\",\"age\":25,\"phone\":58963254,\"id\":2},{\"name\":\"Pedro\",\"firstSurname\":\"Diaz\",\"secondSurname\":\"Perez\",\"sex\":\"M\",\"age\":35,\"phone\":55896325,\"id\":3}]";

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotEmpty();
        assertThat(response.getContentAsString()).isEqualTo(coachListString);
    }

    @Test
    public void getCoachByIdTest() throws Exception {
        Coach c = new Coach();
        c.setId(2); c.setName("Jesus"); c.setFirstSurname("Perez"); c.setSecondSurname("Dominguez"); c.setSex(Sex.M); c.setAge(25); c.setPhone(58963254);
        // given
        given(coachService.getCoachById(2))
                .willReturn(c);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/coach/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        String coachString = "{\"name\":\"Jesus\",\"firstSurname\":\"Perez\",\"secondSurname\":\"Dominguez\",\"sex\":\"M\",\"age\":25,\"phone\":58963254,\"id\":2}";

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(coachString);
    }

    @Test
    public void createCoachTest() throws Exception {
        Coach c = new Coach();
        c.setName("Pepe"); c.setFirstSurname("Perez"); c.setSecondSurname("Gonzalez"); c.setSex(Sex.M); c.setAge(28); c.setPhone(53268974);
        // when
        MockHttpServletResponse response = mvc.perform(
                post("/api/coach/").contentType(MediaType.APPLICATION_JSON).content(
                        jsonCoach.write(c).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void editCoachTest() throws Exception {
        Coach c = new Coach();
        c.setId(2); c.setName("Jesus"); c.setFirstSurname("Perez"); c.setSecondSurname("Dominguez"); c.setSex(Sex.M); c.setAge(25); c.setPhone(58963254);

        given(coachService.getCoachById(2))
                .willReturn(c);

        c.setName("Pepe"); c.setFirstSurname("Perez"); c.setSecondSurname("Gonzalez"); c.setSex(Sex.M); c.setAge(28); c.setPhone(53268974);

        MockHttpServletResponse response = mvc.perform(
                put("/api/coach/2").contentType(MediaType.APPLICATION_JSON).content(
                        jsonCoach.write(c).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void deleteCoachTest() throws Exception {
        Coach c = new Coach();
        c.setId(2); c.setName("Jesus"); c.setFirstSurname("Perez"); c.setSecondSurname("Dominguez"); c.setSex(Sex.M); c.setAge(25); c.setPhone(58963254);
        // given
        given(coachService.getCoachById(2))
                .willReturn(c);

        // when
        MockHttpServletResponse response = mvc.perform(
                delete("/api/coach/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }


}

