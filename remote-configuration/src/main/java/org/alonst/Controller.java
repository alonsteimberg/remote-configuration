package org.alonst;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.alonst.provider.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Controller {
    private final Provider configMapsProvider;

    @GetMapping("config-maps/{name}")
    public JsonNode getConfigMap(@PathVariable("name") String configMapName) {
        return configMapsProvider.get(configMapName);
    }
}
