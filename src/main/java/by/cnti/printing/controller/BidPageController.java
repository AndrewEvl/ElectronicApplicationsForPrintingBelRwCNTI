package by.cnti.printing.controller;

import by.cnti.printing.entity.Bid;
import by.cnti.printing.service.interfaceService.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
public class BidPageController {

    private BidService bidService;

    @Autowired
    public BidPageController(BidService bidService) {
        this.bidService = bidService;
    }

    @ModelAttribute("bid")
    public Bid bid (){
        return new Bid();
    }

    @GetMapping("/")
    public String bidPageGet (Model model){
//        List<PaperSize> paperSizes =
//        model.addAttribute("allPaperSize", paperSizes);
//        model.addAttribute("allDepartments", allDepartments);
        return "homePage";
    }

    @PostMapping("/")
    public String bidPagePost (Bid bid){
        bid.setDate(LocalDate.now());
//        bid.setStatusWork(StatusWork.NOT_APPROVED);
        bidService.save(bid);
        return "redirect:/successfulBid";
    }

    @GetMapping("/successfulBid")
    public String successfulPageGet (){
        return "successfulPage";
    }
}
