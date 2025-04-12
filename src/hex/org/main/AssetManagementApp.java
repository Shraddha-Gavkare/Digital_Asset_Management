package hex.org.main;

import hex.org.dao.AssetManagementService;
import hex.org.dao.AssetManagementServiceImpl;
import hex.org.entity.Asset;
import hex.org.exception.AssetNotFoundException;
import hex.org.exception.AssetNotMaintainException;

import java.util.Scanner;

public class AssetManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AssetManagementService service = new AssetManagementServiceImpl();

        while (true) {
            System.out.println("\n===== DIGITAL ASSET MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Asset");
            System.out.println("2. Update Asset");
            System.out.println("3. Delete Asset");
            System.out.println("4. Allocate Asset");
            System.out.println("5. Deallocate Asset");
            System.out.println("6. Perform Maintenance");
            System.out.println("7. Reserve Asset");
            System.out.println("8. Withdraw Reservation");
            System.out.println("9. Exit");
            System.out.print("Choose an operation: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.println("Enter asset details:");
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Type: ");
                        String type = sc.nextLine();
                        System.out.print("Serial Number: ");
                        String serial = sc.nextLine();
                        System.out.print("Purchase Date (yyyy-mm-dd): ");
                        String date = sc.nextLine();
                        System.out.print("Location: ");
                        String loc = sc.nextLine();
                        System.out.print("Status: ");
                        String status = sc.nextLine();
                        System.out.print("Owner ID: ");
                        int owner = sc.nextInt();

                        Asset asset = new Asset(name, type, serial, date, loc, status, owner);
                        if (service.addAsset(asset))
                            System.out.println("Asset added successfully.");
                        else
                            System.out.println("Failed to add asset.");
                        break;

                    case 2:
                        System.out.print("Enter Asset ID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("New Name: ");
                        name = sc.nextLine();
                        System.out.print("New Type: ");
                        type = sc.nextLine();
                        System.out.print("New Serial Number: ");
                        serial = sc.nextLine();
                        System.out.print("New Purchase Date (yyyy-mm-dd): ");
                        date = sc.nextLine();
                        System.out.print("New Location: ");
                        loc = sc.nextLine();
                        System.out.print("New Status: ");
                        status = sc.nextLine();
                        System.out.print("New Owner ID: ");
                        owner = sc.nextInt();

                        asset = new Asset(updateId, name, type, serial, date, loc, status, owner);
                        if (service.updateAsset(asset))
                            System.out.println("Asset updated successfully.");
                        else
                            System.out.println("Failed to update asset.");
                        break;

                    case 3:
                        System.out.print("Enter Asset ID to delete: ");
                        int deleteId = sc.nextInt();
                        if (service.deleteAsset(deleteId))
                            System.out.println("Asset deleted successfully.");
                        else
                            System.out.println("Failed to delete asset.");
                        break;

                    case 4:
                        System.out.print("Enter Asset ID: ");
                        int assetId = sc.nextInt();
                        System.out.print("Enter Employee ID: ");
                        int empId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Allocation Date (yyyy-mm-dd): ");
                        String allocDate = sc.nextLine();
                        if (service.allocateAsset(assetId, empId, allocDate))
                            System.out.println("Asset allocated successfully.");
                        else
                            System.out.println("Failed to allocate asset.");
                        break;

                    case 5:
                        System.out.print("Enter Asset ID: ");
                        assetId = sc.nextInt();
                        System.out.print("Enter Employee ID: ");
                        empId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Return Date (yyyy-mm-dd): ");
                        String returnDate = sc.nextLine();
                        if (service.deallocateAsset(assetId, empId, returnDate))
                            System.out.println("Asset deallocated successfully.");
                        else
                            System.out.println("Failed to deallocate asset.");
                        break;

                    case 6:
                        System.out.print("Enter Asset ID: ");
                        assetId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Maintenance Date (yyyy-mm-dd): ");
                        String mDate = sc.nextLine();
                        System.out.print("Enter Description: ");
                        String desc = sc.nextLine();
                        System.out.print("Enter Cost: ");
                        double cost = sc.nextDouble();
                        if (service.performMaintenance(assetId, mDate, desc, cost))
                            System.out.println("Maintenance recorded.");
                        else
                            System.out.println("Failed to record maintenance.");
                        break;

                    case 7:
                        System.out.print("Enter Asset ID: ");
                        assetId = sc.nextInt();
                        System.out.print("Enter Employee ID: ");
                        empId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Reservation Date (yyyy-mm-dd): ");
                        String resDate = sc.nextLine();
                        System.out.print("Enter Start Date (yyyy-mm-dd): ");
                        String start = sc.nextLine();
                        System.out.print("Enter End Date (yyyy-mm-dd): ");
                        String end = sc.nextLine();
                        if (service.reserveAsset(assetId, empId, resDate, start, end))
                            System.out.println("Reservation successful.");
                        else
                            System.out.println("Failed to reserve asset.");
                        break;

                    case 8:
                        System.out.print("Enter Reservation ID: ");
                        int resId = sc.nextInt();
                        if (service.withdrawReservation(resId))
                            System.out.println("Reservation withdrawn.");
                        else
                            System.out.println("Failed to withdraw reservation.");
                        break;

                    case 9:
                        System.out.println("Exiting... Thank you!");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (AssetNotFoundException | AssetNotMaintainException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }
        }
    }
}
