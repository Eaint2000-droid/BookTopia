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
@RequestMapping("/api/DailyForm")
public class DailyFormController {

    private DailyFormServiceImpl dailyFormServiceImpl;

    @Autowired
    public DailyFormController( DailyFormServiceImpl dailyFormServiceImpl){
        this.dailyFormServiceImpl=dailyFormServiceImpl;
    }


    @PostMapping("/create/")
    public void newDailyForm(@RequestBody DailyForm newDailyForm) throws EmailExistsException {

        System.out.println("CREATE");
        System.out.println("_______________");
        System.out.println(newDailyForm);
        System.out.println("_______________");
        dailyFormServiceImpl.addDailyForm(newDailyForm);

    }



    @GetMapping("/getAll")
    public List<DailyForm> getDailyForms() throws DailyFormNotFoundException {
        List<DailyForm> toReturn=dailyFormServiceImpl.getAllDailyForms();
        if(toReturn.size()==0){
            throw new DailyFormNotFoundException();
        }
        return toReturn;
    }


    @GetMapping("/get/{user}")
    public List<DailyForm> getDailyFormsByUser(User user) throws DailyFormNotFoundException {
        List<DailyForm> toReturn=dailyFormServiceImpl.getDailyFormByUser(user);
        if(toReturn.size()==0){
            throw new DailyFormNotFoundException();
        }
        return toReturn;
    }

    @GetMapping("/getByUserAndDate/{user}")
    public List<DailyForm> getDailyFormsByUserAndDate(User user, LocalDate dateTime) throws DailyFormNotFoundException {
        List<DailyForm> toReturn=dailyFormServiceImpl.getDailyFormByUserAndDate(user,dateTime);
        if(toReturn.size()==0){
            throw new DailyFormNotFoundException();
        }
        return toReturn;
    }


}
