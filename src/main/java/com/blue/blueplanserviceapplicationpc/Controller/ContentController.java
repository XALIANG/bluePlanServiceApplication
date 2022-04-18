package com.blue.blueplanserviceapplicationpc.Controller;

import com.blue.blueplanserviceapplicationpc.Model.ContentText;
import com.blue.blueplanserviceapplicationpc.Service.ContentImp;
import com.blue.blueplanserviceapplicationpc.common.Result;
import com.blue.blueplanserviceapplicationpc.exception.BlueMAllException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/text")
public class ContentController {
    @Autowired
    private ContentImp contentImp;

    @GetMapping("/insert")
    public Result contentInster(String contentName, int contentAge, String contentDescribe) throws BlueMAllException {
        System.out.println(contentName);

        if (contentName == null || contentAge == 0 || contentDescribe == null) {
            return Result.error(404, "内容为空");
        }
        contentImp.insterContent(contentName, contentAge, contentDescribe);
        return Result.success(contentName);
    }

    @GetMapping("/list")
    public Result contentList() {
        List<ContentText> contentText = contentImp.getList();
        return Result.success(contentText);
    }

    @DeleteMapping("/delete")
    public Result contentDelete(int contentId) {
        int count = contentImp.deleteContent(contentId);
        return Result.success("删除成功", count);
    }

    @PostMapping("/update")
    public Result updateContentTexts(@RequestBody ContentText contentText) {
        int count = contentImp.updateContentText(contentText);
        System.out.println(contentText);
        if (count == 1) {
            return Result.success("更新成功", count);
        }
        return Result.error(404, "更新失败");
    }

    @GetMapping("/search")
    public Result searchContent(String contentName,Integer contentAge, String contentDescribe){
        List<ContentText> contentTexts = contentImp.searchContentText(contentName,contentAge,contentDescribe);
        if(contentTexts.size() == 0){
            return Result.error(404, "查询失败");
        }
        return Result.success("查询成功", contentTexts);
    }
}
