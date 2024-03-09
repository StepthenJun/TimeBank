package com.example.api;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.api.domain.*;
import com.example.api.util.ConvertUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.binary.StringUtils;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.sdk.BlockInfo;
import org.hyperledger.fabric.sdk.BlockchainInfo;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @program: TimeBank
 * @ClassName: FabricApi
 * @description:
 * @author: kai
 * @create: 2024-03-08 19:07
 */
@Component
@AllArgsConstructor
public class FabricApi {
    @Autowired
    final Gateway gateway;
    private  Network getNetwork(){ return gateway.getNetwork("mychannel"); }
    private  Contract getContract(){
        return getNetwork().getContract("basic");
    }
    /**
     * @Description: 重置（初始化）区块链
     * @param
     */
    public boolean init() throws ContractException, TimeoutException, InterruptedException {
        Contract contract = getContract();
        byte[] asset = contract.submitTransaction("InitLedger");
        return true;
    }
    /**
     * @Description: 创建资产，注册时即可调用
     * @param userId
     * @return: com.example.api.domain.AssetUserInfo
     */
    public AssetUserInfo createAsset(Long userId) throws InterruptedException, TimeoutException, ContractException {
        Contract getContract=getContract();
        byte[] data = getContract.submitTransaction("CreateAsset",String.valueOf(userId),String.valueOf(10));
        AssetUserInfo asset= JSONObject.parseObject(StringUtils.newStringUtf8(data), AssetUserInfo.class);
        return asset;
    }
    /**
     * @Description: 用户查询个人资产
     * @param userId
     * @return: com.example.api.domain.AssetUserInfo
     */
    public AssetUserInfo queryAsset(Long userId) throws InterruptedException, TimeoutException, ContractException {
        Contract getContract=getContract();
        byte[] data = getContract.submitTransaction("ReadAsset",String.valueOf(userId));
        AssetUserInfo asset= JSONObject.parseObject(StringUtils.newStringUtf8(data), AssetUserInfo.class);
        return asset;
    }
    /**
     * @Description: 用户进行资产的更新（获得资产/花费资产），返回更新后的Asset对象
     * @param dto
     * @return: com.example.api.domain.AssetUserInfo
     */
    public AssetUserInfo updateAsset(UpdateAssetDto dto) throws InterruptedException, TimeoutException, ContractException {
        Contract getContract=getContract();
        byte[] data = getContract.submitTransaction("UpdateAsset",dto.getAssetId(),dto.getFromAssetId(),String.valueOf(dto.getUpdateValue()));
        AssetUserInfo asset= JSONObject.parseObject(StringUtils.newStringUtf8(data), AssetUserInfo.class);
        return asset;
    }
    /**
     * @Description: 删除用户的所有资产
     * @param userId
     * @return: boolean
     */
    public  boolean deleteAsset(Long userId) throws InterruptedException, TimeoutException, ContractException {
        Contract getContract=getContract();
        byte[] data = getContract.submitTransaction("DeleteAsset",String.valueOf(userId));
        return true;
    }
    /**
     * @Description: 查询区块链相关参数
     * @param
     * @return: com.example.api.domain.LedgerVo
     */
    public LedgerVo getLedgerInfo() throws InvalidArgumentException, ProposalException, ContractException {
        BlockchainInfo blockchainInfo = getNetwork().getChannel().queryBlockchainInfo();
        Contract contract=getContract();
        byte[] data = contract.evaluateTransaction("getTransactionCount");
        int count= ConvertUtil.byteToInt(data[0]);
        LedgerVo ledgerVo = new LedgerVo(blockchainInfo.getHeight(), count, ConvertUtil.byteToArray(blockchainInfo.getPreviousBlockHash()), ConvertUtil.byteToArray(blockchainInfo.getCurrentBlockHash()));
        return ledgerVo;
    }
    /**
     * @Description:用户查询某交易在区块链上的信息（区块号，和前一区块哈希）
     * @param transactionId
     * @return: java.lang.String
     */
    public TransactionInfo getTransactionInfo(String transactionId) throws ProposalException, InvalidArgumentException {
        BlockInfo blockInfo=getNetwork().getChannel().queryBlockByTransactionID(transactionId);
        return  new TransactionInfo(String.valueOf(blockInfo.getBlockNumber()),ConvertUtil.byteToArray(blockInfo.getPreviousHash()));
    }
    /**
     * @Description: 用户查询个人交易记录
     * @param userId
     * @return: java.util.List<com.example.api.domain.AssetHistory>
     */
    public List<AssetHistory> getAssetHistory(Long userId) throws ContractException, TimeoutException, InterruptedException {
        Contract contract = getContract();
        byte[] data = contract.evaluateTransaction("GetAssetHistory",String.valueOf(userId));
        List<AssetHistory> result = JSONArray.parseArray(StringUtils.newStringUtf8(data), AssetHistory.class);
        return result;
    }

}
