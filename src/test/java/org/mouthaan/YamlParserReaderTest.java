package org.mouthaan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;
import org.mouthaan.model.Car;
import org.mouthaan.model.Person;
import org.mouthaan.model.Persons;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class YamlParserReaderTest {
    private static String YAML_DATA = "yaml";

    @Test
    void readYAMLFileToCarObject() throws IOException {
        // given
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        // when
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(YAML_DATA + "/car.yaml");
        Car car = objectMapper.readValue(resourceAsStream, Car.class);

        // then
        assertNotNull(car);
        assertEquals("yellow", car.getColor());
        assertEquals("renault", car.getType());
    }

    @Test
    void readYAMLFileToPersonObject() throws IOException {
        // given
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(YAML_DATA + "/person.yaml");

        // when
        Person person = objectMapper.readValue(resourceAsStream, Person.class);

        // then
        assertNotNull(person);
        assertEquals(1, person.getId());
        assertEquals("Jeanette", person.getFirst_name());
        assertEquals("Penddreth", person.getLast_name());
        assertEquals("jpenddreth0@census.gov", person.getEmail());
        assertEquals("Female", person.getGender());
        assertEquals("10.10.10.1", person.getIp_addresses().get(0).getIp_address());
        assertEquals("10.10.10.2", person.getIp_addresses().get(1).getIp_address());
        assertEquals("My Address Line 1", person.getAddress().get("line1"));

    }

    @Test
    void readYAMLFileToPersonsObject() throws IOException {
        // given
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(YAML_DATA + "/persons.yaml");

        // when
        Persons persons = objectMapper.readValue(resourceAsStream, Persons.class);

        // then
        assertNotNull(persons);
        assertEquals(1, persons.getPersons().get(0).getId());
        assertEquals("Jeanette", persons.getPersons().get(0).getFirst_name());

        assertEquals(2, persons.getPersons().get(1).getId());
        assertEquals("Giavani", persons.getPersons().get(1).getFirst_name());

        assertEquals(3, persons.getPersons().get(2).getId());
        assertEquals("Noell", persons.getPersons().get(2).getFirst_name());

        assertEquals(4, persons.getPersons().get(3).getId());
        assertEquals("Willard", persons.getPersons().get(3).getFirst_name());


    }
}
