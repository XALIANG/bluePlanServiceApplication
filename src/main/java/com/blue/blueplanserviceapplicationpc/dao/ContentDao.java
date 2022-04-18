package com.blue.blueplanserviceapplicationpc.dao;

import com.blue.blueplanserviceapplicationpc.Model.ContentText;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface ContentDao {
    List<ContentText> selectedContentText(@Param("contentName") String contentName, @Param("contentAge") Integer contentAge, @Param("contentDescribe") String contentDescribe);

    int insertContent(ContentText contentText);

    int deleteByPrimaryKeyContent(int id);

    int updateByPrimaryKeySelectiveContent(ContentText contentText);

    List<ContentText> getContentTextList();


}
