package com.ecom.pos.system.controller;

import com.ecom.pos.system.domain.StoreStatus;
import com.ecom.pos.system.exceptions.UserException;
import com.ecom.pos.system.mapper.StoreMapper;
import com.ecom.pos.system.model.Store;
import com.ecom.pos.system.model.User;
import com.ecom.pos.system.payload.dto.StoreDto;
import com.ecom.pos.system.payload.response.ApiResponse;
import com.ecom.pos.system.service.StoreService;
import com.ecom.pos.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto,
                                                @RequestHeader("Authorization")String jwt) throws UserException {
        User user= userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.createStore(storeDto,user));
    }


    @GetMapping
    public ResponseEntity<List<StoreDto>> getAllStore(@RequestParam Long id,
                                                     @RequestHeader("Authorization")String jwt) throws Exception {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("admin")
    public ResponseEntity<StoreDto> getStoreByAdmin(@RequestParam Long id,
                                                      @RequestHeader("Authorization")String jwt) throws Exception {
        return ResponseEntity.ok(StoreMapper.toDto(storeService.getStoreByAdmin()));
    }

    @GetMapping("employee")
    public ResponseEntity<StoreDto> getStoreByEmployee(@RequestParam Long id,
                                                    @RequestHeader("Authorization")String jwt) throws Exception {
        return ResponseEntity.ok(storeService.getStoreByEmployee());
    }

    @PutMapping("/{id}")
    public  ResponseEntity<StoreDto> updateStore(@PathVariable Long id,
                                                 @RequestBody StoreDto storeDto) throws Exception {
        return ResponseEntity.ok(storeService.updateStore(id,storeDto));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id) throws Exception {
        storeService.deleteStore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMassage("store deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}/moderate")
    public  ResponseEntity<StoreDto> moderateStore(@PathVariable Long id,
                                                 @RequestBody StoreStatus status) throws Exception {
        return ResponseEntity.ok(storeService.moderateStore(id,status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStoreById(@RequestBody Long id,
                                                 @RequestHeader("Authorization")String jwt) throws Exception {
        User user= userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

        


}
