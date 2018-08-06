package com.project.packman.packman.controller.newRequestSection;

import com.project.packman.packman.error.RequestNotFoundException;
import com.project.packman.packman.model.Request;
import com.project.packman.packman.model.RolesType.Status;
import com.project.packman.packman.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/request")
public class RequestController implements WebMvcConfigurer {

    @Autowired
    private RequestService requestService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/confirm").setViewName("request-section/request-confirm");
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNewRequest(Model model){
        model.addAttribute("request",new Request());
        return "request-section/new-request";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String validateRequest(Model model, @Valid Request request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "request-section/new-request";
        }
        requestService.saveNewRequest(request);
        return "redirect:/confirm";
    }

    @GetMapping("/{requestId}")
    public Request getRequestDetails(@PathVariable String id) throws RequestNotFoundException {
        return requestService.getRequestById(id);
    }
}
