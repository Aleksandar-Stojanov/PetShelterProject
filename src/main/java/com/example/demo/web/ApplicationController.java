package com.example.demo.web;

import com.example.demo.model.*;
import com.example.demo.service.AdopterService;
import com.example.demo.service.Adopter_adopts_pet_Service;
import com.example.demo.service.ApplicationService;
import com.example.demo.service.PetService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final PetService petService;
    private final Adopter_adopts_pet_Service adopterAdoptsPetService;
    private final AdopterService adopterService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ApplicationController(ApplicationService applicationService, PetService petService, Adopter_adopts_pet_Service adopterAdoptsPetService, AdopterService adopterService) {
        this.applicationService = applicationService;
        this.petService = petService;
        this.adopterAdoptsPetService = adopterAdoptsPetService;
        this.adopterService = adopterService;
    }

    @GetMapping("/all")
    public String viewApplicationsAndApplicants(Model model) {
        String sql = "SELECT * FROM applications_and_applicants WHERE adoption_status='Not adopted'";
        List<Map<String, Object>> applicationsAndApplicantsList = jdbcTemplate.queryForList(sql);
        model.addAttribute("applicationsAndApplicantsList", applicationsAndApplicantsList);
        String sql1 = "SELECT * FROM applications_and_applicants WHERE adoption_status='Adopted' AND reviewed_by!=''";
        List<Map<String, Object>> applicationsAndApplicantsListAdopted = jdbcTemplate.queryForList(sql1);
        model.addAttribute("applicationsAndApplicantsListAdopted", applicationsAndApplicantsListAdopted);

        return "applicationsAndApplicants";
    }
    @GetMapping("/review/{id}")
    public String reviewApplication(@PathVariable Integer id,
                                    Model model) {
        String sql = "SELECT * FROM applications_and_applicants WHERE id_application = ?";
        Map<String, Object> applicationDetails = jdbcTemplate.queryForMap(sql, id);
        Application app = applicationService.findById(id).get();
        String staffPin = app.getPersonal_identification_number_staff();
        String adopterPin = app.getPersonal_identification_number_adopter();
        Integer petId=app.getPet().getId_pet();
        model.addAttribute("staffPin",staffPin);
        model.addAttribute("adopterPin",adopterPin);
        model.addAttribute("petId",petId);
        model.addAttribute("id_application",id);
        model.addAttribute("applicationDetails", applicationDetails);
        return "reviewApplication";
    }
    @Transactional
    @PostMapping("/allow-adoption")
    public String allowAdoption(@RequestParam Integer id_application,
                                @RequestParam String staffPin,
                                @RequestParam String adopterPin,
                                @RequestParam Integer petId){
        applicationService.allowAdoptionAndUpdateApplication(id_application,staffPin);
        petService.updatePetAdoptionStatus(petId,"Adopted");
        Pet pet = petService.findById(petId).get();
        Adopter adopter =adopterService.findById(adopterPin).get();
        AdopterAdoptsPetId aap=new AdopterAdoptsPetId(adopter, pet);
        adopterAdoptsPetService.save(new Adopter_adopts_pet(aap));
        return "redirect:/applications/all";
    }

    @PostMapping("/delete")
    public String deleteApplication(@RequestParam Integer id_application) {

        return "redirect:/applications/all";
    }
}