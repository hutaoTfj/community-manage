package ink.hutao.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * <p></p>
 * @author tfj
 * @since 2021-06-20
 */
@SpringBootApplication
@MapperScan("ink.hutao.manage.mapper")
public class CommunityManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityManageApplication.class, args);
    }

}
