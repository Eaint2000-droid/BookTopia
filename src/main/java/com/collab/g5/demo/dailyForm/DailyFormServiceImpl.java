package com.collab.g5.demo.dailyForm;

import com.collab.g5.demo.users.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
    public List<DailyForm> getDailyFormByUser(String email) {
        System.out.println("____"+ email+ "++++");

        ArrayList<DailyForm> toReturn= new ArrayList<>();
        for(DailyForm dailyForm: dailyFormRepository.findAll()){
//            System.out.println(dailyForm);
//            System.out.println(dailyForm.getUser().getEmail());
            if(dailyForm.getUser()!=null){
                System.out.println(dailyForm.getUser().getEmail());
                if(dailyForm.getUser().getEmail().equals(email)) {
                    System.out.println("TRUEEEE");
//                    System.out.println(dailyForm.getUser().getEmail());
                    toReturn.add(dailyForm);
                }
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
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = now.format(format);

        newDailyForm.setDateExactTime(formatDateTime);
        dailyFormRepository.save(newDailyForm);
    }

    @Override
    public List<DailyForm> getDailyFormByDate(LocalDate dateTime) {
        ArrayList<DailyForm> toReturn= new ArrayList<>();
        for(DailyForm dailyForm: dailyFormRepository.findAll()){
            if(dailyForm.getDateTime().equals(dateTime)) {
                toReturn.add(dailyForm);
            }

        }
        return toReturn;
    }

    @Override
    public int getNumDailyFormByDate(LocalDate dateTime) {
        int count=0;
        for(DailyForm dailyForm: dailyFormRepository.findAll()){
            if(dailyForm.getDateTime().equals(dateTime)) {
               count+=1;
            }

        }
        return count;
    }

    @Override
    public int getUniqueNumDailyFormByDate(LocalDate date) {
        HashSet<User> added= new HashSet<>();
        int count=0;
        for(DailyForm dailyForm: dailyFormRepository.findAll()){

            if(dailyForm.getDateTime().equals(date)) {
                if(!added.contains(dailyForm.getUser())){
                    added.add(dailyForm.getUser());
                    count++;
                }
            }

        }
        return count;
    }

    @Override
    public int[] getUniqueNumDailyFormByWeek(LocalDate date) {
        LocalDate startDate= date.minusDays(6);

        int[] toReturn =new int[7];
        int x=0;
        System.out.println(startDate);

        for(int i=0;i<7;i++){
            System.out.println(startDate.plusDays(i));
            toReturn[x++]=getUniqueNumDailyFormByDate(startDate.plusDays(i));

        }

        return toReturn;
    }
}
