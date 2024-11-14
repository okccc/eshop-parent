import com.okccc.eshop.manager.ManagerApplication;
import com.okccc.eshop.manager.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest(classes = ManagerApplication.class)
class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    void selectUserInfoByUserName() {
        System.out.println(sysUserMapper.selectByUsername("admin"));
    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedis() {
        redisTemplate.opsForValue().set("k1", "v2");
    }
}