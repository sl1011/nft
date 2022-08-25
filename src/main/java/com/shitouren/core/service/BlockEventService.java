package com.shitouren.core.service;


import com.alibaba.fastjson.JSON;
import com.shitouren.core.config.ConfigCache;
import com.shitouren.core.contract.Authority;
import com.shitouren.core.contract.Charge;
import com.shitouren.core.contract.DDC1155;
import com.shitouren.core.contract.DDC721;
import com.shitouren.core.dto.BlockEventBean;
import com.shitouren.core.exception.DDCException;
import com.shitouren.core.utils.Web3jUtils;
import lombok.extern.slf4j.Slf4j;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Strings;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kuan
 * Created on 21/12/11.
 */
@Slf4j
public class BlockEventService extends BaseService {

    /**
     * 获取区块事件并解析
     * 1. 根据块高获取区块信息
     * 2. 根据块中交易获取交易回执
     * 3. 遍历交易回执中的事件并解析
     *
     * @param blockNumber blockNumber
     * @return BlockEventBean
     * @throws IOException IOException
     */
    public BlockEventBean getBlockEvent(BigInteger blockNumber) throws IOException, InterruptedException {
        ArrayList<BaseEventResponse> arrayList = new ArrayList<>();
        // 1. 获取区块信息
        EthBlock.Block blockInfo = getBlockByNumber(blockNumber);

        if (blockInfo == null) {
            throw new DDCException(400, "cannot get blockInfo by blockNumber:" + blockNumber);
        }

        List<EthBlock.TransactionResult> txs = blockInfo.getTransactions();

        // 2. 获取交易
        if (txs != null) {
            txs.forEach(tx -> {
                EthBlock.TransactionObject transaction = (EthBlock.TransactionObject) tx.get();
                String hash = transaction.get().getHash();
                ArrayList<BaseEventResponse> arr = null;
                boolean ok = true;
                int i = 0;
                while (ok) {
                    if (i >= 3) {
                        throw new DDCException(400, "cannot get receipt by hash:" + hash);
                    }
                    try {
                        arr = analyzeEventsByTxHash(hash);
                    } catch (Exception e) {
                        i++;
                        continue;
                    }
                    ok = false;
                }
                arrayList.addAll(arr);
            });
        }

        log.info("块高 {} 解析到区块事件 {}", blockNumber, JSON.toJSONString(arrayList));
        return new BlockEventBean(arrayList, blockInfo.getTimestamp().toString());
    }

    public ArrayList<BaseEventResponse> analyzeEventsByTxHash(String hash) throws Exception {
        ArrayList<BaseEventResponse> result = new ArrayList<>();
        //hash获取receipt
        TransactionReceipt receipt = getTransReceipt(hash);
        for (int i = 0; i < receipt.getLogs().size(); i++) {
            List<BaseEventResponse> list = new ArrayList<>();
            Log log = receipt.getLogs().get(i);
            if (Strings.isEmpty(log.getAddress())) {
                continue;
            }
            if (ConfigCache.get().getAuthorityLogicAddress().equalsIgnoreCase(log.getAddress())) {
                Authority authority = Web3jUtils.getAuthority();
                if (log.getTopics().get(0).equals(EventEncoder.encode(Authority.ADDACCOUNT_EVENT))) {
                    list.addAll(authority.getAddAccountEvents(receipt));
                } else if (log.getTopics().get(0).equals(EventEncoder.encode(Authority.UPDATEACCOUNTSTATE_EVENT))) {
                    list.addAll(authority.getUpdateAccountStateEvents(receipt));
                } else if (log.getTopics().get(0).equals(EventEncoder.encode(Authority.CROSSPLATFORMAPPROVAL_EVENT))) {
                    list.addAll(authority.getAdminChangedEvents(receipt));
                }
            } else if (ConfigCache.get().getChargeLogicAddress().equalsIgnoreCase(log.getAddress())) {
                Charge charge = Web3jUtils.getCharge();
                if (log.getTopics().get(0).equals(EventEncoder.encode(Charge.RECHARGE_EVENT))) {
                    list.addAll(charge.getRechargeEvents(receipt));
                } else if (log.getTopics().get(0).equals(EventEncoder.encode(Charge.SETFEE_EVENT))) {
                    list.addAll(charge.getSetFeeEvents(receipt));
                } else if (log.getTopics().get(0).equals(EventEncoder.encode(Charge.PAY_EVENT))) {
                    list.addAll(charge.getPayEvents(receipt));
                } else if (log.getTopics().get(0).equals(EventEncoder.encode(Charge.DELDDC_EVENT))) {
                    list.addAll(charge.getDelDDCEvents(receipt));
                } else if (log.getTopics().get(0).equals(EventEncoder.encode(Charge.DELFEE_EVENT))) {
                    list.addAll(charge.getDelFeeEvents(receipt));
                }
            } else if (ConfigCache.get().getDdc721Address().equalsIgnoreCase(log.getAddress())) {
                DDC721 ddc721 = Web3jUtils.getDDC721();
                if (log.getTopics().get(0).equals(EventEncoder.encode(DDC721.TRANSFER_EVENT))) {
                    list.addAll(ddc721.getTransferEvents(receipt));
                } else if (log.getTopics().get(0).equals(EventEncoder.encode(DDC721.ENTERBLACKLIST_EVENT))) {
                    list.addAll(ddc721.getEnterBlacklistEvents(receipt));
                } else if (log.getTopics().get(0).equals(EventEncoder.encode(DDC721.EXITBLACKLIST_EVENT))) {
                    list.addAll(ddc721.getExitBlacklistEvents(receipt));
                } else if (log.getTopics().get(0).equals(EventEncoder.encode(DDC721.SETURI_EVENT))) {
                    list.addAll(ddc721.getSetURIEvents(receipt));
                }
            } else if (ConfigCache.get().getDdc1155Address().equalsIgnoreCase(log.getAddress())) {
                DDC1155 ddc1155 = Web3jUtils.getDDC1155();
                if (log.getTopics().get(0).equals(EventEncoder.encode(DDC1155.TRANSFERBATCH_EVENT))) {
                    list.addAll(ddc1155.getTransferBatchEvents(receipt));
                } else if (log.getTopics().get(0).equals(EventEncoder.encode(DDC1155.TRANSFERSINGLE_EVENT))) {
                    list.addAll(ddc1155.getTransferSingleEvents(receipt));
                } else if (log.getTopics().get(0).equals(EventEncoder.encode(DDC1155.ENTERBLACKLIST_EVENT))) {
                    list.addAll(ddc1155.getEnterBlacklistEvents(receipt));
                } else if (log.getTopics().get(0).equals(EventEncoder.encode(DDC1155.EXITBLACKLIST_EVENT))) {
                    list.addAll(ddc1155.getExitBlacklistEvents(receipt));
                } else if (log.getTopics().get(0).equals(EventEncoder.encode(DDC1155.SETURI_EVENT))) {
                    list.addAll(ddc1155.getSetURIEvents(receipt));
                }
            }
            result.addAll(list);

        }
        return result;
    }
}
