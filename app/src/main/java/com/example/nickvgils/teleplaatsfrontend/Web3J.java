package com.example.nickvgils.teleplaatsfrontend;

import android.util.Log;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

public class Web3J {
    private static Web3J instance = null;

    private String TAG = "WEB3J";


    public Web3j web3 = null;

    private static final String ipAdress = "192.168.1.157";
    private static final String port = "8545";

    private final static String PRIVATE_KEY = "8563420c509590079e83c548a1fe1e4eecd087ca9e2e7ad4c051690a984eefc4";


    //private constructor.
    private Web3J(){

        init();
        //Prevent form the reflection api.
//        if (instance != null){
//            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
//        }
    }

    public static Web3J getInstance(){
        if (instance == null){ //if there is no instance available... create new one
            instance = new Web3J();
        }

        return instance;
    }

    public void init(){

        String url = "http://" + ipAdress + ":" + port;
        web3 = Web3jFactory.build(new HttpService(url));  // defaults to http://localhost:8545/
//        try {
//            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
//            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
//            Log.d(TAG, "Web3jTest: " + clientVersion);
//            //web3ClientVersion.
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public static Credentials getCredentialsFromPrivateKey(){
        return Credentials.create(PRIVATE_KEY);
    }


}
