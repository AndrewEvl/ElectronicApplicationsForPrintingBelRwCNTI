package by.cnti.printing.controller;

import by.cnti.printing.dto.BidDto;
import by.cnti.printing.entity.*;
import by.cnti.printing.service.interfaceService.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Map;

@Controller
public class BidPageController {

    //    private static final String PARENT_PATH = "src" + File.separator;
//    private static final String DIR_PATH = PARENT_PATH + File.separator;
    private Long ID;

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
//        createReportBidForMount();
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
        Bid bid = new Bid();
        StatusWork statusWork = new StatusWork();
        statusWork.setId(1L);
        Department department = departmentService.findById(bidDto.getDepartmentId()).get();
        PaperDensity paperDensity = paperDensityService.findById(bidDto.getPaperDensityId()).get();
        PrinterModel printerModel = printerModelService.findById(bidDto.getPrinterModelId()).get();
        PaperSize paperSize = paperSizeService.findById(bidDto.getPaperSizeId()).get();

        bid.setDocumentName(bidDto.getDocumentName());
        bid.setCustomerOder(bidDto.getCustomerOder());
        if (bidDto.getEdition() != 1) {
            bid.setNumberOfPages(bidDto.getNumberOfPages() * bidDto.getEdition());
        } else {
            bid.setNumberOfPages(bidDto.getNumberOfPages());
        }
        bid.setEdition(bidDto.getEdition());
        bid.setStitching(bidDto.getStitching());
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
        return "listBidFromUser";
    }

    @GetMapping("/list-bids-last-mount")
    public String listBidLastGet(Model model) {
        createReportBidForMount();
        Iterable<Bid> all = bidService.findAllLastMonth();
        Iterable<Plotter> allPlotter = plotterService.findAllPlotterByLastMounts();
        model.addAttribute("allPlotter", allPlotter);
        model.addAttribute("all", all);
        return "listBidLastMountsPage";
    }

    @GetMapping("/approval-bid-list")
    public String approvalBidGet(Model model) {
        List<Bid> allByAllowIsNull = bidService.findAllByAllowIsNull();
        model.addAttribute("bids", allByAllowIsNull);
        return "approvalBidPage";
    }

    @GetMapping("/approval-bid")
    public String approvalBidStatusGet(Bid bid) {
        Bid approvalBid = bidService.findById(bid.getId()).get();
        approvalBid.setAllow("Одобренно");
        bidService.save(approvalBid);
        return "redirect:/approval-bid-list";
    }

    @GetMapping("/admin-list-page")
    public String adminListPage(Model model) {
        Iterable<Bid> all = bidService.findAllNowMonth();
        Iterable<Plotter> allPlotter = plotterService.findAllPlotterMountNow();
        model.addAttribute("allPlotter", allPlotter);
        model.addAttribute("all", all);
        return "listBidPage";
    }

    @GetMapping("/admin-panel")
    public String adminPanelPage() {
        return "adminPanelPage";
    }

    @GetMapping("/status-work")
    public String statusWorkGet(Bid bid) {
        Bid statusBid = bidService.findById(bid.getId()).get();
        StatusWork statusWork = new StatusWork();
        statusWork.setId(2L);
        statusBid.setStatusWork(statusWork);
        bidService.save(statusBid);
        return "redirect:/admin-list-page";
    }

    @GetMapping("/info-bid")
    public String infoBidGet(Bid bid, Model model) {
        Bid bidById = bidService.findById(bid.getId()).get();
        ID = bid.getId();
        model.addAttribute("bidById", bidById);
        return "infoBid";
    }

    @PostMapping("/info-bid")
    public String infoBidPost(Bid bid) {
        Bid findBid = bidService.findById(ID).get();
        findBid.setStitching(bid.getStitching());
        bidService.save(findBid);
        return "redirect:/admin-list-page";
    }

    private void createReportBidForMount() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Просто лист");
        List<Bid> bidList = bidService.findAllLastMonth();
        Row header = sheet.createRow(0);
        header.createCell(5).setCellValue(LocalDate.now().getMonth().toString());
        int rowNum = 1;

        Map<String, String> integerListMap = bidService.allPaperForMount(1L, 1L);
        System.out.println(integerListMap.toString());

        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("№");
        row.createCell(1).setCellValue("Даты");
        row.createCell(2).setCellValue("Отдел");
        row.createCell(3).setCellValue("Фамилия");
        row.createCell(4).setCellValue("Название документа");
        row.createCell(5).setCellValue("Принтер");
        row.createCell(6).setCellValue("Плотность");
        row.createCell(7).setCellValue("Формат");
        row.createCell(8).setCellValue("Кол. Лист.");
        row.createCell(9).setCellValue("Сшивка");

        for (Bid dataModel : bidList) {
            createSheetHeader(sheet, ++rowNum, dataModel);
        }

        int plotterRowNum = rowNum + 2;
        Row plotterRow = sheet.createRow(plotterRowNum);
        plotterRow.createCell(0).setCellValue("№");
        plotterRow.createCell(1).setCellValue("Дата");
        plotterRow.createCell(2).setCellValue("Отдел");
        plotterRow.createCell(3).setCellValue("Материал");
        plotterRow.createCell(4).setCellValue("Название документа");
        plotterRow.createCell(5).setCellValue("Длин. Отп.");
        plotterRow.createCell(6).setCellValue("K");
        plotterRow.createCell(7).setCellValue("Y");
        plotterRow.createCell(8).setCellValue("M");
        plotterRow.createCell(9).setCellValue("C");
        plotterRow.createCell(10).setCellValue("LM");
        plotterRow.createCell(11).setCellValue("LC");

        List<Plotter> allPlotterByLastMounts = plotterService.findAllPlotterMountNow();
        for (Plotter dataMidel : allPlotterByLastMounts) {
            createPlotterReport(sheet, ++plotterRowNum, dataMidel);
        }

        try (FileOutputStream out = new FileOutputStream(new File("C:\\Отчет за " + LocalDate.now().getMonth().minus(1L) + " " + LocalDate.now().getYear() + ".xls"))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSheetHeader(HSSFSheet sheet, int rowNum, Bid dataModel) {
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(dataModel.getId());
        row.createCell(1).setCellValue(dataModel.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        row.createCell(2).setCellValue(dataModel.getDepartment().getName());
        row.createCell(3).setCellValue(dataModel.getCustomerOder());
        row.createCell(4).setCellValue(dataModel.getDocumentName());
        row.createCell(5).setCellValue(dataModel.getPrinter().getModel());
        row.createCell(6).setCellValue(dataModel.getPaperDensity().getDensity());
        row.createCell(7).setCellValue(dataModel.getPaperSize().getSize());
        row.createCell(8).setCellValue(dataModel.getNumberOfPages());
        row.createCell(9).setCellValue(dataModel.getStitching());
    }

    private void createPlotterReport(HSSFSheet sheet, int rowNum, Plotter model) {
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(model.getId());
        row.createCell(1).setCellValue(model.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        row.createCell(2).setCellValue(model.getDepartment().getName());
        row.createCell(3).setCellValue(model.getTypeOfPaper().getType());
        row.createCell(4).setCellValue(model.getDocumentName());
        row.createCell(5).setCellValue(model.getRollWidth());
        row.createCell(6).setCellValue(model.getKeyColor());
        row.createCell(7).setCellValue(model.getYellowColor());
        row.createCell(8).setCellValue(model.getMagentaColor());
        row.createCell(9).setCellValue(model.getCyanColor());
        row.createCell(10).setCellValue(model.getLightMagentaColor());
        row.createCell(11).setCellValue(model.getLightCyanColor());
    }
}
