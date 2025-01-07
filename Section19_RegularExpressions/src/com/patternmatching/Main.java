package com.patternmatching;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String sentence = "I like B.M.W. motorcycles.";
        boolean matched = Pattern.matches("[A-Z].*[.]", sentence);
        System.out.println(matched + ": " + sentence);

        Pattern firstPattern = Pattern.compile("[A-Z].*?[.]");
        var matcher = firstPattern.matcher(sentence);
        System.out.println(matcher.matches() + ": " + sentence);
        System.out.println("sentence.length: " + sentence.length());
        System.out.println("Matched Ending Index: " + matcher.end());

        System.out.println(matcher.lookingAt() + ": " + sentence);
        System.out.println("Matched Ending Index: " + matcher.end());
        System.out.println("Matched on : " + sentence.substring(0, matcher.end()));

        matcher.reset();
        System.out.println(matcher.find() + ": " + sentence);
        System.out.println("Matched Ending Index: " + matcher.end());
        System.out.println("Matched on : " + sentence.substring(matcher.start(), matcher.end()));
        System.out.println("Matched on : " + matcher.group());

        String htmlSnippet = """
                <H1>My Heading</H1>
                <h2>Sub-heading</h2>
                <p>This is a paragraph about something.</p>
                <p>This is another paragraph about something else.</p>
                <h3>Summary</h3>
                """;

        Pattern htmlPattern = Pattern.compile("<[hH]\\d>(.*)</[hH]\\d>");
        Matcher htmlMatcher = htmlPattern.matcher(htmlSnippet);

        while (htmlMatcher.find()) {
            //System.out.println("group: " + htmlMatcher.group());
            //System.out.println("group0: " + htmlMatcher.group(0));
            System.out.println(htmlMatcher.group(1));
        }

        Pattern htmlPattern2 = Pattern.compile("<[hH](?<level>\\d)>(.*)</[hH]\\d>");
        Matcher htmlMatcher2 = htmlPattern2.matcher(htmlSnippet);

        while (htmlMatcher2.find()) {
            //System.out.println("group: " + htmlMatcher.group());
            //System.out.println("group0: " + htmlMatcher.group(0));
            System.out.println(htmlMatcher2.group("level") + " " + htmlMatcher2.group(2));
            System.out.println("index = " + htmlMatcher2.start("level"));
        }

        System.out.println("-".repeat(45));

        htmlMatcher2.reset();
        htmlMatcher2.results().forEach(mr -> System.out.println(
                mr.group(1) + " " + mr.group(2)));

        String tabbedText = """
                group1\tgroup2\tgroup3
                1\t2\t3
                a\tb\td
                """;

        tabbedText.lines()
                .flatMap(s -> Pattern.compile("\\t").splitAsStream(s))
                .forEach(System.out::println);

        htmlMatcher2.reset();
        String updatedSnippet = htmlMatcher2.replaceFirst((mr) ->
                "<em>" + mr.group(2) + "</em>");
        System.out.println("-".repeat(45));
        System.out.println(updatedSnippet);
        System.out.println(htmlMatcher2.start() + " : " + htmlMatcher2.end());
        System.out.println(htmlMatcher2.group(2));

        htmlMatcher2.usePattern(Pattern.compile("<([hH]\\d)>(.*)</\\1>"));
        htmlMatcher2.reset();
        System.out.println("-".repeat(45));
        System.out.println("Using Back Reference: \n" + htmlMatcher2.replaceFirst("<em>$2</em>"));

        String replacedHTML = htmlMatcher2.replaceAll((mr) ->
                "<em>" + mr.group(2) + "</em>");
        System.out.println("-".repeat(45));
        System.out.println(replacedHTML);

        htmlMatcher2.reset();
        StringBuilder sb = new StringBuilder();
        int index = 1;
        while (htmlMatcher2.find()) {
            htmlMatcher2.appendReplacement(sb,
                    switch (htmlMatcher2.group(1).toLowerCase()) {
                        case "h1" -> "<head>$2</head>";
                        case "h2" -> "<em>$2</em>";
                        default -> "<$1>" + index++ + ". $2</$1>";
                    });
        }
        htmlMatcher2.appendTail(sb);
        System.out.println(sb);
    }
}
