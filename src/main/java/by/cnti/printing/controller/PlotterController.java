package by.cnti.printing.controller;

import by.cnti.printing.dto.PlotterDto;
import by.cnti.printing.entity.Department;
import by.cnti.printing.entity.Plotter;
import by.cnti.printing.entity.TypeOfPaper;
import by.cnti.printing.service.interfaceService.DepartmentService;
import by.cnti.printing.service.interfaceService.PlotterService;
import by.cnti.printing.service.interfaceService.TypeOfPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class PlotterController {

    private PlotterService plotterService;
    private TypeOfPaperService typeOfPaperService;
    private DepartmentService departmentService;

    @Autowired
    public PlotterController(PlotterService plotterService, TypeOfPaperService typeOfPaperService, DepartmentService departmentService) {
        this.plotterService = plotterService;
        this.typeOfPaperService = typeOfPaperService;
        this.departmentService = departmentService;
    }

    @ModelAttribute("plotter")
    public Plotter plotter(){
        return new Plotter();
    }

    @ModelAttribute("plotterDto")
    public PlotterDto plotterDto(){
        return new PlotterDto();
    }

    @GetMapping("/plotter-save")
    public String plotterSaveGet (Model model){
        Iterable<Department> allDepartments = departmentService.findAll();
        Iterable<TypeOfPaper> allPaper = typeOfPaperService.findAll();
        model.addAttribute("allPaper", allPaper);
        model.addAttribute("allDepartments",allDepartments);
        return "plotterSavePage";
    }

    @PostMapping("/plotter-save")
    public String plotterSavePost (PlotterDto plotterDto){
        Plotter plotter = new Plotter();
        plotter.setDate(LocalDate.now());
        plotter.setDocumentName(plotterDto.getDocumentName());
        plotter.setDepartment(departmentService.findById(plotterDto.getDepartmentId()).get());
        plotter.setTypeOfPaper(typeOfPaperService.findById(plotterDto.getTypeOfPaperId()).get());
        plotter.setRollWidth(plotterDto.getRollWidth());
        plotter.setCyanColor(plotterDto.getCyanColor());
        plotter.setKeyColor(plotterDto.getKeyColor());
        plotter.setLightCyanColor(plotterDto.getLightCyanColor());
        plotter.setLightMagentaColor(plotterDto.getMagentaColor());
        plotter.setMagentaColor(plotterDto.getMagentaColor());
        plotter.setYellowColor(plotterDto.getYellowColor());
        plotterService.save(plotter);
        return "redirect:/plotter-save";
    }
}
