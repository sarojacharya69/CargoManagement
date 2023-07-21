package net.ims.service;

import net.ims.entity.Cargo;

import java.util.List;

public interface CargoServiceDAO {
     void saveCargo(Cargo s);
     List<Cargo> getAllCargo();
     Cargo getCargoById(int id);
     void deleteById(int id);


}
