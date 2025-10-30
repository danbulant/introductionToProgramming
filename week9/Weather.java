import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import common.In;

public class Weather {
    public static void main(String[] args) {
        try {
            // this wasn't needed on my main NixOS system but was needed on SteamOS
            // without this it just printed ? for utf-8 characters
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
        } catch (Exception e) {
        }

        if (args.length != 1) {
            System.out.println("Missing argument for city name");
            return;
        }
        String url = String.format("https://www.flotvejr.dk/%s/observations", args[0]);
        // String url = "./test.html";
        String source = new In(url).readAll();
        UglierSoup soup = new UglierSoup(source);

        System.out.println("╭────────────┬─────────────────────────────┬────────────╮");
        var isFirst = true;
        for (var row : soup.getElements(".nearby-observations-table tr")) {
            if (isFirst)
                isFirst = false;
            else
                System.out.println("├────────────┼─────────────────────────────┼────────────┤");
            var temperature = row.getElement(".nearby-observations-temperature").getInnerText().trim();
            var location = row.getElement(".nobr a").getInnerText();
            var observation = row.getElement(".observation_ago").getInnerText().trim().split(" ");
            var time = observation[1] + " mins ago";
            var place = observation[4] + " km away";
            var nobrs = row.getElements(".nobr");
            var windspeed = nobrs[nobrs.length - 1].getInnerText().trim();
            System.out.printf(
                    "│ %-10s │ %-27s │ %-10s │\n│            │ %-12s - %-12s │            │\n",
                    temperature, location, windspeed, time, place);
        }

        System.out.println("╰────────────┴─────────────────────────────┴────────────╯");
    }
}
