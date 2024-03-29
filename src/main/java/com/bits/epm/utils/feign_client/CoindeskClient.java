package com.bits.epm.utils.feign_client;

import com.bits.epm.data.dto.CoindeskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Coindesk", url = "https://api.coindesk.com/v1/")
public interface CoindeskClient {

    @GetMapping( value = "/bpi/currentprice.json")
    CoindeskDTO getData();

}
