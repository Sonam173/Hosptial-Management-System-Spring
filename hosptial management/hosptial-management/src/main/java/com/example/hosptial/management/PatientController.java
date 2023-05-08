package com.example.hosptial.management;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PatientController {
    HashMap<Integer,Patient> patientDb = new HashMap<>();// this is patient database
    @PostMapping("/addPatientViaParameters")// adding patient
    public String addPatient(@RequestParam("patientId")Integer patientId,@RequestParam("name")String name,@RequestParam("age")Integer age,
                             @RequestParam("disease")String disease)
    {
        Patient patient= new Patient(patientId,name,disease,age);// for creating patient object
        patientDb.put(patientId,patient);
        return "Patient added sucessfully";

    }
    @PostMapping("/addPatientViaRequestBody")
    public String addPatient(@RequestBody Patient patient)
    {
        int key= patient.getPatientId();// kisi object ka variable get krna , get se krenge patientid variable hai
        patientDb.put(key,patient);
        return "Patient added via Request body";
    }
    @GetMapping("/getInfoViaPathVariable/{patientId}")
    public Patient getPatientInfo(@PathVariable("patientId")Integer patientId)
    {
        Patient patient=patientDb.get(patientId);
        return patient;

    }
    @GetMapping("/getPatientInfo")// read patient
    public Patient getPatient(@RequestParam("patientId")Integer patientId)
    {
        Patient patient=patientDb.get(patientId);
        return patient;
    }
    @GetMapping("/getAllPatient")
    public List<Patient> getAllPatient()
    {
        List<Patient> patients= new ArrayList<>();
        for(Patient p: patientDb.values() )//iterate over patient database
        {
            patients.add(p);
        }
        return patients;

    }

    @GetMapping("/getPatientByName")
    public Patient getPatientByName(@RequestParam("name")String name)
    {

        for(Patient p: patientDb.values() )//iterate over patient database
        {
            if(p.getName().equals(name))
            {
                return p;
            }
        }
        return null;

    }
    @GetMapping("/getAllPatientsListGreaterThanAge")
    public List<Patient> getAllPatientsListGreaterThanAge(@RequestParam("age")Integer age)
    {
        List<Patient> patients= new ArrayList<>();
        for(Patient p:patientDb.values())
        {
            if(p.getAge()>age)
            {
                patients.add(p);
            }
        }
        return patients;

    }
    @GetMapping("/getPatients/{age}/{disease}")
    public List<Patient> getPatients(@PathVariable("age")Integer age, @PathVariable("disease")String disease)
    {
        List<Patient> patients= new ArrayList<>();
        for(Patient p:patientDb.values())
        {
            if(p.getAge()>age && p.getDisease().equals(disease))
            {
                patients.add(p);
            }
        }
        return patients;
    }
   //if we want to update one attribute
    @PutMapping("/UpdateDisease")
    public String updateDisease(@RequestParam("patientId")Integer patientId,@RequestParam("disease")String disease)
    {
        if(patientDb.containsKey(patientId))// validation patient exist or not
        {
            Patient patient= patientDb.get(patientId);//getting patientId
            patient.setDisease(disease);//setting patient disease
            patientDb.put(patientId,patient);// save patient
            return "update successfully";
        }
        else
        {
            return "patient does not exist";
        }

    }
    @PutMapping("/updatePatientDetails")
    public String updatePatientDetails(@RequestBody Patient patient)
    {
        int key= patient.getPatientId();
        if(patientDb.containsKey(key)) {
            patientDb.put(key, patient);
            return "patient update successfully";
        }
        else
        {
            return "data was not existing";
        }

    }

    @DeleteMapping("/deletePatient")
    public String deletePatient(@RequestParam("patientId")Integer patientId )
    {
        patientDb.remove(patientId);
        return "patient delete successfully";

    }
}
