package roulette.game;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import roulette.game.model.Roulette;
@Configuration
public class RouletteAppConfig {
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
    public ReactiveValueOperations<String, Roulette> reactiveRedisTemplate(ReactiveRedisTemplate<String, Roulette> factory) {
        return factory.opsForValue();
    }
    //TODO:
    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory("", 123);
    }
}