package com.sz.apollo.ui.models;

import java.util.List;

public class RecordTxHashBean {
    /**
     * receipt : {"status":"executed","cpu_usage_us":442,"net_usage_words":20,"trx":[1,{"signatures":["SIG_K1_Ker7aB7fHqC2vDKFFGjCKCmrd1FZVeZCQSM7xV3hF1fKykuBW3dktMzZugY4hGddCmvQtaQY9ot1oNGwgkSSeTJgExeVrk"],"compression":"none","packed_context_free_data":"","packed_trx":"e1d13c5f7c57e7d2d3e5000000000100a6823403ea305500b061572d3ccdcd01a00c929cd2320e3900000000a8ed32323da00c929cd2320e39d0dc70f63f45fcf5409c000000000000044e424f00000000409c000000000000044e424f000000000ce6bf80e6b4bbe79fbfe69cba00"}]}
     * trx : {"expiration":"2020-08-19T07:16:49","ref_block_num":22396,"ref_block_prefix":3855864551,"max_net_usage_words":0,"max_cpu_usage_ms":0,"delay_sec":0,"context_free_actions":[],"actions":[{"account":"eosio.token","name":"transfergas","authorization":[{"actor":"b4b35oowmcae","permission":"active"}],"data":{"from":"b4b35oowmcae","to":"yry4ejzqi3ih","quantity":"4.0000 NBO","gas":"4.0000 NBO","memo":"激活矿机"},"hex_data":"a00c929cd2320e39d0dc70f63f45fcf5409c000000000000044e424f00000000409c000000000000044e424f000000000ce6bf80e6b4bbe79fbfe69cba"}],"transaction_extensions":[],"signatures":["SIG_K1_Ker7aB7fHqC2vDKFFGjCKCmrd1FZVeZCQSM7xV3hF1fKykuBW3dktMzZugY4hGddCmvQtaQY9ot1oNGwgkSSeTJgExeVrk"],"context_free_data":[]}
     */

    private ReceiptBean receipt;
    private TrxBean trx;

    public RecordTxHashBean() {
    }

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
         * cpu_usage_us : 442
         * net_usage_words : 20
         * trx : [1,{"signatures":["SIG_K1_Ker7aB7fHqC2vDKFFGjCKCmrd1FZVeZCQSM7xV3hF1fKykuBW3dktMzZugY4hGddCmvQtaQY9ot1oNGwgkSSeTJgExeVrk"],"compression":"none","packed_context_free_data":"","packed_trx":"e1d13c5f7c57e7d2d3e5000000000100a6823403ea305500b061572d3ccdcd01a00c929cd2320e3900000000a8ed32323da00c929cd2320e39d0dc70f63f45fcf5409c000000000000044e424f00000000409c000000000000044e424f000000000ce6bf80e6b4bbe79fbfe69cba00"}]
         */

        private String status;
        private int cpu_usage_us;
        private int net_usage_words;
        private List<Object> trx;

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

        public List<Object> getTrx() {
            return trx;
        }

        public void setTrx(List<Object> trx) {
            this.trx = trx;
        }
    }

    public static class TrxBean {
        /**
         * expiration : 2020-08-19T07:16:49
         * ref_block_num : 22396
         * ref_block_prefix : 3855864551
         * max_net_usage_words : 0
         * max_cpu_usage_ms : 0
         * delay_sec : 0
         * context_free_actions : []
         * actions : [{"account":"eosio.token","name":"transfergas","authorization":[{"actor":"b4b35oowmcae","permission":"active"}],"data":{"from":"b4b35oowmcae","to":"yry4ejzqi3ih","quantity":"4.0000 NBO","gas":"4.0000 NBO","memo":"激活矿机"},"hex_data":"a00c929cd2320e39d0dc70f63f45fcf5409c000000000000044e424f00000000409c000000000000044e424f000000000ce6bf80e6b4bbe79fbfe69cba"}]
         * transaction_extensions : []
         * signatures : ["SIG_K1_Ker7aB7fHqC2vDKFFGjCKCmrd1FZVeZCQSM7xV3hF1fKykuBW3dktMzZugY4hGddCmvQtaQY9ot1oNGwgkSSeTJgExeVrk"]
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
             * account : eosio.token
             * name : transfergas
             * authorization : [{"actor":"b4b35oowmcae","permission":"active"}]
             * data : {"from":"b4b35oowmcae","to":"yry4ejzqi3ih","quantity":"4.0000 NBO","gas":"4.0000 NBO","memo":"激活矿机"}
             * hex_data : a00c929cd2320e39d0dc70f63f45fcf5409c000000000000044e424f00000000409c000000000000044e424f000000000ce6bf80e6b4bbe79fbfe69cba
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
                 * from : b4b35oowmcae
                 * to : yry4ejzqi3ih
                 * quantity : 4.0000 NBO
                 * gas : 4.0000 NBO
                 * memo : 激活矿机
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
                 * actor : b4b35oowmcae
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
