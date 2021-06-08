package com.sz.apollo.ui.models;

import java.util.List;

public class DataByAccount {
    /**
     * code : 200
     * data : {"balance":["468.4004 AOT","3.4076 AOC"],"list":[{"blockHash":"00353552be2b5b8e86c94d89c6a6085315e96a1bffa2b1dccfc286d3b6fd702c","quantity":"500.0000","data":{"from":"cult33r3awr2","to":"1lwrb1wgr3o3","quantity":"500.0000 AOT","gas":"3.0000 AOT","memo":"1"},"memo":"1","raw":{"accountActionSeq":1,"actionTrace":{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"cult33r3awr2","permission":"active"}],"data":{"$ref":"$.data.list[0].data"},"hexData":"202e37e38e91a34630e8b88c8773790c404b4c000000000004414f5400000000307500000000000004414f54000000000131","name":"transfergas"},"blockNum":3487058,"blockTime":"2020-06-25T17:17:10.000","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":8,"inlineTraces":[],"producerBlockId":"00353552be2b5b8e86c94d89c6a6085315e96a1bffa2b1dccfc286d3b6fd702c","receipt":{"abiSequence":1,"actDigest":"070fed468da1f237506758443b296311c7ab548bfc68e416b4722e5f54d06256","authSequence":[["cult33r3awr2","66"]],"codeSequence":26,"globalSequence":4002914,"receiver":"1lwrb1wgr3o3","recvSequence":2},"totalCpuUsage":null,"trxId":"b418581c1f71efed1a080abdf9d33bd0dc60d52e78634e026267c26ac041c258","trxStatus":null},"blockNum":3487058,"blockTime":"2020-06-25T17:17:10.000","globalActionSeq":4002914},"type":"transfergas","token":"AOT","gas":"3.0000 AOT","from":"cult33r3awr2","blockTime":"2020-06-26 01:17:10","to":"1lwrb1wgr3o3","hash":"b418581c1f71efed1a080abdf9d33bd0dc60d52e78634e026267c26ac041c258","height":3487058},{"blockHash":"003e4166680df119d9758202a54b25dab95ac78101529454d037aeb2ade74b12","quantity":"0.7013","data":{"from":"aotreceipt","to":"1lwrb1wgr3o3","quantity":"0.7013 AOC","memo":"第三方调用转账"},"memo":"第三方调用转账","raw":{"accountActionSeq":2,"actionTrace":{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"aotreceipt","permission":"active"}],"data":{"$ref":"$.data.list[1].data"},"hexData":"0040ae4e2175333530e8b88c8773790c651b00000000000004414f430000000015e7acace4b889e696b9e8b083e794a8e8bdace8b4a6","name":"transfer"},"blockNum":4079974,"blockTime":"2020-06-29T03:38:08.000","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":8,"inlineTraces":[],"producerBlockId":"003e4166680df119d9758202a54b25dab95ac78101529454d037aeb2ade74b12","receipt":{"abiSequence":1,"actDigest":"ba767f0f160b5fc83452b782bc943df350c1ffbfeb50c491a488fcb67e4f02b9","authSequence":[["aotreceipt","65802"]],"codeSequence":27,"globalSequence":4670243,"receiver":"1lwrb1wgr3o3","recvSequence":3},"totalCpuUsage":null,"trxId":"5b11a9be2762d49a2b6a5c7cfe152b7af37e886a0b79aa466798fb8592346e0f","trxStatus":null},"blockNum":4079974,"blockTime":"2020-06-29T03:38:08.000","globalActionSeq":4670243},"from":"aotreceipt","blockTime":"2020-06-29 11:38:08","to":"1lwrb1wgr3o3","type":"transfer","hash":"5b11a9be2762d49a2b6a5c7cfe152b7af37e886a0b79aa466798fb8592346e0f","token":"AOC","height":4079974},{"blockHash":"003e4184f3e409163d24a1cb23245f64134ef6146893ae8495ba1e78dc177d54","quantity":"0.7000","data":{"from":"aotreceipt","to":"1lwrb1wgr3o3","quantity":"0.7000 AOC","memo":"第三方调用转账"},"memo":"第三方调用转账","raw":{"accountActionSeq":3,"actionTrace":{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"aotreceipt","permission":"active"}],"data":{"$ref":"$.data.list[2].data"},"hexData":"0040ae4e2175333530e8b88c8773790c581b00000000000004414f430000000015e7acace4b889e696b9e8b083e794a8e8bdace8b4a6","name":"transfer"},"blockNum":4080004,"blockTime":"2020-06-29T03:38:23.000","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":6,"inlineTraces":[],"producerBlockId":"003e4184f3e409163d24a1cb23245f64134ef6146893ae8495ba1e78dc177d54","receipt":{"abiSequence":1,"actDigest":"9e86533abd78d2bafcf4222c095cc81b30f68cd2a9bc024403be1281834d8ffb","authSequence":[["aotreceipt","65805"]],"codeSequence":27,"globalSequence":4670287,"receiver":"1lwrb1wgr3o3","recvSequence":4},"totalCpuUsage":null,"trxId":"afe1ad77b70e24c90437bad42fdcfb4b62266b0932989ea9f23fe8a5582a187e","trxStatus":null},"blockNum":4080004,"blockTime":"2020-06-29T03:38:23.000","globalActionSeq":4670287},"from":"aotreceipt","blockTime":"2020-06-29 11:38:23","to":"1lwrb1wgr3o3","type":"transfer","hash":"afe1ad77b70e24c90437bad42fdcfb4b62266b0932989ea9f23fe8a5582a187e","token":"AOC","height":4080004},{"blockHash":"003e41b0852c2e0753a58f0a58ca3cca61998fe075c31b9a92aa13b2c6b00959","quantity":"0.6960","data":{"from":"aotreceipt","to":"1lwrb1wgr3o3","quantity":"0.6960 AOC","memo":"第三方调用转账"},"memo":"第三方调用转账","raw":{"accountActionSeq":4,"actionTrace":{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"aotreceipt","permission":"active"}],"data":{"$ref":"$.data.list[3].data"},"hexData":"0040ae4e2175333530e8b88c8773790c301b00000000000004414f430000000015e7acace4b889e696b9e8b083e794a8e8bdace8b4a6","name":"transfer"},"blockNum":4080048,"blockTime":"2020-06-29T03:38:45.000","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":5,"inlineTraces":[],"producerBlockId":"003e41b0852c2e0753a58f0a58ca3cca61998fe075c31b9a92aa13b2c6b00959","receipt":{"abiSequence":1,"actDigest":"87f1e0ced1f320120641ecb8003932ba04d1f0ba0397036cc95854504da4be5b","authSequence":[["aotreceipt","65808"]],"codeSequence":27,"globalSequence":4670345,"receiver":"1lwrb1wgr3o3","recvSequence":5},"totalCpuUsage":null,"trxId":"12ad9a89c445e7e004f35b1b955e3c9a55b0148c14fa25dc369e840e22aac8a5","trxStatus":null},"blockNum":4080048,"blockTime":"2020-06-29T03:38:45.000","globalActionSeq":4670345},"from":"aotreceipt","blockTime":"2020-06-29 11:38:45","to":"1lwrb1wgr3o3","type":"transfer","hash":"12ad9a89c445e7e004f35b1b955e3c9a55b0148c14fa25dc369e840e22aac8a5","token":"AOC","height":4080048},{"blockHash":"003ff3bd7bb28b21f54ef1f562224c98134d7843f122e872065a1cd23c758e27","quantity":"0.6895","data":{"from":"aotreceipt","to":"1lwrb1wgr3o3","quantity":"0.6895 AOC","memo":"第三方调用转账"},"memo":"第三方调用转账","raw":{"accountActionSeq":5,"actionTrace":{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"aotreceipt","permission":"active"}],"data":{"$ref":"$.data.list[4].data"},"hexData":"0040ae4e2175333530e8b88c8773790cef1a00000000000004414f430000000015e7acace4b889e696b9e8b083e794a8e8bdace8b4a6","name":"transfer"},"blockNum":4191165,"blockTime":"2020-06-29T19:04:43.500","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":10,"inlineTraces":[],"producerBlockId":"003ff3bd7bb28b21f54ef1f562224c98134d7843f122e872065a1cd23c758e27","receipt":{"abiSequence":1,"actDigest":"4f0b9955a88b01fc537780058add34236d4fc793ad69d03c19b4713cc35b4fa3","authSequence":[["aotreceipt","67434"]],"codeSequence":27,"globalSequence":4792817,"receiver":"1lwrb1wgr3o3","recvSequence":6},"totalCpuUsage":null,"trxId":"bc8de1b41729527ec79607b521ab63ce40a0ebd68991fe4e8e369e914b890153","trxStatus":null},"blockNum":4191165,"blockTime":"2020-06-29T19:04:43.500","globalActionSeq":4792817},"from":"aotreceipt","blockTime":"2020-06-30 03:04:43","to":"1lwrb1wgr3o3","type":"transfer","hash":"bc8de1b41729527ec79607b521ab63ce40a0ebd68991fe4e8e369e914b890153","token":"AOC","height":4191165},{"blockHash":"0044a25feb83aded73f584a08a3af5cd1022c9248788b81619d1ec1c555f5841","quantity":"0.6763","data":{"from":"aotreceipt","to":"1lwrb1wgr3o3","quantity":"0.6763 AOC","memo":"第三方调用转账"},"memo":"第三方调用转账","raw":{"accountActionSeq":6,"actionTrace":{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"aotreceipt","permission":"active"}],"data":{"$ref":"$.data.list[5].data"},"hexData":"0040ae4e2175333530e8b88c8773790c6b1a00000000000004414f430000000015e7acace4b889e696b9e8b083e794a8e8bdace8b4a6","name":"transfer"},"blockNum":4498015,"blockTime":"2020-07-01T16:53:39.000","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":5,"inlineTraces":[],"producerBlockId":"0044a25feb83aded73f584a08a3af5cd1022c9248788b81619d1ec1c555f5841","receipt":{"abiSequence":1,"actDigest":"dc55405ace2d9f85d83059d61fc72f0c18438d5dd83d359cdc4f0002c69a597a","authSequence":[["aotreceipt","72429"]],"codeSequence":27,"globalSequence":5134980,"receiver":"1lwrb1wgr3o3","recvSequence":7},"totalCpuUsage":null,"trxId":"75ff742c09ad9e4deaf9bd4a6a9a42d8536b7dbf63c8246fc1cda4d1faa93c2a","trxStatus":null},"blockNum":4498015,"blockTime":"2020-07-01T16:53:39.000","globalActionSeq":5134980},"from":"aotreceipt","blockTime":"2020-07-02 00:53:39","to":"1lwrb1wgr3o3","type":"transfer","hash":"75ff742c09ad9e4deaf9bd4a6a9a42d8536b7dbf63c8246fc1cda4d1faa93c2a","token":"AOC","height":4498015}],"account":"1lwrb1wgr3o3"}
     * success : true
     * message : OK
     * timestamp : 1596542532842
     */

    private int code;
    private DataBeanXX data;
    private boolean success;
    private String message;
    private long timestamp;

    public DataByAccount() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBeanXX getData() {
        return data;
    }

    public void setData(DataBeanXX data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static class DataBeanXX {
        /**
         * balance : ["468.4004 AOT","3.4076 AOC"]
         * list : [{"blockHash":"00353552be2b5b8e86c94d89c6a6085315e96a1bffa2b1dccfc286d3b6fd702c","quantity":"500.0000","data":{"from":"cult33r3awr2","to":"1lwrb1wgr3o3","quantity":"500.0000 AOT","gas":"3.0000 AOT","memo":"1"},"memo":"1","raw":{"accountActionSeq":1,"actionTrace":{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"cult33r3awr2","permission":"active"}],"data":{"$ref":"$.data.list[0].data"},"hexData":"202e37e38e91a34630e8b88c8773790c404b4c000000000004414f5400000000307500000000000004414f54000000000131","name":"transfergas"},"blockNum":3487058,"blockTime":"2020-06-25T17:17:10.000","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":8,"inlineTraces":[],"producerBlockId":"00353552be2b5b8e86c94d89c6a6085315e96a1bffa2b1dccfc286d3b6fd702c","receipt":{"abiSequence":1,"actDigest":"070fed468da1f237506758443b296311c7ab548bfc68e416b4722e5f54d06256","authSequence":[["cult33r3awr2","66"]],"codeSequence":26,"globalSequence":4002914,"receiver":"1lwrb1wgr3o3","recvSequence":2},"totalCpuUsage":null,"trxId":"b418581c1f71efed1a080abdf9d33bd0dc60d52e78634e026267c26ac041c258","trxStatus":null},"blockNum":3487058,"blockTime":"2020-06-25T17:17:10.000","globalActionSeq":4002914},"type":"transfergas","token":"AOT","gas":"3.0000 AOT","from":"cult33r3awr2","blockTime":"2020-06-26 01:17:10","to":"1lwrb1wgr3o3","hash":"b418581c1f71efed1a080abdf9d33bd0dc60d52e78634e026267c26ac041c258","height":3487058},{"blockHash":"003e4166680df119d9758202a54b25dab95ac78101529454d037aeb2ade74b12","quantity":"0.7013","data":{"from":"aotreceipt","to":"1lwrb1wgr3o3","quantity":"0.7013 AOC","memo":"第三方调用转账"},"memo":"第三方调用转账","raw":{"accountActionSeq":2,"actionTrace":{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"aotreceipt","permission":"active"}],"data":{"$ref":"$.data.list[1].data"},"hexData":"0040ae4e2175333530e8b88c8773790c651b00000000000004414f430000000015e7acace4b889e696b9e8b083e794a8e8bdace8b4a6","name":"transfer"},"blockNum":4079974,"blockTime":"2020-06-29T03:38:08.000","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":8,"inlineTraces":[],"producerBlockId":"003e4166680df119d9758202a54b25dab95ac78101529454d037aeb2ade74b12","receipt":{"abiSequence":1,"actDigest":"ba767f0f160b5fc83452b782bc943df350c1ffbfeb50c491a488fcb67e4f02b9","authSequence":[["aotreceipt","65802"]],"codeSequence":27,"globalSequence":4670243,"receiver":"1lwrb1wgr3o3","recvSequence":3},"totalCpuUsage":null,"trxId":"5b11a9be2762d49a2b6a5c7cfe152b7af37e886a0b79aa466798fb8592346e0f","trxStatus":null},"blockNum":4079974,"blockTime":"2020-06-29T03:38:08.000","globalActionSeq":4670243},"from":"aotreceipt","blockTime":"2020-06-29 11:38:08","to":"1lwrb1wgr3o3","type":"transfer","hash":"5b11a9be2762d49a2b6a5c7cfe152b7af37e886a0b79aa466798fb8592346e0f","token":"AOC","height":4079974},{"blockHash":"003e4184f3e409163d24a1cb23245f64134ef6146893ae8495ba1e78dc177d54","quantity":"0.7000","data":{"from":"aotreceipt","to":"1lwrb1wgr3o3","quantity":"0.7000 AOC","memo":"第三方调用转账"},"memo":"第三方调用转账","raw":{"accountActionSeq":3,"actionTrace":{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"aotreceipt","permission":"active"}],"data":{"$ref":"$.data.list[2].data"},"hexData":"0040ae4e2175333530e8b88c8773790c581b00000000000004414f430000000015e7acace4b889e696b9e8b083e794a8e8bdace8b4a6","name":"transfer"},"blockNum":4080004,"blockTime":"2020-06-29T03:38:23.000","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":6,"inlineTraces":[],"producerBlockId":"003e4184f3e409163d24a1cb23245f64134ef6146893ae8495ba1e78dc177d54","receipt":{"abiSequence":1,"actDigest":"9e86533abd78d2bafcf4222c095cc81b30f68cd2a9bc024403be1281834d8ffb","authSequence":[["aotreceipt","65805"]],"codeSequence":27,"globalSequence":4670287,"receiver":"1lwrb1wgr3o3","recvSequence":4},"totalCpuUsage":null,"trxId":"afe1ad77b70e24c90437bad42fdcfb4b62266b0932989ea9f23fe8a5582a187e","trxStatus":null},"blockNum":4080004,"blockTime":"2020-06-29T03:38:23.000","globalActionSeq":4670287},"from":"aotreceipt","blockTime":"2020-06-29 11:38:23","to":"1lwrb1wgr3o3","type":"transfer","hash":"afe1ad77b70e24c90437bad42fdcfb4b62266b0932989ea9f23fe8a5582a187e","token":"AOC","height":4080004},{"blockHash":"003e41b0852c2e0753a58f0a58ca3cca61998fe075c31b9a92aa13b2c6b00959","quantity":"0.6960","data":{"from":"aotreceipt","to":"1lwrb1wgr3o3","quantity":"0.6960 AOC","memo":"第三方调用转账"},"memo":"第三方调用转账","raw":{"accountActionSeq":4,"actionTrace":{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"aotreceipt","permission":"active"}],"data":{"$ref":"$.data.list[3].data"},"hexData":"0040ae4e2175333530e8b88c8773790c301b00000000000004414f430000000015e7acace4b889e696b9e8b083e794a8e8bdace8b4a6","name":"transfer"},"blockNum":4080048,"blockTime":"2020-06-29T03:38:45.000","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":5,"inlineTraces":[],"producerBlockId":"003e41b0852c2e0753a58f0a58ca3cca61998fe075c31b9a92aa13b2c6b00959","receipt":{"abiSequence":1,"actDigest":"87f1e0ced1f320120641ecb8003932ba04d1f0ba0397036cc95854504da4be5b","authSequence":[["aotreceipt","65808"]],"codeSequence":27,"globalSequence":4670345,"receiver":"1lwrb1wgr3o3","recvSequence":5},"totalCpuUsage":null,"trxId":"12ad9a89c445e7e004f35b1b955e3c9a55b0148c14fa25dc369e840e22aac8a5","trxStatus":null},"blockNum":4080048,"blockTime":"2020-06-29T03:38:45.000","globalActionSeq":4670345},"from":"aotreceipt","blockTime":"2020-06-29 11:38:45","to":"1lwrb1wgr3o3","type":"transfer","hash":"12ad9a89c445e7e004f35b1b955e3c9a55b0148c14fa25dc369e840e22aac8a5","token":"AOC","height":4080048},{"blockHash":"003ff3bd7bb28b21f54ef1f562224c98134d7843f122e872065a1cd23c758e27","quantity":"0.6895","data":{"from":"aotreceipt","to":"1lwrb1wgr3o3","quantity":"0.6895 AOC","memo":"第三方调用转账"},"memo":"第三方调用转账","raw":{"accountActionSeq":5,"actionTrace":{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"aotreceipt","permission":"active"}],"data":{"$ref":"$.data.list[4].data"},"hexData":"0040ae4e2175333530e8b88c8773790cef1a00000000000004414f430000000015e7acace4b889e696b9e8b083e794a8e8bdace8b4a6","name":"transfer"},"blockNum":4191165,"blockTime":"2020-06-29T19:04:43.500","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":10,"inlineTraces":[],"producerBlockId":"003ff3bd7bb28b21f54ef1f562224c98134d7843f122e872065a1cd23c758e27","receipt":{"abiSequence":1,"actDigest":"4f0b9955a88b01fc537780058add34236d4fc793ad69d03c19b4713cc35b4fa3","authSequence":[["aotreceipt","67434"]],"codeSequence":27,"globalSequence":4792817,"receiver":"1lwrb1wgr3o3","recvSequence":6},"totalCpuUsage":null,"trxId":"bc8de1b41729527ec79607b521ab63ce40a0ebd68991fe4e8e369e914b890153","trxStatus":null},"blockNum":4191165,"blockTime":"2020-06-29T19:04:43.500","globalActionSeq":4792817},"from":"aotreceipt","blockTime":"2020-06-30 03:04:43","to":"1lwrb1wgr3o3","type":"transfer","hash":"bc8de1b41729527ec79607b521ab63ce40a0ebd68991fe4e8e369e914b890153","token":"AOC","height":4191165},{"blockHash":"0044a25feb83aded73f584a08a3af5cd1022c9248788b81619d1ec1c555f5841","quantity":"0.6763","data":{"from":"aotreceipt","to":"1lwrb1wgr3o3","quantity":"0.6763 AOC","memo":"第三方调用转账"},"memo":"第三方调用转账","raw":{"accountActionSeq":6,"actionTrace":{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"aotreceipt","permission":"active"}],"data":{"$ref":"$.data.list[5].data"},"hexData":"0040ae4e2175333530e8b88c8773790c6b1a00000000000004414f430000000015e7acace4b889e696b9e8b083e794a8e8bdace8b4a6","name":"transfer"},"blockNum":4498015,"blockTime":"2020-07-01T16:53:39.000","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":5,"inlineTraces":[],"producerBlockId":"0044a25feb83aded73f584a08a3af5cd1022c9248788b81619d1ec1c555f5841","receipt":{"abiSequence":1,"actDigest":"dc55405ace2d9f85d83059d61fc72f0c18438d5dd83d359cdc4f0002c69a597a","authSequence":[["aotreceipt","72429"]],"codeSequence":27,"globalSequence":5134980,"receiver":"1lwrb1wgr3o3","recvSequence":7},"totalCpuUsage":null,"trxId":"75ff742c09ad9e4deaf9bd4a6a9a42d8536b7dbf63c8246fc1cda4d1faa93c2a","trxStatus":null},"blockNum":4498015,"blockTime":"2020-07-01T16:53:39.000","globalActionSeq":5134980},"from":"aotreceipt","blockTime":"2020-07-02 00:53:39","to":"1lwrb1wgr3o3","type":"transfer","hash":"75ff742c09ad9e4deaf9bd4a6a9a42d8536b7dbf63c8246fc1cda4d1faa93c2a","token":"AOC","height":4498015}]
         * account : 1lwrb1wgr3o3
         */

        private String account;
        private List<String> balance;
        private List<ListBean> list;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public List<String> getBalance() {
            return balance;
        }

        public void setBalance(List<String> balance) {
            this.balance = balance;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * blockHash : 00353552be2b5b8e86c94d89c6a6085315e96a1bffa2b1dccfc286d3b6fd702c
             * quantity : 500.0000
             * data : {"from":"cult33r3awr2","to":"1lwrb1wgr3o3","quantity":"500.0000 AOT","gas":"3.0000 AOT","memo":"1"}
             * memo : 1
             * raw : {"accountActionSeq":1,"actionTrace":{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"cult33r3awr2","permission":"active"}],"data":{"$ref":"$.data.list[0].data"},"hexData":"202e37e38e91a34630e8b88c8773790c404b4c000000000004414f5400000000307500000000000004414f54000000000131","name":"transfergas"},"blockNum":3487058,"blockTime":"2020-06-25T17:17:10.000","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":8,"inlineTraces":[],"producerBlockId":"00353552be2b5b8e86c94d89c6a6085315e96a1bffa2b1dccfc286d3b6fd702c","receipt":{"abiSequence":1,"actDigest":"070fed468da1f237506758443b296311c7ab548bfc68e416b4722e5f54d06256","authSequence":[["cult33r3awr2","66"]],"codeSequence":26,"globalSequence":4002914,"receiver":"1lwrb1wgr3o3","recvSequence":2},"totalCpuUsage":null,"trxId":"b418581c1f71efed1a080abdf9d33bd0dc60d52e78634e026267c26ac041c258","trxStatus":null},"blockNum":3487058,"blockTime":"2020-06-25T17:17:10.000","globalActionSeq":4002914}
             * type : transfergas
             * token : AOT
             * gas : 3.0000 AOT
             * from : cult33r3awr2
             * blockTime : 2020-06-26 01:17:10
             * to : 1lwrb1wgr3o3
             * hash : b418581c1f71efed1a080abdf9d33bd0dc60d52e78634e026267c26ac041c258
             * height : 3487058
             */

            private String blockHash;
            private String quantity;
            private DataBean data;
            private String memo;
            private RawBean raw;
            private String type;
            private String token;
            private String gas;
            private String from;
            private String blockTime;
            private String to;
            private String hash;
            private int height;

            public String getBlockHash() {
                return blockHash;
            }

            public void setBlockHash(String blockHash) {
                this.blockHash = blockHash;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public RawBean getRaw() {
                return raw;
            }

            public void setRaw(RawBean raw) {
                this.raw = raw;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getGas() {
                return gas;
            }

            public void setGas(String gas) {
                this.gas = gas;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getBlockTime() {
                return blockTime;
            }

            public void setBlockTime(String blockTime) {
                this.blockTime = blockTime;
            }

            public String getTo() {
                return to;
            }

            public void setTo(String to) {
                this.to = to;
            }

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public static class DataBean {
                /**
                 * from : cult33r3awr2
                 * to : 1lwrb1wgr3o3
                 * quantity : 500.0000 AOT
                 * gas : 3.0000 AOT
                 * memo : 1
                 */

                private String from;
                private String to;
                private String quantity;
                private String gas;
                private String memo;

                public String getFrom() {
                    return from;
                }

                public void setFrom(String from) {
                    this.from = from;
                }

                public String getTo() {
                    return to;
                }

                public void setTo(String to) {
                    this.to = to;
                }

                public String getQuantity() {
                    return quantity;
                }

                public void setQuantity(String quantity) {
                    this.quantity = quantity;
                }

                public String getGas() {
                    return gas;
                }

                public void setGas(String gas) {
                    this.gas = gas;
                }

                public String getMemo() {
                    return memo;
                }

                public void setMemo(String memo) {
                    this.memo = memo;
                }
            }

            public static class RawBean {
                /**
                 * accountActionSeq : 1
                 * actionTrace : {"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"cult33r3awr2","permission":"active"}],"data":{"$ref":"$.data.list[0].data"},"hexData":"202e37e38e91a34630e8b88c8773790c404b4c000000000004414f5400000000307500000000000004414f54000000000131","name":"transfergas"},"blockNum":3487058,"blockTime":"2020-06-25T17:17:10.000","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":8,"inlineTraces":[],"producerBlockId":"00353552be2b5b8e86c94d89c6a6085315e96a1bffa2b1dccfc286d3b6fd702c","receipt":{"abiSequence":1,"actDigest":"070fed468da1f237506758443b296311c7ab548bfc68e416b4722e5f54d06256","authSequence":[["cult33r3awr2","66"]],"codeSequence":26,"globalSequence":4002914,"receiver":"1lwrb1wgr3o3","recvSequence":2},"totalCpuUsage":null,"trxId":"b418581c1f71efed1a080abdf9d33bd0dc60d52e78634e026267c26ac041c258","trxStatus":null}
                 * blockNum : 3487058
                 * blockTime : 2020-06-25T17:17:10.000
                 * globalActionSeq : 4002914
                 */

                private int accountActionSeq;
                private ActionTraceBean actionTrace;
                private int blockNum;
                private String blockTime;
                private int globalActionSeq;

                public int getAccountActionSeq() {
                    return accountActionSeq;
                }

                public void setAccountActionSeq(int accountActionSeq) {
                    this.accountActionSeq = accountActionSeq;
                }

                public ActionTraceBean getActionTrace() {
                    return actionTrace;
                }

                public void setActionTrace(ActionTraceBean actionTrace) {
                    this.actionTrace = actionTrace;
                }

                public int getBlockNum() {
                    return blockNum;
                }

                public void setBlockNum(int blockNum) {
                    this.blockNum = blockNum;
                }

                public String getBlockTime() {
                    return blockTime;
                }

                public void setBlockTime(String blockTime) {
                    this.blockTime = blockTime;
                }

                public int getGlobalActionSeq() {
                    return globalActionSeq;
                }

                public void setGlobalActionSeq(int globalActionSeq) {
                    this.globalActionSeq = globalActionSeq;
                }

                public static class ActionTraceBean {
                    /**
                     * accountRamDeltas : []
                     * act : {"account":"token.aotc","authorization":[{"actor":"cult33r3awr2","permission":"active"}],"data":{"$ref":"$.data.list[0].data"},"hexData":"202e37e38e91a34630e8b88c8773790c404b4c000000000004414f5400000000307500000000000004414f54000000000131","name":"transfergas"}
                     * blockNum : 3487058
                     * blockTime : 2020-06-25T17:17:10.000
                     * console :
                     * contextFree : false
                     * cpuUsage : null
                     * createdAt : null
                     * elapsed : 8
                     * inlineTraces : []
                     * producerBlockId : 00353552be2b5b8e86c94d89c6a6085315e96a1bffa2b1dccfc286d3b6fd702c
                     * receipt : {"abiSequence":1,"actDigest":"070fed468da1f237506758443b296311c7ab548bfc68e416b4722e5f54d06256","authSequence":[["cult33r3awr2","66"]],"codeSequence":26,"globalSequence":4002914,"receiver":"1lwrb1wgr3o3","recvSequence":2}
                     * totalCpuUsage : null
                     * trxId : b418581c1f71efed1a080abdf9d33bd0dc60d52e78634e026267c26ac041c258
                     * trxStatus : null
                     */

                    private ActBean act;
                    private int blockNum;
                    private String blockTime;
                    private String console;
                    private boolean contextFree;
                    private Object cpuUsage;
                    private Object createdAt;
                    private int elapsed;
                    private String producerBlockId;
                    private ReceiptBean receipt;
                    private Object totalCpuUsage;
                    private String trxId;
                    private Object trxStatus;
                    private List<?> accountRamDeltas;
                    private List<?> inlineTraces;

                    public ActBean getAct() {
                        return act;
                    }

                    public void setAct(ActBean act) {
                        this.act = act;
                    }

                    public int getBlockNum() {
                        return blockNum;
                    }

                    public void setBlockNum(int blockNum) {
                        this.blockNum = blockNum;
                    }

                    public String getBlockTime() {
                        return blockTime;
                    }

                    public void setBlockTime(String blockTime) {
                        this.blockTime = blockTime;
                    }

                    public String getConsole() {
                        return console;
                    }

                    public void setConsole(String console) {
                        this.console = console;
                    }

                    public boolean isContextFree() {
                        return contextFree;
                    }

                    public void setContextFree(boolean contextFree) {
                        this.contextFree = contextFree;
                    }

                    public Object getCpuUsage() {
                        return cpuUsage;
                    }

                    public void setCpuUsage(Object cpuUsage) {
                        this.cpuUsage = cpuUsage;
                    }

                    public Object getCreatedAt() {
                        return createdAt;
                    }

                    public void setCreatedAt(Object createdAt) {
                        this.createdAt = createdAt;
                    }

                    public int getElapsed() {
                        return elapsed;
                    }

                    public void setElapsed(int elapsed) {
                        this.elapsed = elapsed;
                    }

                    public String getProducerBlockId() {
                        return producerBlockId;
                    }

                    public void setProducerBlockId(String producerBlockId) {
                        this.producerBlockId = producerBlockId;
                    }

                    public ReceiptBean getReceipt() {
                        return receipt;
                    }

                    public void setReceipt(ReceiptBean receipt) {
                        this.receipt = receipt;
                    }

                    public Object getTotalCpuUsage() {
                        return totalCpuUsage;
                    }

                    public void setTotalCpuUsage(Object totalCpuUsage) {
                        this.totalCpuUsage = totalCpuUsage;
                    }

                    public String getTrxId() {
                        return trxId;
                    }

                    public void setTrxId(String trxId) {
                        this.trxId = trxId;
                    }

                    public Object getTrxStatus() {
                        return trxStatus;
                    }

                    public void setTrxStatus(Object trxStatus) {
                        this.trxStatus = trxStatus;
                    }

                    public List<?> getAccountRamDeltas() {
                        return accountRamDeltas;
                    }

                    public void setAccountRamDeltas(List<?> accountRamDeltas) {
                        this.accountRamDeltas = accountRamDeltas;
                    }

                    public List<?> getInlineTraces() {
                        return inlineTraces;
                    }

                    public void setInlineTraces(List<?> inlineTraces) {
                        this.inlineTraces = inlineTraces;
                    }

                    public static class ActBean {
                        /**
                         * account : token.aotc
                         * authorization : [{"actor":"cult33r3awr2","permission":"active"}]
                         * data : {"$ref":"$.data.list[0].data"}
                         * hexData : 202e37e38e91a34630e8b88c8773790c404b4c000000000004414f5400000000307500000000000004414f54000000000131
                         * name : transfergas
                         */

                        private String account;
                        private DataBeanX data;
                        private String hexData;
                        private String name;
                        private List<AuthorizationBean> authorization;

                        public String getAccount() {
                            return account;
                        }

                        public void setAccount(String account) {
                            this.account = account;
                        }

                        public DataBeanX getData() {
                            return data;
                        }

                        public void setData(DataBeanX data) {
                            this.data = data;
                        }

                        public String getHexData() {
                            return hexData;
                        }

                        public void setHexData(String hexData) {
                            this.hexData = hexData;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public List<AuthorizationBean> getAuthorization() {
                            return authorization;
                        }

                        public void setAuthorization(List<AuthorizationBean> authorization) {
                            this.authorization = authorization;
                        }

                        public static class DataBeanX {
                            /**
                             * $ref : $.data.list[0].data
                             */

                            private String $ref;

                            public String get$ref() {
                                return $ref;
                            }

                            public void set$ref(String $ref) {
                                this.$ref = $ref;
                            }
                        }

                        public static class AuthorizationBean {
                            /**
                             * actor : cult33r3awr2
                             * permission : active
                             */

                            private String actor;
                            private String permission;

                            public String getActor() {
                                return actor;
                            }

                            public void setActor(String actor) {
                                this.actor = actor;
                            }

                            public String getPermission() {
                                return permission;
                            }

                            public void setPermission(String permission) {
                                this.permission = permission;
                            }
                        }
                    }

                    public static class ReceiptBean {
                        /**
                         * abiSequence : 1
                         * actDigest : 070fed468da1f237506758443b296311c7ab548bfc68e416b4722e5f54d06256
                         * authSequence : [["cult33r3awr2","66"]]
                         * codeSequence : 26
                         * globalSequence : 4002914
                         * receiver : 1lwrb1wgr3o3
                         * recvSequence : 2
                         */

                        private int abiSequence;
                        private String actDigest;
                        private int codeSequence;
                        private int globalSequence;
                        private String receiver;
                        private int recvSequence;
                        private List<List<String>> authSequence;

                        public int getAbiSequence() {
                            return abiSequence;
                        }

                        public void setAbiSequence(int abiSequence) {
                            this.abiSequence = abiSequence;
                        }

                        public String getActDigest() {
                            return actDigest;
                        }

                        public void setActDigest(String actDigest) {
                            this.actDigest = actDigest;
                        }

                        public int getCodeSequence() {
                            return codeSequence;
                        }

                        public void setCodeSequence(int codeSequence) {
                            this.codeSequence = codeSequence;
                        }

                        public int getGlobalSequence() {
                            return globalSequence;
                        }

                        public void setGlobalSequence(int globalSequence) {
                            this.globalSequence = globalSequence;
                        }

                        public String getReceiver() {
                            return receiver;
                        }

                        public void setReceiver(String receiver) {
                            this.receiver = receiver;
                        }

                        public int getRecvSequence() {
                            return recvSequence;
                        }

                        public void setRecvSequence(int recvSequence) {
                            this.recvSequence = recvSequence;
                        }

                        public List<List<String>> getAuthSequence() {
                            return authSequence;
                        }

                        public void setAuthSequence(List<List<String>> authSequence) {
                            this.authSequence = authSequence;
                        }
                    }
                }
            }
        }
    }
}
