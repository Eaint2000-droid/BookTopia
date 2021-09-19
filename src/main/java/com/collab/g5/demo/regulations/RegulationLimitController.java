package com.collab.g5.demo.regulations;

import com.collab.g5.demo.exceptions.users.UserNotFoundException;
import com.collab.g5.demo.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/regulationlimit")
public class RegulationLimitController {
    @Autowired
    RegulationLimitService regulationLimitService = new RegulationLimitServiceImpl();

    @GetMapping("/")
    public List<RegulationLimit> getRegulationLimit() {
        return regulationLimitService.getAllRegulationLimit();
    }





    @GetMapping("/get/{regulationLimit}")
    public RegulationLimit getRegulationLimitById(@PathVariable RegulationLimit toadd ){

        RegulationLimit regulationLimit= regulationLimitService.getRegulationLimitById(toadd.getRegulation().getStartDate(), toadd.getCompany().getCid());

        if(regulationLimit==null){
            // throw an exception
            throw new regulationLimitNotFoundException();
        }
        return regulationLimit;
    }


    @DeleteMapping("/del/{regulationLimit}")
    void deleteRegulationLimit(@PathVariable RegulationLimit toadd ){
        RegulationLimit regulationLimit= regulationLimitService.getRegulationLimitById(toadd.getRegulation().getStartDate(), toadd.getCompany().getCid());
        if(regulationLimit==null){
            // throw an exception
            throw new regulationLimitNotFoundException();
        }
        regulationLimitService.delete(regulationLimit);
    }




}
