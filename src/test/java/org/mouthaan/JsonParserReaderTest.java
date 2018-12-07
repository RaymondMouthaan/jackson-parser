package org.mouthaan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mouthaan.model.Car;
import org.mouthaan.model.Person;
import org.mouthaan.model.Persons;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonParserReaderTest {
    private static String JSON_DATA = "json";

    @Test
    void readJSONToCarObject() throws IOException {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson = "{\"color\": \"yellow\",\"type\": \"renault\"}";

        // when
        Car car = objectMapper.readValue(carJson, Car.class);

        // then
        assertNotNull(car);
        assertEquals("yellow", car.getColor());
        assertEquals("renault", car.getType());
    }

    @Test
    void readJSONFileToCarObject() throws IOException {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(JSON_DATA + "/car.json");

        // when
        Car car = objectMapper.readValue(resourceAsStream, Car.class);

        // then
        assertNotNull(car);
        assertEquals("yellow", car.getColor());
        assertEquals("renault", car.getType());
    }

    @Test
    void readJSONFileToPersonObject() throws IOException {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(JSON_DATA + "/person.json");

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
    }

    @Test
    void readJSONFileToPersonsObject() throws IOException {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(JSON_DATA + "/persons.json");

        // when
        Persons persons = objectMapper.readValue(resourceAsStream, Persons.class);

        // then
        assertNotNull(persons);
        assertEquals(1, persons.getPersons().get(0).getId());
        assertEquals(2, persons.getPersons().get(1).getId());
        assertEquals(3, persons.getPersons().get(2).getId());
        assertEquals(4, persons.getPersons().get(3).getId());


        assertEquals("Jeanette", persons.getPersons().get(0).getFirst_name());
    }

    @Test
    void readJSONFileToMap() throws IOException {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(JSON_DATA + "/persons.json");

        // when
        Map<String, Object> jsonMap = objectMapper.readValue(resourceAsStream, new TypeReference<Map<String, Object>>() {
        });

    }

    @Test
    void readJSONFileUsingJsonTreeModel() throws IOException {

        // given
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(JSON_DATA + "/persons.json");

        // when
        JsonNode jsonNode = objectMapper.readValue(resourceAsStream, JsonNode.class);

        // then
        assertEquals("Willard", jsonNode.get("persons").get(3).get("first_name").asText());
    }

    @Test
    void creatingJavaListFromJSONArrayString() throws IOException {

        // given
        String jsonCarArray =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";

        ObjectMapper objectMapper = new ObjectMapper();

        // when
        List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>() {
        });

        // then
        assertEquals("Red", listCar.get(1).getColor());
    }

    @Test
    void readJSONFileUsingTypeReferencePersons() throws IOException {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(JSON_DATA + "/persons.json");

        // when
        Persons persons = objectMapper.readValue(resourceAsStream, new TypeReference<Persons>() {
        });

        // then
        persons.getPersons().forEach(person -> {
            System.out.println(person.getId());
            System.out.println(person.getFirst_name());
            System.out.println(person.getLast_name());
            System.out.println(person.getGender());
            System.out.println(person.getEmail());
            person.getIp_addresses().forEach(a -> System.out.println(a.getIp_address()));
            System.out.println("-----");
        });
    }
}
