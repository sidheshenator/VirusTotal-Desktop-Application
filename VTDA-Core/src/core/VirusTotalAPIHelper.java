/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.File;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openide.util.Exceptions;

/**
 *
 * @author SMhatre
 */
public class VirusTotalAPIHelper {

    public static String apiKey = "";
    public static String httpsPost = "https://www.virustotal.com/vtapi/v2/";

    public static CloseableHttpResponse scanFile(File fileToScan) {
        if (apiKey == null || apiKey.isEmpty()) {
            return null;
        }
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpsScanFilePost = new HttpPost(httpsPost + "file/scan");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("apikey", apiKey);
            builder.addBinaryBody("file", fileToScan, ContentType.APPLICATION_OCTET_STREAM, fileToScan.getName());
            HttpEntity multipart = builder.build();
            httpsScanFilePost.setEntity(multipart);

            CloseableHttpResponse response = client.execute(httpsScanFilePost);
            return response;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    public static CloseableHttpResponse reScan(String sha256) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpsScanFilePost = new HttpPost(httpsPost + "file/rescan");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("apikey", apiKey);
            builder.addTextBody("resource", sha256);
            HttpEntity multipart = builder.build();
            httpsScanFilePost.setEntity(multipart);

            CloseableHttpResponse response = client.execute(httpsScanFilePost);
            return response;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
    
    public static CloseableHttpResponse getReport(String sha256) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpsScanFilePost = new HttpPost(httpsPost + "file/report");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("apikey", apiKey);
            builder.addTextBody("resource", sha256);
            HttpEntity multipart = builder.build();
            httpsScanFilePost.setEntity(multipart);

            CloseableHttpResponse response = client.execute(httpsScanFilePost);
            return response;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    /**
     * @param aApiKey the apiKey to set
     */
    public static void setApiKey(String aApiKey) {
        apiKey = aApiKey;
    }
}
