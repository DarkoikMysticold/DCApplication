package com.distancecalc.DCApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class MainController {

    @Autowired
    private CityHelperImpl helper;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistanceRepository distanceRepository;

    @GetMapping(value="/all", produces = MediaType.APPLICATION_XML_VALUE)
    public Cities getAllUsers() {

            return helper.findAll();
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public String calculateDistance(@RequestParam("cityname1") String cityname1,
                                    @RequestParam("cityname2") String cityname2,
                                    @RequestParam("calculateMethod") String method){
        switch (method) {
            case "CrowFlight":
                try {
                    double distance = helper.Crowflight(cityname1, cityname2);
                    return "The Crowflight distance is " + distance + "!";
                } catch (RuntimeException e) {
                    return e.getMessage();
                }
            case "Distance_Matrix":
                try {
                    double distance = helper.DistanceMatrix(cityname1, cityname2);
                    return "The distance in the distance matrix is " + distance + "!";
                } catch (RuntimeException e) {
                    return e.getMessage();
                }
            case "All":
                try {
                    double crow = helper.Crowflight(cityname1, cityname2);
                    double matrix = helper.DistanceMatrix(cityname1, cityname2);
                    return "The distance by distance matrix is " + matrix + " and distance by crowflight is " + crow + "!";

                } catch (RuntimeException e) {
                    return e.getMessage();
                }
            default:
                return "No such method exists";
        }
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.GET)
    public String provideCalculationInfo(){
        return "С помощью того же URL вы можете расчитать интересующую вас дистанцию";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                InputStream fileStream = file.getInputStream();

                fileStream.close();
                return "Вы удачно загрузили " + file.getName() + " !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + file.getName() + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + file.getName() + " потому что файл пустой.";
        }
    }

    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "Вы можете загружать файл с использованием того же URL.";
    }
}
