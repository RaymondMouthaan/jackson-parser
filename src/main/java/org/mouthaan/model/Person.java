package org.mouthaan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private Map<String, String> address;
    private ArrayList<Ip_Address> ip_addresses = new ArrayList<>();
}
