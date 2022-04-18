package com.blue.blueplanserviceapplicationpc.Controller;


import com.blue.blueplanserviceapplicationpc.Model.Store;
import com.blue.blueplanserviceapplicationpc.Service.StoreImp;
import com.blue.blueplanserviceapplicationpc.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreImp storeImp;

    @GetMapping("/shop_list")
    public Result obtainStoreList(){
        List<Store> stores = storeImp.obtainContentList();
        return Result.success("获取成功", stores);
    }

    @PostMapping("/search_shop")
    public Result searchStoreContentRow(@RequestParam("contentId") Integer contentId){
        List<Store> stores = storeImp.searchROwContentProduct(contentId);
        if(stores.size() == 0|| contentId == null){
            return Result.error(404,"获取失败");
        }
        return Result.success("获取成功", stores);
    }

}
