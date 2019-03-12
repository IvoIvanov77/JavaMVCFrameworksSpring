package org.softuini.exodia.web.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.modelmapper.ModelMapper;
import org.softuini.exodia.domain.models.binding.DocumentScheduleBindingModel;
import org.softuini.exodia.domain.models.binding.PrintBindingModel;
import org.softuini.exodia.domain.models.service.DocumentServiceModel;
import org.softuini.exodia.domain.models.view.DocumentDetailsViewModel;
import org.softuini.exodia.service.DocumentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;

@Controller
public class DocumentController extends BaseController {
    private final DocumentService documentService;

    private final ModelMapper modelMapper;

    public DocumentController(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/schedule")
    public ModelAndView schedule(HttpSession session) {
        if (!hasLoggedInUser(session)) {
            return redirect("/login");
        }
        return this.render("schedule");
    }

    @PostMapping("/schedule")
    public ModelAndView scheduleAction(@ModelAttribute DocumentScheduleBindingModel bindingModel,
                                       HttpSession session) {
        if (!hasLoggedInUser(session)) {
            return redirect("/login");
        }
        DocumentServiceModel serviceModel = this.modelMapper.map(bindingModel,
                DocumentServiceModel.class);
        String id = this.documentService.scheduleDocument(serviceModel);

        return this.redirect(String.format("/details?id=%s", id));
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") String id, HttpSession session) {
        if (!hasLoggedInUser(session)) {
            return redirect("/login");
        }
        DocumentDetailsViewModel model = this.modelMapper
                .map(this.documentService.getById(id), DocumentDetailsViewModel.class);
        ModelAndView modelAndView = this.render("details");

        modelAndView.addObject("model", model);
        return modelAndView;
    }

    @GetMapping("/print")
    public ModelAndView print(@RequestParam("id") String id, HttpSession session) {
        if (!hasLoggedInUser(session)) {
            return redirect("/login");
        }
        DocumentDetailsViewModel model = this.modelMapper
                .map(this.documentService.getById(id), DocumentDetailsViewModel.class);
        ModelAndView modelAndView = this.render("print");
        modelAndView.addObject("model", model);
        return modelAndView;
    }

    @PostMapping("/print")
    public ModelAndView printAction(@RequestParam("id") String id, HttpSession session,
                                    @ModelAttribute PrintBindingModel bindingModel) {
        if (!hasLoggedInUser(session)) {
            return redirect("/login");
        }
        this.documentService.printDocument(id);
        return redirect("/");
    }

    @GetMapping("/getpdf")
    public ResponseEntity<byte[]> getPDF(PrintBindingModel bindingModel) throws DocumentException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk title = new Chunk(bindingModel.getTitle(), font);
        Chunk content = new Chunk(bindingModel.getContent(), font);

        document.add(title);
        document.add(content);
        document.close();

        byte[] pdfBytes = byteArrayOutputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        return response;
    }
}
