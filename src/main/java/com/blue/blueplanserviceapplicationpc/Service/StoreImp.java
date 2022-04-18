package com.blue.blueplanserviceapplicationpc.Service;

import com.blue.blueplanserviceapplicationpc.Model.Store;
import com.blue.blueplanserviceapplicationpc.dao.StoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreImp {
    @Autowired
    StoreDao storeDao;

    public List<Store> obtainContentList(){
        return storeDao.getStoreContentList();
    }

    public  List<Store> searchROwContentProduct(Integer contentId){
        List<Store> stores = storeDao.selectedContentProduct(contentId);
        return stores;
    }
}
