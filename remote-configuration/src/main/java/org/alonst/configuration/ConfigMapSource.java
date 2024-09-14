package org.alonst.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConfigMapSource {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final ConfigMapSourceProperties properties;

    @Bean
    Map<String, JsonNode> generateConfigMaps() throws ConfigMapsLoadingException {
        try (Stream<Path> paths = Files.walk(Paths.get(this.properties.getSourceDirPath()))) {
            return paths.filter(path -> FilenameUtils.getExtension(path.toString()).equals("json"))
                    .map(Path::toFile)
                    .map(this::isValidJsonFile)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        } catch (IOException e) {
            throw new ConfigMapsLoadingException(e);
        }
    }

    Optional<Map.Entry<String, JsonNode>> isValidJsonFile(File file) {
        try {
            return Optional.of(new AbstractMap.SimpleEntry<>(FilenameUtils.removeExtension(file.getName()), OBJECT_MAPPER.readTree(file)));
        } catch (IOException e) {
            System.out.printf("could not parse json file. [file-name]: %s", file.getName());
            return Optional.empty();
        }
    }
}
