import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class skelbiuLT {
    public void acceptCookies(WebDriver driver){
        driver.get("https://skelbiu.lt");
        WebElement acceptBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
        acceptBtn.click();
    }


//    @Test
//    public void openWebsite() throws InterruptedException {
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();//reikia parasyti, nes kartais irgi luzta testai, kai per didele rezoliucija, sitas padeda sumazinti erroru tikimybe
//        driver.get("https://skelbiu.lt");
//        Thread.sleep(500);//reikia parasyti, ns kartais del to luzta testai, nes nespeja uzkrauti
//        WebElement acceptBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
//        acceptBtn.click();
//        driver.findElement(By.id("searchKeyword")).sendKeys("kengura");
//        driver.findElement(By.id("searchButton")).click();
//        Thread.sleep(500);
//        String adsQantity = driver.findElement(By.xpath("//*[@id=\"body-container\"]/div[2]/div[1]/ul/li/span")).getText();
//        System.out.println("Radome " + adsQantity + " skelbimu.");
//        WebElement container = driver.findElement(By.className("standard-list-container"));
//        List<WebElement> contentBlocks = container.findElements(By.className("content-block"));
//        Thread.sleep(1000);
//        for (int i = 0; i < contentBlocks.size(); i++) {
//            String price = "0";
//            try {
//                price = contentBlocks.get(i).findElement(By.className("price")).getText();
//            } catch (Exception e){
//                System.out.println("nebuvo kainos");
//            }
//            System.out.println(price);
//        }
//        System.out.println("===================================================================================");
//    }


//    @Test
//    public void openWebsite1() throws InterruptedException {
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://skelbiu.lt");
//        Thread.sleep(500);
//        WebElement acceptBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
//        acceptBtn.click();
//        driver.findElement(By.id("searchKeyword")).sendKeys("verpimo ratelis");
//        driver.findElement(By.id("searchButton")).click();
//        Thread.sleep(500);
//
//        String adsQantity = driver.findElement(By.xpath("//*[@id=\"body-container\"]/div[2]/div[1]/ul/li/span")).getText();
//        System.out.println("Radome " + adsQantity + " skelbimu.");
//        double adsQantityDbl = Double.parseDouble(adsQantity.replaceAll("[^\\d]", ""));
//        double priceSum = 0;
//        while (true) {
//            WebElement container1 = driver.findElement(By.className("standard-list-container"));
//            List<WebElement> contentBlocks = container1.findElements(By.className("content-block"));
//            Thread.sleep(1000);
//            for (int i = 0; i < contentBlocks.size(); i++) {
//                String price = "0";
//                double priceDbl = 0;
//                try {
//                    price = contentBlocks.get(i).findElement(By.className("price")).getText();
//                }
//                catch (Exception e) {}
//                priceDbl = Double.parseDouble(price.replaceAll("[^\\d,]", ""));
//                priceSum += priceDbl;
//                System.out.println(price + " " + priceDbl);
//            }
//            WebElement paginationBlock = driver.findElement(By.id("pagination"));
//            try {
//                WebElement lastElement = paginationBlock.findElement(By.linkText("»"));
//                lastElement.click();
//            }catch(Exception e){
//                break;
//            }
//        }
//        System.out.println("Total sum = " + priceSum);
//        System.out.printf("Average price =  %.2f", priceSum / adsQantityDbl);
//        System.out.println("===================================================================================");
//    }

    @Test
    public void dataGatherer() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        acceptCookies(driver);

        String searchKeyword = "banglente";
        String baseUrl = "https://www.skelbiu.lt/skelbimai/";
        List<String> urls = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            String url = baseUrl + i + "?keywords=" + searchKeyword.replace(" ", "%20");
            driver.get(url);
            String currentUrl = driver.getCurrentUrl();
            if (!url.equals(currentUrl)) {
                break;
            }
            urls.addAll(getUrls(driver));
        }
        System.out.println(urls.size());
        getUserData(driver, urls);
    }

    private void getUserData(WebDriver driver, List<String> urls) {
        for (String url : urls) {
            driver.get(url);
            try {
                System.out.println(driver.findElement(By.cssSelector(".profile-data > .name")).getText() + " " +driver.getCurrentUrl());
            }
            catch (Exception e) {
                System.out.println("##### " + driver.getCurrentUrl());
            }
        }
    }


    private List<String> getUrls (WebDriver driver){
        List<WebElement> urlsElements = driver.findElements(By.cssSelector(".standard-list-container > div > a"));
        List<String> urls = new ArrayList<>();
        for (WebElement urlElem:urlsElements){
            String url = ("https://www.skelbiu.lt" + urlElem.getDomAttribute("href"));
            urls.add(url);
        }
        return urls;
    }


//        driver.findElement(By.id("searchKeyword")).sendKeys("banglente");
//        driver.findElement(By.id("searchButton")).click();
//        Thread.sleep(500);
//
//        String adsQantity = driver.findElement(By.xpath("//*[@id=\"body-container\"]/div[2]/div[1]/ul/li/span")).getText();
//        System.out.println("Radome " + adsQantity + " skelbimu.");
//
//        while (true) {
//            WebElement container2 = driver.findElement(By.className("standard-list-container"));
//            List<WebElement> contentBlocks2 = container2.findElements(By.className("content-block"));
//            Thread.sleep(1000);
//
//            for (int i = 0; i < contentBlocks2.size(); i++) {
//                String url = driver.getCurrentUrl();
//                String name = "0";
//                try {
//                    contentBlocks2.get(i).click();
//                    Thread.sleep(1000);
//                    name = contentBlocks2.get(i).findElement(By.xpath("//*[@id=\"user-info-container\"]/div[1]/div[2]/div/div[1]")).getText();
//                    String itemUrl = driver.getCurrentUrl();
//                    Thread.sleep(1000);
//                    System.out.println(itemUrl);
//                }
//                catch (Exception e) {
////                    System.out.println("Nera vardo");
//                }
//                try {
//                    driver.get(url);
//                    Thread.sleep(1000);
//                    container2 = driver.findElement(By.className("standard-list-container"));
//                    contentBlocks2 = container2.findElements(By.className("content-block"));
//                }
//                catch (Exception e){
//                    break;
//                }
//            }
//            WebElement paginationBlock1 = driver.findElement(By.id("pagination"));
//            try {
//                WebElement lastElement = paginationBlock1.findElement(By.linkText("»"));
//                lastElement.click();
//            } catch (Exception e) {
//                break;
//            }




























}
