package com.alok328.MutualFunds.service;

import com.alok328.MutualFunds.model.Stock;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FetchPortfolio {

    public List<Stock> fetchByURL(String URL){
        List<Stock> stocksInPortfolio = new ArrayList<>();
        try {
            Document document = Jsoup.connect(URL).get();
            Elements elements = document.getAllElements().select("#equityCompleteHoldingTable").get(0).getElementsByTag("a");
            for(Element e : elements){
                stocksInPortfolio.add(new Stock(e.text()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stocksInPortfolio;
    }
}
