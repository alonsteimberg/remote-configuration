package org.alonst.provider;

import com.fasterxml.jackson.databind.JsonNode;

@FunctionalInterface
public interface Provider {
    JsonNode get(String configMapName);
}
