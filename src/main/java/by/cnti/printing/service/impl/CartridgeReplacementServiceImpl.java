package by.cnti.printing.service.impl;

import by.cnti.printing.entity.CartridgeReplacement;
import by.cnti.printing.repository.CartridgeReplacementRepository;
import by.cnti.printing.repository.CartridgeRepository;
import by.cnti.printing.service.interfaceService.CartridgeReplacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class CartridgeReplacementServiceImpl implements CartridgeReplacementService {


    private CartridgeReplacementRepository cartridgeReplacementRepository;


    @Autowired
    public CartridgeReplacementServiceImpl(CartridgeReplacementRepository cartridgeReplacementRepository) {
        this.cartridgeReplacementRepository = cartridgeReplacementRepository;
    }

    @Override
    public void save(CartridgeReplacement cartridgeReplacement) {
        cartridgeReplacementRepository.save(cartridgeReplacement);
    }

    @Override
    public Iterable<CartridgeReplacement> findAll() {
        return cartridgeReplacementRepository.findAll();
    }

    @Override
    public Optional<CartridgeReplacement> findById(Long id) {
        return cartridgeReplacementRepository.findById(id);
    }

    @Override
    public void delete(CartridgeReplacement cartridgeReplacement) {
        cartridgeReplacementRepository.delete(cartridgeReplacement);
    }
}
