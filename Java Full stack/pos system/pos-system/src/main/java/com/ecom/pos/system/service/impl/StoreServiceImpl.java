package com.ecom.pos.system.service.impl;

import com.ecom.pos.system.exceptions.UserException;
import com.ecom.pos.system.mapper.StoreMapper;
import com.ecom.pos.system.model.Store;
import com.ecom.pos.system.model.User;
import com.ecom.pos.system.payload.dto.StoreDto;
import com.ecom.pos.system.repository.StoreRepository;
import com.ecom.pos.system.service.StoreService;
import com.ecom.pos.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {

        Store store= StoreMapper.toEntity(storeDto,user);

        return StoreMapper.toDto(storeRepository.save(store));
    }

    @Override
    public StoreDto getStoreById(Long id) throws Exception {
        Store store = storeRepository.findById(id).orElseThrow(()->new Exception("store not found..."));
        return StoreMapper.toDto(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User admin = userService.getCurrentUser();
        return storeRepository.findByStoreAdminId(admin.getId());
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) {
        return null;
    }

    @Override
    public StoreDto deleteStore(Long id) {
        return null;
    }

    @Override
    public StoreDto getStoreByEmployee() {
        return null;
    }
}
