package com.example.client.controller;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.example.api.FabricApi;
import com.example.api.domain.AssetHistory;
import com.example.api.domain.AssetUserInfo;
import com.example.api.domain.LedgerVo;
import com.example.api.domain.TransactionInfo;
import com.example.core.domain.R;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeoutException;
/**
 * @program: TimeBank
 * @ClassName: TimeCoinController
 * @description:
 * @author: kai
 * @create: 2024-03-13 21:02
 */
@RestController
@SaIgnore
@RequestMapping("/timeCoin")
public class TimeCoinController {
    @Autowired
    private FabricApi fabricApi;
    /**
     * @Description:转赠
     */
    @PostMapping("/transfer")
    public R<AssetUserInfo> TransferCoin(@RequestParam Integer transferValue,@RequestParam Long fromUserId) throws InterruptedException, TimeoutException, ContractException {
        if(transferValue.equals(0)&&transferValue<0)
            return R.fail("转赠值不可为零");
        AssetUserInfo assetUserInfo = fabricApi.transferAsset((Long) StpUtil.getLoginId(), fromUserId, transferValue);
        //TODO 转赠对象可能有通知

        return R.ok(assetUserInfo);
    }
    /**
     * @Description:历史交易记录
     */

    @GetMapping("/getHistoryInfo")
    public R<List<AssetHistory>> getAssetHistoryInfo(@RequestParam Long userId) throws InterruptedException, TimeoutException, ContractException {
        List<AssetHistory> assetHistories = fabricApi.getAssetHistory(userId);
        return R.ok(assetHistories);
    }
    /**
     * @Description:个人资产
     */

    @GetMapping("/getAssetInfo")
    public R<AssetUserInfo> getAssetInfo(@RequestParam Long userId) throws InterruptedException, TimeoutException, ContractException {
        AssetUserInfo assetUserInfo = fabricApi.queryAsset(userId);
        return R.ok(assetUserInfo);
    }
    /**
     * @Description:区块溯源查询
     */

    @GetMapping("/getTransactionInfo")
    public R<TransactionInfo> getTransactionInfo(@RequestParam String transactionId) throws InvalidArgumentException, ProposalException {
        TransactionInfo transactionInfo = fabricApi.getTransactionInfo(transactionId);
        return R.ok(transactionInfo);
    }




}
