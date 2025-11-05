import java.util.Arrays;
import java.util.HashSet;

class JavaSpellchecker implements Spellchecker {
    public HashSet<String> words = new HashSet<String>(Arrays.asList("I", "Java", "Factory", "Extended"));

    public boolean isWord(String word) {
        // we could use slices but they copy it char for char anyway
        var buf = "";
        for (var i = 0; i < word.length(); i++) {
            var ch = word.charAt(i);
            if (Character.isUpperCase(ch)) {
                if (!buf.isEmpty() && !words.contains(buf))
                    return false;
                buf = "";
            }
            buf += ch;
        }
        if (!buf.isEmpty() && !words.contains(buf))
            return false;
        return true;
    };
}