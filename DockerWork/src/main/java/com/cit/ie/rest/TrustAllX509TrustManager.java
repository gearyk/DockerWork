package com.cit.ie.rest;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 * @author gearyk2
 * @name TrustAllX509TrustManager
 * @description Trust Manager needed for rest api calls to bypass SSL certs
 */
public class TrustAllX509TrustManager implements X509TrustManager 
{
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    public void checkClientTrusted(java.security.cert.X509Certificate[] certs,
            String authType) {
    }

    public void checkServerTrusted(java.security.cert.X509Certificate[] certs,
            String authType) {
    }
}
