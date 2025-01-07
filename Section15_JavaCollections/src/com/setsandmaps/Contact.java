package com.setsandmaps;

import java.util.HashSet;
import java.util.Set;

public class Contact {

    private String name;
    private Set<String> emails = new HashSet<>();
    private Set<String> phones = new HashSet<>();

    public Contact(String name) {
        this(name,null);
    }

    public Contact(String name, String email) {
        this(name,email,0);
    }

    public Contact(String name, long phone) {
        this(name,null,phone);
    }

    public Contact(String name, String email, long phone) {
        this.name=name;
        if(email!=null&&email.contains("@")) {
            emails.add(email);
        }
        if(phone!=0&&phone>1000000000) {
            String phoneNo = "" + phone;
            phoneNo = "(%s) %s-%s".formatted(phoneNo.substring(0,3),phoneNo.substring(3,6),
                    phoneNo.substring(6));
            phones.add(phoneNo);
        }
    }

    public String getName() {
        return name;
    }

    public String getNameLastFirst() {
        return name.substring(name.indexOf(" ") + 1) + ", " + name.substring(0, name.indexOf(" "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;
        return getName().equals(contact.getName());
    }

    @Override
    public int hashCode() {
        return 33 * getName().hashCode();
    }

    public void addEmail(String companyName) {

        String[] names = name.split(" ");
        String email = "%c%s@%s.com".formatted(name.charAt(0),names[names.length-1],
                companyName.replaceAll(" ","").toLowerCase());
        if(!emails.add(email)){
            System.out.println(name + " already has email " + email);
        } else {
            System.out.println(name + " now has email " + email);
        }
    }

    public void replaceEmailIfExists(String oldEmail, String newEmail) {

        if(emails.contains(oldEmail)) {
            emails.remove(oldEmail);
            emails.add(newEmail);
        }
    }

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(getName(), contact.getName()) && Objects.equals(emails, contact.emails)
                && Objects.equals(phones, contact.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), emails, phones);
    }
 */

    public Contact mergeContactData(Contact contact) {
        Contact newContact = new Contact(name);
        newContact.emails = new HashSet<>(this.emails);
        newContact.phones = new HashSet<>(this.phones);
        newContact.emails.addAll(contact.emails);
        newContact.phones.addAll(contact.phones);
        return newContact;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Emails: " + emails + " | Phones: " + phones;
    }
}
