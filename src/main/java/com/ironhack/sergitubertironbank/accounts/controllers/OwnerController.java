package com.ironhack.sergitubertironbank.accounts.controllers;

import com.ironhack.sergitubertironbank.accounts.shared.Owner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable Long id) {
        return null;
    }
}
