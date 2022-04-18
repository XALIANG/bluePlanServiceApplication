package com.blue.blueplanserviceapplicationpc.Controller;

import com.auth0.jwt.JWT;
import com.blue.blueplanserviceapplicationpc.Model.User;
import com.blue.blueplanserviceapplicationpc.Service.UserServiceImp;
import com.blue.blueplanserviceapplicationpc.common.Result;
import com.blue.blueplanserviceapplicationpc.core.CommonPage;
import com.blue.blueplanserviceapplicationpc.exception.BlueExceptionEnum;
import com.blue.blueplanserviceapplicationpc.exception.BlueMAllException;
import com.blue.blueplanserviceapplicationpc.utils.DateUtils;
import com.blue.blueplanserviceapplicationpc.utils.MD5Utils;
import com.blue.blueplanserviceapplicationpc.utils.RxUilts;
import com.blue.blueplanserviceapplicationpc.utils.TokenUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;


/**
 * 用户
 */

@RestController
@RequestMapping("/user")
public class UserController {
    public static final String IMAGE_USER_FACE_LOCATION = "C:" + File.separator + "Users" + File.separator + "86156" + File.separator + "Desktop" + File.separator + "blue" + File.separator + "image";
    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    private CacheManager cacheManager;

    @ApiOperation("注册用户")
    @PostMapping("/blue/register")
    @ResponseBody
    public Result register(@RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("code") String code, HttpSession session) throws BlueMAllException {
        String serverCode = (String) session.getAttribute("code");
//        校验非空
        if (StringUtils.isEmpty(userName)) {
            return Result.error(BlueExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return Result.error(BlueExceptionEnum.NEED_PASSWORD);
        }
        if (password.length() < 8) {
            return Result.error(BlueExceptionEnum.PASSWORD_TOO_SHORT);
        }
        if (null == code || serverCode == null || !serverCode.equalsIgnoreCase(code)) {
            return Result.error(404, "验证码错误");
        }
        userServiceImp.register(userName, password);
        return Result.success("注册成功", 200);
    }

    @ApiOperation("用户登录")
    @PostMapping("/blue/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
    })
    @ResponseBody
    public Result userLogin(@RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("code") String code, HttpSession session) {
        String serverCode = (String) session.getAttribute("code");

        User user = userServiceImp.userLogin(userName, password);
        if (null == user) {
            return Result.error(BlueExceptionEnum.NEED_NULL_USER);
        }
        String token = TokenUtil.getToken(user);
        if (null == userName || password == null) {
            Result.error(400, "不得为空");
        }
        if (serverCode == null) {
            return Result.error(404, "接收验证码失败");
        }
        if (null == code || !serverCode.equalsIgnoreCase(code)) {
            return Result.error(404, "验证码错误");
        }
        if (!user.getUserPassword().equals(password)) {
            return Result.error(BlueExceptionEnum.WRONG_PASSWORD);
        }
        user.setToken(token);
        user.setUserPassword(MD5Utils.stringToMD5(user.getUserPassword()));
        return Result.success(user);
    }

    @PostMapping("/public/action")
    public Result obtainUserAction(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String[] userTokenSplit = token.split(" ");
        String userId = JWT.decode(userTokenSplit[1]).getAudience().get(0);
        User user = userServiceImp.findUserById(userId);

        return Result.success();
    }

    @GetMapping("blue/set_code")
    public Result setUserStatusCode(String userId) {
        userServiceImp.updateUserStatusCode(userId, "1");
        return Result.success("设置成功");
    }

    @GetMapping("blue/out_code")
    public Result outUserStatusCode(String userId) {
        userServiceImp.updateUserStatusCode(userId, "0");
        return Result.success("设置成功");
    }

    @ApiOperation("用户分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "每页显示数量", required = true, dataType = "String", paramType = "query"),
    })
    @GetMapping("/public/list")
    public CommonPage<User> userList(int page, int limit) {
        PageInfo<User> userList = userServiceImp.getListPage(page, limit);
        return CommonPage.restPage(userList, 200);
    }


    @ApiOperation(value = "头像上传", httpMethod = "POST")
    @PostMapping("/blue/update_header")
    @ResponseBody
    public Result uploadAvatar(MultipartFile file) {
        //保存地址
        String fileSpace = IMAGE_USER_FACE_LOCATION;
        String uploadPathPrefix = File.separator + RxUilts.getUUID();
        if (file != null) {
            FileOutputStream fileOutputStream = null;
            try {
                //获取上传文件名称
                String fileName = file.getOriginalFilename();
                if (org.apache.commons.lang.StringUtils.isNotBlank(fileName)) {
                    // 文件重命名 imooc-face.png -> ["imooc-face","png"]
                    String fileNameArr[] = fileName.split("\\.");
                    //获取文件的后缀名
                    String suffix = fileNameArr[fileNameArr.length - 1];
                    //face-{uploadPathPrefix}.png
                    //文件名称重组，覆盖式上传，增量式，额外拼接当前时间
                    String newFileName = "face-" + RxUilts.getUUID() + "." + suffix;
                    //上传保存地址
                    String finalFacePath = fileSpace + uploadPathPrefix + File.separator + newFileName;
                    uploadPathPrefix += ("/" + newFileName); //提供web服务地址
                    File outFile = new File(finalFacePath);
                    if (null != outFile) {
                        //创建文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    InputStream inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return Result.error(BlueExceptionEnum.DOCUMENT_ERROR);
        }
        String finalUserFaceUrl = "localhost:9999/Users/86156/Desktop/blue/image" + uploadPathPrefix + "?t=" + DateUtils.dateToTime(new Date());
        return Result.success("上传成功", finalUserFaceUrl);
    }
}
