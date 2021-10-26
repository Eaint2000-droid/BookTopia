package dailyForm;

import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.companies.CompanyServiceImpl;
import com.collab.g5.demo.exceptions.companies.CompaniesNotFoundException;
import com.collab.g5.demo.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/get/{user}")
    public List<DailyForm> getDailyFormsByUserAndDate(User user, LocalDate dateTime) throws DailyFormNotFoundException {
        List<DailyForm> toReturn=dailyFormServiceImpl.getDailyFormByUserAndDate(user,dateTime);
        if(toReturn.size()==0){
            throw new DailyFormNotFoundException();
        }
        return toReturn;
    }


}
