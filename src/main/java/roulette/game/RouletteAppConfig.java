package roulette.game;
import io.netty.util.internal.StringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.reactive.config.EnableWebFlux;
import roulette.game.constant.OperationalConstant;
import roulette.game.model.Roulette;
@Configuration
@EnableWebFlux
public class RouletteAppConfig {
    private final String redisHost;
    private final Integer redisPort;
    public RouletteAppConfig() {
        this.redisHost = System.getenv(OperationalConstant.REDIS_HOST_VAR_NAME);
        String redisPortTmp = System.getenv(OperationalConstant.REDIS_PORT_VAR_NAME);
        if (StringUtil.isNullOrEmpty(redisHost) || StringUtil.isNullOrEmpty(redisPortTmp)) {
            throw new RuntimeException("Not present environments  vars for redis connection");
        }
        this.redisPort = Integer.valueOf(redisPortTmp);
    }
    @Bean
    public ReactiveRedisTemplate<String, Roulette> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Roulette> valueSerializer = new Jackson2JsonRedisSerializer<>(Roulette.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, Roulette> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, Roulette> context = builder.value(valueSerializer).build();
        return new ReactiveRedisTemplate<>(factory, context);
    }
    @Bean
    public ReactiveValueOperations<String, Roulette> reactiveRedisOpsForValue(ReactiveRedisTemplate<String, Roulette> factory) {
        return factory.opsForValue();
    }
    @Bean
    @Primary
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory(this.redisHost, this.redisPort);
    }
}
