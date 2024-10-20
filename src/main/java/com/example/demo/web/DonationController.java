package com.example.demo.web;

import com.example.demo.model.*;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/donations")
public class DonationController {

    private final DonationsService donationsService;
    private final ShelterService shelterService;
    private final PersonService personService;
    private final StaffService staffService;
    private final Staff_works_at_shelter_Service staffWorksAtShelterService;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DonationController(DonationsService donationsService, ShelterService shelterService, PersonService personService, StaffService staffService, Staff_works_at_shelter_Service staffWorksAtShelterService) {
        this.donationsService = donationsService;
        this.shelterService = shelterService;
        this.personService = personService;
        this.staffService = staffService;
        this.staffWorksAtShelterService = staffWorksAtShelterService;
    }

    @GetMapping("/all")
    public String viewDonationsAndDonators(Model model) {
        String sql = "SELECT * FROM donations_and_donators";
        List<Map<String, Object>> donationsAndDonatorsList = jdbcTemplate.queryForList(sql);
        model.addAttribute("donationsAndDonatorsList", donationsAndDonatorsList);
        return "donationsAndDonators";
    }

    @GetMapping("/new")
    public String showDonationForm(@RequestParam(required = false) String message,
                                   Model model) {
        model.addAttribute("message",message);
        return "donationForm";
    }

    @PostMapping("/add")
    public String addDonation(@RequestParam String personalIdentificationNumber,
                              @RequestParam Integer shelterId,
                              @RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String personalIdentificationNumberStaff) {
        Shelter shelter = shelterService.findById(shelterId).orElseThrow(() -> new NotFoundException("Shelter not found"));
        Staff staff = staffService.findById(personalIdentificationNumberStaff).orElseThrow(() -> new NotFoundException("Staff not found"));
//        List<Staff_works_at_shelter> staffWorksAtShelters = staffWorksAtShelterService.findAll();
//        Staff_works_at_shelter staffWorksAtShelter = staffWorksAtShelterService.findById(new Staff_works_at_shelter_id(staff,shelter)).orElseThrow(() -> new NotFoundException("Staff doesn't work in that shelter!"));
//        if(staffWorksAtShelter == null){
//            return "redirect:/pet-home";
//        }

        Optional<Person> person = personService.findById(personalIdentificationNumber);
        if(person.isEmpty()){
            return "redirect:/save-person?personId=" + personalIdentificationNumber + "&name=" + name + "&surname=" + surname+"&shelterId="+shelterId;

        }
        else if(!person.get().getName().equals(name) || !person.get().getSurname().equals(surname)){
            return "redirect:/donations/new?message=The Donator Id doesn't match with their name or surname!";
        }
        Donations donations = new Donations(person.get().getPersonal_identification_number(),shelter);
        donationsService.save(donations);

        return "redirect:/donations/all";
    }

}
