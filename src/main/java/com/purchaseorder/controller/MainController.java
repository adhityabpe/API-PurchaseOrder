package com.purchaseorder.controller;

import com.purchaseorder.config.ApiResponseUtil;
import com.purchaseorder.config.Constant;
import com.purchaseorder.model.PoD;
import com.purchaseorder.model.PoH;
import com.purchaseorder.model.Users;
import com.purchaseorder.model.Item;
import com.purchaseorder.service.PoService;
import com.purchaseorder.service.UsersService;
import com.purchaseorder.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private PoService poService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ItemService itemService;

    // PO Endpoints
    @GetMapping("/po/all")
    public ResponseEntity<?> getAllPos() {
        List<PoH> poHs = poService.findAllHeaders();
        return ApiResponseUtil.successResponse(poHs);
    }

    @GetMapping("/po/search")
    public ResponseEntity<?> getPoById(@RequestParam int id) {
        PoH poH = poService.findHeaderById(id);
        if (poH == null) {
            return ApiResponseUtil.notFoundResponse(Constant.PO_NOT_FOUND);
        }
        return ApiResponseUtil.successResponse(poH);
    }

    @PostMapping("/po/create")
    public ResponseEntity<?> createPo(@RequestBody PoH poH) {
        PoH createdPoH = poService.saveHeader(poH);
        return ApiResponseUtil.successResponse(createdPoH);
    }

    @PutMapping("/po/update")
    public ResponseEntity<?> updatePo(@RequestParam int id, @RequestBody PoH poH) {
        PoH updatedPoH = poService.updateHeader(id, poH);
        if (updatedPoH == null) {
            return ApiResponseUtil.notFoundResponse(Constant.PO_NOT_FOUND);
        }
        return ApiResponseUtil.successResponse(updatedPoH);
    }

    @DeleteMapping("/po/delete")
    public ResponseEntity<?> deletePo(@RequestParam int id) {
        PoH existingPoH = poService.findHeaderById(id);
        if (existingPoH == null) {
            return ApiResponseUtil.notFoundResponse(Constant.PO_NOT_FOUND);
        }
        poService.deleteHeader(id);
        return ApiResponseUtil.successResponse(Constant.PO_DELETED_SUCCESS);
    }

    @GetMapping("/pod/all")
    public ResponseEntity<?> getAllPoDs() {
        List<PoD> poDs = poService.findAllDetails();
        return ApiResponseUtil.successResponse(poDs);
    }

    @GetMapping("/pod/search")
    public ResponseEntity<?> getPoDById(@RequestParam int id) {
        PoD poD = poService.findDetailById(id);
        if (poD == null) {
            return ApiResponseUtil.notFoundResponse(Constant.POD_NOT_FOUND);
        }
        return ApiResponseUtil.successResponse(poD);
    }

    @PostMapping("/pod/create")
    public ResponseEntity<?> createPoD(@RequestBody PoD poD) {
        PoD createdPoD = poService.saveDetail(poD);
        if (createdPoD == null) {
            return ApiResponseUtil.notFoundResponse(Constant.POH_ID_OR_ITEM_NOT_EXIST);
        }
        return ApiResponseUtil.successResponse(createdPoD);
    }

    @PutMapping("/pod/update")
    public ResponseEntity<?> updatePoD(@RequestParam int id, @RequestBody PoD poD) {
        PoD updatedPoD = poService.updateDetail(id, poD);
        if (updatedPoD == null) {
            return ApiResponseUtil.notFoundResponse(Constant.POD_NOT_FOUND);
        }
        return ApiResponseUtil.successResponse(updatedPoD);
    }

    @DeleteMapping("/pod/delete")
    public ResponseEntity<?> deletePoD(@RequestParam int id) {
        PoD existingPoD = poService.findDetailById(id);
        if (existingPoD == null) {
            return ApiResponseUtil.notFoundResponse(Constant.POD_NOT_FOUND);
        }
        poService.deleteDetail(id);
        return ApiResponseUtil.successResponse(Constant.POD_DELETED_SUCCESS);
    }

    // Users Endpoints
    @GetMapping("/users/all")
    public ResponseEntity<?> getAllUsers() {
        List<Users> users = usersService.findAll();
        return ApiResponseUtil.successResponse(users);
    }

    @GetMapping("/users/search")
    public ResponseEntity<?> getUserById(@RequestParam int id) {
        Users user = usersService.findById(id);
        if (user == null) {
            return ApiResponseUtil.notFoundResponse(Constant.USER_NOT_FOUND);
        }
        return ApiResponseUtil.successResponse(user);
    }

    @PostMapping("/users/create")
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        Users createdUser = usersService.save(user);
        return ApiResponseUtil.successResponse(createdUser);
    }

    @PutMapping("/users/update")
    public ResponseEntity<?> updateUser(@RequestParam int id, @RequestBody Users user) {
        Users updatedUser = usersService.update(id, user);
        if (updatedUser == null) {
            return ApiResponseUtil.notFoundResponse(Constant.USER_NOT_FOUND);
        }
        return ApiResponseUtil.successResponse(updatedUser);
    }

    @DeleteMapping("/users/delete")
    public ResponseEntity<?> deleteUser(@RequestParam int id) {
        Users existingUser = usersService.findById(id);
        if (existingUser == null) {
            return ApiResponseUtil.notFoundResponse(Constant.USER_NOT_FOUND);
        }
        usersService.delete(id);
        return ApiResponseUtil.successResponse(Constant.USER_DELETED_SUCCESS);
    }

    // Items Endpoints
    @GetMapping("/items/all")
    public ResponseEntity<?> getAllItems() {
        List<Item> items = itemService.findAll();
        return ApiResponseUtil.successResponse(items);
    }

    @GetMapping("/items/search")
    public ResponseEntity<?> getItemById(@RequestParam int id) {
        Item item = itemService.findById(id);
        if (item == null) {
            return ApiResponseUtil.notFoundResponse(Constant.ITEM_NOT_FOUND);
        }
        return ApiResponseUtil.successResponse(item);
    }

    @PostMapping("/items/create")
    public ResponseEntity<?> createItem(@RequestBody Item item) {
        Item createdItem = itemService.save(item);
        return ApiResponseUtil.successResponse(createdItem);
    }

    @PutMapping("/items/update")
    public ResponseEntity<?> updateItem(@RequestParam int id, @RequestBody Item item) {
        Item updatedItem = itemService.update(id, item);
        if (updatedItem == null) {
            return ApiResponseUtil.notFoundResponse(Constant.ITEM_NOT_FOUND);
        }
        return ApiResponseUtil.successResponse(updatedItem);
    }

    @DeleteMapping("/items/delete")
    public ResponseEntity<?> deleteItem(@RequestParam int id) {
        Item existingItem = itemService.findById(id);
        if (existingItem == null) {
            return ApiResponseUtil.notFoundResponse(Constant.ITEM_NOT_FOUND);
        }
        itemService.delete(id);
        return ApiResponseUtil.successResponse(Constant.ITEM_DELETED_SUCCESS);
    }
}
