package com.goshadydar.rest.goshadudar.controller;

import com.goshadydar.rest.goshadudar.model.ItEmploees;
import com.goshadydar.rest.goshadudar.repository.ItEmploeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller

public class ItEmploeessController {
    @Autowired
    private ItEmploeesRepository itEmploeesRepository;

    @GetMapping("/itemp")
    public String itemp( Model model) {
        Iterable<ItEmploees> itEmploees = itEmploeesRepository.findAll();
        model.addAttribute("itEmploees",itEmploees);
        return "itemp";
    }

    @GetMapping("/itemp/add")
    public String itempAdd( Model model) {
        return "itemp-add";
    }

    @PostMapping("/itemp/add")
    public String itempPostAdd(@RequestParam String name,@RequestParam String department,@RequestParam int salaries, Model model){
        ItEmploees itemploees = new ItEmploees(name,department,salaries);
        itEmploeesRepository.save(itemploees);
        return "redirect:/itemp";
    }

    @GetMapping("/itemp/{id}")
    public String itempDetail(@PathVariable(name="id") long id, Model model) {
        if(!itEmploeesRepository.existsById(id)){
            return "500";
           // return "redirect:/itemp";
        }
        Optional<ItEmploees> itEmp = itEmploeesRepository.findById(id);
        ArrayList<ItEmploees> res = new ArrayList<>();
        itEmp.ifPresent(res::add);
        model.addAttribute("itEmp",res);
        return "itemp-details";
        //2
//        Optional<ItEmploees> itEmp = itEmploeesRepository.findAllById(id,); может вариант, но стоит еще почитать
//
//        model.addAttribute("itEmp",itEmp);
//        return "itemp-details";
//3
//        Optional<ItEmploees> itEmp = itEmploeesRepository.findById(id);
//        if (itEmp.isEmpty())
//            return "404";
//        model.addAttribute("itEmp", itEmp.get());
//        return "itemp-details";

    }

    @GetMapping("itemp/{id}/edit")
    public String itempEdit(@PathVariable(name="id") long id, Model model) {
        if(!itEmploeesRepository.existsById(id)){
            return "redirect:/itemp";
        }
        Optional<ItEmploees> itEmp = itEmploeesRepository.findById(id);
        ArrayList<ItEmploees> res = new ArrayList<>();
        itEmp.ifPresent(res::add);
        model.addAttribute("itEmp",res);
        return "itemp-edit";
    }

    @PostMapping("/itemp/{id}/edit")
    public String itempPostUpdate(@PathVariable(name="id") long id,@RequestParam String name,@RequestParam String department,@RequestParam int salaries, Model model){
        ItEmploees itemploees = itEmploeesRepository.findById(id).orElseThrow();
        itemploees.setName(name);
        itemploees.setDepartment(department);
        itemploees.setSalaries(salaries);

        itEmploeesRepository.save(itemploees);
        return "redirect:/itemp";
    }

    @PostMapping("/itemp/{id}/remove")
    public String itempPostRemove(@PathVariable(name="id") long id, Model model){
        ItEmploees itemploees = itEmploeesRepository.findById(id).orElseThrow();
        itEmploeesRepository.delete(itemploees);
        return "redirect:/itemp";
    }
}
