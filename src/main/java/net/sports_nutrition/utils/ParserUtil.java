package net.sports_nutrition.utils;

import net.sports_nutrition.domain.entities.Brand;
import net.sports_nutrition.domain.entities.Category;
import net.sports_nutrition.domain.entities.Product;
import net.sports_nutrition.domain.entities.Taste;
import net.sports_nutrition.domain.enumx.Form;
import net.sports_nutrition.domain.enumx.Gender;
import net.sports_nutrition.services.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 22.02.2016 11:12
 */


public class ParserUtil {

    private IBrandService brandService;
    private ITasteService tasteService;
    private IDiscountService discountService;
    private ICountryService countryService;
    private ICategoryService categoryService;
    private IProductService productService;

    Map<String, Long> categories = new HashMap<>();

    public ParserUtil() {
        categories.put("Аминокислоты", new Long(1));
        categories.put("Витамины", new Long(2));
        categories.put("Гейнеры", new Long(3));
        categories.put("Жиросжигатели", new Long(4));
        categories.put("Креатин", new Long(5));//
        categories.put("Протеин", new Long(6));//
        categories.put("Предтренировочные комплексы", new Long(7));
        categories.put("Специальные продукты", new Long(8));
        categories.put("L-карнитин", new Long(9));
        categories.put("Бца", new Long(10));
        categories.put("Для суставов и связок", new Long(11));
        categories.put("Для улучшения сна", new Long(12));
        categories.put("Повышения тестостерона", new Long(13));
    }

    @RequestMapping(value = "/parse", method = RequestMethod.GET)
    public void parse() throws IOException {
        // productBuildAndSaveToDbByCategoryName("povyishenie-testosterona",categories.get("Повышения тестостерона"),2);
    }


    public void productBuildAndSaveToDbByCategoryName(String categoryName, Long categoryId, Integer pageAmount) {
        for (int i = 0; i < pageAmount; i++) {
            if (i == 0) {
                productBuildAndSaveToDb("http://shop.bodybuilding.ua/katalog/" + categoryName + "/", categoryId);
            } else {
                productBuildAndSaveToDb("http://shop.bodybuilding.ua/katalog/" + categoryName + "/?page=" + i + 1, categoryId);
            }
        }
    }

    List<Product> productBuildAndSaveToDb(String prUrl, Long categoryId) {
        Random rand = new Random();
        List<Map<String, String>> productList = null;

            productList = parseProduct(prUrl);

        List<Product> prodList = new ArrayList<>();

        for (Map<String, String> map : productList) {

            try {
                String[] stTaste = map.get("tasteNames").trim().split(",");

                Category category = categoryService.getCategoryById(categoryId);
                String name = map.get("name");
                String description = map.get("description");
                String fullDescription = map.get("fullDescription");
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(map.get("price"))).setScale(2, BigDecimal.ROUND_HALF_EVEN);
                String quantityInPackage = map.get("amountPack");
                Long articleNumber = Long.parseLong(map.get("art") + "" + rand.nextInt(85666565));
                Brand brand = null;
                try {
                    brand = brandService.getBrandByName(map.get("brand").trim());
                } catch (Exception exx) {
                    System.out.println("Error brand" + map.get("brand").trim());
                }
                byte[] img = null;
                try {
                    String imgUrl = "http://shop.bodybuilding.ua" + map.get("img");
                    img = downloadImageByUrl(imgUrl);
                } catch (Exception e) {
                    System.out.println("Error img");
                }
                List<Taste> tasteList = new ArrayList<>();
                try {
                    for (String tasteName : stTaste) {
                        Taste taste = tasteService.getTasteByName(tasteName.trim());
                        if (taste != null)
                            tasteList.add(taste);
                    }
                } catch (Exception exe) {
                    System.out.println("Eror tasteGet " + exe);
                }
                if (tasteList.size() == 0) tasteList.add(tasteService.getTasteByName("Нет"));

                Form form = null;
                if (quantityInPackage.contains("шт")) {
                    form = Form.TABLETS;
                } else if (quantityInPackage.contains("кг") || quantityInPackage.contains("г")) {
                    form = Form.POWDER;
                } else if (quantityInPackage.contains("табл")) {
                    form = Form.TABLETS;
                } else if (quantityInPackage.contains("капс")) {
                    form = Form.CAPSULE;
                } else if (quantityInPackage.contains("мл")||quantityInPackage.contains(" л")) {
                   form = form.FLUIDE;
                } else {
                    form = Form.POWDER;
                }

                Product product = new Product();
                product.setName(name);
                product.setPrice(price);
                product.setImageByte(img);
                product.setArticleNumber(articleNumber);
                product.setBrand(brand);
                product.setCategory(category);
                product.setDiscount((price.intValue() > 1000) ? discountService.getDiscountById(new Long(2)) : discountService.getDiscountById(new Long(1)));
                product.setDescription((fullDescription == null) ? null : ((fullDescription.length() < 400) ? null : description));
                product.setFullDescription((fullDescription == null) ? null : ((fullDescription.length() < 400) ? null : fullDescription));
                product.setForm(form);
                product.setGender(Gender.values()[rand.nextInt(3)]);
                product.setQuantityInPackage(quantityInPackage);
                product.setStockAmount((price != null) ? ((price.intValue() == 0) ? 0 : 50) : 0);
                product.setTasteList(tasteList);

                prodList.add(product);
            } catch (Throwable eth) {
                System.out.println("Error in built product->" + eth.getMessage());
            }
        }

        for (Product product : prodList) {
            try {
                productService.saveProduct(product);
            } catch (Exception e) {
                System.out.println("Error save product" + product + " message-> " + e.getMessage());
            }
        }

        return prodList;
    }

    public static byte[] downloadImageByUrl(String urlName) {

        URL url;
        URLConnection conn;
        byte[] b = null;
        try {
            url = new URL(urlName); //Формирование url-адреса файла
            conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            ByteArrayOutputStream f = new ByteArrayOutputStream();

            int ch;
            while ((ch = bis.read()) != -1) {
                f.write(ch);

            }
            b = f.toByteArray();
            bis.close();
            f.flush();
            f.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return b;
    }

    private List<Map<String, String>> parseProduct(String url){
        List<Map<String, String>> productList = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("Error connection to url" + url);
        }

        Elements productsTitleElements = doc.getElementsByClass("product-tile");
        Elements propertiesElements = doc.getElementsByClass("product-char-container");
        Iterator<Element> productsTitelIter = productsTitleElements.iterator();
        Iterator<Element> propertiesIter = propertiesElements.iterator();

        for (; propertiesIter.hasNext() && productsTitelIter.hasNext(); ) {

            try {
                Map<String, String> productMap = new HashMap<>();
                Element good = productsTitelIter.next();
                Element pro = propertiesIter.next();
                productMap.put("name", good.getElementsByClass("product-pagetitle").text());
                productMap.put("price", good.getElementsByClass("product-price-content").text().replaceAll("\\D+", ""));
                productMap.put("img", good.getElementsByClass("product-img-content").first().select("img").first().attr("src").toString());
                List<String> tmpList = new ArrayList<>();
                for (Element p : pro.getElementsByClass("product-char-row"))
                    tmpList.add(deleteToChar(p.text(), ":"));

                productMap.put("brand", tmpList.get(0));
                productMap.put("art", tmpList.get(1).replaceAll("\\D+", ""));
                productMap.put("categoryName", tmpList.get(2));
                productMap.put("amountPack", tmpList.get(3));
                productMap.put("tasteNames", tmpList.get(5));

                String productDetailUrl = "http://shop.bodybuilding.ua/" + good.getElementsByClass("product-img-content").first().select("a").first().attr("href").toString();

                Document docDetail = null;
                Element fullDescription = null;
                String description = null;
                try {
                    docDetail = Jsoup.connect(productDetailUrl).get();
                    System.out.println(productDetailUrl + "------");
                    docDetail.getElementsByClass("product-tabs-content").first().select("a").remove();
                    docDetail.getElementsByClass("product-tabs-content").first().select("img").remove();

                    fullDescription = docDetail.getElementsByClass("product-tabs-content").first().getElementsByClass("product-text-content").first();

                    description = docDetail.getElementsByClass("product-tabs-content").first().getElementsByClass("product-text-content").first().select("p[class=justifyfull]").get(1).html();
                    description += docDetail.getElementsByClass("product-tabs-content").first().getElementsByClass("product-text-content").first().select("p[class=justifyfull]").get(2).html();

                    productMap.put("fullDescription", fullDescription.html());

                    productMap.put("description", description);
                } catch (Exception e) {
                    System.out.println("error parse inner url" + productDetailUrl + e.getMessage());
                }

                productList.add(new HashMap<String, String>(productMap));
                productMap.clear();
                tmpList.clear();
            } catch (Throwable th) {
                System.out.println("Error get information of product" + th.getMessage() + " " + th.getLocalizedMessage());
            }

        }
        return productList;
    }


    public void tasteBuildAndSaveToDb(String url) {
        List<String> tasteName = tasteParse(url);
        for (String name : tasteName) {
            try {
                tasteService.saveTaste(new Taste(name));
            } catch (Exception e) {
                System.out.println("Error save taste to data base" + name);
            }
        }
    }

    public List<String> tasteParse(String url) {
        List<String> tasteList = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("Error connection to url:"+ url + e.getMessage());
        }
        Elements e = doc.select("input[id^=mse2_msoption|color_");

        for (Element aElement : e) {
            tasteList.add(aElement.attr("value"));
        }
        return tasteList;
    }

    public void buildAndSaveToDbBrands(String url) {
        Random rand = new Random();
        Long l = new Long(230);
        List<Brand> brands = new ArrayList<>();
        List<String> brandList = brandParse(url);
        for (String brandName : brandList) {
            Number numb = rand.nextInt(230);
            brands.add(new Brand(brandName, countryService.getCountryById(numb.longValue()), ""));
        }
        System.out.println("List brands" + brands);
        for (Brand brand : brands)
            brandService.saveBrand(brand);

    }

    /*
    List<String> urls = new ArrayList<>();
    urls.add("http://shop.bodybuilding.ua/katalog/creatine/");
    urls.add("http://shop.bodybuilding.ua/katalog/vitaminyi-i-mineralyi/");
    urls.add("http://shop.bodybuilding.ua/katalog/predtrenirovochnyie-kompleksyi/");
    urls.add("http://shop.bodybuilding.ua/katalog/spetsialnyie-dobavki/");
    urls.add("http://shop.bodybuilding.ua/katalog/aminokislotyi/");
    urls.add("http://shop.bodybuilding.ua/katalog/snijenie-vesa/");
    for(String url: urls)
    tasteBuildAndSaveToDb(url);
    */

    // buildAndSaveToDbBrands("http://shop.bodybuilding.ua/brendyi/");

    private List<String> brandParse(String url) {
        List<String> brandList = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements e = doc.getElementsByClass("brand-header-content-link");
        for (Element aElement : e) {
            Elements img = aElement.select("img");
            brandList.add(img.attr("alt"));
        }
        return brandList;
    }

    public   static String deleteToChar(String str, String ch) {

        int i = str.indexOf(ch);
        return str.substring(i + 1, str.length());
    }

   public static byte[] trimByteArray(byte[] bytes) {
        int i = bytes.length - 1;
        while (i >= 0 && bytes[i] == 0)
            --i;

        return Arrays.copyOf(bytes, i + 1);
    }

    public static File getFileFromBytes(byte[] bytes,String filePath) {

        File file = new File(filePath);

        try(FileOutputStream os = new FileOutputStream(file)) {

            os.write(bytes);
            os.close();
        } catch (Exception e) {
            System.out.print("Error write file" + e.getMessage());
        }

        return file;
    }

    public void setBrandService(IBrandService brandService) {
        this.brandService = brandService;
    }

    public void setTasteService(ITasteService tasteService) {
        this.tasteService = tasteService;
    }

    public void setDiscountService(IDiscountService discountService) {
        this.discountService = discountService;
    }

    public void setCountryService(ICountryService countryService) {
        this.countryService = countryService;
    }

    public void setCategoryService(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public void setCategories(Map<String, Long> categories) {
        this.categories = categories;
    }
}
