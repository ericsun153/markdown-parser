//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        boolean contSwitch = false;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            //If there is no more openBracket in the text, set the contSwitch on.
            if (openBracket == -1){
                contSwitch = true;
            }
            int closeBracket = markdown.indexOf("]", openBracket);
            //If there is no more closeBracket in the text, set the contSwitch on.
            if (closeBracket == -1){
                contSwitch = true;
            }
            int openParen = markdown.indexOf("(", closeBracket);
            //If there isn't an openParen directly after closeBracket, set contSwitch on.
            if (openParen == -1 || openParen != closeBracket+1){
                contSwitch = true;
            }
            int closeParen = markdown.indexOf(")", openParen);
            //If there is no more closeParen in the text, set the contSwitch on.
            if (closeParen == -1){
                contSwitch = true;
            }
            int period = markdown.indexOf(".", openParen);
            //If there is no more period in the text OR a period after
            //the closeParen, set the contSwitch on.
            if (period == -1 || period > closeParen){
                contSwitch = true;
            }
            int space = markdown.indexOf(" ", openParen);
            //If there is a space between the parentheses, set the
            //contSwtich on.
            if (space != -1 && space < closeParen){
                contSwitch = true;
            }
            //If there is an exclamation mark directly before openBracket,
            //set the contSwitch on.
            if (openBracket > 0){
                if (markdown.charAt(openBracket-1) == '!'){
                    contSwitch = true;
                }
            }

            //IF contSwitch is TRUE, increment currentIndex and
            //skip to the next iteration of the loop.
            if (contSwitch){
                currentIndex++;
                contSwitch = false;
                continue;
            }

            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
