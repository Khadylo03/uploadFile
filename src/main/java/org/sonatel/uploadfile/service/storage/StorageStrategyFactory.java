package org.sonatel.uploadfile.service.storage;

import org.sonatel.uploadfile.domain.enums.StorageType;
import org.sonatel.uploadfile.service.interfaces.IStorageStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StorageStrategyFactory {

    private final Map<StorageType, IStorageStrategy> strategies;

    @Autowired
    public StorageStrategyFactory(Map<StorageType, IStorageStrategy> strategies) {
        this.strategies = strategies;
    }

    public IStorageStrategy getStrategy(StorageType storageType) {
        return strategies.get(storageType);
    }
}
