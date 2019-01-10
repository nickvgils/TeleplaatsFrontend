package com.example.nickvgils.teleplaatsfrontend;

import android.os.AsyncTask;
import android.util.Log;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tuples.generated.Tuple8;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.web3j.tx.Contract.GAS_LIMIT;
import static org.web3j.tx.ManagedTransaction.GAS_PRICE;

public class Web3J {
    private static Web3J instance = null;

    private static String TAG = "WEB3J";

    public static List<Phone> phones;

    public Web3j web3 = null;
    public static String ownAddr;

    private static final String ipAdress = "192.168.1.160";
    private static final String port = "7545";

    private final static String PRIVATE_KEY = "4e81218c256c039e229b803065266b45c19fa04649f6ee96936c1dc9c5d059e1";

    private static Teleplaats contract;

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

    public static class InitContractTask extends AsyncTask<Void, Void, Void> {

        public Web3jInterface delegate = null;

        public InitContractTask(Web3jInterface delegate){
            this.delegate = delegate;
        }

        @Override
        protected Void doInBackground(Void... voids) {


            //first time deploy then load is the intented way but not sure if works
            contract = loadContract("0x6632f46a433a997b7538b2bc4c5b2178d65690d0");
            //contract = deployContract();

            ownAddr = Web3J.getCredentialsFromPrivateKey().getAddress();


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //new GetPhones().execute();
            Log.d(TAG, "onPostExecute: " + contract.getContractAddress());
        }
    }

    public static class GetPhonesTask extends AsyncTask<Void, Void, List<Phone>> {

        public Web3jInterface delegate = null;

        public GetPhonesTask(Web3jInterface delegate){
            this.delegate = delegate;
        }

        @Override
        protected List<Phone> doInBackground(Void... voids) {

            if(contract != null)
            {
                List<Phone> phones = getAllPhones(contract);
                return phones;
            }
            return new ArrayList<>();
        }

        @Override
        protected void onPostExecute(List<Phone> phones) {
            delegate.refreshPhones(phones);
        }
    }

    public static class SellPhoneTask extends AsyncTask<Phone, Void, Void> {

        @Override
        protected Void doInBackground(Phone... phones) {

            if(contract != null)
            {
                sellPhone(phones[0]);
            }
            return null;
        }
    }

    public static Teleplaats loadContract(String contractAddress)
    {

        try {
            Teleplaats contract = Teleplaats.load(contractAddress,Web3J.getInstance().web3, Web3J.getCredentialsFromPrivateKey(), GAS_PRICE, GAS_LIMIT);
            return contract;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Teleplaats deployContract(){
        try {
            Teleplaats contract = Teleplaats.deploy(
                    Web3J.getInstance().web3, Web3J.getCredentialsFromPrivateKey(),
                    GAS_PRICE, GAS_LIMIT).send();
            return contract;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Phone> getAllPhones(Teleplaats teleplaats){
        phones = new ArrayList<>();
        try {
            int phoneCount = teleplaats.phoneid().send().intValue();

            for (int i = 1; i <= phoneCount; i++){
                //(String brand, String model, Status status, String imei, int price, String owner, boolean bidding)
                Tuple6<String, String, String, String, String, String> phoneTuple = teleplaats.phones(BigInteger.valueOf(i)).send();
                Tuple8<String, String, Boolean, BigInteger, Boolean, String, String, BigInteger> orderTuple = teleplaats.orders(BigInteger.valueOf(i)).send();
                phones.add(new Phone(phoneTuple.getValue1(), phoneTuple.getValue2(), phoneTuple.getValue3(), phoneTuple.getValue4(), phoneTuple.getValue5(), phoneTuple.getValue6(), orderTuple.getValue4().intValue(), orderTuple.getValue3()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return phones;
    }

    private static void sellPhone(Phone phone){
        try {
            TransactionReceipt tr = contract.sellPhone(phone.getUsername(), phone.getImei(), phone.getModel(), phone.getBrand(), phone.getStatus().toString(), BigInteger.valueOf(phone.getPrice()), phone.isBidding()).send();
            Log.d(TAG, "doInBackground: ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buyPhone(String username, int id){
        try {
            TransactionReceipt tr = contract.buyOrder(username,BigInteger.valueOf(id + 1), BigInteger.valueOf(phones.get(id).getPrice())).send();
            Log.d(TAG, "doInBackground: ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
