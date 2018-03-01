package by.cnti.printing.controller;

import by.cnti.printing.dto.BidDto;
import by.cnti.printing.entity.*;
import by.cnti.printing.service.interfaceService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Controller
public class BidPageController {

    private static final String PARENT_PATH = "src" + File.separator;
    public static final String DIR_PATH = PARENT_PATH + File.separator;

    private BidService bidService;
    private DepartmentService departmentService;
    private PaperSizeService paperSizeService;
    private PaperDensityService paperDensityService;
    private PrinterModelService printerModelService;
    private PlotterService plotterService;
    private StatusWorkService statusWorkService;

    @Autowired
    public BidPageController(BidService bidService,
                             DepartmentService departmentService,
                             PaperSizeService paperSizeService,
                             PaperDensityService paperDensityService,
                             PrinterModelService printerModelService,
                             PlotterService plotterService, StatusWorkService statusWorkService) {
        this.bidService = bidService;
        this.departmentService = departmentService;
        this.paperSizeService = paperSizeService;
        this.paperDensityService = paperDensityService;
        this.printerModelService = printerModelService;
        this.plotterService = plotterService;
        this.statusWorkService = statusWorkService;
    }

    @ModelAttribute("bid")
    public Bid bid() {
        return new Bid();
    }

    @ModelAttribute("bidDto")
    public BidDto bidDto() {
        return new BidDto();
    }

    @GetMapping("/")
    public String bidPageGet(Model model) {
        createRepartBidForMounth();
        Iterable<PaperSize> allPaperSize = paperSizeService.findAll();
        Iterable<PaperDensity> allPaperDensity = paperDensityService.findAll();
        Iterable<Department> allDepartments = departmentService.findAll();
        Iterable<PrinterModel> allPrinterModel = printerModelService.findAll();
        model.addAttribute("allPaperSize", allPaperSize);
        model.addAttribute("allDepartments", allDepartments);
        model.addAttribute("allPaperDensity", allPaperDensity);
        model.addAttribute("allPrinters", allPrinterModel);
        return "homePage";
    }

    @PostMapping("/")
    public String bidPagePost(BidDto bidDto) {
        createRepartBidForMounth();
        Bid bid = new Bid();
        StatusWork statusWork = new StatusWork();
        statusWork.setId(1L);
        Department department = departmentService.findById(bidDto.getDepartmentId()).get();
        PaperDensity paperDensity = paperDensityService.findById(bidDto.getPaperDensityId()).get();
        PrinterModel printerModel = printerModelService.findById(bidDto.getPrinterModelId()).get();
        PaperSize paperSize = paperSizeService.findById(bidDto.getPaperSizeId()).get();

        bid.setDocumentName(bidDto.getDocumentName());
        bid.setCustomerOder(bidDto.getCustomerOder());
        bid.setEdition(bidDto.getEdition());
        bid.setSheets(bidDto.getSheets());

        bid.setPaperSize(paperSize);
        bid.setDepartment(department);
        bid.setPaperDensity(paperDensity);
        bid.setPrinter(printerModel);
        bid.setDate(LocalDate.now());
        bid.setStatusWork(statusWork);
        bidService.save(bid);
        return "redirect:/";
    }

    @GetMapping("/list-bids-now-mount")
    public String listBidGet(Model model) {
        Iterable<Bid> all = bidService.findAllNowMonth();
        Iterable<Plotter> allPlotter = plotterService.findAllPlotterMountNow();
        model.addAttribute("allPlotter", allPlotter);
        model.addAttribute("all", all);
        return "listBidPage";
    }

    @GetMapping("/list-bids-last-mount")
    public String listBidLastGet(Model model) {
        Iterable<Bid> all = bidService.findAllLastMonth();
        Iterable<Plotter> allPlotter = plotterService.findAllPlotterByLastMounts();
        model.addAttribute("allPlotter", allPlotter);
        model.addAttribute("all", all);
        return "listBidLastMountsPage";
    }

    @GetMapping("/approval-bid")
    public String approvalBidGet(Model model) {
        List<Bid> allByAllowIsNull = bidService.findAllByAllowIsNull();
        model.addAttribute("bids", allByAllowIsNull);
        return "approvalBidPage";
    }

    @PostMapping("/approval-bid")
    public String approvalBidPost (Model model, Bid bid){
        Long id = bid.getId();
        model.addAttribute("id", id);
        return "redirect:/approval-bid/{id}";
    }

    @GetMapping("redir-page/{id}")
    public String redirPage (@PathVariable("id") Long id){
        bidService.updateBid(id);
        return "redirect:/approval-bid";
    }

    @GetMapping("/approval-bid/{id}")
    public String approvalIdBid (@PathVariable("id") Long id){

        return "redirect:/approval-bid";
    }

    public void createRepartBidForMounth (){
        File directory = new File(DIR_PATH);
        File report = new File(directory,"reportsForMount.doc");
        System.out.println(report.getAbsolutePath());
        try(InputStream inputStream = new BufferedInputStream(new FileInputStream(report))) {
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter(";");
            while(scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(Reader reader = new BufferedReader(new FileReader(report))) {
            int result = 0;
            while(result != -1) {
                result = reader.read();
                System.out.println((char) result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(report)))) {
            outputStream.writeUTF(bidService.findAll().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
