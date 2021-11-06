package com.collab.g5.demo.dailyForm;

import com.collab.g5.demo.exceptions.users.EmailExistsException;
import com.collab.g5.demo.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/dailyForm")
public class DailyFormController {

    private DailyFormServiceImpl dailyFormServiceImpl;

    @Autowired
    public DailyFormController( DailyFormServiceImpl dailyFormServiceImpl){
        this.dailyFormServiceImpl=dailyFormServiceImpl;
    }


    @PostMapping("/new")
    public void newDailyForm(@RequestBody DailyForm newDailyForm) throws EmailExistsException {

        System.out.println("CREATE");
        System.out.println("_______________");
        System.out.println(newDailyForm);
        System.out.println("_______________");
        dailyFormServiceImpl.addDailyForm(newDailyForm);

    }



    @GetMapping("/dailyforms")
    public List<DailyForm> getAllDailyForms() throws DailyFormNotFoundException {
        List<DailyForm> toReturn=dailyFormServiceImpl.getAllDailyForms();
        if(toReturn.size()==0){
            throw new DailyFormNotFoundException();
        }
        return toReturn;
    }


    @GetMapping("/user/{email}")
    public List<DailyForm> getDailyFormsByUser(@PathVariable String email) throws DailyFormNotFoundException {
        List<DailyForm> toReturn=dailyFormServiceImpl.getDailyFormByUser(email);
        if(toReturn.size()==0){
            throw new DailyFormNotFoundException();
        }
        return toReturn;
    }

    @GetMapping("/useranddate/{user}")
    public List<DailyForm> getDailyFormsByUserAndDate(@PathVariable User user, @PathVariable LocalDate dateTime) throws DailyFormNotFoundException {
        List<DailyForm> toReturn=dailyFormServiceImpl.getDailyFormByUserAndDate(user,dateTime);
        if(toReturn.size()==0){
            throw new DailyFormNotFoundException();
        }
        return toReturn;
    }


}
