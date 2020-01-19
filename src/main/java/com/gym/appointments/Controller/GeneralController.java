package com.gym.appointments.Controller;

import com.gym.appointments.Model.Sex;
import com.gym.appointments.Service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GeneralController {

    @Autowired
    GeneralService generalService;

    @GetMapping("/sex")
    public List<Sex> findAllSex(){
        return generalService.findAllSex();
    }

    @GetMapping("/hour")
    public List<String> findAllHour(){
        return generalService.findAllHour();
    }
}
