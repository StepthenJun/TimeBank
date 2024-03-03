package com.example.client.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.api.util.ConvertUtil;
import com.example.client.domain.dto.UpdateAssetDto;
import com.example.client.domain.vo.LedgerVo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.math3.fitting.leastsquares.EvaluationRmsChecker;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.sdk.BlockchainInfo;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * @program: TimeBank
 * @ClassName: LedgerController
 * @description: 区块链接口
 * @author: kai
 * @create: 2024-03-02 11:50
 */
@RestController
@RequestMapping("/demo")
@Slf4j
@SaIgnore
@AllArgsConstructor
public class LedgerController {

   Gateway gateway;
    @GetMapping("/init")
    public JSON init() throws ContractException, TimeoutException, InterruptedException {
        Contract contract = getContract();
        byte[] asset = contract.submitTransaction("InitLedger");
        JSON result= JSONObject.parseObject(StringUtils.newStringUtf8(asset));
        return result;
    }
    @GetMapping("/create/{id}")
    public JSON createAsset(@PathVariable("id") String assetId) throws InterruptedException, TimeoutException, ContractException {
        Contract contract = getContract();
        byte[] asset = contract.submitTransaction("CreateAsset",String.valueOf(10));
        JSON result=JSONObject.parseObject(StringUtils.newStringUtf8(asset));
        return result;
    }
    @GetMapping("/getAll")
    public JSONArray getAllAssets() throws ContractException, TimeoutException, InterruptedException {
        Contract contract = getContract();
        byte[] asset = contract.evaluateTransaction("GetAssetHistory","test");
        JSONArray result = JSONArray.parseArray(StringUtils.newStringUtf8(asset));
        return result;
    }
    @GetMapping("/query/{id}")
    public JSON queryAsset(@PathVariable("id") String assetId) throws ContractException, TimeoutException, InterruptedException, InvalidArgumentException, ProposalException, NoSuchAlgorithmException {
        Contract contract = getContract();
        byte[] asset = contract.evaluateTransaction("ReadAsset",assetId);
        BlockchainInfo blockchainInfo = getNetwork().getChannel().queryBlockchainInfo();
        JSON result=JSONObject.parseObject(StringUtils.newStringUtf8(asset));
        return result;
    }
    @PutMapping("/update")
    public JSON updateAsset(@RequestBody UpdateAssetDto dto) throws ContractException, TimeoutException, InterruptedException{
        Contract contract = getContract();
        byte[] asset = contract.submitTransaction("UpdateAsset",dto.getAssetId(),dto.getFromAssetId(),String.valueOf(dto.getUpdateValue()));
        JSON result=JSONObject.parseObject(StringUtils.newStringUtf8(asset));
        return result;
    }
    @DeleteMapping("/delete/{id}")
    public JSON deleteAsset(@PathVariable("id") String assetId) throws InterruptedException, TimeoutException, ContractException {
        Contract contract = getContract();
        byte[] asset = contract.submitTransaction("DeleteAsset",assetId);
        JSON result=JSONObject.parseObject(StringUtils.newStringUtf8(asset));
        return result;
    }
    @GetMapping("/getLedgerInfo")
    public LedgerVo getLedgerInfo() throws InvalidArgumentException, ProposalException, ContractException {
        BlockchainInfo blockchainInfo = getNetwork().getChannel().queryBlockchainInfo();
        Contract contract=getContract();
        byte[] asset = contract.evaluateTransaction("getTransactionCount");
        int count=ConvertUtil.byteToInt(asset[0]);
        LedgerVo ledgerVo = new LedgerVo(blockchainInfo.getHeight(), count, ConvertUtil.byteToArray(blockchainInfo.getPreviousBlockHash()), ConvertUtil.byteToArray(blockchainInfo.getCurrentBlockHash()));
        return ledgerVo;
    }

    private Network getNetwork(){
        return gateway.getNetwork("mychannel");
    }
    private  Contract getContract(){
        return getNetwork().getContract("basic");
    }

}
