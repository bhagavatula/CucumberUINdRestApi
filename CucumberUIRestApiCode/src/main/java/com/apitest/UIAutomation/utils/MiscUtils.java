package com.apitest.UIAutomation.utils;

import com.apitest.UIAutomation.utils.CucumberLogUtils;
import com.apitest.automation.utils.LocalUtil;
import groovy.json.StringEscapeUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang3.*;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;

public class MiscUtils {
    private static String DEFLET_DATE_FORMAT = "MM/dd/yyyy";
    public static final CharSequenceTranslator ESCAPE_HTML3 = new AggregateTranslator(new CharSequenceTranslator[]{new LookupTranslator(EntityArrays.BASIC_ESCAPE), new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE)});

    public MiscUtils() {
    }

    public static long[] swap(long... tobeSwapped) {
        long[] swappendArray = new long[]
                {tobeSwapped[1], tobeSwapped[0]};
        return swappendArray;
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException var3) {
            CucumberLogUtils.logError(var3.getMessage());
        }
    }

    public static void closeQuitely(Closeable... closeble) {
        Closeable[] var1 = closeble;
        int var2 = closeble.length;
        for (int var3 = 0; var3 < var2; ++var3) {
            Closeable obj2Close = var1[var3];
            if (obj2Close != null) {
                try {
                    obj2Close.close();
                } catch (IOException var6) {
                    CucumberLogUtils.logDebug("Exception encountered while closing the stream:\n" + var6);
                }
            }
        }
    }


    public static String makeStringHtmlSafe(String str) {
        return escapeHtml3(str);
    }

    public static final String escapeHtml3(String input) {
        return ESCAPE_HTML3.translate(input);
    }

    public static void setupHttpsProxy(){
        String proxyHost = LocalUtil.getProperty("proxyHost");
        String proxyPort = LocalUtil.getProperty("proxyport");
        System.setProperty("https.proxyHost",proxyHost);
        System.setProperty("https.proxyHost",proxyPort);
    }
    public static void clearHttpsProxy(){
        System.clearProperty("https.proxyHost");
        System.clearProperty("https.proxyPort");
    }


}
