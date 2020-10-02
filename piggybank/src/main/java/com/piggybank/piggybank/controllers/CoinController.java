package com.piggybank.piggybank.controllers;
import com.piggybank.piggybank.models.Coin;

import com.piggybank.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//making it a bean by using a RestController
//provide a route
@RestController
public class CoinController
{
    @Autowired
    private CoinRepository coinrepos;

    private List<Coin> findCoin(List<Coin> myList, CheckCoin tester)
    {
        List<Coin> tempList = new ArrayList<>();

        for (Coin c : myList)
        {
            if (tester.test(c))
            {
                tempList.add(c);
            }
        }
        return tempList;
    }
    // http://localhost:2019/total
    @GetMapping(value = "/total", produces = {"application/json"})
    public ResponseEntity<?> displayTotal()
    {
        List<Coin> myList = new ArrayList<>();

        coinrepos.findAll().iterator().forEachRemaining(myList::add);

        double total = 0.00;
        for (Coin c : myList)
        {

            if (c.getQuantity() == 1)
            {
                System.out.println(c.getQuantity() + " " + c.getName());
                total = total + c.getValue() * c.getQuantity();
            } else
            {
                System.out.println(c.getQuantity() + " " + c.getNameplural());
                total = total + c.getValue() * c.getQuantity();
            }
        }
        System.out.println("piggy bank total: " + total);
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }
}