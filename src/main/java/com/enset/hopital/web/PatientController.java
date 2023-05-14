package com.enset.hopital.web;

import com.enset.hopital.entities.Patient;
import com.enset.hopital.repository.PatientRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class  PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }
    @GetMapping("/user/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kwd){
        Page<Patient> pagePatients=patientRepository.findByNomContains(kwd,PageRequest.of(page,size));
        model.addAttribute("pages", new int[pagePatients.getTotalPages()] );
        model.addAttribute("ListPatients", pagePatients.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword",kwd);
        return "patients";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id,String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword+"&page="+page;
    }
    @GetMapping("/admin/formPatients")
    public String formPatient(Model model){

        model.addAttribute("patient", new Patient());
        return "formPatients";
    }
    @PostMapping("/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword ){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword+"";
    }
    @GetMapping("/admin/editPatient")
    public String editPatient(Model model, Long id, String keyword, int page ){
        Patient patient=patientRepository.findById(id).orElse(null);
        if (patient==null)throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient", patient);
        model.addAttribute("page", page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }

}
