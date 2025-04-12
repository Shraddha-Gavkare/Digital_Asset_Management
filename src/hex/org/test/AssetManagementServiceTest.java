package hex.org.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hex.org.dao.AssetManagementServiceImpl;
import hex.org.entity.Asset;
import hex.org.exception.AssetNotFoundException;
import hex.org.exception.AssetNotMaintainException;

public class AssetManagementServiceTest {

    AssetManagementServiceImpl service = new AssetManagementServiceImpl();

    @Test
    void testAddAsset() {
        Asset asset = new Asset("Mouse", "Peripheral", "SN12350", "2023-10-01", "Office", "Available", 1);
        assertTrue(service.addAsset(asset));
    }

    @Test
    void testMaintenance() throws AssetNotMaintainException {
        assertTrue(service.performMaintenance(1, "2024-03-01", "Routine check", 150.0));
    }

    @Test
    void testReservation() {
        assertTrue(service.reserveAsset(1, 1, "2024-04-01", "2024-04-05", "2024-04-10"));
    }

    @Test
    void testAssetNotFoundException() {
        assertThrows(AssetNotFoundException.class, () -> {
            service.allocateAsset(999, 1, "2024-04-01");
        });
    }

    @Test
    void testAssetNotMaintainException() {
        assertThrows(AssetNotMaintainException.class, () -> {
            service.performMaintenance(999, "2024-04-01", "Invalid Asset", 100);
        });
    }
}
