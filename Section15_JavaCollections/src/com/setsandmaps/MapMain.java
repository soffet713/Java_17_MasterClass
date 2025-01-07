package com.setsandmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {

    public static void main(String[] args) {

        List<Contact> emails = ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        String separator = "-".repeat(85);

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.forEach(System.out::println);
        System.out.println(separator);

        Map<String, Contact> contacts = new HashMap<>();

        for(Contact c : fullList) {
            contacts.put(c.getName(), c);
        }

        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(separator);
        System.out.println(contacts.get("Charlie Brown"));

        System.out.println(contacts.get("Chuck Brown"));
        Contact defaultContact = new Contact("Chuck Brown");
        System.out.println(contacts.getOrDefault("Chuck Brown", defaultContact));

        System.out.println(separator);
        contacts.clear();
        for(Contact c : fullList) {
            Contact duplicate = contacts.put(c.getName(), c);
            if(duplicate != null) {
                //System.out.println("duplicate = " + duplicate);
                //System.out.println("current = " + c);
                contacts.put(c.getName(), c.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(separator);
        contacts.clear();

        for(Contact c : fullList) {
            contacts.putIfAbsent(c.getName(), c);
        }
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(separator);
        contacts.clear();

        for(Contact c : fullList) {
            Contact duplicate = contacts.putIfAbsent(c.getName(), c);
            if(duplicate != null) {
                contacts.put(c.getName(), c.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(separator);
        contacts.clear();

        fullList.forEach(c -> contacts.merge(c.getName(), c, Contact::mergeContactData));
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(separator);
        for(String contactName : new String[] {"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
            contacts.computeIfAbsent(contactName, Contact::new);
        }
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(separator);
        for(String contactName : new String[] {"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
            contacts.computeIfPresent(contactName, (k, v) -> { v.addEmail("Fun Place"); return v; });
        }
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(separator);
        contacts.replaceAll((k, v) -> {
            String newEmail = k.replaceAll(" ", "") + "@funplace.com";
            v.replaceEmailIfExists("DDuck@funplace.com", newEmail);
            return v;
        });
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(separator);
        Contact daisy = new Contact("Daisy Jane Duck", "daisyj@duck.com");

        Contact replacedContact = contacts.replace("Daisy Duck", daisy);
        System.out.println("daisy = " + daisy);
        System.out.println("replacedContact = " + replacedContact);
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(separator);
        Contact updatedDaisy = replacedContact.mergeContactData(daisy);
        System.out.println("updatedDaisy = " + updatedDaisy);
        boolean success = contacts.replace("Daisy Duck", daisy, updatedDaisy);
        if(success) {
            System.out.println("Successfully replaced element.");
        } else {
            System.out.printf("Did not match on both key: %s and value: %s %n", "Daisy Duck", replacedContact);
        }
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(separator);
        success = contacts.remove("Daisy Duck", daisy);
        if(success) { System.out.println("Successfully removed element."); }
        else { System.out.printf("Did not match on both key: %s and value: %s %n", "Daisy Duck", daisy); }
        contacts.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));
    }
}
