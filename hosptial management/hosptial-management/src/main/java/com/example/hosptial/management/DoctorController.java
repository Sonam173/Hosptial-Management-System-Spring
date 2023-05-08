package com.example.hosptial.management;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@RestController
@RequestMapping("/doctor")// as a prefix ,infront of all API.
public class DoctorController {
    HashMap<Integer,Doctor> doctorDb = new HashMap<>();// this is doctor database
    @PostMapping("/add")// for add doctor
    public String addDoctor(@RequestBody Doctor doctor)
    {
        int key=doctor.getDoctorId();
        doctorDb.put(key,doctor);
        return "doctor added successfully";

    }
}
