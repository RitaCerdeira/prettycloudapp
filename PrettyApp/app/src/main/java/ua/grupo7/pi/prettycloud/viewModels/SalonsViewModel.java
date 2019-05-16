package ua.grupo7.pi.prettycloud.viewModels;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ua.grupo7.pi.prettycloud.communication.PrettyCloudWebService;
import ua.grupo7.pi.prettycloud.models.Salon;


public class SalonsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<Salon>> salonList;
    private PrettyCloudWebService webService;
    public LiveData<List<Salon>> getSalons() {
        if (salonList == null){
            salonList = new MutableLiveData<>();
            loadSalons();
        }
        return salonList;
    }

    private void loadSalons() {
        salonList = (MutableLiveData<List<Salon>>) webService.getSalons();
    }
}
