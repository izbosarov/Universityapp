package com.example.universityapp.service;

import com.example.universityapp.entity.Address;
import com.example.universityapp.entity.University;
import com.example.universityapp.payload.ApiResponse;
import com.example.universityapp.payload.UniversityDTO;
import com.example.universityapp.repository.AddressRepository;
import com.example.universityapp.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;

    public ApiResponse addUniver(UniversityDTO universityDTO) {
        Address address = new Address();
        address.setCity(universityDTO.getCity());
        address.setStreet(universityDTO.getStreet());
        address.setHome(universityDTO.getHome());

        Address save = addressRepository.save(address);

        University university = new University();
        university.setName(universityDTO.getName());
        university.setAddress(save);

        universityRepository.save(university);
        return new ApiResponse("Saved", true, university);
    }

    public List<University> getAll() {
        return universityRepository.findAll();
    }

    public ApiResponse getOneById(int id) {

        //1-usul
//        Optional<University> byId = universityRepository.findById(id);
//        if (byId.isPresent()) {
//            University university = byId.get();
//            return new Result("Ok", true, university);
//        } else {
//            return new Result("NOT found!", false, null);
//        }

        //2-usul
        Optional<University> byId = universityRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("NOT found!", false, null);
        return new ApiResponse("Ok", true, byId.get());
    }

    public ApiResponse edit(int id, UniversityDTO universityDTO) {
        Optional<University> byId = universityRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("NOT found!", false, null);

        University university = byId.get();
        if (universityDTO.getName() != null) {
            university.setName(universityDTO.getName());
        }

        Optional<Address> optionalAddress = addressRepository.findById(byId.get().getAddress().getId());

        if (!optionalAddress.isPresent()) return new ApiResponse("Xato address", false);
        if (universityDTO.getCity() != null) optionalAddress.get().setCity(universityDTO.getCity());
        if (universityDTO.getStreet() != null) optionalAddress.get().setStreet(universityDTO.getStreet());
        if (universityDTO.getHome() != null) optionalAddress.get().setHome(universityDTO.getHome());

        if (universityDTO.getCity() != null && universityDTO.getStreet() != null && universityDTO.getHome() != null) {
            Address save = addressRepository.save(optionalAddress.get());
            university.setAddress(save);
        }
        universityRepository.save(university);
        return new ApiResponse("Updated!", true, null);
    }

    public ApiResponse deleteUniver(int id) {

        Optional<University> byId = universityRepository.findById(id);

        if (!byId.isPresent()) return new ApiResponse("Xatolik", false);

        universityRepository.deleteById(id);
        addressRepository.deleteById(byId.get().getAddress().getId());
        return new ApiResponse("Deleted!", true);
    }
}
