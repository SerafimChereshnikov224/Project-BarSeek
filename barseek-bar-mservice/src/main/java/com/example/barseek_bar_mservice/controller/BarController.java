package com.example.barseek_bar_mservice.controller;


import com.example.barseek_bar_mservice.model.entity.Bar;
import com.example.barseek_bar_mservice.model.entity.Drink;
import com.example.barseek_bar_mservice.security.UserPrincipal;
import com.example.barseek_bar_mservice.service.BarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bar-service-api/v1/bars")
public class BarController {

    private final BarService barService;

    @PostMapping
    public ResponseEntity<String> addNew(@RequestBody Bar bar,
                                         @AuthenticationPrincipal UserPrincipal principal) {

        //System.out.println("got req 1");
            Long ownerId = Long.parseLong(principal.getUsername());
            //System.out.println("got req 2");
            Bar newBar = barService.addNewBar(bar,ownerId);
        //System.out.println("got req 3");
            return new ResponseEntity<>("New bar created with name : " + newBar.getName(),HttpStatus.CREATED);

    }

    @GetMapping("/{barId}")
    public ResponseEntity<Bar> findById(@PathVariable("barId") Long id) {

            Bar barResp = barService.findBarById(id);
            return ResponseEntity.ok(barResp);

    }

    @GetMapping("/{barName}")
    public ResponseEntity<List<Bar>> findByName(@PathVariable("barName") String name) {

            List<Bar> barsResp = barService.findBarByName(name);
            return barsResp.isEmpty() ?
                    new ResponseEntity("No bars found", HttpStatus.NO_CONTENT) :
                    ResponseEntity.ok(barsResp);

    }

    @DeleteMapping("/{barId}")
    public ResponseEntity<String> deleteById(@PathVariable("barId") Long id,
                                             @AuthenticationPrincipal UserPrincipal principal) {

            Long ownerId = Long.parseLong(principal.getUsername());
            barService.deleteBarById(id,ownerId);
            return new ResponseEntity<>("Bar was deleted, id : " + id, HttpStatus.OK);

    }

    @PutMapping("/{barId}")
    public ResponseEntity<String> updateById(@PathVariable("barId") Long id,
                                             @RequestBody Bar updatedBar,
                                             @AuthenticationPrincipal UserPrincipal principal) {

            Long ownerId = Long.parseLong(principal.getUsername());
            Bar newBar = barService.updateBarById(id, updatedBar, ownerId);
            return new ResponseEntity<>("Bar was updated and saved, name : " + newBar.getName(), HttpStatus.OK);

    }

    @GetMapping("/{barId}/all")
    public ResponseEntity<List<Drink>> findAllDrinksById(@PathVariable("barId") Long id) {

            List<Drink> drinks = barService.findAllDrinksById(id);
            return drinks.isEmpty() ?
                    new ResponseEntity("No drinks in chosen bar!", HttpStatus.NO_CONTENT) :
                    ResponseEntity.ok(drinks);

    }

}
