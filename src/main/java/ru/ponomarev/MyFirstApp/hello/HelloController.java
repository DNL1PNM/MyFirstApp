package ru.ponomarev.MyFirstApp.hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class HelloController {
    private ArrayList<String> stringList = new ArrayList<>();
    private HashMap<String, String> myMap = new HashMap<>();

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/update-array")
    public String updateArrayList(@RequestParam String s) {
        if (stringList.isEmpty()) {
            stringList = new ArrayList<>();
        }
        stringList.add(s);
        return "Added to the array: " + s;
    }
    @GetMapping("/show-array")
    public ResponseEntity<List<String>> showArrayList() {
        return ResponseEntity.status(HttpStatus.OK).body(stringList);
    }
    @GetMapping("/update-map")
    public String updateHashMap(@RequestParam("key") String key, @RequestParam("value") String value) {
        if (myMap.isEmpty()) {
            myMap = new HashMap<>();
        }
        myMap.put(key, value);;
        return "Value " + value + " has been added to the HashMap with key " + key + ".";
    }
    @GetMapping("/show-map")
    public ResponseEntity<HashMap<String, String>> showHashMap() {
        return new ResponseEntity<>(myMap, HttpStatus.OK);
    }
    @GetMapping("/show-all-length")
    public String showAllLength() {
        int arrayListLength = stringList.size();
        int hashMapLength = myMap.size();

        return "ArrayList length: " + arrayListLength + "\n"
                + "HashMap length: " + hashMapLength;
    }
}