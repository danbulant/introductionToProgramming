import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import common.In;

public class UglierSoup {
    public static void main(String[] args) {
        // var in = new In("./test.html");
        // var content = in.readAll();
        var content = "\n" + //
                        "\t\t\t\t<tr>\n" + //
                        "\t\t\t\t\t<td class=\"nearby-observations-temperature red\">\n" + //
                        "\t\t\t\t\t\t9.4&deg;\n" + //
                        "\t\t\t\t\t</td>\n" + //
                        "\t\t\t\t\t<td class=\"nobr\">\n" + //
                        "\t\t\t\t\t\t<a href=\"/weather-station/askov\">Askov</a>\n" + //
                        "\t\t\t\t\t\t<br>\n" + //
                        "\t\t\t\t\t\t<span class=\"observation_ago\">\n" + //
                        "\t\t\t\t\t\tFor 70 minutter siden, 103 km\n" + //
                        "\t\t\t\t\t\t<!--\n" + //
                        "\t\t\t\t\t\t åt \n" + //
                        "\t\t\t\t\t\t-->\n" + //
                        "\t\t\t\t\t\t</span>\n" + //
                        "\t\t\t\t\t\t\n" + //
                        "\t\t\t\t\t</td>\n" + //
                        "\n" + //
                        "\t\t\t\t\t<td class=\"nobr\">\n" + //
                        "\t\t\t\t\t\t\t3 m/s\n" + //
                        "\t\t\t\t\t\t\t\n" + //
                        "\t\t\t\t\t</td>\t\t\t\t\t\t\t\n" + //
                        "\t\t\t\t\t\n" + //
                        "\t\t\t\t\t<td>\n" + //
                        "\t\t\t\t\t\t\t<img alt=\"wind arrow\" class=\"\" src=\"//static.flotvejr.dk/images/arrows/blue/15/270.png\" />\n" + //
                        "\t\t\t\t\t</td>\n" + //
                        "\n" + //
                        "\t\t\t\t</tr>";
        var parser = new UglierSoup(content);
        System.out.println(parser.data.toString());
    }

    final CharBuffer content;
    final ArrayList<HtmlItem> data;

    UglierSoup(String content) {
        this.content = CharBuffer.wrap(content);
        this.data = loop();
    }

    UglierSoup(CharBuffer content) {
        this.content = content;
        this.data = loop();
    }

    public HtmlTag[] getElements(String selector) {
        var results = new ArrayList<HtmlTag>();
        var htmlselector = new HtmlSelector(selector);
        getElements(htmlselector, results);
        return results.toArray(new HtmlTag[results.size()]);
    }

    void getElements(HtmlSelector selector, ArrayList<HtmlTag> results) {
        for (var kid : data) {
            if (kid.type != HtmlItem.HtmlItemType.Tag)
                continue;
            kid.getTag().getElements(selector, results);
        }
    }

    // script should also have separate handling to completely skip contents
    // like with comments, as HTML can be present within script tags
    final static String[] selfClosingTags = { "!doctype", "link", "meta", "img", "br", "script" };

    final static Pattern whitespace = Pattern.compile("^\\s+");
    final static Pattern comment = Pattern.compile("^<!--[\\s\\S]*?-->");
    final static Pattern identifier = Pattern.compile("^[!a-zA-Z0-9_][a-zA-Z0-9_-]*");
    final static Pattern paramContents = Pattern.compile("^(\"[^\"]*\"|'[^']*'|[a-zA-Z0-9_][a-zA-Z0-9_-]*)");
    final static Pattern textContent = Pattern.compile("^[^<]+");
    final static Pattern scriptEnd = Pattern.compile("</script\\s*>");
    final static Pattern htmlEntities = Pattern.compile("&([a-zA-Z0-9_-]+);");

    boolean shouldSkipKids(String name) {
        var lowercase = name.toLowerCase();

        for (var item : selfClosingTags) {
            if (item.equals(lowercase))
                return true;
        }

        return false;
    }

    void advance() {
        advance(1);
    }

    void advance(int num) {
        content.position(content.position() + num);
    }

    void skipPattern(Pattern pat) {
        var wsmatcher = pat.matcher(content);
        if (!wsmatcher.find())
            return;
        advance(wsmatcher.end());
    }

    String cleanupParamValue(String value) {
        if (value.charAt(0) == '"') {
            value = value.substring(1, value.length() - 1);
        }

        return handleHtmlEntities(value);
    }

    String handleHtmlEntities(String value) {
        return htmlEntities.matcher(value).replaceAll((MatchResult res) -> {
            var name = res.group(1).toLowerCase();
            switch(name) {
                case "deg":
                    return "°";
            }
            return res.group();
        });
    }

    String parseText() {
        var matcher = textContent.matcher(content);
        if (!matcher.find())
            return null;
        var text = matcher.group();
        advance(matcher.end());
        return handleHtmlEntities(text);
    }

    HtmlTag parseTag() {
        if (content.charAt(0) != '<')
            return null;
        advance();
        skipPattern(whitespace);
        var nameMatcher = identifier.matcher(content);
        if (!nameMatcher.find())
            return null;
        var name = nameMatcher.group();
        advance(nameMatcher.end());
        var params = new HashMap<String, String>();
        while (true) {
            skipPattern(whitespace);
            if (content.charAt(0) == '/')
                advance();
            if (content.charAt(0) != '>') {
                var identMatcher = identifier.matcher(content);
                if (!identMatcher.find())
                    break;
                var paramName = identMatcher.group();
                advance(identMatcher.end());
                var paramContent = "";
                if (content.charAt(0) == '=') {
                    advance();
                    var contentMatcher = paramContents.matcher(content);
                    if (contentMatcher.find()) {
                        paramContent = cleanupParamValue(contentMatcher.group());
                        advance(contentMatcher.end());
                    }
                }
                params.put(paramName, paramContent);
            } else
                break;
        }
        advance();
        ArrayList<HtmlItem> kids = null;
        // shouldSkipKids completely omits content including ending tags
        // for script we need to skip content until ending tag.
        // use regex for quick lookahead.
        if (name.equals("script")) {
            var scriptMatcher = scriptEnd.matcher(content);
            if (!scriptMatcher.find())
                throw new Error("Script doesn't have end tag?");
            advance(scriptMatcher.end());
        } else if (!shouldSkipKids(name)) {
            var kidParser = new UglierSoup(content);
            kids = kidParser.data;
        }

        return new HtmlTag(name, params, kids == null ? new HtmlItem[0] : kids.toArray(new HtmlItem[kids.size()]));
    }

    // this is wrong behaviour
    // ideally this should check the closing element's name and otherwise go up the chain
    // so that it 'heals' if an element is not closed before it's parent is
    // this completely ignores the name of an element and just closes the current one
    // and depends on valid HTML being submitted (with only the few self closing tags defined above)
    boolean detectClosingTag() {
        if (!(content.charAt(0) == '<' && content.charAt(1) == '/'))
            return false;
        advance(2);
        var identMatcher = identifier.matcher(content);
        if (!identMatcher.find())
            throw new Error("Ending tag without tag name?");
        advance(identMatcher.end());
        skipPattern(whitespace);
        if (content.charAt(0) != '>')
            throw new Error("Ending tag without > after whitespace");
        advance();
        return true;
    }

    ArrayList<HtmlItem> loop() {
        var data = new ArrayList<HtmlItem>();
        while (true) {
            skipPattern(whitespace);
            var lastPos = content.position();
            while (true) {
                skipPattern(comment);
                skipPattern(whitespace);
                if (content.position() == lastPos)
                    break;
                lastPos = content.position();
            }
            if (content.remaining() == 0)
                break;
            if (detectClosingTag())
                break;
            var tag = parseTag();
            if (tag != null) {
                data.add(HtmlItem.tag(tag));
            } else {
                var text = parseText();
                if (text != null) {
                    data.add(HtmlItem.text(text));
                } else
                    break;
            }
        }
        return data;
    }
}
