package com.penho.java.shopping;

/**
 * User: Neo
 * Date: 11-7-7
 * Time: 下午3:33
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MbpChecker {
    private static final Log log = LogFactory.getLog(MbpChecker.class);

    public void amazonMbpCheck() {
        //mbpCheck("http://www.amazon.cn/gp/product/B004PYEGE8/ref=s9_simh_gw_p147_d0_i1?pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=center-1&pf_rd_r=0M55D7E1T3GNK2YFQN3M&pf_rd_t=101&pf_rd_p=58840952&pf_rd_i=899254051");
        //vote("http://www.xgdj-wh.com/result.asp");
        voteDelegate("http://www.xgdj-wh.com/result.asp","222.172.99.11", 8909);
        //voteDelegate("http://www.xgdj-wh.com/result.asp","59.175.128.43", 80);
    }

    public void voteCheck() {
        //voteDelegate("http://www.xgdj-wh.com/result.asp","123.125.156.92", 82);
        //voteDelegate("http://www.xgdj-wh.com/result.asp","219.147.197.114", 8909);
        //voteDelegate("http://www.xgdj-wh.com/result.asp","221.130.7.93", 80);
        voteDelegate("http://www.xgdj-wh.com/result.asp","205.213.195.70", 8080);
    }

    public void voteRobot(){
        //voteDelegate("http://www.xgdj-wh.com/result.asp", "202.105.134.74", 8909);
        //voteDelegate("http://www.xgdj-wh.com/result.asp","114.30.47.10", 80);
        //voteDelegate("http://www.xgdj-wh.com/result.asp","222.77.69.210", 3128);
        voteDelegate("http://www.xgdj-wh.com/result.asp","190.90.118.2", 8080);
    }

    public void voteRobot2(){
        //voteDelegate("http://www.xgdj-wh.com/result.asp","72.167.250.203",808 );
        //voteDelegate("http://www.xgdj-wh.com/result.asp","111.1.32.154", 80);
        //voteDelegate("http://www.xgdj-wh.com/result.asp","218.106.113.245", 8909);
        voteDelegate("http://www.xgdj-wh.com/result.asp","111.1.32.154", 80);
    }

    public void voteRobot3(){
        //voteDelegate("http://www.xgdj-wh.com/result.asp","219.217.13.237",8909);
        //voteDelegate("http://www.xgdj-wh.com/result.asp","219.147.197.114", 8909);
    }

    public static DefaultHttpClient getThreadSafeClient() {

        DefaultHttpClient client = new DefaultHttpClient();
        ClientConnectionManager mgr = client.getConnectionManager();
        SchemeRegistry schemeRegistry = mgr.getSchemeRegistry();
        client = new DefaultHttpClient(new ThreadSafeClientConnManager(schemeRegistry));
        return client;
    }


    public void voteDelegate(String strUrl, String strProxy, int intPort) {
        DefaultHttpClient httpclient = getThreadSafeClient();

        HttpHost proxy = new HttpHost(strProxy, intPort);
        httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

        HttpPost httpPost = new HttpPost(strUrl);

        HttpResponse response;
        HttpEntity entity;

        try {
            HttpParams httpParams = new BasicHttpParams();
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("action", "participate"));
            nameValuePair.add(new BasicNameValuePair("classid", "1"));
            nameValuePair.add(new BasicNameValuePair("id", "6"));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            httpPost.setParams(httpParams);

            response = httpclient.execute(httpPost);

            log.debug("--- vote via proxy [" + strProxy + "] ---");

            entity = response.getEntity();

            if (entity != null) {
                //entity = new BufferedHttpEntity(entity);
                InputStream instream = entity.getContent();
                //getVoteResult(instream);
                instream.close();
            }
        } catch (ClientProtocolException cpe) {
            cpe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public void vote(String strUrl) {
        DefaultHttpClient dhc = getThreadSafeClient();

        HttpPost httpPost = new HttpPost(strUrl);

        HttpResponse response;
        HttpEntity entity;

        try {
            HttpParams httpParams = new BasicHttpParams();
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("action", "participate"));
            nameValuePair.add(new BasicNameValuePair("classid", "1"));
            nameValuePair.add(new BasicNameValuePair("id", "19"));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            httpPost.setParams(httpParams);


            response = dhc.execute(httpPost);

            log.debug("--- vote ---");

            entity = response.getEntity();

            if (entity != null) {
                //entity = new BufferedHttpEntity(entity);
                InputStream instream = entity.getContent();
                //log.debug(instream);
                instream.close();
                //getVoteResult(instream);
            }
        } catch (ClientProtocolException cpe) {
            cpe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public void voteViaProxy(String strUrl) {
        DefaultHttpClient httpclient = getThreadSafeClient();

        HttpHost proxy = new HttpHost("123.125.156.92", 82);
        httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

        HttpPost httpPost = new HttpPost(strUrl);

        HttpResponse response;
        HttpEntity entity;

        try {
            HttpParams httpParams = new BasicHttpParams();
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("action", "participate"));
            nameValuePair.add(new BasicNameValuePair("classid", "1"));
            nameValuePair.add(new BasicNameValuePair("id", "19"));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            httpPost.setParams(httpParams);

            response = httpclient.execute(httpPost);

            log.debug("--- vote via proxy ---");

            entity = response.getEntity();

            if (entity != null) {
                //entity = new BufferedHttpEntity(entity);
                InputStream instream = entity.getContent();
                //getVoteResult(instream);
                instream.close();
            }
        } catch (ClientProtocolException cpe) {
            cpe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
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
                //entity = new BufferedHttpEntity(entity);
                InputStream instream = entity.getContent();
                printContent(instream);
            }
        } catch (ClientProtocolException cpe) {
            cpe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void getVoteResult(InputStream inputStream) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(
                            inputStream));
            String inputLine;
            while (
                    (inputLine = in.readLine()) != null) {
                if (inputLine.indexOf("#FF0000") != -1) {
                    //inputLine = inputLine.trim();
                    //int indexPoint = inputLine.lastIndexOf(".");
                    //inputLine = inputLine.substring(indexPoint - 5, indexPoint + 3);
                    log.debug(inputLine);
                    //System.out.println(inputLine);
                    //break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                    inputLine = inputLine.trim();
                    int indexPoint = inputLine.lastIndexOf(".");
                    inputLine = inputLine.substring(indexPoint - 5, indexPoint + 3);
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
