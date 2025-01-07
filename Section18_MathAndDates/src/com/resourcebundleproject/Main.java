package com.resourcebundleproject;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {

        //Locale.setDefault(Locale.JAPAN);
        for (Locale l : List.of(Locale.US, Locale.CANADA_FRENCH, Locale.CANADA, Locale.JAPAN)) {
            ResourceBundle rb = ResourceBundle.getBundle("BasicText", l);
            //System.out.println(rb.getClass().getName());
            //System.out.println(rb.getBaseBundleName());
            //System.out.println(rb.keySet());
            String hello = new String(rb.getString("hello").getBytes(StandardCharsets.ISO_8859_1),
                    StandardCharsets.UTF_8);
            String world = new String(rb.getString("world").getBytes(StandardCharsets.ISO_8859_1),
                    StandardCharsets.UTF_8);

            String message = "%s %s!%n".formatted(hello, world);

            ResourceBundle ui = ResourceBundle.getBundle("UIComponents",l);

            JOptionPane.showOptionDialog(null, message, ui.getString("first.title"),
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, new Object[]{rb.getString("yes"), rb.getString("no")}, null);
        }
    }
}

