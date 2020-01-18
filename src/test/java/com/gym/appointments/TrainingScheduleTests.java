package com.gym.appointments;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.appointments.Model.Coach;
import com.gym.appointments.Model.Sex;
import com.gym.appointments.Model.TrainingSchedule;
import com.gym.appointments.Service.TrainingScheduleService;
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
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TrainingScheduleTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TrainingScheduleService trainingScheduleService;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<TrainingSchedule> jsonTrainingSchedule;

    @Before
    public void setup() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void findAllTest() throws Exception {

        List<TrainingSchedule> trainingScheduleList = new ArrayList<TrainingSchedule>();

        Coach c = new Coach();
        c.setId(2); c.setName("Jesus"); c.setFirstSurname("Perez"); c.setSecondSurname("Dominguez"); c.setSex(Sex.M); c.setAge(25); c.setPhone(58963254);

        TrainingSchedule apptrainSched1 = new TrainingSchedule();
        apptrainSched1.setId(2); apptrainSched1.setStartTime("14:00"); apptrainSched1.setEndTime("16:00"); apptrainSched1.setDate("2020-01-28"); apptrainSched1.setCoach(c);

        TrainingSchedule apptrainSched2 = new TrainingSchedule();
        apptrainSched2.setId(3); apptrainSched2.setStartTime("16:00"); apptrainSched2.setEndTime("18:00"); apptrainSched2.setDate("2020-01-30"); apptrainSched2.setCoach(c);

        trainingScheduleList.add(apptrainSched1);
        trainingScheduleList.add(apptrainSched2);

        // given
        given(trainingScheduleService.findAll())
                .willReturn(trainingScheduleList);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/trainingschedule/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        String coach = "{\"name\":\"Jesus\",\"firstSurname\":\"Perez\",\"secondSurname\":\"Dominguez\",\"sex\":\"M\",\"age\":25,\"phone\":58963254,\"id\":2}";
        String trainingScheduleString = "[{\"id\":2,\"startTime\":\"14:00\",\"endTime\":\"16:00\",\"date\":\"2020-01-28\",\"coach\":" + coach + "},{\"id\":3,\"startTime\":\"16:00\",\"endTime\":\"18:00\",\"date\":\"2020-01-30\",\"coach\":" + coach + "}]";

        //String trainingScheduleString = "[{\"name\":\"Jesus\",\"firstSurname\":\"Perez\",\"secondSurname\":\"Dominguez\",\"sex\":\"M\",\"age\":25,\"phone\":58963254,\"id\":2,\"registrationDate\":\"},{\"name\":\"Pedro\",\"firstSurname\":\"Diaz\",\"secondSurname\":\"Perez\",\"sex\":\"M\",\"age\":35,\"phone\":55896325,\"id\":3,\"registrationDate\":\"}]";

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotEmpty();
        assertThat(response.getContentAsString()).isEqualTo(trainingScheduleString);
    }

    @Test
    public void getTrainingScheduleByIdTest() throws Exception {

        Coach c = new Coach();
        c.setId(2); c.setName("Jesus"); c.setFirstSurname("Perez"); c.setSecondSurname("Dominguez"); c.setSex(Sex.M); c.setAge(25); c.setPhone(58963254);

        TrainingSchedule apptrainSched = new TrainingSchedule();
        apptrainSched.setId(2); apptrainSched.setStartTime("16:00"); apptrainSched.setEndTime("18:00"); apptrainSched.setDate("2020-01-30"); apptrainSched.setCoach(c);
        // given
        given(trainingScheduleService.getTrainingScheduleById(2))
                .willReturn(apptrainSched);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/trainingschedule/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        String coach = "{\"name\":\"Jesus\",\"firstSurname\":\"Perez\",\"secondSurname\":\"Dominguez\",\"sex\":\"M\",\"age\":25,\"phone\":58963254,\"id\":2}";
        String trainingScheduleString = "{\"id\":2,\"startTime\":\"16:00\",\"endTime\":\"18:00\",\"date\":\"2020-01-30\",\"coach\":" + coach + "}";

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(trainingScheduleString);
    }

    @Test
    public void createTrainingScheduleTest() throws Exception {
        Coach c = new Coach();
        c.setId(2); c.setName("Jesus"); c.setFirstSurname("Perez"); c.setSecondSurname("Dominguez"); c.setSex(Sex.M); c.setAge(25); c.setPhone(58963254);

        TrainingSchedule apptrainSched = new TrainingSchedule();
        apptrainSched.setId(2); apptrainSched.setStartTime("16:00"); apptrainSched.setEndTime("18:00"); apptrainSched.setDate("2020-01-30"); apptrainSched.setCoach(c);
        // when
        MockHttpServletResponse response = mvc.perform(
                post("/api/trainingschedule/" + c.getId()).contentType(MediaType.APPLICATION_JSON).content(
                        jsonTrainingSchedule.write(apptrainSched).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void editTrainingScheduleTest() throws Exception {
        Coach c = new Coach();
        c.setId(2); c.setName("Jesus"); c.setFirstSurname("Perez"); c.setSecondSurname("Dominguez"); c.setSex(Sex.M); c.setAge(25); c.setPhone(58963254);

        Coach ch = new Coach();
        ch.setId(3); ch.setName("Pedro"); ch.setFirstSurname("Diaz"); ch.setSecondSurname("Ramirez"); ch.setSex(Sex.M); ch.setAge(28); ch.setPhone(55236985);

        TrainingSchedule apptrainSched = new TrainingSchedule();
        apptrainSched.setId(2); apptrainSched.setStartTime("16:00"); apptrainSched.setEndTime("18:00"); apptrainSched.setDate("2020-01-30"); apptrainSched.setCoach(c);

        given(trainingScheduleService.getTrainingScheduleById(2))
                .willReturn(apptrainSched);

        apptrainSched.setId(2); apptrainSched.setStartTime("18:00"); apptrainSched.setEndTime("20:00"); apptrainSched.setDate("2020-01-30"); apptrainSched.setCoach(ch);

        MockHttpServletResponse response = mvc.perform(
                put("/api/trainingschedule/" + ch.getId() + "/2").contentType(MediaType.APPLICATION_JSON).content(
                        jsonTrainingSchedule.write(apptrainSched).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void deleteTrainingScheduleTest() throws Exception {
        Coach c = new Coach();
        c.setId(2); c.setName("Jesus"); c.setFirstSurname("Perez"); c.setSecondSurname("Dominguez"); c.setSex(Sex.M); c.setAge(25); c.setPhone(58963254);

        TrainingSchedule apptrainSched = new TrainingSchedule();
        apptrainSched.setId(2); apptrainSched.setStartTime("16:00"); apptrainSched.setEndTime("18:00"); apptrainSched.setDate("2020-01-30"); apptrainSched.setCoach(c);

        // given
        given(trainingScheduleService.getTrainingScheduleById(2))
                .willReturn(apptrainSched);

        // when
        MockHttpServletResponse response = mvc.perform(
                delete("/api/trainingschedule/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
