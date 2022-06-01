import com.yssj.core.module.crawler.service.impl.GoodsServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;

@SuppressWarnings("ALL")
@ServletComponentScan
@MapperScan("com.yssj.core.module.*.mapper")
@SpringBootApplication(scanBasePackages = {"com.yssj.base.common", "com.yssj.core"})
@EnableCaching
public class StartApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StartApplication.class, args);
        GoodsServiceImpl goodsService = context.getBean(GoodsServiceImpl.class);
        goodsService.pinGoodsSync();
    }

    @PostConstruct
    public void init() {
        System.out.println("init........................");
    }
}
