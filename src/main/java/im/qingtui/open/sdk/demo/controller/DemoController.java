package im.qingtui.open.sdk.demo.controller;

import com.cisdi.nudgeplus.sdk.service.MemberService;
import com.cisdi.nudgeplus.sdk.service.OAuthService;
import com.cisdi.nudgeplus.sdk.service.ProcessService;
import com.cisdi.nudgeplus.sdk.service.ServiceMessageService;
import com.cisdi.nudgeplus.sdk.service.SingleMessageService;
import com.cisdi.nudgeplus.tmsbeans.beans.UserInfoResult;
import com.cisdi.nudgeplus.tmsbeans.beans.member.PagedUserInfoResult;
import com.cisdi.nudgeplus.tmsbeans.model.TextMsg;
import com.cisdi.nudgeplus.tmsbeans.model.request.member.RequestPagedUserInfo;
import com.cisdi.nudgeplus.tmsbeans.model.request.process.CompleteProcess;
import com.cisdi.nudgeplus.tmsbeans.model.request.process.ProcessMsg;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yiya
 */
@Controller
public class DemoController {

    /**
     * 轻应用入口
     * @param qtCode 开放平台重定向到轻应用时自动添加的参数，可用于获取当前进入轻应用的用户信息。
     * 每次请求qtCode都会重新生成，每个qtCode只能获取一次用户信息，且必须在生成qtCode后5分钟内获取。
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(@RequestParam("qt_code") String qtCode,Model model,HttpServletRequest request) {
        UserInfoResult userInfoResult = null;
        userInfoResult = OAuthService.getUserInfo(qtCode);
        if(userInfoResult !=null){
            request.getSession().setAttribute("user",userInfoResult);
        } else {
            userInfoResult = (UserInfoResult) request.getSession().getAttribute("user");
        }
        model.addAttribute("user",userInfoResult);
        return "index";
    }

    /**
     * 单发纯文本消息
     * @param text 消息内容
     * @param openId 消息接收者
     * @return 纯文本消息msgId
     */
    @RequestMapping("text/msg/single/send")
    @ResponseBody
    public String singleSend(String text,String openId){
        TextMsg textMsg = new TextMsg();
        textMsg.setContent(text);
        return SingleMessageService.sendTextMsg(openId,textMsg);
    }

    /**
     * 全体关注者群发纯文本消息
     * @param text 消息内容
     * @return 纯文本消息msgId
     */
    @RequestMapping("text/msg/service/send")
    @ResponseBody
    public String serviceSend(String text){
        TextMsg textMsg = new TextMsg();
        textMsg.setContent(text);
        return ServiceMessageService.sendTextMsg(textMsg);
    }

    /**
     * 发送待办消息
     * @param openId 消息接收者
     * @param title 待办消息标题
     * @param content 待办消息内容
     * @param url 待办消息链接
     * @return 待办消息msgId
     */
    @RequestMapping("process/msg/single/send")
    @ResponseBody
    public String processMsgSingleSend(String openId,String title, String content, String url){
        ProcessMsg processMsg = new ProcessMsg();
        processMsg.setBody(content);
        processMsg.setTitle(title);
        processMsg.setUrl(url);
        return SingleMessageService.sendProcessMsg(openId,processMsg);
    }

    /**
     * 待办消息完成
     * @param msgId 消息id
     * @param openId 完成者openId
     * @return
     */
    @RequestMapping("process/msg/complete")
    @ResponseBody
    public String processMsgComplete(String msgId,String openId){
        CompleteProcess completeProcess = new CompleteProcess();
        completeProcess.setMsgId(msgId);
        completeProcess.setOpenId(openId);
        ProcessService.processComplete(completeProcess);
        return "success";
    }

    /**
     * 分页获取企业内成员信息，使用该接口需要企业管理员对轻应用授权
     * @param requestPage 请求页数（从1开始）
     * @param pageSize 页码大小 1到100
     * @return
     */
    @RequestMapping("member/page")
    @ResponseBody
    public PagedUserInfoResult memberPage(int requestPage,int pageSize){
        RequestPagedUserInfo requestPagedUserInfo = new RequestPagedUserInfo();
        requestPagedUserInfo.setRequestPage(requestPage);
        requestPagedUserInfo.setPageSize(pageSize);
        return MemberService.pageAllUserInfo(requestPagedUserInfo);
    }

}
