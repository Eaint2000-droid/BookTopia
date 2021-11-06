package com.collab.g5.demo.dailyForm;

import com.collab.g5.demo.users.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class DailyFormServiceImpl implements DailyFormService{
    @Autowired
    private DailyFormRepository dailyFormRepository;
    public List<DailyForm> getAllDailyForms() {
        return  dailyFormRepository.findAll();
    }

    @Override
    public List<DailyForm> getDailyFormByUser(User user) {
        ArrayList<DailyForm> toReturn= new ArrayList<>();
        for(DailyForm dailyForm: dailyFormRepository.findAll()){
            if(dailyForm.getUser().equals(user)) {
                toReturn.add(dailyForm);
            }

        }
            return toReturn;
    }

    @Override
    public List<DailyForm> getDailyFormByUserAndDate(User user, LocalDate dateTime) {
        ArrayList<DailyForm> toReturn= new ArrayList<>();
        for(DailyForm dailyForm: dailyFormRepository.findAll()){
            if(dailyForm.getUser().equals(user) && dailyForm.getDateTime().equals(dateTime)) {
                toReturn.add(dailyForm);
            }

        }
        return toReturn;


    }

    @Override
    public void addDailyForm(DailyForm newDailyForm) {
        dailyFormRepository.save(newDailyForm);
    }
}
