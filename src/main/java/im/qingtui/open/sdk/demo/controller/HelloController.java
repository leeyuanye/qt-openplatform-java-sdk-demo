package im.qingtui.open.sdk.demo.controller;

import static im.qingtui.open.sdk.demo.utils.AppConfig.APP_ID;
import static im.qingtui.open.sdk.demo.utils.AppConfig.APP_SECRET;

import com.cisdi.nudgeplus.sdk.service.MemberService;
import com.cisdi.nudgeplus.sdk.service.OAuthService;
import com.cisdi.nudgeplus.sdk.service.ServiceMessageService;
import com.cisdi.nudgeplus.sdk.service.SingleMessageService;
import com.cisdi.nudgeplus.tmsbeans.beans.UserInfoResult;
import com.cisdi.nudgeplus.tmsbeans.beans.member.PagedUserInfoResult;
import com.cisdi.nudgeplus.tmsbeans.model.TextMsg;
import com.cisdi.nudgeplus.tmsbeans.model.request.member.RequestPagedUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yiya
 */
@Controller
public class HelloController {

    @RequestMapping("/index")
    public String index(@RequestParam("qt_code") String qtCode,Model model) {
        UserInfoResult userInfoResult = OAuthService.getUserInfo(APP_ID,APP_SECRET,qtCode);
        model.addAttribute("user",userInfoResult);
        return "hello";
    }

    @RequestMapping("single/send")
    @ResponseBody
    public String singleSend(String text,String openId){
        TextMsg textMsg = new TextMsg();
        textMsg.setContent(text);
        return SingleMessageService.sendTextMsg(openId,textMsg);
    }

    @RequestMapping("service/send")
    @ResponseBody
    public String serviceSend(String text){
        TextMsg textMsg = new TextMsg();
        textMsg.setContent(text);
        return ServiceMessageService.sendTextMsg(textMsg);
    }

    @RequestMapping("member/page")
    @ResponseBody
    public PagedUserInfoResult memberPage(int requestPage,int pageSize){
        RequestPagedUserInfo requestPagedUserInfo = new RequestPagedUserInfo();
        requestPagedUserInfo.setRequestPage(requestPage);
        requestPagedUserInfo.setPageSize(pageSize);
        PagedUserInfoResult pagedUserInfoResult = MemberService.pageAllUserInfo(requestPagedUserInfo);
        return pagedUserInfoResult;
    }

}
