package im.qingtui.open.sdk.demo.listener;

import com.cisdi.nudgeplus.sdk.service.TokenService;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author yiya
 */

@Service
public class StartUpListener {

    /**
     * 刷新token的毫秒数
     */
    private static final Long REFRESH_TOKEN_PERIOD_MS = 60*60*1000L;

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String token = TokenService.getToken().getAccessToken();
                TokenService.ACCESS_TOKEN = token;
            }
        }, 0,REFRESH_TOKEN_PERIOD_MS);
    }
}
