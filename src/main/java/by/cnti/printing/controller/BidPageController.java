package by.cnti.printing.controller;

import by.cnti.printing.dto.BidDto;
import by.cnti.printing.entity.*;
import by.cnti.printing.service.interfaceService.*;
import com.google.common.collect.Iterables;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.apache.commons.io.filefilter.DirectoryFileFilter.DIRECTORY;

@Controller
public class BidPageController{

    private static final String DIR_PATH = "src" + File.separator;
    private Long ID;

    private BidService bidService;
    private DepartmentService departmentService;
    private PaperSizeService paperSizeService;
    private PaperDensityService paperDensityService;
    private PrinterModelService printerModelService;
    private PlotterService plotterService;

    @Autowired
    public BidPageController(BidService bidService,
                             DepartmentService departmentService,
                             PaperSizeService paperSizeService,
                             PaperDensityService paperDensityService,
                             PrinterModelService printerModelService,
                             PlotterService plotterService) {
        this.bidService = bidService;
        this.departmentService = departmentService;
        this.paperSizeService = paperSizeService;
        this.paperDensityService = paperDensityService;
        this.printerModelService = printerModelService;
        this.plotterService = plotterService;
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
        if (Objects.equals(bidDto.getTwoSidedPrinting(), "+")) {
            bid.setTwoSidedPrinting(bidDto.getTwoSidedPrinting());
        } else {
            bid.setTwoSidedPrinting("-");
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
        HSSFSheet sheet = workbook.createSheet("Mount Report");
        List<Bid> bidList = bidService.findAllLastMonth();
        List<Plotter> allPlotterByLastMounts = plotterService.findAllPlotterMountNow();
        Map reportForMontPaper = reportForMontPaper();
        Row header = sheet.createRow(0);
        header.createCell(5).setCellValue(LocalDate.now().getMonth().toString());
        int rowNum = 1;

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

        for (Bid bidModelList : bidList) {
            createPaperReport(sheet, ++rowNum, bidModelList);
        }
        int reportRow = rowNum;
        createAllPaperReport(sheet,reportRow,reportForMontPaper);

        int plotterRowNum = rowNum + 3;
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

        for (Plotter plotterList : allPlotterByLastMounts) {
            createPlotterReport(sheet, ++plotterRowNum, plotterList);
        }

        try (FileOutputStream out = new FileOutputStream(new File(DIR_PATH + "Отчет за " + LocalDate.now().getMonth().minus(1L) + " " + LocalDate.now().getYear() + ".xls"))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try (FileOutputStream out = new FileOutputStream(new File("C:\\Отчет за " + LocalDate.now().getMonth().minus(1L) + " " + LocalDate.now().getYear() + ".xls"))) {
//            workbook.write(out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void createPaperReport(HSSFSheet sheet, int rowNum, Bid bidList) {
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(bidList.getId());
        row.createCell(1).setCellValue(bidList.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        row.createCell(2).setCellValue(bidList.getDepartment().getName());
        row.createCell(3).setCellValue(bidList.getCustomerOder());
        row.createCell(4).setCellValue(bidList.getDocumentName());
        row.createCell(5).setCellValue(bidList.getPrinter().getModel());
        row.createCell(6).setCellValue(bidList.getPaperDensity().getDensity());
        row.createCell(7).setCellValue(bidList.getPaperSize().getSize());
        row.createCell(8).setCellValue(bidList.getNumberOfPages());
        row.createCell(9).setCellValue(bidList.getStitching());
    }

    private void createAllPaperReport(HSSFSheet sheet, int rowNum, Map reportMonth){
        Row row = sheet.createRow(rowNum+1);
        try {
            for (int i = 0; i <= reportMonth.size(); i++) {
                row.createCell(i+1).setCellValue(reportMonth.get(i).toString());
            }
        }
        catch (NullPointerException ignored){
        }
    }

    private void createPlotterReport(HSSFSheet sheet, int rowNum, Plotter plotterList) {
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(plotterList.getId());
        row.createCell(1).setCellValue(plotterList.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        row.createCell(2).setCellValue(plotterList.getDepartment().getName());
        row.createCell(3).setCellValue(plotterList.getTypeOfPaper().getType());
        row.createCell(4).setCellValue(plotterList.getDocumentName());
        row.createCell(5).setCellValue(plotterList.getRollWidth());
        row.createCell(6).setCellValue(plotterList.getKeyColor());
        row.createCell(7).setCellValue(plotterList.getYellowColor());
        row.createCell(8).setCellValue(plotterList.getMagentaColor());
        row.createCell(9).setCellValue(plotterList.getCyanColor());
        row.createCell(10).setCellValue(plotterList.getLightMagentaColor());
        row.createCell(11).setCellValue(plotterList.getLightCyanColor());
    }

    private Map reportForMontPaper() {
        Iterable<PaperSize> paperSizeList = paperSizeService.findAll();
        Iterable<PaperDensity> paperDensitiesList = paperDensityService.findAll();

        int k = 0;
        Map<Integer, String> reportPaperMap = new HashMap<>();
        for (long i = 1; i <= Iterables.size(paperSizeList); i++) {
            for (long j = 1; j <= Iterables.size(paperDensitiesList); j++) {
                Map<String, String> longStringMap = bidService.allPaperForMount(i, j);
                if (longStringMap.get("sum_pages") != null) {
                    reportPaperMap.put(k,longStringMap.get("size_destiny")
                            + " = " + longStringMap.get("sum_pages"));
//                    reportPaperMap.put(k, "Формат|Плотность : " + longStringMap.get("size_destiny")
//                            + " Колличество листов : " + longStringMap.get("sum_pages"));
                    k++;
                } else {
                    j++;
                }
            }
        }
        return reportPaperMap;
    }
}
