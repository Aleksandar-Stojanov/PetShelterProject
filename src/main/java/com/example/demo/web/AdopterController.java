package com.example.demo.web;

import com.example.demo.model.Adopter;
import com.example.demo.model.Pet;
import com.example.demo.model.Application;
import com.example.demo.model.exceptions.NotFoundException;
import com.example.demo.service.AdopterService;
import com.example.demo.service.ApplicationService;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/save-adopter")
public class AdopterController {

    private final AdopterService adopterService;
    private final PetService petService;
    private final ApplicationService applicationService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AdopterController(AdopterService adopterService, PetService petService, ApplicationService applicationService) {
        this.adopterService = adopterService;
        this.petService = petService;
        this.applicationService = applicationService;
    }

    @GetMapping
    public String saveAdopter(@RequestParam String adopterId,
                              @RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam Integer petId,
                              Model model) {
//        if (adopterId.length()!=13){
//            String message = "Your Id must be 13 characters long!";
//            model.addAttribute("message", message);
//            return "redirect:/pet/"+petId+"/adopt?message="+message;
//        }
        model.addAttribute("adopterId",adopterId);
        model.addAttribute("name",name);
        model.addAttribute("surname",surname);
        model.addAttribute("petId",petId);
        return "save_adopter";
    }

    @PostMapping
    public String handleAdopterConfirmation(@RequestParam String choice,
                                            @RequestParam String adopterId,
                                            @RequestParam String name,
                                            @RequestParam String surname,
                                            @RequestParam Integer petId,
                                            Model model) {
        if ("yes".equals(choice)) {
//            if (adopterId.length()!=13){
//                String message = "Your Id must be 13 characters long!";
//                model.addAttribute("message", message);
//                return "redirect:/pet/"+petId+"/adopt?message="+message;
//            }
            //TODO:proveri dali se kreira nov person ako ne e dodaden samo vo Adopters
            adopterService.save(new Adopter(adopterId, name, surname));
            Pet pet = petService.findById(petId).orElseThrow(() -> new NotFoundException("Pet not found"));
            if (pet.getAdoption_status().equals("Adopted")){
                String message = "This pet is already adopted, please select another one!";
                model.addAttribute("message", message);
                return "redirect:/pet/"+petId+"?message="+message;
            }
            Application application = new Application(adopterId, null, pet);
            applicationService.saveApplication(application);
            return "redirect:/pet-home";
        } else {
            String message = "Unsuccesful application for adoption!";
            model.addAttribute("message", message);
            return "redirect:/pet/"+petId+"?message="+message;
        }
    }

}
