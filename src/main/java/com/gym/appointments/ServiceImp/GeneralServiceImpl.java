package com.gym.appointments.ServiceImp;

import com.gym.appointments.Model.Sex;
import com.gym.appointments.Service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GeneralServiceImpl implements GeneralService {

    @Override
    public List<Sex> findAllSex() {
        List<Sex> sexList = new ArrayList<Sex>();
        sexList.add(Sex.F);
        sexList.add(Sex.M);
        return sexList;
    }

    @Override
    public List<String> findAllHour() {
        List<String> hourList = Arrays.asList("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00");

        return hourList;
    }
}
