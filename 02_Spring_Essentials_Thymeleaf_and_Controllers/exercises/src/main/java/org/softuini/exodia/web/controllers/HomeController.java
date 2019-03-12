package org.softuini.exodia.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuini.exodia.domain.models.view.DocumentListViewModel;
import org.softuini.exodia.service.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController {
    private final DocumentService documentService;

    private final ModelMapper modelMapper;

    public HomeController(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    private List<DocumentListViewModel> allDocuments(){
        return this.documentService.listAllDocuments()
                .stream()
                .map(serviceModel -> this.modelMapper.map(serviceModel, DocumentListViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/")
    public ModelAndView home(HttpSession session){
        if(session.getAttribute("currentUser") == null){
            return this.render("fragments/home-guest");
        }else{
            return this.render("fragments/home-user")
                    .addObject("documents", this.allDocuments());
        }

    }
}
