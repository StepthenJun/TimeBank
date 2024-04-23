package com.example.controller;
import cn.dev33.satoken.annotation.SaIgnore;
import com.example.api.FabricApi;
import com.example.api.domain.LedgerVo;
import com.example.core.domain.R;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Objects;
/**
 * @program: TimeBank
 * @ClassName: LedgerController
 * @description:
 * @author: kai
 * @create: 2024-03-14 19:35
 */
@RequestMapping("/ledger")
@RestController
@SaIgnore
public class LedgerController {
    @Autowired
    private FabricApi fabricApi;
    @GetMapping("getLedgerInfo")
    public R<LedgerVo> getLedgerInfo() throws ProposalException, ContractException, InvalidArgumentException {
        LedgerVo ledgerInfo = fabricApi.getLedgerInfo();
        if (Objects.isNull(ledgerInfo))
            return R.fail("区块链信息获取失败");
        return R.ok(ledgerInfo);
    }
}


