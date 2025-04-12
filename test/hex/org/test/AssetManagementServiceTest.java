package hex.org.test;

import hex.org.dao.AssetManagementService;
import hex.org.dao.AssetManagementServiceImpl;
import hex.org.entity.Asset;
import hex.org.exception.AssetNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssetManagementServiceTest {

    private AssetManagementService assetService;

    @BeforeEach
    public void setUp() {
        assetService = new AssetManagementServiceImpl();
    }

    @Test
    public void testAddAssetSuccess() {
        Asset asset = new Asset("Dell Laptop", "Electronics", "1234", "2024-05-10", "Pune", "Available", 101);
        boolean result = assetService.addAsset(asset);
        assertTrue(result, "Asset should be added successfully");
    }


    @Test
    public void testAddAssetToMaintenanceSuccess() {
        boolean result = assetService.addAssetToMaintenance(6); // Use an existing asset ID
        assertTrue(result, "Asset should be added to maintenance successfully");
    }

    @Test
    public void testReserveAssetSuccess() {
        boolean result = assetService.reserveAsset(6, 201); // Use valid asset ID and employee ID
        assertTrue(result, "Asset should be reserved successfully");
    }
    
    @Test
    public void testAllocateAssetThrowsExceptionForInvalidIds() {
        assertThrows(AssetNotFoundException.class, () -> {
            assetService.allocateAsset(9999, 8888, "2025-04-11"); // Invalid asset/employee
        });
    }

    
}
