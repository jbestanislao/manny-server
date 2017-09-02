package com.manny.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class MainController {
    private Map<String, Data> dataStorage = new HashMap<>();

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "I am alive";
    }

    /** It stores the Data object using "name" as a key. **/
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody Data data) {
        dataStorage.put(data.getCustNo(), data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/{custNo}", method = RequestMethod.GET)
    public ResponseEntity<Data> get(@PathVariable(value = "custNo") final String custNo) {
        Data data = dataStorage.get(custNo);
        if (data != null) {
            return new ResponseEntity<Data>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
        }
    }

    private static class Data {
        private String custNo;
        private String value;

        public void setName(String custNo) {
            this.custNo = custNo;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getCustNo() {
            return custNo;
        }

        public String getValue() {
            return value;
        }
    }
}
