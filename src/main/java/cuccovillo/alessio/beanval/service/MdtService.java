package cuccovillo.alessio.beanval.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cuccovillo.alessio.beanval.entity.MdtRow;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class MdtService {
    Map<String, Map<String, MdtRow>> allMdtValues;

    public MdtService() throws IOException {
        TypeReference<Map<String, Map<String, MdtRow>>> type = new TypeReference<Map<String, Map<String, MdtRow>>>() {
        };
        try (InputStream is = getClass().getResourceAsStream("all_mdt_values_map.json")) {
            allMdtValues = new ObjectMapper().readValue(is, type);
        }

    }

    public Optional<Map<String, MdtRow>> getMdtValues(String mdt) {
        return Optional.ofNullable(allMdtValues.get(mdt));
    }
}
