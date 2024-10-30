package com.example.demo.web;

import com.example.demo.model.*;
import com.example.demo.model.exceptions.NotFoundException;
import com.example.demo.service.*;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class PetController {

    private final PetService petService;
    private final JdbcTemplate jdbcTemplate;
    private final AdopterService adopterService;
    private final ApplicationService applicationService;
    private final Pet_lives_at_shelter_Service petLivesAtShelterService;
    private final Medical_records_Service medicalRecordsService;
    private final ShelterService shelterService;
    // public PetController(PetService petService, JdbcTemplate jdbcTemplate, AdopterService adopterService, ApplicationService applicationService, Pet_lives_at_shelter_Service petLivesAtShelterService, Medical_records_Service medicalRecordsService, ShelterService shelterService){
    public PetController(PetService petService, JdbcTemplate jdbcTemplate, AdopterService adopterService, ApplicationService applicationService, Pet_lives_at_shelter_Service petLivesAtShelterService, ShelterService shelterService){
        this.petService = petService;
        this.jdbcTemplate = jdbcTemplate;
        this.adopterService = adopterService;
        this.applicationService = applicationService;
        this.petLivesAtShelterService = petLivesAtShelterService;
        // this.medicalRecordsService = medicalRecordsService;
        this.shelterService = shelterService;
    }

//    @GetMapping("/pet-home")
//    public String showPetLessInfo(Model model) {
//        String sql = "SELECT * FROM pet_less_info";
//        List<Map<String, Object>> petLessInfoList = jdbcTemplate.queryForList(sql);
//        model.addAttribute("petLessInfoList", petLessInfoList);
//        return "pet_less_info";
//    }

    @GetMapping("/pet-home")
    public String showFilteredPets(
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "species", required = false) String species,
            @RequestParam(name = "message", required = false) String message,
            @RequestParam(name = "adoption_status", required = false) String adoption_status,
            Model model) {

        String sql = "SELECT * FROM pet_less_info1 WHERE 1=1";

        if (gender != null && !gender.isEmpty()) {
            sql += " AND gender = '" + gender + "'";
        }

        if (species != null && !species.isEmpty()) {
            sql += " AND species = '" + species + "'";
        }
        if (adoption_status != null && !adoption_status.isEmpty()) {
            sql += " AND adoption_status = '" + adoption_status + "'";
        }
        List<Map<String, Object>> petLessInfoList = jdbcTemplate.queryForList(sql);
        model.addAttribute("petLessInfoList", petLessInfoList);
        return "pet_less_info";
    }

    @GetMapping("/pet/{id}")
    public String viewPetDetails(@PathVariable Integer id,
                                 @RequestParam(name = "message", required = false) String message,
                                 Model model) {
        String sql = "SELECT * FROM pet_more_info WHERE id_pet = ?";
        Map<String, Object> petMoreInfo = jdbcTemplate.queryForMap(sql, id);
        model.addAttribute("petMoreInfo", petMoreInfo);
        model.addAttribute("message",message);
        return "pet_more_info";
    }

    @GetMapping("/pet/{id}/adopt")
    public String showAdoptionForm(@PathVariable Integer id,
                                   @RequestParam(required = false) String message,
                                   Model model) {
        Pet pet = petService.findById(id).get();
        model.addAttribute("pet",pet);
        model.addAttribute("message",message);
        if (pet.getAdoption_status().equals("Adopted")) {
            String message1 = "This pet is already adopted, please select another one!";
            model.addAttribute("message", message1);
            return "redirect:/pet/"+id+"?message="+message1;
        }
        return "adopt_pet";
    }

    @PostMapping("/adopt")
    public String submitAdoptionForm(@RequestParam Integer petId,
                                     @RequestParam String adopterId,
                                     @RequestParam String name,
                                     @RequestParam String surname,
                                     Model model) {

        Pet pet = petService.findById(petId).orElseThrow(() -> new NotFoundException("Pet not found"));

        Optional<Adopter> adopter = adopterService.findById(adopterId);
        if(adopter.isEmpty()){
            return "redirect:/save-adopter?adopterId=" + adopterId + "&name=" + name + "&surname=" + surname+"&petId="+petId;
//            adopterService.save(new Adopter(adopterId,name,surname));
//            return "redirect:/save-adopter";
        }
        else if(!adopter.get().getName().equals(name) || !adopter.get().getSurname().equals(surname)){
            return "redirect:/pet/"+petId+"/adopt?message=Your Id doesn't match with your name or surname!";
        }
        Application application = new Application(adopter.get().getPersonal_identification_number(), null, pet);
        applicationService.saveApplication(application);

        return "redirect:/pet-home";
    }

    @GetMapping("/pet/add")
    public String showAddPetForm(Model model) {
//        model.addAttribute("pet", new Pet());
        return "addPet";
    }

    @PostMapping("/pet/add")
    public String addPet(
                         @RequestParam(name="name",defaultValue = "") String name,
                         @RequestParam("gender") char gender,
                         @RequestParam("species") String species,
                         @RequestParam(name="breed",defaultValue = "") String breed,
                         @RequestParam(name="date_of_birth")Date date_of_birth,
                         @RequestParam(name="medical_records",defaultValue = "Unknown")String medical_records,
                         @RequestParam("date_of_examination")Date date_of_examination,
                         @RequestParam("vaccinations") String vaccinations,
                         @RequestParam("id_shelter") String id_shelter,
                         Model model) {
        Pet pet = new Pet(name,gender,species,"Not adopted",breed,date_of_birth);
        petService.save(pet);
        Shelter shelter = shelterService.findById(Integer.parseInt(id_shelter)).get();
        Pet_lives_at_shelter_Id petlivesid=new Pet_lives_at_shelter_Id(pet,shelter);
        LocalDateTime currentDateTime = LocalDateTime.now();
        Date currentDate = Date.valueOf(currentDateTime.toLocalDate());
        Pet_lives_at_shelter petLivesAtShelter=new Pet_lives_at_shelter(petlivesid,currentDate);
        petLivesAtShelterService.save(petLivesAtShelter);
        Medical_records mr = new Medical_records(medical_records,date_of_examination,vaccinations,pet);
        medicalRecordsService.save(mr);
        return "redirect:/pet-home";
    }

}
