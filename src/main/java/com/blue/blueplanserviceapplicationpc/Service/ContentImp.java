package com.blue.blueplanserviceapplicationpc.Service;

import com.blue.blueplanserviceapplicationpc.Model.ContentText;
import com.blue.blueplanserviceapplicationpc.dao.ContentDao;
import com.blue.blueplanserviceapplicationpc.exception.BlueExceptionEnum;
import com.blue.blueplanserviceapplicationpc.exception.BlueMAllException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ContentImp {
    @Autowired
    ContentDao contentDao;

    public void insterContent(String contentName,int contentAge,String contentDescribe) throws BlueMAllException {

        ContentText contentText = new ContentText();
        contentText.setContentName(contentName);
        contentText.setContentAge(contentAge);
        contentText.setContentDescribe(contentDescribe);
        int count = contentDao.insertContent(contentText);

        if(count == 0){
            throw new BlueMAllException(BlueExceptionEnum.INSERT_FAILED);
        }

    }

    public List<ContentText> getList(){
        List<ContentText> contentText = contentDao.getContentTextList();
        return contentText;
    }

    public int deleteContent(int id){
        return contentDao.deleteByPrimaryKeyContent(id);
    }

    public int updateContentText(ContentText contentText){
        return contentDao.updateByPrimaryKeySelectiveContent(contentText);
    }

    public List<ContentText> searchContentText(String contentName,Integer contentAge,String contentDescribe){
        List<ContentText> contentTexts = contentDao.selectedContentText(contentName,contentAge,contentDescribe);
        return contentTexts;
    }
}
