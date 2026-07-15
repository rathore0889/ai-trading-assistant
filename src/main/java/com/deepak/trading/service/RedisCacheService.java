package com.deepak.trading.service;

import java.time.Duration;

public interface RedisCacheService {

    <T> T get(String key, Class<T> type);

    void put(String key, Object value, Duration ttl);

    void delete(String key);

}