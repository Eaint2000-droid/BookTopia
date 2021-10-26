package dailyForm;

import com.collab.g5.demo.users.User;

import java.time.LocalDate;
import java.util.List;

public interface DailyFormService {
    public List<DailyForm> getAllDailyForms();
    public List<DailyForm> getDailyFormByUser(User user);
    public List<DailyForm> getDailyFormByUserAndDate(User user, LocalDate dateTime);
}
