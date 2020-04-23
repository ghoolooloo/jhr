package jo.jhr.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.hibernate.cache.jcache.ConfigSettings;

import java.util.concurrent.TimeUnit;

import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

import io.github.jhipster.config.JHipsterProperties;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration(JHipsterProperties jHipsterProperties) {
        MutableConfiguration<Object, Object> jcacheConfig = new MutableConfiguration<>();
        Config config = new Config();
        if (jHipsterProperties.getCache().getRedis().isCluster()) {
            config.useClusterServers().addNodeAddress(jHipsterProperties.getCache().getRedis().getServer());
        } else {
            config.useSingleServer().setAddress(jHipsterProperties.getCache().getRedis().getServer()[0]);
        }
        jcacheConfig.setStatisticsEnabled(true);
        jcacheConfig.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, jHipsterProperties.getCache().getRedis().getExpiration())));
        return RedissonConfiguration.fromInstance(Redisson.create(config), jcacheConfig);
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cm) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cm);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer(javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration) {
        return cm -> {
            createCache(cm, jo.jhr.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            createCache(cm, jo.jhr.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            createCache(cm, jo.jhr.domain.User.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.Authority.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            createCache(cm, jo.jhr.domain.FoodCategory.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.Food.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.WeekMenu.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.WeekMenu.class.getName() + ".weekStocks", jcacheConfiguration);
            createCache(cm, jo.jhr.domain.WeekStock.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.Shop.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.Merchant.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.Member.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.DeliveryAddress.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.Order.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.OrderItem.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.DeliveryOrder.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.ExceptionOrder.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.RefundOrder.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.ClosedOrder.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.Sales.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.FoodSalesReport.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.RewardPoints.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.RewardPoints.class.getName() + ".records", jcacheConfiguration);
            createCache(cm, jo.jhr.domain.RewardPointsRecord.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.Card.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.FinalCard.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.CardHolder.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.WriteOffCard.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.Carousel.class.getName(), jcacheConfiguration);
            createCache(cm, jo.jhr.domain.Notice.class.getName(), jcacheConfiguration);
            // jhipster-needle-redis-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName, javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

}
