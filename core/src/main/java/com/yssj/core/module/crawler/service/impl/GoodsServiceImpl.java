package com.yssj.core.module.crawler.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.browser.BrowserFetcher;
import com.ruiyun.jvppeteer.core.page.ElementHandle;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.core.page.Request;
import com.ruiyun.jvppeteer.exception.ProtocolException;
import com.ruiyun.jvppeteer.options.Clip;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder;
import com.ruiyun.jvppeteer.options.Viewport;
import com.yssj.core.module.crawler.entity.Goods;
import com.yssj.core.module.crawler.mapper.GoodsMapper;
import com.yssj.core.module.crawler.service.IGoodsService;
import com.yssj.core.utils.RobotNotify;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2022-05-31
 */
@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    final String redirectUrl ="https://mms.pinduoduo.com/login/sso?platform=wholesale&redirectUrl=https%3A%2F%2Fpifa.pinduoduo.com%2Fclint%2Fapi%2Flogin%3Fredirect%3Dhttps%253A%252F%252Fpifa.pinduoduo.com%252Fsearch%253Fcate%253D10364%2526level%253D1";

    final String categoryTpl = "https://pifa.pinduoduo.com/search?cate=%s&level=%s";

    final String wordTpl = "https://pifa.pinduoduo.com/search?word=%s";

    final String detailTpl = "https://pifa.pinduoduo.com/goods/detail/?gid=%s";

    static Integer level1_point = 0;

    @Override
    public void pinGoodsSync() {
        Browser browser = null;
        Page page = null;
        try {
            browser = initBrowser();
            log.info("浏览器初始化完毕");
            page = initNewPage(browser);
            // 类目遍历
            syncCategary(page);
            // 关键字遍历
        }catch (Exception e){
            log.error("爬虫异常,{}",e);
            try {
                if(page!=null){
                    page.close();
                }
                if(browser!=null){
                    browser.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
            if(e instanceof ProtocolException){
                this.pinGoodsSync();
            }
        }
    }

    private Browser initBrowser() throws Exception{
        BrowserFetcher.downloadIfNotExist(null);
        ArrayList<String> arrayList = new ArrayList<>();
        Viewport viewport = new Viewport();
        viewport.setHeight(577);
        viewport.setWidth(1280);
        LaunchOptions options = new LaunchOptionsBuilder().withIgnoreDefaultArgs(true)
                .withArgs(arrayList).withDevtools(false)
//                .withIgnoreHTTPSErrors(true)
                .withViewport(viewport)
                .withHeadless(true)
                .withDumpio(true).build();
        arrayList.add("--no-sandbox");
        arrayList.add("--disable-infobars");
//        arrayList.add("--proxy-server=http://47.243.53.78:8888");
//        arrayList.add("--ignore-certificate-errors");
        arrayList.add("--disable-blink-features=AutomationControlled");
        arrayList.add("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
        Browser browser = Puppeteer.launch(options);
        return browser;
    }

    /**
     * 关键字爬取
     * @param page
     * @throws Exception
     */
    private void syncWord(Page page,String word) throws Exception{
        String url = String.format(wordTpl, word);
        page.goTo(url);
        // 价格升序
        page.click("div.type");

        for (int j = 0; j < 5; j++) {
            ElementHandle elem = page.$("#root");
            Clip boundingBox = elem.boundingBox();
            //鼠标移动到目标
            page.mouse().move(boundingBox.getX() + boundingBox.getWidth() / 2,
                    boundingBox.getY() + boundingBox.getHeight() / 2);
            //开始鼠标滚动
            page.mouse().wheel(0.00,boundingBox.getHeight()+100);
            // 延时
            Thread.sleep(RandomUtil.randomInt(15,30) * 1000);
        }
    }

    /**
     * 分类爬取
     * @param page
     * @throws Exception
     */
    private void syncCategary(Page page) throws Exception{
        ClassPathResource classPathResource = new ClassPathResource("category.json");
        InputStream inputStream = classPathResource.getInputStream();
        JSONArray jsonArray = JSONUtil.parseArray(IOUtils.toString(inputStream, StandardCharsets.UTF_8));

        int i = level1_point==0?0:level1_point;
        String node = System.getProperty("node");
        if(!StringUtil.isNullOrEmpty(node)&&level1_point==0){
            i = (jsonArray.size() / 4 ) * Integer.parseInt(node);
        }

        for (; i < jsonArray.size() ; i++) {
            level1_point = i;
            Object level1 = jsonArray.get(i);
            JSONObject level1Object = JSONUtil.parseObj(level1);
            JSONArray level1Children = level1Object.getJSONArray("children");
            String level1Name = level1Object.getStr("optName");
            for (Object level2 : level1Children) {
                JSONObject level2Object = JSONUtil.parseObj(level2);
                JSONArray level2Children = level2Object.getJSONArray("children");
                String level2Name = level2Object.getStr("optName");
                String level2Id = level2Object.getStr("optId");
                if(level2Children.size()==0){
                    // 2层末级分类，爬取
                    String url = String.format(categoryTpl,level2Id, "2");
                    log.info("目录细分:{},{}",level1Name,level2Name);
                    fetchGoods(page,url);
                }else {
                    for (Object level3 : level2Children) {
                        JSONObject level3Object = JSONUtil.parseObj(level3);
                        String level3Name = level3Object.getStr("optName");
                        String level3Id = level3Object.getStr("optId");
                        String url = String.format(categoryTpl,level3Id, "3");
                        log.info("目录细分:{},{},{}",level1Name,level2Name,level3Name);
                        fetchGoods(page,url);
                    }
                }
            }
        }
    }

    private void fetchGoods(Page page,String url) throws Exception{
        page.goTo(url);
        Thread.sleep(60*1000);

        // 价格升序
        page.click("div.type");

        for (int j = 0; j < 5; j++) {
            ElementHandle elem = page.$("#root");
            Clip boundingBox = elem.boundingBox();
            //鼠标移动到目标
            page.mouse().move(boundingBox.getX() + boundingBox.getWidth() / 2,
                    boundingBox.getY() + boundingBox.getHeight() / 2);
            //开始鼠标滚动
            page.mouse().wheel(0.00,boundingBox.getHeight()+100);
            // 延时
            int i = RandomUtil.randomInt(70, 120);
            Thread.sleep( i * 1000);
        }
    }

    private Page initNewPage(Browser browser) throws Exception{
        Page page = browser.newPage();
//        Page page = browser.createIncognitoBrowserContext().newPage();
        page.evaluate("function(){Object.defineProperty(navigator, 'webdriver', {get: () => undefined});window.chrome = {};window.chrome.app = {\"InstallState\":\"hehe\", \"RunningState\":\"haha\", \"getDetails\":\"xixi\", \"getIsInstalled\":\"ohno\"};window.chrome.csi = function(){};window.chrome.loadTimes = function(){};window.chrome.runtime = function(){};Object.defineProperty(navigator, 'userAgent', {get: () => \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.113 Safari/537.36\",});Object.defineProperty(navigator, 'plugins', {get: () => [{\"description\": \"Portable Document Format\",\"filename\": \"internal-pdf-viewer\",\"length\": 1,\"name\": \"Chrome PDF Plugin\"}]});const originalQuery = window.navigator.permissions.query;window.navigator.permissions.query = (parameters) => (parameters.name === 'notifications'?Promise.resolve({ state: Notification.permission }):originalQuery(parameters));}",null);
//        int i = RandomUtil.randomInt(0, USER_AGENTS.length - 1);
        page.setUserAgent(USER_AGENTS[0]);
        page.setJavaScriptEnabled(true);

        // 响应拦截
        page.onResponse( response -> {
            Request request = response.request();
            if(request.url().contains("searchOptGoods")){
                try {
                    log.info("商品列表响应:{},{}",response.status(),response.url());
                    if(response.status()!=200){
                        RobotNotify.robotMsg("爬取中断");
                        System.exit(1);
                    }
                    JSONArray array = JSONUtil.parseObj(response.text()).getJSONObject("result").getJSONArray("goodsList");
                    for (Object o : array) {
                        JSONObject parseObj = JSONUtil.parseObj(o);
                        Integer minDiscount = parseObj.getInt("minDiscount");
                        if(minDiscount!=null&&minDiscount<50){
                            Goods entity = new Goods();
                            entity.setGoodsName(parseObj.getStr("goodsName"));
                            entity.setUrl(String.format(detailTpl,parseObj.getLong("goodsId")));
                            entity.setSrc(1);
                            entity.setStatus(0);
                            entity.setDisCount(minDiscount.toString());
                            entity.setBussinessId(parseObj.getLong("goodsId").toString());
                            Long count = this.baseMapper.selectCount(new LambdaQueryWrapper<Goods>().eq(Goods::getBussinessId, parseObj.getLong("goodsId")));
                            if(count>0){
                                this.baseMapper.update(entity,new LambdaQueryWrapper<Goods>().eq(Goods::getBussinessId,parseObj.getLong("goodsId")));
                            }else {
                                this.baseMapper.insert(entity);
                            }
                            log.info("折扣商品-{},{}",entity.getGoodsName(),entity.getUrl());
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 登录
        page.goTo(redirectUrl);
        Thread.sleep(3000);
        page.click("div.last-item");
        Thread.sleep(2000);
        page.type("#usernameId","16671087131");
        Thread.sleep(2000);
        page.type("#passwordId","Q1q1q1q1");
        Thread.sleep(2000);
        page.click("button");
        Thread.sleep(5000);

        return page;
    }



    public static void main(String[] args) throws Exception{
        final String url ="https://pifa.pinduoduo.com/search?cate=10364&level=1";//大家这可以填自己爬虫的地址
        final String redirectUrl ="https://mms.pinduoduo.com/login/sso?platform=wholesale&redirectUrl=https%3A%2F%2Fpifa.pinduoduo.com%2Fclint%2Fapi%2Flogin%3Fredirect%3Dhttps%253A%252F%252Fpifa.pinduoduo.com%252Fsearch%253Fcate%253D10364%2526level%253D1";//大家这可以填自己爬虫的地址
        final String loginUrl = "https://mms.pinduoduo.com/login/sso";
//        System.setProperty("webdriver.chrome.driver","D:\\codog\\Downloads\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//            driver.get(url);
//        String title = driver.getTitle();
//        System.out.printf(title);
//
//        driver.close();


        //自动下载，第一次下载后不会再下载
        BrowserFetcher.downloadIfNotExist(null);
        ArrayList<String> arrayList = new ArrayList<>();
        //生成pdf必须在无厘头模式下才能生效
        Viewport viewport = new Viewport();
        viewport.setHeight(577);
        viewport.setWidth(1280);
        LaunchOptions options = new LaunchOptionsBuilder().withIgnoreDefaultArgs(true)
                .withArgs(arrayList).withDevtools(false)
                .withViewport(viewport).withHeadless(true)
//                .withExecutablePath("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe")
                .withDumpio(false).build();
        arrayList.add("--no-sandbox");
//        arrayList.add("--disable-setuid-sandbox");
        arrayList.add("--disable-blink-features=AutomationControlled");
        Browser browser = Puppeteer.launch(options);
        Page page = browser.newPage();
        page.evaluate("function(){Object.defineProperty(navigator, 'webdriver', {get: () => undefined});window.chrome = {};window.chrome.app = {\"InstallState\":\"hehe\", \"RunningState\":\"haha\", \"getDetails\":\"xixi\", \"getIsInstalled\":\"ohno\"};window.chrome.csi = function(){};window.chrome.loadTimes = function(){};window.chrome.runtime = function(){};Object.defineProperty(navigator, 'userAgent', {get: () => \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.113 Safari/537.36\",});Object.defineProperty(navigator, 'plugins', {get: () => [{\"description\": \"Portable Document Format\",\"filename\": \"internal-pdf-viewer\",\"length\": 1,\"name\": \"Chrome PDF Plugin\"}]});const originalQuery = window.navigator.permissions.query;window.navigator.permissions.query = (parameters) => (parameters.name === 'notifications'?Promise.resolve({ state: Notification.permission }):originalQuery(parameters));}",null);
        page.setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win32; x32) AppleWebKit/507.36 (KHTML, like Gecko) Chrome/99.0.4951.64 Safari/527.36 Edg/100.0.1210.53");
        page.setJavaScriptEnabled(true);

//        page.setRequestInterception(true);
        page.onResponse( response -> {
            Request request = response.request();
            if(request.url().contains("searchOptGoods")){
                try {
                    log.info("页面爬取处理,{},{}",response.status(),response.text());
                    if(response.status()!=200){
                        RobotNotify.robotMsg("爬取中断");
                        System.exit(1);
                    }
                    System.out.println(response.status());
                    System.out.println(response.text());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

//        PageNavigateOptions options1 = new PageNavigateOptions();
//        //如果不设置 domcontentloaded 算页面导航完成的话，那么goTo方法会超时，因为图片请求被拦截了，页面不会达到loaded阶段
//        options1.setWaitUntil(Collections.singletonList("domcontentloaded"));

        page.goTo(redirectUrl);
        Thread.sleep(3000);
        page.click("div.last-item");
        Thread.sleep(2000);
        page.type("#usernameId","16671087131");
        Thread.sleep(2000);
        page.type("#passwordId","Q1q1q1q1");
        Thread.sleep(2000);
        page.click("button");
        Thread.sleep(5000);
        page.click("div.type");
        Thread.sleep(2000);

        ElementHandle elem = page.$("#root");
        Clip boundingBox = elem.boundingBox();
        //鼠标移动到目标
        page.mouse().move(boundingBox.getX() + boundingBox.getWidth() / 2,
                boundingBox.getY() + boundingBox.getHeight() / 2);
        //开始鼠标滚动
        page.mouse().wheel(0.00,boundingBox.getHeight()+100);

        page.goTo("https://pifa.pinduoduo.com/search?cate=9981&level=2");
        Thread.sleep(2000);

        page.goTo("https://pifa.pinduoduo.com/search?cate=9978&level=2");
        Thread.sleep(2000);

//        page = browser.newPage();
//        page.evaluate("function(){Object.defineProperty(navigator, 'webdriver', {get: () => undefined});window.chrome = {};window.chrome.app = {\"InstallState\":\"hehe\", \"RunningState\":\"haha\", \"getDetails\":\"xixi\", \"getIsInstalled\":\"ohno\"};window.chrome.csi = function(){};window.chrome.loadTimes = function(){};window.chrome.runtime = function(){};Object.defineProperty(navigator, 'userAgent', {get: () => \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.113 Safari/537.36\",});Object.defineProperty(navigator, 'plugins', {get: () => [{\"description\": \"Portable Document Format\",\"filename\": \"internal-pdf-viewer\",\"length\": 1,\"name\": \"Chrome PDF Plugin\"}]});const originalQuery = window.navigator.permissions.query;window.navigator.permissions.query = (parameters) => (parameters.name === 'notifications'?Promise.resolve({ state: Notification.permission }):originalQuery(parameters));}",null);
//        page.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36 Edg/101.0.1210.53");
//        page.setJavaScriptEnabled(true);
//        page.goTo(url);
//        page.waitForNavigation();
//        ElementHandle handle = page.waitForSelector(".avatar");
//        handle.click();
        String s = page.content();
        Thread.sleep(160000);
        page.close();
        browser.close();


//        final String url ="https://www.mengsign.com/";//大家这可以填自己爬虫的地址
//        WebClient webClient = new WebClient(BrowserVersion.EDGE);//创建火狐浏览器 2.23版本是FIREFOX_45 new不写参数是默认浏览器
//        webClient.getOptions().setCssEnabled(true);//（屏蔽)css 因为css并不影响我们抓取数据 反而影响网页渲染效率
//        webClient.getOptions().setThrowExceptionOnScriptError(false);//（屏蔽)异常
//        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//（屏蔽)日志
//        webClient.getOptions().setJavaScriptEnabled(true);//加载js脚本
//        webClient.getOptions().setTimeout(20000);//设置超时时间
//        webClient.setJavaScriptTimeout(20000);
//        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//设置ajax
//        webClient.waitForBackgroundJavaScript(20000);
//
//        HtmlPage htmlPage = webClient.getPage(url);//将客户端获取的树形结构转化为HtmlPage
//        String html = htmlPage.asXml();
//        Document document = Jsoup.parse(html);
//        Elements elements = document.getElementsByClass("price-text");
//        System.out.println(html);
//        Thread.sleep(10000);//主线程休眠10秒 让客户端有时间执行js代码 也可以写成webClient.waitForBackgroundJavaScript(1000)
    }


    final String[] USER_AGENTS = {
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36 Edg/101.0.1210.53",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:96.0) Gecko/20100101 Firefox/96.0"
    };

}
