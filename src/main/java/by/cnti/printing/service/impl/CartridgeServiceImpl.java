package by.cnti.printing.service.impl;

import by.cnti.printing.entity.Cartridge;
import by.cnti.printing.repository.CartridgeRepository;
import by.cnti.printing.service.interfaceService.CartridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Transactional
@Service
public class CartridgeServiceImpl implements CartridgeService {

    private CartridgeRepository cartridgeRepository;

    @Autowired
    public CartridgeServiceImpl(CartridgeRepository cartridgeRepository) {
        this.cartridgeRepository = cartridgeRepository;
    }

    @Override
    public void save(Cartridge cartridge) {
        cartridgeRepository.save(cartridge);

    }

    @Override
    public Iterable<Cartridge> findAll() {
        return cartridgeRepository.findAll();
    }

    @Override
    public Optional<Cartridge> findById(Long id) {
        return cartridgeRepository.findById(id);
    }

    @Override
    public void delete(Cartridge cartridge) {
            cartridgeRepository.delete(cartridge);
    }
}
