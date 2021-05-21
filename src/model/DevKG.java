package model;

import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class DevKG {

    public static void fillSampleData(ObservableList<Vacancy> list2) {
        String url = "";
        //List<Vacancy> list = new ArrayList<>();

        for (int i = 1; i < 12; i++) {
            if (i >= 2) {
                url = "https://devkg.com/ru/jobs" + ("?page=") + String.valueOf(i);
            } else {
                url = "https://devkg.com/ru/jobs";
            }
            try {
                final Document document = Jsoup.connect(url).get();
                //System.out.println(document.outerHtml());


                Vacancy v = new Vacancy();
                for (Element row : document.select(
                        "div.jobs-list article a")) {
                    if (row.select("div.jobs-item-field.company").text().equals("")) {
                        continue;
                    } else {
                        //System.out.println(document.select("div.pagination a").get(0));
                        v.setCompanyName(row.select("div.jobs-item-field.company").text().replace("Компания", "").strip());
                        v.setTitle(row.select("div.jobs-item-field.position").text().replace("Должность", "").strip());
                        v.setSalary(row.select("div.information div.jobs-item-field.price").text().replace("Оклад", "").strip());
                        if (v.getSalary() == "-") {
                            v.setSalary("Not mentioned");
                        }
                        v.setCountry("Kyrgyzstan");
                    }
                    list2.add(new Vacancy(v.getTitle(), v.getCompanyName(), v.getCountry(), v.getSalary()));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
