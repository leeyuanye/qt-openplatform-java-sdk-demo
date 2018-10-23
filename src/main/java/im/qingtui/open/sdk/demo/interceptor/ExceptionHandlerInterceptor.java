package im.qingtui.open.sdk.demo.interceptor;

import com.cisdi.nudgeplus.tmsbeans.beans.BaseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 *
 * @author yiya
 */
@ControllerAdvice
public class ExceptionHandlerInterceptor {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResult handleIllegalRequestException(Exception e) {
        BaseResult baseResult = new BaseResult();
        baseResult.setErrcode(-1);
        baseResult.setErrmsg("system error");
        e.printStackTrace();
        return baseResult;
    }

}
