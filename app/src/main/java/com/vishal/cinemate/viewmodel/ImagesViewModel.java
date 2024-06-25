package com.vishal.cinemate.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vishal.cinemate.model.Backdrops;
import com.vishal.cinemate.repositories.ImagesRepository;

public class ImagesViewModel extends AndroidViewModel {

    private ImagesRepository repository;

    public ImagesViewModel(@NonNull Application application) {
        super(application);
        repository = ImagesRepository.getInstance();
    }

    //==Start of view model get trending
    private MutableLiveData<Backdrops> resultGetImages = new MutableLiveData<>();
    public void getImages(String movieId) {
        resultGetImages = repository.getImagesData(movieId);
    }
    public LiveData<Backdrops> getResultGetTrending() {
        return resultGetImages;
    }
    //==End of view model get trending
}
