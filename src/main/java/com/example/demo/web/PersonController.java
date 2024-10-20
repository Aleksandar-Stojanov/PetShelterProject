package com.example.demo.web;

import com.example.demo.model.Donations;
import com.example.demo.model.Person;
import com.example.demo.model.Shelter;
import com.example.demo.model.exceptions.NotFoundException;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/save-person")
public class PersonController {


    private final PersonService personService;
    private final ShelterService shelterService;
    private final DonationsService donationsService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PersonController(PersonService personService, ShelterService shelterService, DonationsService donationsService) {
        this.personService = personService;
        this.shelterService = shelterService;
        this.donationsService = donationsService;
    }

    @GetMapping
    public String savePerson(@RequestParam String personId,
                              @RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam Integer shelterId,
                              Model model) {
//        if (personId.length()!=13){
//            String message = "Your Donator Id must be 13 characters long!";
//            model.addAttribute("message", message);
//            return "redirect:/donations/new?message="+message;
//        }
        model.addAttribute("personId",personId);
        model.addAttribute("name",name);
        model.addAttribute("surname",surname);
        model.addAttribute("shelterId",shelterId);
        return "save_person";
    }

    @PostMapping
    public String handlePersonConfirmation(@RequestParam String choice,
                                            @RequestParam String personId,
                                            @RequestParam String name,
                                            @RequestParam String surname,
                                            @RequestParam Integer shelterId,
                                            Model model) {

        if ("yes".equals(choice)) {
//            if (personId.length()!=13){
//                String message = "Your Donator Id must be 13 characters long!";
//                model.addAttribute("message", message);
//                return "redirect:/donations/new/?message="+message;
//            }
            personService.save(new Person(personId,name,surname));
            Shelter shelter= shelterService.findById(shelterId).orElseThrow(() -> new NotFoundException("Shelter not found"));

            Donations donation = new Donations(personId,shelter);
            donationsService.save(donation);
            return "redirect:/donations/all";
        } else {
            String message = "Unsuccesful donation insertion!";
            model.addAttribute("message", message);
            return "redirect:/donations/new?message="+message;
        }
    }

}
