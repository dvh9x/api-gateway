package com.example.pricemicroservice;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PriceController {
    @GetMapping("/price")
    public String getPrice() {
        log.info("Successfully found price info");
        return "20";
    }
}
