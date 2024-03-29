package com.bits.epm.service.impl;

import com.bits.epm.data.dto.CoindeskDTO;
import com.bits.epm.service.CoindeskService;
import com.bits.epm.utils.feign_client.CoindeskClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service("CoindeskService")
@AllArgsConstructor
@Slf4j
public class CoindeskServiceImpl implements CoindeskService {


    private final CoindeskClient client;

    @Override
    public CoindeskDTO getData(){

        try {
//            log.error("coindesk "+data);
            return client.getData();
        }catch (Exception ignored){}

        return null;
    }
}
