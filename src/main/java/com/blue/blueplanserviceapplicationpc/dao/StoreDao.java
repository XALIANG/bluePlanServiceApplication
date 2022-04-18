package com.blue.blueplanserviceapplicationpc.dao;

import com.blue.blueplanserviceapplicationpc.Model.Store;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface StoreDao {

    List<Store> getStoreContentList();

    List<Store> selectedContentProduct(Integer contentId);
}
