package car_showroom_service.org;

import java.util.ArrayList;

import car_showroom_model.org.CarMasterModel;
import car_showroom_repository.org.CarMasterRepository;

public class CarMasterService {
	CarMasterRepository cMRepo= new CarMasterRepository();
	public boolean addCarData(CarMasterModel cMModel) {
		// TODO Auto-generated method stub
		return cMRepo.addCarData(cMModel);
	}
	public ArrayList<CarMasterModel> getAllCars() {
		// TODO Auto-generated method stub
		return cMRepo.getAllCars();
	}
	public long getShowCarPriceById(int carId) {
		// TODO Auto-generated method stub
		return cMRepo.getShowCarPriceById(carId);
	}
	public String getShowCarNameById(int carId) {
		// TODO Auto-generated method stub
		return cMRepo.getShowCarNameById(carId);
	}

}
