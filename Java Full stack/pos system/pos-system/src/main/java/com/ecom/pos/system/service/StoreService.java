package com.ecom.pos.system.service;

import com.ecom.pos.system.exceptions.UserException;
import com.ecom.pos.system.model.Store;
import com.ecom.pos.system.model.User;
import com.ecom.pos.system.payload.dto.StoreDto;

import java.util.List;

public interface StoreService {
    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(Long id) throws Exception;
    List<StoreDto> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDto updateStore(Long id,StoreDto storeDto);
    StoreDto deleteStore(Long id);
    StoreDto getStoreByEmployee();

}
