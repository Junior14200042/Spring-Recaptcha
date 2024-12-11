package com.devjr.recaptcha.controllers;

import com.devjr.recaptcha.models.EmployeeEntity;
import com.devjr.recaptcha.services.EmployeeService;
import com.devjr.recaptcha.services.RecaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeEntityController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private RecaptchaService recaptchaService;

    @GetMapping("/all")
    public String inicio(Model model){

        List<EmployeeEntity> employeeEntityList = employeeService.findAll();
        model.addAttribute("employees",employeeEntityList);

        return "inicio";
    }

    @GetMapping("/create/form")
    public String showForm(Model model){
        model.addAttribute("employee", new EmployeeEntity());
        return "form";
    }


    @PostMapping("/create/process")
    public String create(@ModelAttribute(name="employee") EmployeeEntity employeeEntity, @RequestParam(name="g-recaptcha-response") String captcha, Model model){

        boolean captchaValid = recaptchaService.validateRecaptcha(captcha);

        if(!captchaValid){
            employeeService.createEmployeeEntity(employeeEntity);
        }else{
            model.addAttribute("message","captcha invalido");
            return "error";
        }

        return "redirect:/all";

    }
}
