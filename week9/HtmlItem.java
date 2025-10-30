public class HtmlItem {
    public enum HtmlItemType {
        Text,
        Tag
    }

    HtmlItemType type;
    String textContent;
    HtmlTag tagContent;

    public static HtmlItem tag(HtmlTag tag) {
        var item = new HtmlItem();
        item.type = HtmlItemType.Tag;
        item.tagContent = tag;
        return item;
    }

    public static HtmlItem text(String content) {
        var item = new HtmlItem();
        item.type = HtmlItemType.Text;
        item.textContent = content;
        return item;
    }

    public HtmlTag getTag() {
        assert this.type == HtmlItemType.Tag;
        return this.tagContent;
    }

    public String getText() {
        assert this.type == HtmlItemType.Text;
        return this.textContent;
    }

    @Override
    public String toString() {
        switch (this.type) {
            case HtmlItemType.Tag:
                return this.tagContent.toString();
            case HtmlItemType.Text:
                return this.textContent;
        }
        return null;
    }
}
