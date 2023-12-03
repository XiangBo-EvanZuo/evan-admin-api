package cn.evan.admin.user.intf.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * 类描述: 领域事件
 * ********************************
 */
@Service
@Slf4j
public class subscribe implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            log.info("BusinessCenterClaimEventSubscribe start! ");
        } catch (Exception e) {
            log.error("BusinessCenterClaimEventSubscribe error!", e);
            throw e;
        }
    }

}
