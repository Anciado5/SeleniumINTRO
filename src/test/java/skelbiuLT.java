import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class skelbiuLT {

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
//    }

    @Test
    public void openWebsite() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();//reikia parasyti, nes kartais irgi luzta testai, kai per didele rezoliucija, sitas padeda sumazinti erroru tikimybe
        driver.get("https://skelbiu.lt");
        Thread.sleep(500);//reikia parasyti, ns kartais del to luzta testai, nes nespeja uzkrauti
        WebElement acceptBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
        acceptBtn.click();
        driver.findElement(By.id("searchKeyword")).sendKeys("verpimo ratelis");
        driver.findElement(By.id("searchButton")).click();
        Thread.sleep(500);
        String adsQantity = driver.findElement(By.xpath("//*[@id=\"body-container\"]/div[2]/div[1]/ul/li/span")).getText();
        double adsQantityDbl = Double.parseDouble(adsQantity.replaceAll("[^\\d]", ""));
        System.out.println("Radome " + adsQantity + " skelbimu.");
        double priceSum = 0;
        while (true) {//begalinis ciklas, kuris nutraukiamas ivykus kazkuriai tai salygai
            WebElement container = driver.findElement(By.className("standard-list-container"));
            List<WebElement> contentBlocks = container.findElements(By.className("content-block"));
            Thread.sleep(1000);
            for (int i = 0; i < contentBlocks.size(); i++) {//foras yra kiekvienam skelbimui puslapyje
                String price = "0";
                double priceDbl = 0;
                try {
                    price = contentBlocks.get(i).findElement(By.className("price")).getText();
                }
                catch (Exception e) {}//try catch --> gaudo klaidas ir arba su jom kazka daro, arba nedaro nieko (priklausomai koks scenarijus)
                priceDbl = Double.parseDouble(price.replaceAll("[^\\d,]", ""));
                priceSum += priceDbl;
                System.out.println(price + " " + priceDbl);
            }
            WebElement paginationBlock = driver.findElement(By.id("pagination"));
            try {
                WebElement lastElement = paginationBlock.findElement(By.linkText("»"));
                lastElement.click();
            }catch(Exception e){
                break;
            }
        }
        System.out.println("Total sum = " + priceSum);
        System.out.printf("Average price =  %.2f", priceSum / adsQantityDbl);







































    }






















}
