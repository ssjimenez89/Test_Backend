package com.gym.appointments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.appointments.Model.*;
import com.gym.appointments.Service.AppointmentService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppointmentService appointmentService;

    LocalDate ld = LocalDate.now();
    String dateString = ld.toString();

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<Appointment> jsonAppointment;

    @Before
    public void setup() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void findAllTest() throws Exception {

        List<Appointment> AppointmentList = new ArrayList<Appointment>();

        Coach c = new Coach();
        c.setId(2); c.setName("Jesus"); c.setFirstSurname("Perez"); c.setSecondSurname("Dominguez"); c.setSex(Sex.M); c.setAge(25); c.setPhone(58963254);

        TrainingSchedule apptrainSched = new TrainingSchedule();
        apptrainSched.setId(2); apptrainSched.setStartTime("16:00"); apptrainSched.setEndTime("18:00"); apptrainSched.setDate("2020-01-30"); apptrainSched.setCoach(c);

        Member m = new Member();
        m.setId(2); m.setName("Jesus"); m.setFirstSurname("Perez"); m.setSecondSurname("Dominguez"); m.setSex(Sex.M); m.setAge(25); m.setPhone(58963254); m.setRegistrationDate(dateString);

        AppointmentType appTyp1 = new AppointmentType(1, "PILATES", "Intense Exercise 2");

        m.setId(2); m.setName("Pedro"); m.setFirstSurname("Hernandez"); m.setSecondSurname("Diaz"); m.setSex(Sex.M); m.setAge(29); m.setPhone(53258741); m.setRegistrationDate(dateString);

        Appointment appoint1 = new Appointment();
        appoint1.setId(2); appoint1.setName("Strong exercise"); appoint1.setTrainingSchedule(apptrainSched); appoint1.setAppointmentType(appTyp1); appoint1.setMember(m); appoint1.setRegistrationDate(dateString);


        AppointmentType appTyp2 = new AppointmentType(2, "YOGA", "Medium intensity");

        TrainingSchedule apptrainSched2 = new TrainingSchedule();
        apptrainSched2.setId(3); apptrainSched2.setStartTime("14:00"); apptrainSched2.setEndTime("16:00"); apptrainSched2.setDate("2020-01-28"); apptrainSched2.setCoach(c);

        Appointment appoint2 = new Appointment();
        appoint2.setId(3); appoint2.setName("Medium intensity exercise"); appoint2.setTrainingSchedule(apptrainSched2); appoint2.setAppointmentType(appTyp2); appoint2.setMember(m); appoint2.setRegistrationDate(dateString);


        AppointmentList.add(appoint1);
        AppointmentList.add(appoint2);

        // given
        given(appointmentService.findAll())
                .willReturn(AppointmentList);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/appointment/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        String coach = "{\"name\":\"Jesus\",\"firstSurname\":\"Perez\",\"secondSurname\":\"Dominguez\",\"sex\":\"M\",\"age\":25,\"phone\":58963254,\"id\":2}";
        String trainingScheduleString = "{\"id\":2,\"startTime\":\"16:00\",\"endTime\":\"18:00\",\"date\":\"2020-01-30\",\"coach\":" + coach + "}";
        String appointmentTypeString = "{\"id\":1,\"name\":\"PILATES\",\"description\":\"Intense Exercise 2\"}";
        String memberString = "{\"name\":\"Pedro\",\"firstSurname\":\"Hernandez\",\"secondSurname\":\"Diaz\",\"sex\":\"M\",\"age\":29,\"phone\":53258741,\"id\":2,\"registrationDate\":\"" + dateString + "\"}";
        String appointmentTypeString2 = "{\"id\":2,\"name\":\"YOGA\",\"description\":\"Medium intensity\"}";
        String trainingScheduleString2 = "{\"id\":3,\"startTime\":\"14:00\",\"endTime\":\"16:00\",\"date\":\"2020-01-28\",\"coach\":" + coach + "}";

        String appointmentString = "[{\"id\":2,\"name\":\"Strong exercise\"," + "\"registrationDate\":\"" + dateString + "\",\"appointmentType\":" + appointmentTypeString + ",\"trainingSchedule\":" + trainingScheduleString +",\"member\":" + memberString + "}," +
                "{\"id\":3,\"name\":\"Medium intensity exercise\"," + "\"registrationDate\":\"" + dateString + "\",\"appointmentType\":" + appointmentTypeString2 + ",\"trainingSchedule\":" + trainingScheduleString2 +",\"member\":" + memberString + "}]";


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotEmpty();
        assertThat(response.getContentAsString()).isEqualTo(appointmentString);
    }

    @Test
    public void getTrainingScheduleByIdTest() throws Exception {

        Coach c = new Coach();
        c.setId(2); c.setName("Jesus"); c.setFirstSurname("Perez"); c.setSecondSurname("Dominguez"); c.setSex(Sex.M); c.setAge(25); c.setPhone(58963254);

        TrainingSchedule apptrainSched = new TrainingSchedule();
        apptrainSched.setId(2); apptrainSched.setStartTime("16:00"); apptrainSched.setEndTime("18:00"); apptrainSched.setDate("2020-01-30"); apptrainSched.setCoach(c);

        Member m = new Member();
        m.setId(2); m.setName("Jesus"); m.setFirstSurname("Perez"); m.setSecondSurname("Dominguez"); m.setSex(Sex.M); m.setAge(25); m.setPhone(58963254); m.setRegistrationDate(dateString);

        AppointmentType appTyp = new AppointmentType(1, "PILATES", "Intense Exercise 2");

        Appointment appoint = new Appointment();
        appoint.setId(2); appoint.setName("Strong exercise"); appoint.setTrainingSchedule(apptrainSched); appoint.setAppointmentType(appTyp); appoint.setMember(m); appoint.setRegistrationDate(dateString);

        // given
        given(appointmentService.getAppointmentById(2))
                .willReturn(appoint);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/appointment/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        String coach = "{\"name\":\"Jesus\",\"firstSurname\":\"Perez\",\"secondSurname\":\"Dominguez\",\"sex\":\"M\",\"age\":25,\"phone\":58963254,\"id\":2}";
        String trainingScheduleString = "{\"id\":2,\"startTime\":\"16:00\",\"endTime\":\"18:00\",\"date\":\"2020-01-30\",\"coach\":" + coach + "}";
        String appointmentTypeString = "{\"id\":1,\"name\":\"PILATES\",\"description\":\"Intense Exercise 2\"}";
        String memberString = "{\"name\":\"Jesus\",\"firstSurname\":\"Perez\",\"secondSurname\":\"Dominguez\",\"sex\":\"M\",\"age\":25,\"phone\":58963254,\"id\":2,\"registrationDate\":\"" + dateString + "\"}";

        String appointmentString = "{\"id\":2,\"name\":\"Strong exercise\"," + "\"registrationDate\":\"" + dateString + "\",\"appointmentType\":" + appointmentTypeString + ",\"trainingSchedule\":" + trainingScheduleString +",\"member\":" + memberString + "}";

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(appointmentString);
    }

    @Test
    public void createTrainingScheduleTest() throws Exception {
        Coach c = new Coach();
        c.setId(2); c.setName("Jesus"); c.setFirstSurname("Perez"); c.setSecondSurname("Dominguez"); c.setSex(Sex.M); c.setAge(25); c.setPhone(58963254);

        TrainingSchedule apptrainSched = new TrainingSchedule();
        apptrainSched.setId(2); apptrainSched.setStartTime("16:00"); apptrainSched.setEndTime("18:00"); apptrainSched.setDate("2020-01-30"); apptrainSched.setCoach(c);

        Member m = new Member();
        m.setId(2); m.setName("Jesus"); m.setFirstSurname("Perez"); m.setSecondSurname("Dominguez"); m.setSex(Sex.M); m.setAge(25); m.setPhone(58963254); m.setRegistrationDate(dateString);

        AppointmentType appTyp = new AppointmentType(1, "PILATES", "Intense Exercise 2");

        Appointment appoint = new Appointment();
        appoint.setId(2); appoint.setName("Strong exercise"); appoint.setTrainingSchedule(apptrainSched); appoint.setAppointmentType(appTyp); appoint.setMember(m); appoint.setRegistrationDate(dateString);

        MockHttpServletResponse response = mvc.perform(
                post("/api/appointment/" + m.getId() + "/" + appTyp.getId() + "/" + apptrainSched.getId()).contentType(MediaType.APPLICATION_JSON).content(
                        jsonAppointment.write(appoint).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void editTrainingScheduleTest() throws Exception {
        Coach c = new Coach();
        c.setId(2); c.setName("Jesus"); c.setFirstSurname("Perez"); c.setSecondSurname("Dominguez"); c.setSex(Sex.M); c.setAge(25); c.setPhone(58963254);

        TrainingSchedule apptrainSched = new TrainingSchedule();
        apptrainSched.setId(2); apptrainSched.setStartTime("16:00"); apptrainSched.setEndTime("18:00"); apptrainSched.setDate("2020-01-30"); apptrainSched.setCoach(c);

        Member m = new Member();
        m.setId(2); m.setName("Jesus"); m.setFirstSurname("Perez"); m.setSecondSurname("Dominguez"); m.setSex(Sex.M); m.setAge(25); m.setPhone(58963254); m.setRegistrationDate(dateString);

        AppointmentType appTyp = new AppointmentType(1, "PILATES", "Intense Exercise 2");

        AppointmentType appTypEdit = new AppointmentType(1, "YOGA", "Intense Exercise 3");
        Member memberEdit = new Member();
        memberEdit.setId(3); memberEdit.setName("Pedro"); memberEdit.setFirstSurname("Hernandez"); memberEdit.setSecondSurname("Diaz"); memberEdit.setSex(Sex.M); memberEdit.setAge(29); memberEdit.setPhone(53258741); memberEdit.setRegistrationDate(dateString);

        Appointment appoint = new Appointment();
        appoint.setId(2); appoint.setName("Strong exercise"); appoint.setTrainingSchedule(apptrainSched); appoint.setAppointmentType(appTyp); appoint.setMember(m); appoint.setRegistrationDate(dateString);

        given(appointmentService.getAppointmentById(2))
                .willReturn(appoint);

        appoint.setAppointmentType(appTypEdit); appoint.setMember(memberEdit);

        MockHttpServletResponse response = mvc.perform(
                put("/api/appointment/" + memberEdit.getId() +  "/"+ appTypEdit.getId() + "/" + apptrainSched.getId() + "/2").contentType(MediaType.APPLICATION_JSON).content(
                        jsonAppointment.write(appoint).getJson()
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

        Member m = new Member();
        m.setId(2); m.setName("Jesus"); m.setFirstSurname("Perez"); m.setSecondSurname("Dominguez"); m.setSex(Sex.M); m.setAge(25); m.setPhone(58963254); m.setRegistrationDate(dateString);

        AppointmentType appTyp = new AppointmentType(1, "PILATES", "Intense Exercise 2");

        Appointment appoint = new Appointment();
        apptrainSched.setId(2); appoint.setName("Strong exercise"); appoint.setTrainingSchedule(apptrainSched); appoint.setAppointmentType(appTyp); appoint.setMember(m); appoint.setRegistrationDate(dateString);

        // given
        given(appointmentService.getAppointmentById(2))
                .willReturn(appoint);

        // when
        MockHttpServletResponse response = mvc.perform(
                delete("/api/appointment/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
