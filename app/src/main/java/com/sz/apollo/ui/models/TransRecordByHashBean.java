package com.sz.apollo.ui.models;

import java.util.List;

public class TransRecordByHashBean {
    /**
     * code : 200
     * data : {"blockHash":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","expireTime":"2020-08-03 11:11:27","raw":{"blockNum":10101682,"blockTime":"2020-08-03T03:11:27.500","cpuUsageUs":null,"id":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","lastIrreversibleBlock":10121676,"netUsageWords":null,"status":null,"traces":[{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hexData":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000","name":"transfergas"},"blockNum":10101682,"blockTime":"2020-08-03T03:11:27.500","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":5561,"inlineTraces":[{"receipt":{"receiver":"1lwrb1wgr3o3","act_digest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","global_sequence":11549819,"recv_sequence":69,"auth_sequence":[["1lwrb1wgr3o3",179]],"code_sequence":46,"abi_sequence":1},"act":{"account":"token.aotc","name":"transfergas","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hex_data":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000"},"context_free":false,"elapsed":7,"console":"","trx_id":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","block_num":10101682,"block_time":"2020-08-03T03:11:27.500","producer_block_id":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","account_ram_deltas":[],"except":null,"inline_traces":[]},{"receipt":{"receiver":"kskommacxhxs","act_digest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","global_sequence":11549820,"recv_sequence":12,"auth_sequence":[["1lwrb1wgr3o3",180]],"code_sequence":46,"abi_sequence":1},"act":{"account":"token.aotc","name":"transfergas","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hex_data":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000"},"context_free":false,"elapsed":8,"console":"","trx_id":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","block_num":10101682,"block_time":"2020-08-03T03:11:27.500","producer_block_id":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","account_ram_deltas":[],"except":null,"inline_traces":[]}],"producerBlockId":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","receipt":{"abiSequence":1,"actDigest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","authSequence":[["1lwrb1wgr3o3","178"]],"codeSequence":46,"globalSequence":11549818,"receiver":"token.aotc","recvSequence":81925},"totalCpuUsage":null,"trxId":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","trxStatus":null},{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hexData":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000","name":"transfergas"},"blockNum":10101682,"blockTime":"2020-08-03T03:11:27.500","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":7,"inlineTraces":[],"producerBlockId":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","receipt":{"abiSequence":1,"actDigest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","authSequence":[["1lwrb1wgr3o3","179"]],"codeSequence":46,"globalSequence":11549819,"receiver":"1lwrb1wgr3o3","recvSequence":69},"totalCpuUsage":null,"trxId":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","trxStatus":null},{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hexData":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000","name":"transfergas"},"blockNum":10101682,"blockTime":"2020-08-03T03:11:27.500","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":8,"inlineTraces":[],"producerBlockId":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","receipt":{"abiSequence":1,"actDigest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","authSequence":[["1lwrb1wgr3o3","180"]],"codeSequence":46,"globalSequence":11549820,"receiver":"kskommacxhxs","recvSequence":12},"totalCpuUsage":null,"trxId":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","trxStatus":null}],"trx":{"receipt":{"status":"executed","cpu_usage_us":24883,"net_usage_words":18,"trx":[1,{"signatures":["SIG_K1_K914RCLkaraogDXaUfbShFstSci6hFoe6WC8K5a5QU8phfnHNsRgS6xsMX1jW8JhgNj2rfYjkLq32aH6Nkw1szN2vFR3i6"],"compression":"none","packed_context_free_data":"","packed_trx":"9a80275faf23cddafac700000000010000cad480a920cd00b061572d3ccdcd0130e8b88c8773790c00000000a8ed32323130e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f54000000000000"}]},"trx":{"expiration":"2020-08-03T03:12:26","ref_block_num":9135,"ref_block_prefix":3355105997,"max_net_usage_words":0,"max_cpu_usage_ms":0,"delay_sec":0,"context_free_actions":[],"actions":[{"account":"token.aotc","name":"transfergas","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hex_data":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000"}],"transaction_extensions":[],"signatures":["SIG_K1_K914RCLkaraogDXaUfbShFstSci6hFoe6WC8K5a5QU8phfnHNsRgS6xsMX1jW8JhgNj2rfYjkLq32aH6Nkw1szN2vFR3i6"],"context_free_data":[]}}},"blockTime":"2020-08-03 11:11:27","actions":{"authorization":[{"$ref":"$.data.raw.traces[0].act.authorization[0]"}],"data":{"$ref":"$.data.raw.traces[0].act.data"},"name":"transfergas","hexData":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000","account":"token.aotc"},"hash":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","height":10101682}
     * success : true
     * message : OK
     * timestamp : 1596434286325
     */

    private int code;
    private DataBeanXXXX data;
    private boolean success;
    private String message;
    private long timestamp;

    public TransRecordByHashBean() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBeanXXXX getData() {
        return data;
    }

    public void setData(DataBeanXXXX data) {
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

    public static class DataBeanXXXX {
        /**
         * blockHash : 009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0
         * expireTime : 2020-08-03 11:11:27
         * raw : {"blockNum":10101682,"blockTime":"2020-08-03T03:11:27.500","cpuUsageUs":null,"id":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","lastIrreversibleBlock":10121676,"netUsageWords":null,"status":null,"traces":[{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hexData":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000","name":"transfergas"},"blockNum":10101682,"blockTime":"2020-08-03T03:11:27.500","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":5561,"inlineTraces":[{"receipt":{"receiver":"1lwrb1wgr3o3","act_digest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","global_sequence":11549819,"recv_sequence":69,"auth_sequence":[["1lwrb1wgr3o3",179]],"code_sequence":46,"abi_sequence":1},"act":{"account":"token.aotc","name":"transfergas","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hex_data":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000"},"context_free":false,"elapsed":7,"console":"","trx_id":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","block_num":10101682,"block_time":"2020-08-03T03:11:27.500","producer_block_id":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","account_ram_deltas":[],"except":null,"inline_traces":[]},{"receipt":{"receiver":"kskommacxhxs","act_digest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","global_sequence":11549820,"recv_sequence":12,"auth_sequence":[["1lwrb1wgr3o3",180]],"code_sequence":46,"abi_sequence":1},"act":{"account":"token.aotc","name":"transfergas","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hex_data":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000"},"context_free":false,"elapsed":8,"console":"","trx_id":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","block_num":10101682,"block_time":"2020-08-03T03:11:27.500","producer_block_id":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","account_ram_deltas":[],"except":null,"inline_traces":[]}],"producerBlockId":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","receipt":{"abiSequence":1,"actDigest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","authSequence":[["1lwrb1wgr3o3","178"]],"codeSequence":46,"globalSequence":11549818,"receiver":"token.aotc","recvSequence":81925},"totalCpuUsage":null,"trxId":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","trxStatus":null},{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hexData":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000","name":"transfergas"},"blockNum":10101682,"blockTime":"2020-08-03T03:11:27.500","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":7,"inlineTraces":[],"producerBlockId":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","receipt":{"abiSequence":1,"actDigest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","authSequence":[["1lwrb1wgr3o3","179"]],"codeSequence":46,"globalSequence":11549819,"receiver":"1lwrb1wgr3o3","recvSequence":69},"totalCpuUsage":null,"trxId":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","trxStatus":null},{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hexData":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000","name":"transfergas"},"blockNum":10101682,"blockTime":"2020-08-03T03:11:27.500","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":8,"inlineTraces":[],"producerBlockId":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","receipt":{"abiSequence":1,"actDigest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","authSequence":[["1lwrb1wgr3o3","180"]],"codeSequence":46,"globalSequence":11549820,"receiver":"kskommacxhxs","recvSequence":12},"totalCpuUsage":null,"trxId":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","trxStatus":null}],"trx":{"receipt":{"status":"executed","cpu_usage_us":24883,"net_usage_words":18,"trx":[1,{"signatures":["SIG_K1_K914RCLkaraogDXaUfbShFstSci6hFoe6WC8K5a5QU8phfnHNsRgS6xsMX1jW8JhgNj2rfYjkLq32aH6Nkw1szN2vFR3i6"],"compression":"none","packed_context_free_data":"","packed_trx":"9a80275faf23cddafac700000000010000cad480a920cd00b061572d3ccdcd0130e8b88c8773790c00000000a8ed32323130e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f54000000000000"}]},"trx":{"expiration":"2020-08-03T03:12:26","ref_block_num":9135,"ref_block_prefix":3355105997,"max_net_usage_words":0,"max_cpu_usage_ms":0,"delay_sec":0,"context_free_actions":[],"actions":[{"account":"token.aotc","name":"transfergas","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hex_data":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000"}],"transaction_extensions":[],"signatures":["SIG_K1_K914RCLkaraogDXaUfbShFstSci6hFoe6WC8K5a5QU8phfnHNsRgS6xsMX1jW8JhgNj2rfYjkLq32aH6Nkw1szN2vFR3i6"],"context_free_data":[]}}}
         * blockTime : 2020-08-03 11:11:27
         * actions : {"authorization":[{"$ref":"$.data.raw.traces[0].act.authorization[0]"}],"data":{"$ref":"$.data.raw.traces[0].act.data"},"name":"transfergas","hexData":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000","account":"token.aotc"}
         * hash : 4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25
         * height : 10101682
         */

        private String blockHash;
        private String expireTime;
        private RawBean raw;
        private String blockTime;
        private ActionsBeanX actions;
        private String hash;
        private int height;

        public String getBlockHash() {
            return blockHash;
        }

        public void setBlockHash(String blockHash) {
            this.blockHash = blockHash;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }

        public RawBean getRaw() {
            return raw;
        }

        public void setRaw(RawBean raw) {
            this.raw = raw;
        }

        public String getBlockTime() {
            return blockTime;
        }

        public void setBlockTime(String blockTime) {
            this.blockTime = blockTime;
        }

        public ActionsBeanX getActions() {
            return actions;
        }

        public void setActions(ActionsBeanX actions) {
            this.actions = actions;
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

        public static class RawBean {
            /**
             * blockNum : 10101682
             * blockTime : 2020-08-03T03:11:27.500
             * cpuUsageUs : null
             * id : 4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25
             * lastIrreversibleBlock : 10121676
             * netUsageWords : null
             * status : null
             * traces : [{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hexData":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000","name":"transfergas"},"blockNum":10101682,"blockTime":"2020-08-03T03:11:27.500","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":5561,"inlineTraces":[{"receipt":{"receiver":"1lwrb1wgr3o3","act_digest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","global_sequence":11549819,"recv_sequence":69,"auth_sequence":[["1lwrb1wgr3o3",179]],"code_sequence":46,"abi_sequence":1},"act":{"account":"token.aotc","name":"transfergas","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hex_data":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000"},"context_free":false,"elapsed":7,"console":"","trx_id":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","block_num":10101682,"block_time":"2020-08-03T03:11:27.500","producer_block_id":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","account_ram_deltas":[],"except":null,"inline_traces":[]},{"receipt":{"receiver":"kskommacxhxs","act_digest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","global_sequence":11549820,"recv_sequence":12,"auth_sequence":[["1lwrb1wgr3o3",180]],"code_sequence":46,"abi_sequence":1},"act":{"account":"token.aotc","name":"transfergas","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hex_data":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000"},"context_free":false,"elapsed":8,"console":"","trx_id":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","block_num":10101682,"block_time":"2020-08-03T03:11:27.500","producer_block_id":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","account_ram_deltas":[],"except":null,"inline_traces":[]}],"producerBlockId":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","receipt":{"abiSequence":1,"actDigest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","authSequence":[["1lwrb1wgr3o3","178"]],"codeSequence":46,"globalSequence":11549818,"receiver":"token.aotc","recvSequence":81925},"totalCpuUsage":null,"trxId":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","trxStatus":null},{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hexData":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000","name":"transfergas"},"blockNum":10101682,"blockTime":"2020-08-03T03:11:27.500","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":7,"inlineTraces":[],"producerBlockId":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","receipt":{"abiSequence":1,"actDigest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","authSequence":[["1lwrb1wgr3o3","179"]],"codeSequence":46,"globalSequence":11549819,"receiver":"1lwrb1wgr3o3","recvSequence":69},"totalCpuUsage":null,"trxId":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","trxStatus":null},{"accountRamDeltas":[],"act":{"account":"token.aotc","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hexData":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000","name":"transfergas"},"blockNum":10101682,"blockTime":"2020-08-03T03:11:27.500","console":"","contextFree":false,"cpuUsage":null,"createdAt":null,"elapsed":8,"inlineTraces":[],"producerBlockId":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","receipt":{"abiSequence":1,"actDigest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","authSequence":[["1lwrb1wgr3o3","180"]],"codeSequence":46,"globalSequence":11549820,"receiver":"kskommacxhxs","recvSequence":12},"totalCpuUsage":null,"trxId":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","trxStatus":null}]
             * trx : {"receipt":{"status":"executed","cpu_usage_us":24883,"net_usage_words":18,"trx":[1,{"signatures":["SIG_K1_K914RCLkaraogDXaUfbShFstSci6hFoe6WC8K5a5QU8phfnHNsRgS6xsMX1jW8JhgNj2rfYjkLq32aH6Nkw1szN2vFR3i6"],"compression":"none","packed_context_free_data":"","packed_trx":"9a80275faf23cddafac700000000010000cad480a920cd00b061572d3ccdcd0130e8b88c8773790c00000000a8ed32323130e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f54000000000000"}]},"trx":{"expiration":"2020-08-03T03:12:26","ref_block_num":9135,"ref_block_prefix":3355105997,"max_net_usage_words":0,"max_cpu_usage_ms":0,"delay_sec":0,"context_free_actions":[],"actions":[{"account":"token.aotc","name":"transfergas","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hex_data":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000"}],"transaction_extensions":[],"signatures":["SIG_K1_K914RCLkaraogDXaUfbShFstSci6hFoe6WC8K5a5QU8phfnHNsRgS6xsMX1jW8JhgNj2rfYjkLq32aH6Nkw1szN2vFR3i6"],"context_free_data":[]}}
             */

            private int blockNum;
            private String blockTime;
            private Object cpuUsageUs;
            private String id;
            private int lastIrreversibleBlock;
            private Object netUsageWords;
            private Object status;
            private TrxBeanX trx;
            private List<TracesBean> traces;

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

            public Object getCpuUsageUs() {
                return cpuUsageUs;
            }

            public void setCpuUsageUs(Object cpuUsageUs) {
                this.cpuUsageUs = cpuUsageUs;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getLastIrreversibleBlock() {
                return lastIrreversibleBlock;
            }

            public void setLastIrreversibleBlock(int lastIrreversibleBlock) {
                this.lastIrreversibleBlock = lastIrreversibleBlock;
            }

            public Object getNetUsageWords() {
                return netUsageWords;
            }

            public void setNetUsageWords(Object netUsageWords) {
                this.netUsageWords = netUsageWords;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public TrxBeanX getTrx() {
                return trx;
            }

            public void setTrx(TrxBeanX trx) {
                this.trx = trx;
            }

            public List<TracesBean> getTraces() {
                return traces;
            }

            public void setTraces(List<TracesBean> traces) {
                this.traces = traces;
            }

            public static class TrxBeanX {
                /**
                 * receipt : {"status":"executed","cpu_usage_us":24883,"net_usage_words":18,"trx":[1,{"signatures":["SIG_K1_K914RCLkaraogDXaUfbShFstSci6hFoe6WC8K5a5QU8phfnHNsRgS6xsMX1jW8JhgNj2rfYjkLq32aH6Nkw1szN2vFR3i6"],"compression":"none","packed_context_free_data":"","packed_trx":"9a80275faf23cddafac700000000010000cad480a920cd00b061572d3ccdcd0130e8b88c8773790c00000000a8ed32323130e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f54000000000000"}]}
                 * trx : {"expiration":"2020-08-03T03:12:26","ref_block_num":9135,"ref_block_prefix":3355105997,"max_net_usage_words":0,"max_cpu_usage_ms":0,"delay_sec":0,"context_free_actions":[],"actions":[{"account":"token.aotc","name":"transfergas","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hex_data":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000"}],"transaction_extensions":[],"signatures":["SIG_K1_K914RCLkaraogDXaUfbShFstSci6hFoe6WC8K5a5QU8phfnHNsRgS6xsMX1jW8JhgNj2rfYjkLq32aH6Nkw1szN2vFR3i6"],"context_free_data":[]}
                 */

                private ReceiptBean receipt;
                private TrxBean trx;

                public ReceiptBean getReceipt() {
                    return receipt;
                }

                public void setReceipt(ReceiptBean receipt) {
                    this.receipt = receipt;
                }

                public TrxBean getTrx() {
                    return trx;
                }

                public void setTrx(TrxBean trx) {
                    this.trx = trx;
                }

                public static class ReceiptBean {
                    /**
                     * status : executed
                     * cpu_usage_us : 24883
                     * net_usage_words : 18
                     * trx : [1,{"signatures":["SIG_K1_K914RCLkaraogDXaUfbShFstSci6hFoe6WC8K5a5QU8phfnHNsRgS6xsMX1jW8JhgNj2rfYjkLq32aH6Nkw1szN2vFR3i6"],"compression":"none","packed_context_free_data":"","packed_trx":"9a80275faf23cddafac700000000010000cad480a920cd00b061572d3ccdcd0130e8b88c8773790c00000000a8ed32323130e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f54000000000000"}]
                     */

                    private String status;
                    private int cpu_usage_us;
                    private int net_usage_words;
                    private List<Integer> trx;

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public int getCpu_usage_us() {
                        return cpu_usage_us;
                    }

                    public void setCpu_usage_us(int cpu_usage_us) {
                        this.cpu_usage_us = cpu_usage_us;
                    }

                    public int getNet_usage_words() {
                        return net_usage_words;
                    }

                    public void setNet_usage_words(int net_usage_words) {
                        this.net_usage_words = net_usage_words;
                    }

                    public List<Integer> getTrx() {
                        return trx;
                    }

                    public void setTrx(List<Integer> trx) {
                        this.trx = trx;
                    }
                }

                public static class TrxBean {
                    /**
                     * expiration : 2020-08-03T03:12:26
                     * ref_block_num : 9135
                     * ref_block_prefix : 3355105997
                     * max_net_usage_words : 0
                     * max_cpu_usage_ms : 0
                     * delay_sec : 0
                     * context_free_actions : []
                     * actions : [{"account":"token.aotc","name":"transfergas","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hex_data":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000"}]
                     * transaction_extensions : []
                     * signatures : ["SIG_K1_K914RCLkaraogDXaUfbShFstSci6hFoe6WC8K5a5QU8phfnHNsRgS6xsMX1jW8JhgNj2rfYjkLq32aH6Nkw1szN2vFR3i6"]
                     * context_free_data : []
                     */

                    private String expiration;
                    private int ref_block_num;
                    private long ref_block_prefix;
                    private int max_net_usage_words;
                    private int max_cpu_usage_ms;
                    private int delay_sec;
                    private List<?> context_free_actions;
                    private List<ActionsBean> actions;
                    private List<?> transaction_extensions;
                    private List<String> signatures;
                    private List<?> context_free_data;

                    public String getExpiration() {
                        return expiration;
                    }

                    public void setExpiration(String expiration) {
                        this.expiration = expiration;
                    }

                    public int getRef_block_num() {
                        return ref_block_num;
                    }

                    public void setRef_block_num(int ref_block_num) {
                        this.ref_block_num = ref_block_num;
                    }

                    public long getRef_block_prefix() {
                        return ref_block_prefix;
                    }

                    public void setRef_block_prefix(long ref_block_prefix) {
                        this.ref_block_prefix = ref_block_prefix;
                    }

                    public int getMax_net_usage_words() {
                        return max_net_usage_words;
                    }

                    public void setMax_net_usage_words(int max_net_usage_words) {
                        this.max_net_usage_words = max_net_usage_words;
                    }

                    public int getMax_cpu_usage_ms() {
                        return max_cpu_usage_ms;
                    }

                    public void setMax_cpu_usage_ms(int max_cpu_usage_ms) {
                        this.max_cpu_usage_ms = max_cpu_usage_ms;
                    }

                    public int getDelay_sec() {
                        return delay_sec;
                    }

                    public void setDelay_sec(int delay_sec) {
                        this.delay_sec = delay_sec;
                    }

                    public List<?> getContext_free_actions() {
                        return context_free_actions;
                    }

                    public void setContext_free_actions(List<?> context_free_actions) {
                        this.context_free_actions = context_free_actions;
                    }

                    public List<ActionsBean> getActions() {
                        return actions;
                    }

                    public void setActions(List<ActionsBean> actions) {
                        this.actions = actions;
                    }

                    public List<?> getTransaction_extensions() {
                        return transaction_extensions;
                    }

                    public void setTransaction_extensions(List<?> transaction_extensions) {
                        this.transaction_extensions = transaction_extensions;
                    }

                    public List<String> getSignatures() {
                        return signatures;
                    }

                    public void setSignatures(List<String> signatures) {
                        this.signatures = signatures;
                    }

                    public List<?> getContext_free_data() {
                        return context_free_data;
                    }

                    public void setContext_free_data(List<?> context_free_data) {
                        this.context_free_data = context_free_data;
                    }

                    public static class ActionsBean {
                        /**
                         * account : token.aotc
                         * name : transfergas
                         * authorization : [{"actor":"1lwrb1wgr3o3","permission":"active"}]
                         * data : {"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""}
                         * hex_data : 30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000
                         */

                        private String account;
                        private String name;
                        private DataBean data;
                        private String hex_data;
                        private List<AuthorizationBean> authorization;

                        public String getAccount() {
                            return account;
                        }

                        public void setAccount(String account) {
                            this.account = account;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public DataBean getData() {
                            return data;
                        }

                        public void setData(DataBean data) {
                            this.data = data;
                        }

                        public String getHex_data() {
                            return hex_data;
                        }

                        public void setHex_data(String hex_data) {
                            this.hex_data = hex_data;
                        }

                        public List<AuthorizationBean> getAuthorization() {
                            return authorization;
                        }

                        public void setAuthorization(List<AuthorizationBean> authorization) {
                            this.authorization = authorization;
                        }

                        public static class DataBean {
                            /**
                             * from : 1lwrb1wgr3o3
                             * to : kskommacxhxs
                             * quantity : 1.0000 AOT
                             * gas : 0.0060 AOT
                             * memo :
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

                        public static class AuthorizationBean {
                            /**
                             * actor : 1lwrb1wgr3o3
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
                }
            }

            public static class TracesBean {
                /**
                 * accountRamDeltas : []
                 * act : {"account":"token.aotc","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hexData":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000","name":"transfergas"}
                 * blockNum : 10101682
                 * blockTime : 2020-08-03T03:11:27.500
                 * console :
                 * contextFree : false
                 * cpuUsage : null
                 * createdAt : null
                 * elapsed : 5561
                 * inlineTraces : [{"receipt":{"receiver":"1lwrb1wgr3o3","act_digest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","global_sequence":11549819,"recv_sequence":69,"auth_sequence":[["1lwrb1wgr3o3",179]],"code_sequence":46,"abi_sequence":1},"act":{"account":"token.aotc","name":"transfergas","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hex_data":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000"},"context_free":false,"elapsed":7,"console":"","trx_id":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","block_num":10101682,"block_time":"2020-08-03T03:11:27.500","producer_block_id":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","account_ram_deltas":[],"except":null,"inline_traces":[]},{"receipt":{"receiver":"kskommacxhxs","act_digest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","global_sequence":11549820,"recv_sequence":12,"auth_sequence":[["1lwrb1wgr3o3",180]],"code_sequence":46,"abi_sequence":1},"act":{"account":"token.aotc","name":"transfergas","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hex_data":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000"},"context_free":false,"elapsed":8,"console":"","trx_id":"4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25","block_num":10101682,"block_time":"2020-08-03T03:11:27.500","producer_block_id":"009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0","account_ram_deltas":[],"except":null,"inline_traces":[]}]
                 * producerBlockId : 009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0
                 * receipt : {"abiSequence":1,"actDigest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","authSequence":[["1lwrb1wgr3o3","178"]],"codeSequence":46,"globalSequence":11549818,"receiver":"token.aotc","recvSequence":81925}
                 * totalCpuUsage : null
                 * trxId : 4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25
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
                private ReceiptBeanX receipt;
                private Object totalCpuUsage;
                private String trxId;
                private Object trxStatus;
                private List<?> accountRamDeltas;
                private List<InlineTracesBean> inlineTraces;

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

                public ReceiptBeanX getReceipt() {
                    return receipt;
                }

                public void setReceipt(ReceiptBeanX receipt) {
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

                public List<InlineTracesBean> getInlineTraces() {
                    return inlineTraces;
                }

                public void setInlineTraces(List<InlineTracesBean> inlineTraces) {
                    this.inlineTraces = inlineTraces;
                }

                public static class ActBean {
                    /**
                     * account : token.aotc
                     * authorization : [{"actor":"1lwrb1wgr3o3","permission":"active"}]
                     * data : {"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""}
                     * hexData : 30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000
                     * name : transfergas
                     */

                    private String account;
                    private DataBeanX data;
                    private String hexData;
                    private String name;
                    private List<AuthorizationBeanX> authorization;

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

                    public List<AuthorizationBeanX> getAuthorization() {
                        return authorization;
                    }

                    public void setAuthorization(List<AuthorizationBeanX> authorization) {
                        this.authorization = authorization;
                    }

                    public static class DataBeanX {
                        /**
                         * from : 1lwrb1wgr3o3
                         * to : kskommacxhxs
                         * quantity : 1.0000 AOT
                         * gas : 0.0060 AOT
                         * memo :
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

                    public static class AuthorizationBeanX {
                        /**
                         * actor : 1lwrb1wgr3o3
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

                public static class ReceiptBeanX {
                    /**
                     * abiSequence : 1
                     * actDigest : 31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675
                     * authSequence : [["1lwrb1wgr3o3","178"]]
                     * codeSequence : 46
                     * globalSequence : 11549818
                     * receiver : token.aotc
                     * recvSequence : 81925
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

                public static class InlineTracesBean {
                    /**
                     * receipt : {"receiver":"1lwrb1wgr3o3","act_digest":"31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675","global_sequence":11549819,"recv_sequence":69,"auth_sequence":[["1lwrb1wgr3o3",179]],"code_sequence":46,"abi_sequence":1}
                     * act : {"account":"token.aotc","name":"transfergas","authorization":[{"actor":"1lwrb1wgr3o3","permission":"active"}],"data":{"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""},"hex_data":"30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000"}
                     * context_free : false
                     * elapsed : 7
                     * console :
                     * trx_id : 4de211dbc98c1ac3310586ac66610032ffa3d2a7a5b473598c6a27db6dff4a25
                     * block_num : 10101682
                     * block_time : 2020-08-03T03:11:27.500
                     * producer_block_id : 009a23b208a46f5a1e8aa48ad943d68e2087100d3dd2a4cf04d8664ad90931d0
                     * account_ram_deltas : []
                     * except : null
                     * inline_traces : []
                     */

                    private ReceiptBeanXX receipt;
                    private ActBeanX act;
                    private boolean context_free;
                    private int elapsed;
                    private String console;
                    private String trx_id;
                    private int block_num;
                    private String block_time;
                    private String producer_block_id;
                    private Object except;
                    private List<?> account_ram_deltas;
                    private List<?> inline_traces;

                    public ReceiptBeanXX getReceipt() {
                        return receipt;
                    }

                    public void setReceipt(ReceiptBeanXX receipt) {
                        this.receipt = receipt;
                    }

                    public ActBeanX getAct() {
                        return act;
                    }

                    public void setAct(ActBeanX act) {
                        this.act = act;
                    }

                    public boolean isContext_free() {
                        return context_free;
                    }

                    public void setContext_free(boolean context_free) {
                        this.context_free = context_free;
                    }

                    public int getElapsed() {
                        return elapsed;
                    }

                    public void setElapsed(int elapsed) {
                        this.elapsed = elapsed;
                    }

                    public String getConsole() {
                        return console;
                    }

                    public void setConsole(String console) {
                        this.console = console;
                    }

                    public String getTrx_id() {
                        return trx_id;
                    }

                    public void setTrx_id(String trx_id) {
                        this.trx_id = trx_id;
                    }

                    public int getBlock_num() {
                        return block_num;
                    }

                    public void setBlock_num(int block_num) {
                        this.block_num = block_num;
                    }

                    public String getBlock_time() {
                        return block_time;
                    }

                    public void setBlock_time(String block_time) {
                        this.block_time = block_time;
                    }

                    public String getProducer_block_id() {
                        return producer_block_id;
                    }

                    public void setProducer_block_id(String producer_block_id) {
                        this.producer_block_id = producer_block_id;
                    }

                    public Object getExcept() {
                        return except;
                    }

                    public void setExcept(Object except) {
                        this.except = except;
                    }

                    public List<?> getAccount_ram_deltas() {
                        return account_ram_deltas;
                    }

                    public void setAccount_ram_deltas(List<?> account_ram_deltas) {
                        this.account_ram_deltas = account_ram_deltas;
                    }

                    public List<?> getInline_traces() {
                        return inline_traces;
                    }

                    public void setInline_traces(List<?> inline_traces) {
                        this.inline_traces = inline_traces;
                    }

                    public static class ReceiptBeanXX {
                        /**
                         * receiver : 1lwrb1wgr3o3
                         * act_digest : 31aa1262e337fd8b53936505ff8374b1cd918bc33c85af084acd8b8765e3f675
                         * global_sequence : 11549819
                         * recv_sequence : 69
                         * auth_sequence : [["1lwrb1wgr3o3",179]]
                         * code_sequence : 46
                         * abi_sequence : 1
                         */

                        private String receiver;
                        private String act_digest;
                        private int global_sequence;
                        private int recv_sequence;
                        private int code_sequence;
                        private int abi_sequence;
                        private List<List<String>> auth_sequence;

                        public String getReceiver() {
                            return receiver;
                        }

                        public void setReceiver(String receiver) {
                            this.receiver = receiver;
                        }

                        public String getAct_digest() {
                            return act_digest;
                        }

                        public void setAct_digest(String act_digest) {
                            this.act_digest = act_digest;
                        }

                        public int getGlobal_sequence() {
                            return global_sequence;
                        }

                        public void setGlobal_sequence(int global_sequence) {
                            this.global_sequence = global_sequence;
                        }

                        public int getRecv_sequence() {
                            return recv_sequence;
                        }

                        public void setRecv_sequence(int recv_sequence) {
                            this.recv_sequence = recv_sequence;
                        }

                        public int getCode_sequence() {
                            return code_sequence;
                        }

                        public void setCode_sequence(int code_sequence) {
                            this.code_sequence = code_sequence;
                        }

                        public int getAbi_sequence() {
                            return abi_sequence;
                        }

                        public void setAbi_sequence(int abi_sequence) {
                            this.abi_sequence = abi_sequence;
                        }

                        public List<List<String>> getAuth_sequence() {
                            return auth_sequence;
                        }

                        public void setAuth_sequence(List<List<String>> auth_sequence) {
                            this.auth_sequence = auth_sequence;
                        }
                    }

                    public static class ActBeanX {
                        /**
                         * account : token.aotc
                         * name : transfergas
                         * authorization : [{"actor":"1lwrb1wgr3o3","permission":"active"}]
                         * data : {"from":"1lwrb1wgr3o3","to":"kskommacxhxs","quantity":"1.0000 AOT","gas":"0.0060 AOT","memo":""}
                         * hex_data : 30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000
                         */

                        private String account;
                        private String name;
                        private DataBeanXX data;
                        private String hex_data;
                        private List<AuthorizationBeanXX> authorization;

                        public String getAccount() {
                            return account;
                        }

                        public void setAccount(String account) {
                            this.account = account;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public DataBeanXX getData() {
                            return data;
                        }

                        public void setData(DataBeanXX data) {
                            this.data = data;
                        }

                        public String getHex_data() {
                            return hex_data;
                        }

                        public void setHex_data(String hex_data) {
                            this.hex_data = hex_data;
                        }

                        public List<AuthorizationBeanXX> getAuthorization() {
                            return authorization;
                        }

                        public void setAuthorization(List<AuthorizationBeanXX> authorization) {
                            this.authorization = authorization;
                        }

                        public static class DataBeanXX {
                            /**
                             * from : 1lwrb1wgr3o3
                             * to : kskommacxhxs
                             * quantity : 1.0000 AOT
                             * gas : 0.0060 AOT
                             * memo :
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

                        public static class AuthorizationBeanXX {
                            /**
                             * actor : 1lwrb1wgr3o3
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
                }
            }
        }

        public static class ActionsBeanX {
            /**
             * authorization : [{"$ref":"$.data.raw.traces[0].act.authorization[0]"}]
             * data : {"$ref":"$.data.raw.traces[0].act.data"}
             * name : transfergas
             * hexData : 30e8b88c8773790c807bebc848492186102700000000000004414f54000000003c0000000000000004414f540000000000
             * account : token.aotc
             */

            private DataBeanXXX data;
            private String name;
            private String hexData;
            private String account;
            private List<AuthorizationBeanXXX> authorization;

            public DataBeanXXX getData() {
                return data;
            }

            public void setData(DataBeanXXX data) {
                this.data = data;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHexData() {
                return hexData;
            }

            public void setHexData(String hexData) {
                this.hexData = hexData;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public List<AuthorizationBeanXXX> getAuthorization() {
                return authorization;
            }

            public void setAuthorization(List<AuthorizationBeanXXX> authorization) {
                this.authorization = authorization;
            }

            public static class DataBeanXXX {
                /**
                 * $ref : $.data.raw.traces[0].act.data
                 */

                private String $ref;

                public String get$ref() {
                    return $ref;
                }

                public void set$ref(String $ref) {
                    this.$ref = $ref;
                }
            }

            public static class AuthorizationBeanXXX {
                /**
                 * $ref : $.data.raw.traces[0].act.authorization[0]
                 */

                private String $ref;

                public String get$ref() {
                    return $ref;
                }

                public void set$ref(String $ref) {
                    this.$ref = $ref;
                }
            }
        }
    }
}
