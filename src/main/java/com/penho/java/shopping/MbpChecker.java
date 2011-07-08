package com.penho.java.shopping;

/**
 * User: Neo
 * Date: 11-7-7
 * Time: 下午3:33
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MbpChecker {
    private static final Log log = LogFactory.getLog(MbpChecker.class);

    public void amazonMbpCheck() {
        mbpCheck("http://www.amazon.cn/gp/product/B004PYEGE8/ref=s9_simh_gw_p147_d0_i1?pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=center-1&pf_rd_r=0M55D7E1T3GNK2YFQN3M&pf_rd_t=101&pf_rd_p=58840952&pf_rd_i=899254051");
    }

    public void mbpCheck(String strUrl) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(strUrl);

        HttpResponse response;
        HttpEntity entity;

        try {
            response = httpclient.execute(httpget);

            entity = response.getEntity();

            if (entity != null) {
                entity = new BufferedHttpEntity(entity);
                InputStream instream = entity.getContent();
                printContent(instream);
            }
        } catch (ClientProtocolException cpe) {
            cpe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void printContent(InputStream inputStream) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(
                            inputStream));
            String inputLine;
            while (
                    (inputLine = in.readLine()) != null) {
                if (inputLine.indexOf("priceLarge") != -1) {
                    log.debug(inputLine);
                    //System.out.println(inputLine);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        //new MbpChecker().mbpCheck("http://www.cnbeta.com");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        context.getBean("checkJob");
    }
}
