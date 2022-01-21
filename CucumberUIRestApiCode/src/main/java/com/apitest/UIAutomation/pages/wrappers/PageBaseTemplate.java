package com.apitest.UIAutomation.pages.wrappers;

import com.apitest.automation.exceptions.AutomationExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;


public class PageBaseTemplate {
    Logger log = Logger.getLogger(String.valueOf(PageBaseTemplate.class));
    @Value("$(baseUri:}")
    public String baseUri;
    public String PageLink;
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;
    @Autowired
    private WebDriverWrapper webDriverWrapper;
    @Autowired
    private AutomationExceptionHandler expectionHandler;


    public PageBaseTemplate() {

    }

    public void openPage(ArrayList<String> params) {
        if (params != null && params.size() != 0) {
//            this.pageLink = CommonUtils.constructPageLink(params);
        }
//        CommonoUtils.sleep(1500L);
        this.webDriverWrapper.openPage((this.baseUri + this.PageLink).replaceAll("(?<=[^:\\s])(\\/+\\/)", "/"));
    }

    public void closePage() {
        this.webDriverWrapper.closPage();
    }

    public void refreshPage() {
        this.webDriverWrapper.refreshPage();
    }

    public void getPageElements(String fileName) {
        try {
            ArrayList<Page> pageElements = new ArrayList();
            Yaml yaml = new Yaml();
            InputStream input = this.getClass().getClassLoader().getResourceAsStream(("pagerepository/" + fileName));
            Page map = (Page) yaml.loadAs(input, Page.class);
            pageElements.add(map);
            Class clazz = this.getClass();
            Object obj = this.autowireCapableBeanFactory.getBean(clazz);
            Iterator var8 = ((Page) pageElements.get(0)).getElements().iterator();
            while (var8.hasNext()) {
                Element element = (Element) var8.next();
                Field field = clazz.getDeclaredField(element.getName());
                field.setAccessible(true);
                Object elementWrapper = this.getPageElement(field.getType().getName(), element, clazz.getSimpleName());
                field.set(obj, elementWrapper);
            }
        } catch (Exception var12) {
            this.expectionHandler.handleException(var12, "Error while processing page config file :" + fileName);
        }
    }

    public Object getPageElement(String className, Element element, String simpleName) {
        Object obj = null;
        try {
            Class clazz = Class.forName(className);
            obj = clazz.newInstance();
            Field field = clazz.getField("pageName");
            field.set(obj, simpleName);
            if (element.isScreenShot()) {
                Field field1 = clazz.getField("screenShot");
                field1.set(obj, element.isScreenShot());
            }
            Field[] fields = clazz.getFields();
            for (int i = 0; i < fields.length; i++) {
                if (element.getLocators().containsKey(fields[i].getName())) {
                    fields[i].setAccessible(true);
                    fields[i].set(obj, (String) element.getLocators().get(fields[i].getName()));
                }
            }

        } catch (Exception var9) {
            this.expectionHandler.handleException(var9, "Error while processing page config file");
        }
        return obj;
    }

}
