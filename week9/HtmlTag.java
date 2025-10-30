import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HtmlTag {
    public final String name;
    public final HashMap<String, String> params;
    public final HtmlItem[] kids;

    HtmlTag(String name, HashMap<String, String> params, HtmlItem[] kids) {
        this.name = name;
        this.params = params;
        this.kids = kids;
    }

    public String getInnerText() {
        var text = "";
        for (var item : kids) {
            switch (item.type) {
                case HtmlItem.HtmlItemType.Text:
                    text += item.getText();
                    break;
                case HtmlItem.HtmlItemType.Tag:
                    text += item.getTag().getInnerText();
                    break;
            }
        }
        text += " ";
        return text.replaceAll("\\s+", " ");
    }

    public String[] className() {
        var classNames = this.params.get("class");
        if (classNames == null)
            return null;
        return classNames.split(" ");
    }

    public boolean matches(HtmlSelector.HtmlSelectorItem item) {
        if(item == null) return true;
        return item.matches(this);
    }

    public HtmlTag[] getElements(String selector) {
        var results = new ArrayList<HtmlTag>();
        var htmlselector = new HtmlSelector(selector);
        getElements(htmlselector, results);
        return results.toArray(new HtmlTag[results.size()]);
    }
    void getElements(HtmlSelector selector, ArrayList<HtmlTag> results) {
        var current = selector.current();
        if(matches(current)) {
            // these two ifs result in the algorithm stopping at the last selector
            // but if no selector is provided, all elements will be returned
            if(selector.length() < 2) results.add(this);
            if(selector.length() == 1) return;
            selector = selector.inner();
        }
        for(var kid : kids) {
            if(kid.type != HtmlItem.HtmlItemType.Tag) continue;
            kid.getTag().getElements(selector, results);
        }
    }

    public HtmlTag getElement(String selector) {
        return getElements(selector)[0];
    }

    @Override
    public String toString() {
        return String.format("<%s params: %s> %s </%s>", name, params, Arrays.toString(kids), name);
    }
}
