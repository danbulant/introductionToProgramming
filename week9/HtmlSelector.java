import java.util.ArrayList;
import java.util.Arrays;

public class HtmlSelector {
    static class HtmlSelectorItem {
        final String tagName;
        final String[] classNames;

        HtmlSelectorItem(String tagName, String[] classNames) {
            this.tagName = tagName;
            this.classNames = classNames;
        }

        static HtmlSelectorItem parse(String item) {
            if (item.length() == 0)
                return new HtmlSelectorItem(null, null);
            var sectors = item.split(".");
            if(sectors.length == 0) {
                if(item.charAt(0) == '.') {
                    return new HtmlSelectorItem(null, new String[]{ item.substring(1) });
                } else {
                    return new HtmlSelectorItem(item, null);
                }
            }
            return new HtmlSelectorItem(
                    sectors[0].isEmpty() ? null : sectors[0],
                    Arrays.copyOfRange(sectors, 1, sectors.length));
        }

        boolean matches(HtmlTag tag) {
            if (this.tagName != null && !this.tagName.equals(tag.name))
                return false;
            if(classNames != null) {
                var classes = tag.className();
                if(classes == null) return false;
                for (var cn : classNames) {
                    var found = false;
                    for (var tcn : classes)
                        if (cn.equalsIgnoreCase(tcn)) {
                            found = true;
                            break;
                        }
                    if (!found)
                        return false;
                }
            }
            return true;
        }

        @Override
        public String toString() {
            return String.format("{name: %s, classes: %s}", tagName, Arrays.toString(classNames));
        }
    }

    final HtmlSelectorItem[] items;

    HtmlSelector(String selector) {
        var items = selector.split(" ");
        var res = new ArrayList<>();
        for (var item : items) {
            res.add(HtmlSelectorItem.parse(item));
        }
        this.items = res.toArray(new HtmlSelectorItem[res.size()]);
    }

    HtmlSelector(HtmlSelectorItem[] items) {
        this.items = items;
    }

    HtmlSelector inner() {
        return new HtmlSelector(Arrays.copyOfRange(this.items, 1, this.items.length));
    }
    HtmlSelectorItem current() {
        return this.items[0];
    }
    int length() {
        return this.items.length;
    }
}
