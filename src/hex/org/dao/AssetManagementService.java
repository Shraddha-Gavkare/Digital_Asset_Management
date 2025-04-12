package hex.org.dao;

import hex.org.entity.Asset;
import hex.org.exception.AssetNotFoundException;
import hex.org.exception.AssetNotMaintainException;

public interface AssetManagementService {
    boolean addAsset(Asset asset);
    boolean updateAsset(Asset asset);
    boolean deleteAsset(int assetId);
    boolean allocateAsset(int assetId, int employeeId, String allocationDate) throws AssetNotFoundException;
    boolean deallocateAsset(int assetId, int employeeId, String returnDate) throws AssetNotFoundException;
    boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) throws AssetNotMaintainException;
    boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate);
    boolean withdrawReservation(int reservationId);
    boolean addAssetToMaintenance(int assetId);
    boolean reserveAsset(int assetId, int empId);
}
