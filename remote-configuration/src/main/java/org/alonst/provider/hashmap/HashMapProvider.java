package org.alonst.provider.hashmap;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.alonst.provider.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HashMapProvider implements Provider {
    private final Map<String, JsonNode> configMaps;

    @Override
    public JsonNode get(String configMapName) {
        return this.configMaps.get(configMapName);
    }
}
