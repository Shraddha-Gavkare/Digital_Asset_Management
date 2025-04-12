package hex.org.dao;

import hex.org.entity.Asset;
import hex.org.exception.AssetNotFoundException;
import hex.org.exception.AssetNotMaintainException;
import hex.org.util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AssetManagementServiceImpl implements AssetManagementService {

    @Override
    public boolean addAsset(Asset asset) {
        String query = "INSERT INTO assets (name, type, serial_number, purchase_date, location, status, owner_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, asset.getName());
            ps.setString(2, asset.getType());
            ps.setString(3, asset.getSerialNumber());
            ps.setString(4, asset.getPurchaseDate());
            ps.setString(5, asset.getLocation());
            ps.setString(6, asset.getStatus());
            ps.setInt(7, asset.getOwnerId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding asset: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateAsset(Asset asset) {
        String query = "UPDATE assets SET name=?, type=?, serial_number=?, purchase_date=?, location=?, status=?, owner_id=? WHERE asset_id=?";
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, asset.getName());
            ps.setString(2, asset.getType());
            ps.setString(3, asset.getSerialNumber());
            ps.setString(4, asset.getPurchaseDate());
            ps.setString(5, asset.getLocation());
            ps.setString(6, asset.getStatus());
            ps.setInt(7, asset.getOwnerId());
            ps.setInt(8, asset.getAssetId()); // <-- Correct method from Asset.java
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating asset: " + e.getMessage());
        }
        return false;
    }


    @Override
    public boolean deleteAsset(int assetId) {
        String query = "DELETE FROM assets WHERE asset_id=?";
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, assetId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting asset: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean allocateAsset(int assetId, int employeeId, String allocationDate) throws AssetNotFoundException {
        String query = "INSERT INTO asset_allocations (asset_id, employee_id, allocation_date) VALUES (?, ?, ?)";
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, assetId);
            ps.setInt(2, employeeId);
            ps.setString(3, allocationDate);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new AssetNotFoundException("Allocation failed: " + e.getMessage());
        }
    }

    @Override
    public boolean deallocateAsset(int assetId, int employeeId, String returnDate) throws AssetNotFoundException {
        String query = "UPDATE asset_allocations SET return_date=? WHERE asset_id=? AND employee_id=?";
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, returnDate);
            ps.setInt(2, assetId);
            ps.setInt(3, employeeId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new AssetNotFoundException("Deallocation failed: " + e.getMessage());
        }
    }

    @Override
    public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) throws AssetNotMaintainException {
        String query = "INSERT INTO maintenance_records (asset_id, maintenance_date, description, cost) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, assetId);
            ps.setString(2, maintenanceDate);
            ps.setString(3, description);
            ps.setDouble(4, cost);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new AssetNotMaintainException("Maintenance failed: " + e.getMessage());
        }
    }

    @Override
    public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) {
        String query = "INSERT INTO reservations (asset_id, employee_id, reservation_date, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, assetId);
            ps.setInt(2, employeeId);
            ps.setString(3, reservationDate);
            ps.setString(4, startDate);
            ps.setString(5, endDate);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error reserving asset: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean withdrawReservation(int reservationId) {
        String query = "DELETE FROM reservations WHERE reservation_id=?";
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, reservationId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error withdrawing reservation: " + e.getMessage());
        }
        return false;
    }
    
    @Override
    public boolean addAssetToMaintenance(int assetId) {
        String query = "UPDATE assets SET status='Maintenance' WHERE asset_id=?";
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, assetId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding asset to maintenance: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean reserveAsset(int assetId, int empId) {
        // Just a dummy basic reservation with current date
        String query = "INSERT INTO reservations (asset_id, employee_id, reservation_date) VALUES (?, ?, CURRENT_DATE)";
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, assetId);
            ps.setInt(2, empId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error reserving asset with simplified method: " + e.getMessage());
        }
        return false;
    }

}
