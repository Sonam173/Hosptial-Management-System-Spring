package com.example.hosptial.management;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nurse")
public class NurseController {

    NurseService nurseService = new NurseService();// create object for nurseService to call nurse service class functions


    @PostMapping("/add")
    public String addNurse(@RequestBody Nurse nurse){

        String ans = nurseService.addNurse(nurse);

        return ans;
    }

    @GetMapping("/getByAge")
    public List<Nurse> getNursesGreaterThanAge(@RequestParam("age")Integer age){

        List<Nurse> nurseList = nurseService.getList(age);
        return nurseList;
    }

    @GetMapping("/getByQualification")
    public List<Nurse> getNursesByQualification(@RequestParam("qualification")String qualification){

        List<Nurse> nurses =nurseService. getNursesWithQualification(qualification);
        return nurses;
    }

}
