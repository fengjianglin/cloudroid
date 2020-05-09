package com.manlanvideo.cloud.api;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class SSL {

    private final static String CLIENT_P12_PASSWORD = "123456";

    public static SSLSocketFactory getSSLSocketFactory(Context context) {

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(getKeyManagerFactory(context).getKeyManagers(), getTrustManagerFactory(context).getTrustManagers(), null);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return sslContext != null ? sslContext.getSocketFactory() : null;
    }

    /**
     *client端
     * @param context
     * @return
     */
    private static KeyManagerFactory getKeyManagerFactory(Context context){
        KeyStore keyStore = null;
        KeyManagerFactory keyManagerFactory = null;
        try {
            keyStore = KeyStore.getInstance("PKCS12");
            InputStream ksIn = context.getAssets().open("client.p12");
            keyStore.load(ksIn, CLIENT_P12_PASSWORD.toCharArray());
            ksIn.close();
            keyManagerFactory = KeyManagerFactory.getInstance("X509");
            keyManagerFactory.init(keyStore, CLIENT_P12_PASSWORD.toCharArray());
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keyManagerFactory;
    }

    /**
     *server端
     * @param context
     * @return
     */
    private static TrustManagerFactory getTrustManagerFactory(Context context){
        KeyStore trustStore = null;
        TrustManagerFactory trustManagerFactory = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream in = context.getAssets().open("fullchain.pem");
            Certificate ca = cf.generateCertificate(in);
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            trustStore.setCertificateEntry("ca", ca);
            trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trustManagerFactory;
    }
}
