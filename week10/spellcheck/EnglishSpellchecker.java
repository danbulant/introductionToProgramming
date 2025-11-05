import java.util.Arrays;
import java.util.HashSet;

class EnglishSpellchecker implements Spellchecker {
    public HashSet<String> words = new HashSet<String>(
            Arrays.asList("buffalo", "I", "the", "a", "old", "young", "man", "woman", "boat"));

    public boolean isWord(String word) {
        if (words.contains(word))
            return true;
        var lowercasedFirst = Character.toLowerCase(word.charAt(0)) + word.substring(1);
        if (words.contains(lowercasedFirst))
            return true;
        return false;
    }
}
