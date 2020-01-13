package com.gym.appointments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.appointments.Model.AppointmentType;
import com.gym.appointments.Service.AppointmentTypeService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentTypeTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppointmentTypeService appointmentTypeService;


    // This object will be magically initialized by the initFields method below.
    private JacksonTester<AppointmentType> jsonAppointmentType;

    @Before
    public void setup() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void findAllTest() throws Exception {

        List<AppointmentType> appointmentTypeList = new ArrayList<AppointmentType>();
        AppointmentType appTyp1 = new AppointmentType(1, "PILATES", "Intense Exercise 2");
        AppointmentType appTyp2 = new AppointmentType(2, "FITNESS", "Intense Exercise");
        appointmentTypeList.add(appTyp1);
        appointmentTypeList.add(appTyp2);

        // given
        given(appointmentTypeService.findAll())
                .willReturn(appointmentTypeList);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/appointmenttype/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        String appointmentTypeListString = "[{\"id\":1,\"name\":\"PILATES\",\"description\":\"Intense Exercise 2\"},{\"id\":2,\"name\":\"FITNESS\",\"description\":\"Intense Exercise\"}]";

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotEmpty();
        assertThat(response.getContentAsString()).isEqualTo(appointmentTypeListString);
    }

    @Test
    public void getAppointmentTypeByIdTest() throws Exception {
        // given
        given(appointmentTypeService.getAppointmentTypeById(2))
                .willReturn(new AppointmentType(2, "FITNESS", "Intense Exercise"));

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/appointmenttype/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonAppointmentType.write(new AppointmentType(2, "FITNESS", "Intense Exercise")).getJson()
        );
    }

    @Test
    public void createAppointmentTypeTest() throws Exception {
        // when
        MockHttpServletResponse response = mvc.perform(
                post("/api/appointmenttype/").contentType(MediaType.APPLICATION_JSON).content(
                        jsonAppointmentType.write(new AppointmentType(3, "FITNESS", "Intense Exercise")).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void editAppointmentTypeTest() throws Exception {

        MockHttpServletResponse response = mvc.perform(
                put("/api/appointmenttype/2").contentType(MediaType.APPLICATION_JSON).content(
                        jsonAppointmentType.write(new AppointmentType(2, "YOGA", "Intense Exercise 3")).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void deleteAppointmentTypeTest() throws Exception {

        // given
        given(appointmentTypeService.getAppointmentTypeById(2))
                .willReturn(new AppointmentType(2, "FITNESS", "Intense Exercise"));

        // when
        MockHttpServletResponse response = mvc.perform(
                delete("/api/appointmenttype/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
