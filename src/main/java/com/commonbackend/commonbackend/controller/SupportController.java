package com.commonbackend.commonbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupportController {

    @GetMapping("/supportissues/{customerId}")
    public ResponseEntity<?> getSupportIssueTicketsById(@PathVariable long customerId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/supportissues")
    public ResponseEntity<?> getAllSupportIssueTickets() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("supportissue/{customerId}")
    public ResponseEntity<?> postSupportIssue(@PathVariable long id, @RequestBody Object obj) { // ? = customer support
                                                                                                // object
        return ResponseEntity.ok().build();
    }

}
