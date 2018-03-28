package by.cnti.printing.repository;

import by.cnti.printing.config.RepositoryConfigurationTest;
import by.cnti.printing.entity.Bid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfigurationTest.class})
public class BidRepositoryTest {

    @Autowired
    private BidRepository bidRepository;

    @Test
    public void saveAndFindById (){
        Bid bid = entityTest();
        bidRepository.save(bid);
        Optional<Bid> byId = bidRepository.findById(bid.getId());
        assertEquals(byId.get().getDocumentName(), bid.getDocumentName());
    }

    @Test
    public void deleteTest (){
        Bid bid = entityTest();
        bidRepository.save(bid);
        bidRepository.delete(bid);
    }

    private Bid entityTest (){
        Bid bid = new Bid();
        bid.setCustomerOder("Evlash");
        bid.setDate(LocalDate.now());
        bid.setDocumentName("Test");
        bid.setEdition(5L);
        bid.setStitching("no");
        return bid;
    }
}