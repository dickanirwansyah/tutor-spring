package com.dicka.junittestexample;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/test")
//cara membuat test --> click lampu pada editor
//kemudian pilih "create test"
public class HelloExampleResource {

    @GetMapping
    public String getHelloWorld(){
        return "Hello world !";
    }

    @GetMapping(value = "/check")
    public String chekSpellString(){
       return "test string";
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Hello checkJson(){
        return new Hello("denada rosa florina", "mantan terindah");
    }

    private class Hello{

        private String title;
        private String value;

        public Hello(String title, String value){
            this.title = title;
            this.value = value;
        }

        public String getTitle(){
            return title;
        }

        public void setTitle(String title){
            this.title = title;
        }

        public String getValue(){
            return value;
        }

        public void setValue(String value){
            this.value = value;
        }
    }
}
