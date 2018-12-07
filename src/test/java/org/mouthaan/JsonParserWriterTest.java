package org.mouthaan;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mouthaan.model.Car;
import org.mouthaan.model.Ip_Address;
import org.mouthaan.model.Person;
import org.mouthaan.model.Persons;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class JsonParserWriterTest {

    @Test
    void writeCarObjectToJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Car car = new Car("yellow", "renault");
        objectMapper.writeValue(new File("target/car.json"), car);
    }

    @Test
    void writePersonObjectToJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Person person = new Person();
        person.setId(1);
        person.setFirst_name("Ray");
        person.setLast_name("mouthaan");
        person.setEmail("test@test.org");
        person.setGender("male");

        Map<String, String> address = new HashMap<>();
        address.put("line1", "my address line 1");
        address.put("line2", "my address line 1");
        person.setAddress(address);

        ArrayList<Ip_Address> addresses = new ArrayList<>();
        addresses.add(new Ip_Address("10.10.10.1"));
        addresses.add(new Ip_Address("10.10.10.2"));
        person.setIp_addresses(addresses);

        objectMapper.writeValue(new File("target/person.json"), person);
    }

    @Test
    void writePersonsObjectToJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Persons persons = new Persons();
        Person person1 = new Person();
        person1.setId(1);
        person1.setFirst_name("Ray");
        person1.setLast_name("mouthaan");
        person1.setEmail("test@test.org");
        person1.setGender("male");
        ArrayList<Ip_Address> addresses1 = new ArrayList<>();
        addresses1.add(new Ip_Address("10.10.10.1"));
        addresses1.add(new Ip_Address("10.10.10.2"));
        person1.setIp_addresses(addresses1);

        Person person2 = new Person();
        person2.setId(2);
        person2.setFirst_name("Luna");
        person2.setLast_name("de Puna");
        person2.setEmail("test@test.org");
        person2.setGender("female");
        ArrayList<Ip_Address> addresses2 = new ArrayList<>();
        addresses2.add(new Ip_Address("10.10.10.11"));
        addresses2.add(new Ip_Address("10.10.10.21"));
        person2.setIp_addresses(addresses2);

        ArrayList<Person> personArrayList = new ArrayList<>();
        personArrayList.add(person1);
        personArrayList.add(person2);
        persons.setPersons(personArrayList);

        objectMapper.writeValue(new File("target/persons.json"), persons);
    }

}
