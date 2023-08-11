package net.lab1024.sa.common.module.support.cache;

import com.google.common.collect.Lists;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CacheService {

    @Resource
    private CaffeineCacheManager caffeineCacheManager;

    /**
     * Acquire all cache name
     *
     * @return
     */
    public List<String> cacheNames() {
        return Lists.newArrayList(caffeineCacheManager.getCacheNames());
    }

    /**
     * All the keys after one cache
     *
     * @param cacheName
     * @return
     */
    public List<String> cacheKey(String cacheName) {
        CaffeineCache cache = (CaffeineCache) caffeineCacheManager.getCache(cacheName);
        if (cache == null) {
            return Lists.newArrayList();
        }
        Set<Object> cacheKey = cache.getNativeCache().asMap().keySet();
        return cacheKey.stream().map(e -> e.toString()).collect(Collectors.toList());
    }

    /**
     * Remove key
     *
     * @param cacheName
     */

    public void removeCache(String cacheName) {
        CaffeineCache cache = (CaffeineCache) caffeineCacheManager.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        }
    }

    public void clearAllCache() {
        Collection<String> cacheNames = caffeineCacheManager.getCacheNames();
        for (String name : cacheNames) {
            CaffeineCache cache = (CaffeineCache) caffeineCacheManager.getCache(name);
            if (cache != null) {
                cache.clear();
            }
        }
    }
}
