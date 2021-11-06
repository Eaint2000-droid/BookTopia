package com.collab.g5.demo.dailyForm;

import com.collab.g5.demo.users.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


public interface DailyFormService {
    public List<DailyForm> getAllDailyForms();
    public List<DailyForm> getDailyFormByUser(String useremail);
    public List<DailyForm> getDailyFormByUserAndDate(User user, LocalDate dateTime);
    public void addDailyForm(DailyForm dailyForm);
}
