//package com.example.nickvgils.teleplaatsfrontend;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import org.web3j.crypto.CipherException;
//import org.web3j.crypto.Credentials;
//import org.web3j.crypto.WalletUtils;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.Web3jFactory;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.web3j.protocol.core.methods.response.Web3ClientVersion;
//import org.web3j.protocol.http.HttpService;
//import org.web3j.tuples.generated.Tuple6;
//
//import java.io.IOException;
//import java.math.BigInteger;
//import java.util.concurrent.Future;
//
//import static org.web3j.tx.Contract.GAS_LIMIT;
//import static org.web3j.tx.ManagedTransaction.GAS_PRICE;
//
//class RetreiveWeb3jData extends AsyncTask<Void, Void, Void> {
//
//    private String TAG = "MAINACTIVITY";
//
//    @Override
//    protected Void doInBackground(Void... voids) {
//        Web3jTest();
//        return null;
//    }
//
//    public void Web3jTest()
//    {
//        Web3j web3 = Web3jFactory.build(new HttpService("http://192.168.178.33:8545"));  // defaults to http://localhost:8545/
//
//        Web3ClientVersion web3ClientVersion = null;
//        try {
//            web3ClientVersion = web3.web3ClientVersion().send();
//            //web3ClientVersion.
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
//        Log.d(TAG, "Web3jTest: " + clientVersion);
//
//        Credentials credentials = getCredentialsFromPrivateKey();
//
//        Teleplaats contract = Teleplaats.load(
//                "0x7008d8d2138E8ecAAb77E876915e8aA5A37E295a", web3, credentials, GAS_PRICE, GAS_LIMIT);
//        try {
//            //contract.setName("bieke").send();
//            BigInteger test = contract.getPhoneCount().send();
//
//            TransactionReceipt tr = contract.addPhone("imei", "model", "brand", "state", "info").send();
//
//            BigInteger test2 = contract.getPhoneCount().send();
//
//
//            BigInteger phoneIndex = test2.subtract(BigInteger.valueOf(1));
//
//            Tuple6<String, String, String, String, String, String> phoneInfo = contract.getPhone(phoneIndex).send();
//            Log.d(TAG, "Web3jTest: " + phoneInfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    private Credentials getCredentialsFromWallet() throws IOException, CipherException {
//        return WalletUtils.loadCredentials("passsphrase", "wallet/path");
//    }
//
//}
//
//
//
//class DeployWeb3jContract extends AsyncTask<Void, Void, String> {
//
//    @Override
//    protected String doInBackground(Void... voids) {
//
//        try {
//            Teleplaats contract = Teleplaats.deploy(
//                    Web3J.getInstance().web3, Web3J.getCredentialsFromPrivateKey(),
//                    GAS_PRICE, GAS_LIMIT).send();
//            return contract.getContractAddress();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}