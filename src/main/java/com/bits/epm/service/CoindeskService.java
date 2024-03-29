package com.bits.epm.service;

import com.bits.epm.data.dto.CoindeskDTO;
import com.bits.epm.utils.feign_client.CoindeskClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class CoindeskService {


    private final CoindeskClient client;

    public CoindeskDTO getData(){

        try {
//            log.error("coindesk "+data);
            return client.getData();
        }catch (Exception ignored){}

        return null;
    }
}
