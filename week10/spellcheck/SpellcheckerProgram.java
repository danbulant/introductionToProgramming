import java.util.Arrays;

public class SpellcheckerProgram {
    public static void main(String[] args) {
        if (args.length < 2) {
            assertTests();
            System.out.println("SpellcheckerProgram <lang> <...words>");
            return;
        }
        var lang = args[0];
        var otherArgs = Arrays.copyOfRange(args, 1, args.length);
        var text = String.join(" ", otherArgs);
        // in real / more advanced spell checker, this would be done per language
        // capital letters aren't checked properly as the implementations don't know if
        // the word starts a sentence or not
        var words = splitWords(text);

        var spellchecker = getSpellchecker(lang);

        var failed = false;
        for (var word : words) {
            if (!spellchecker.isWord(word)) {
                failed = true;
                System.out.println(word);
            }
        }

        if (failed) {
            System.exit(1);
        }
    }

    static String[] splitWords(String text) {
        return text.split("[\\s.!?'\"]");
    }

    static Spellchecker getSpellchecker(String lang) {
        return switch (lang) {
            case "cs" -> new CzechSpellchecker();
            case "en" -> new EnglishSpellchecker();
            case "java" -> new JavaSpellchecker();
            default -> throw new Error("Unknown language");
        };
    }

    static boolean checkAllWords(Spellchecker spellchecker, String text) {
        for (var word : splitWords(text)) {
            if (!spellchecker.isWord(word)) {
                return false;
            }
        }
        return true;
    }

    static void assertTests() {
        var cs = getSpellchecker("cs");
        var en = getSpellchecker("en");
        var java = getSpellchecker("java");

        assert checkAllWords(cs,
                "tři tisíce tři třicet tři stříbrných stříkaček stříkalo přes tři tisíce tři třicet tři stříbrných střech.");
        assert !checkAllWords(cs, "třitisíce");
        assert checkAllWords(en, "Buffalo buffalo Buffalo buffalo buffalo buffalo Buffalo buffalo");
        assert !checkAllWords(en, "Buffal buffal Buffal buffal buffal buffal Buffal buffal");
        assert checkAllWords(java, "IExtendedJavaFactoryFactory FactoryJavaFactoryExtended");
        assert !checkAllWords(java, "IExtendedJavaFactoryFactor");
    }
}
